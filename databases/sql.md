# SQL

* Execute a command from a file (in psql)

```psql
\i [filename here]
```

* Write the results of a query to a file

```sql
COPY ([query here]) TO '[absolute file path here]' WITH CSV DELIMITER ',';
```
