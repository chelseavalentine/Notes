# Database Design & Web Implementations course

Notes from taking NYU's Database Design & Web Implementations course.

### MySQL

* **primary key:** one field in every table must be designated this
* **foreign key:** ensures data consistency
* **surrogate keys:** are any column or set of columns that can be declared as the primary key instead of a natural key
  * sometimes there're several natural keys declarable as the primary key, and each one of them are thus candidate keys
  * surrogate keys are artificially-generated values whose sole purpose is to be used as a primary key
* **temporary tables:** tables only visible to the current session, and are automatically dropped at the end of the session
* **aliases:** temporary names for columns or tables
  * useful to make more readable column names, or for table aliases, to shorten your SQL or when performing a self join (listing the same table more than 1 time)
* **inner joins:** you can join tables as a common row and get other fields too
* **outer joins** don't enforce referential integrity since it may return results where there're matching results within one table, but not in the other related table(s)
* **left join:** allow you to see all results in the source table veen if nothing matches. It shows NULL in the related table if no match, but that doesn't signify a problem with the data.
* **right join:** brings up records, regardless of match, but signifies serious problems with the data (i.e. if there's an A without a matching B, there's a data integrity issue)
* **full outer joins:** a union of a left and right join on 2 tables
* **reflexive joins:** do a join within a table. Only works if there's only 1 relationship

### Blockchain

* a decentralized database technology
* controlled by everyone; immutable
* needs to maintain order & consistency in its records
* each node in the blockchain contains a **ledger** (a complete set of all records)
* records = blocks
* records are validated for authenticiity & depend on the authenticity of those before it
  * if a record is found invalid, then so are all the records after it
  * conflicts in ledgers are resolved amongst peers
* a block puzzle (cryptographic nonce) needs to be solved in order to create a block
  * is time-consuming to make it unlikely that 2 blocks publish at the same time
  * solving the puzzle = "mining"; adjusts difficulty so that the puzzle is solved approximately once every 10 mins

### APIs

* **OAuth** allows a third-party to access a user's account without giving up user credentials
  * done via an app having a consumer token & secret, and acquiring users OAuth token and secret

### Database normalization

* normalization rules are designed to prevent update anomalies and data inconsistencies
  * assumes all non-key fields will get frequent updates, therefore it penalizes retrieval

#### First Normal Form

* a database that only contains atomic values and has no repeating groups
  * shouldn't have columns w/ multiple values; needs a primary key

#### Second Normal Form

* shouldn't include fields about another field's details (e.g. shouldn't have a warehouse and warehouse_address in a table about inventory; should put warehouse details in a separate table)
  * because if you update the warehouse_address, you need to update it everywhere
* is in first normal form
* all non-key  attributes are fully functionally dependent on the primary key

#### Third Normal Form

* is violated when a non-key field is about another non-key field

#### Concepts

* **functional dependencies**: field A is functionally dependent on a field (or fields) X if it's invalid to have 2 records with the same X-value but different A values.
  * only exists when things are unique and have single identifiers

