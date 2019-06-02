# Hadoop: The Definitive Guide: Storage and Analysis at Internet Scale

Notes taken while reading _Hadoop: The Definitive Guide: Storage and Analysis at Internet Scale_.

## Ch 1: Meet Hadoop

* stream processing systems (e.g. Samza) allow you to run real-time distributed computations on unbounded streams of data and emit results to HDFS or other systems

### What is Hadoop good for?

* Hadoop is necessary b/c disk seek time improves slower than transfer rate, so it's impractical to just have a database with tons of disks instead of a system like Hadoop
  - good for batch, offline jobs
  - Hadoop systems (e.g. Hive) are becoming more interactive, by adding RDBMS (Relational Database Management System) features, like indexing and transactions

* Hadoop is good @ processing unstructured data (e.g. plain text, image data), because it interprets data at processing time (__"schema on read"__)
  - cheaper to do this w/ Hadoop b/c just need to copy the file, instead of having to load in all of the data if you were using an RDBMS

* Hadoop tries to co-locate data w/ the compute nodes to have faster processing via data locality

## Ch 2: MapReduce

* Hadoop can run MapReduce programs written in various languages
  - jobs are packaged into a JAR file (which is then distributed around the cluster)

* general process of a MapReduce job's execution:
  - you submit a MapReduce program, input data, and configurations
    + input data is divided into fixed-sized pieces ("input splits" / "splits"), and the map function is run on each record within a split
      - it's important for the split size to be the same size as the block size (HDFS block replicas), because it's unlikely for any HDFS node to store multiple blocks, and thus larger sizes require some of the split to be transferred across network 
  - Hadoop runs the job and divides it into map and reduce tasks
  - tasks are scheduled using YARN, and then run on nodes in the cluster
    + upon failure, tasks are rescheduled to run on a different node
  - each map task's result is stored to local disk, and then processed by the reduce tasks (the output of the reduce task is stored in HDFS)
    + sorted map outputs are transferred across network
    + you can specify a combiner function to be run on map output, so the combined result is the data that's sent to the reduce jobs instead of the raw map output
      - but the combine function needs to have no side-effects, because it can be called an arbitrary amount of times

## Ch 3: The Hadoop Distributed FileSystem (HDFS)

* HDFS is built for the **write-once, ready-many-times** pattern
  * datasets are typically copied from source, and then processing it often uses a large potion of, or all of, the dataset
* **what Hadoop _isn't_ good for:**
  * low latency data access
  * lots of small files
    * because the namenode holds filesystem metadat a in  memory and there's a file limit on the namenode
  * multiple writers with arbitrary file modifications
    * because there's single-writer, append-to-end writing

#### HDFS concepts

* blocks
  * on regular filesystems, blocks have small sizes (e.g. 512 bytes), but HDFS blocks are larger (128mb)
    * files occupy multiple blocks in their entirety on regular filesystems, whereas files are smaller than the block in HDFS and will take up their actual size instead of the entire block
    * the blocks are larger to minimize expensive disk seeks, because we generally want seek time to be 1% of the total transfer time
      * however, each task operates on 1 block at a time
* namenodes and datanodes
  * namenode (the master) and datanodes (the workers)
    * the **namenode** manages the filesystem's namespace by maintaining the file tree and metadata for files. It's stored on local disk (The namespace image and edit log)
      * knows the datanodes and where a file's blocks are, because that info is reconstructed from datanodes when the system starts
    * **datanodes** periodically report back to the master with the list of blocks they store
  * namenodes are critical to HDFS' usability: namenodes usually backup its persistent state by also writingi t to a remote NFS mount
  * secondary namenodes can be used to periodically merge the namespace image with the edit log to keep the edit log small
    * uses a lot of memory to perform the merge
    * can be used to recover a failed namenode if you copy the namenode's metadata files onto NFS
* **blockcaching:** a datanode caches a block, getting you a performance boost if there're multiple jobs
* **HDFS federation:** where each namenode manages a part of the filesystem's namespace
  * each namenode has a namespace volume (all metadata) to manage, and a block pule (blocks for files in the namespace)
* HDFS high availability (HA)
  * a new namenode needs to (1) load its namespace image into memory, (2) replace its edit log, and (3) receive enough block reports from the datanodes, in order to leave safe mode and serve requests
    * can take ~30 mins+!!!
  * HA: another namenode is on standby, configured as if it was the primary namenode
    * how?
      * highly available storage holds the edit log
      * datanodes send block data to both namenodes
  * allows standby namenode to take over in tens of seconds upon a primary's failure, but the system needs to conservatively detect failure (so it takes approximately 1 min)
* failover and fencing
  * failover controller handles transmission from the active namenode to the standby
    * e.g. Zookeeper ensures only 1 namenode is active
    * e.g. using a heartbeat system to figure out if there're failuers
  * graceful failover: failover is initiatived and orchestrated by an administrator

#### Client reading data from HDFS

1. asks HDFS to open the file
2. block locations are obtained from the namenode
3. read from FSDataInputStream
4. that reads from multiple datanodes
5. close input stream

#### Client writing data to HDFS

1. ask HDFS to create a file
2. asks namenode to create new file in the namespace
3. write data to FSDataOutputStreams, which writes packets to datanodes
4. replicate packets across other datanodes
5. close output stream
6. signal file creation is complete to the namenode

## Ch 4: YARN (Yet Another Resource Negotiator)

* **YARN** is Hadoop's cluster management system w/ 2 long-running daemons
  * one resource manager/cluster manages resources for the cluster
  * node managers on all nodes launch and monitor its containers
* allows an app to specify locality constraints for requested containers
* apps can make resource requests dynamically as well

#### Yarn vs. MapReduce I

* **MapReduce I** uses two daemons to control job execution: (1) a job tracker, and (2) one or more task trackers
  * **job tracker** coordinates jobs by scheduling tasks to run on task trackers
  * **task trackers** run tasks and send progress reports to the job tracker
* with YARN, there's a resource manager and application master (per MapReduce job) to job schedule and monitor progress

#### Benefits to using YARN

* scalability: since it splits its resource manager and application master responsibilities, it can scale to 10k nodes and 100k tasks
* availability: a divide-and-conquer problem for YARN since it splits responsibilities
* utilization: resources aren't segregated (e.g. only allocated for reduce jobs, or only for map jobs), thus YARN can better give jobs the resources to run the task upon availability
* multitenancy: YARN has enough functionality to make MapReduce only one of the many YARN apps you could have

#### Scheduling in YARN

* there could be resource limitations, so sometimes you need to wait
  * scheduling methods: FIFO, Capacity, Fair Scheduler
* shared clusters mean that larger apps will use all cluster resources, therefore capacity or fair scheduling is better
  * capacity scheduling gives freed up resources to smaller jobs and lets that run, returning the resources to the bigger job afterward
    * supports preemption to enforce fairness

## Ch 5: Hadoop I/O

#### Data integrity

* detect corrupted data via computing a checksum for data when it enters the system and each time it's transmitted across a channel that's prone to/capable of corrupting data
* datanodes verify all data received before storing it, and its checksum

#### Serialization

* **serialization:** turning structured objects into a bytestream for transmission over a network or for writing to persistent storage
  * **deserialization:** converting from a bytestream to a structured object
* Hadoop's interprocess communication between nodes is done via RPCs (Remote Procedure Calls), which serializes and deserializes messages
* there're some serialization frameworks that use IDLs (Interface Description Languages) instead of code, for interoperability
  * e.g. Google's Protocol buffers, Apache Thrift, and Avro are all IDL-based

## Ch 6: Developing a MapReduce application

#### Tuning a job checklist

* number of mappers (mappers should run for ~1 min, as a rule of thumb)
* number of reducers (should run ~5 min and product at least a block of data)
* combiners (should you add one to reduce the amount of data to shuffle?)
* intermediate compression (enable it?)
* custom serialization (if using custom writeable objects)
* shuffle tweaks

#### MapReduce workflows

* Apache Oozie allows you to run workflows of dependent jobs
  * consists of a workflow engine and a coordinator engine

## Ch 7: How MapReduce works

1. job submission
2. job initialization (allocate a container, task run order/division/location)
3. task assignment
4. task execution
5. streaming; progress and status updates
6. job completion

## Ch 8: MapReduce types and formats

* map and reduce functions are structured like:
  * `map(k1, v1) -> list(k2, v2)`
  * `reduce(k2, list(v2)) -> list(k3, v3)`

## Ch 12: Avro

* **Avro** is a language-neutral data serialization system
  * it has rich schema resolution capabilities, therefore the schema used to read the data does not need to be identical to the schema used to write it
    * this makes schema evolution very possible!

## Ch 13: Parquet

* **Parquet** is a columnar storage format that can efficiently store nested data
  * columnar formats provide greater efficiency in file size and query performance

## Ch 14: Flume

* **Flume** is a system designed for high-volume ingestion into Hadoop of event-based data
  * e.g. using flume to collect log files from web servers, and then moving log events into new aggregated files in HDFS for processing
  * can fan-out events (e.g. delivering 1 event to multiple channels)
  * has delivery guarantees by wrapping events in transactions, which aren't committed until they're delivered successfully

## Ch 15: Sqoop

* **Sqoop** is an open-source tool allowing users to extract data from HDFS for further processing (doable via MapReduce programs, or high-level programs like Hive)
  * can even use Sqoop to move data from a database into HBase
* in the context of Sqoop, an import is the movement of data from a DB system to HDFS, and an export is the movement of data from HDFS to an external DB

## Ch 16: Pig

* **Pig** raises the level of abstraction for processing large datasets

  * it has kmultivalued and nested data structures with more powerful transformations when compared to the typical MapReduce job

    additional benefit: you can test your script on a small subset of the data

## Ch 17: Hive

* **Hive** is a framework for data warehousing on top of Hadoop; it's used to manage and learn from the huge amount of data in HDFS
* unlike a traditional database, Hive enforces schema on reads, whic hmakes for a fast initial load since you can just bulk move the data without validating, and allows for multiple schemas for underlying data

## Ch 18: Crunch

* **Crunch** is a higher-level API for writing MapReduce pipelines, and focuses on Java types and POTOs, with rich data transformation operations and multistage pipelines

## Ch 19: Spark

* **Spark** is a cluster computing framework for large-scale data processing
  * is excellent because it keeps large working datasets in memory between jobs, therefore you can skip reloading data from disk after every job in some cases
* **Mesos** is a general-purpose cluster resource manager, allowing for fine-grained resource sharing across different apps according to an organizational policy

## Ch 20: HBase

* **HBase** is a distributed column-oriented database built on top of HDFS
  * use it when you want/require real-time read and write random access to super large datasets

## Ch 21: ZooKeeper

* **ZooKeeper** is Hadoop's distributed coordination service. It's highly available and allows you to handle partial failures