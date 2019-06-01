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
