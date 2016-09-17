# The Linux Command Line

## Ch 20 - Regular expressions

* `grep` "global regular expression print": searches text files for occurrence & prints matching lines
  - `grep [[options]] [regex] [[file...]]
  - options:
    + `-i` ignore case
    + `-v` nonmatching lines
    + `-c` number of matches
    + `-l` name of file w/ matches, not the lines themselves
    + `-L` print only names of nonmatching files
    + `-n` prefix each line w/ # of line w/i the file
    + `-h` suppress the output of filenames (For multi-file searches)

* regex metacharacters: `^ $ . [ ] { } - ? * + ( ) | \`

* `.` the any character

* `^` & `$` are treated as anchors, thus the match only occurs if the match is found @ the beginning of the line (`^`) or at the end of the line (`$`)

* bracket expressions make it so that the match needs to include one or more of the characters
  - within a bracket expression, `^` indicates negation & `-` indicates range
  - you can have multiple ranges, eg. `grep -h '^[A-Za-z0-9]' file.txt`

* character classes
  - `[:alnum:]` alphanumeric characters (A-Z, a-z, 0-9)
  - `[:word:]` same as alnum, w/ inclusion of the `_` character
  - `[:alpha:]` A-Z a-z
  - `[:blank:]` space & blank characters
  - `[:cntrl:]` ASCII control codes
  - `[:digit:]` 0-9
  - `[:graph:]` visible characters
  - `[:lower:]`
  - `[:punct:]`
  - `[:print:]` printable charactwea, includes `:graph:` & space char
  - `[:space:]` all spaces
  - `[:upper:]`
  - `[:xdigit:]` characters for hexadecimal numbers

* `|` functions as or here, too

* quantifiers
  - `?` match an element zero or one time
  - `*` match an element zero or more times
  - `+` match an element one or more times
  - `{}` match an element a specific number of times
    + `{n}` match only if it occurs n times
    + `{n,m}` match if occurs n-m times
    + `{n,} match if occurs n or more times
    + `{,m} match if occurs up to m times

## Ch 21 - Text processing (282)

* `cat` concatenate files & print on the standard input

* `sort` sort lines of text files

* `uniq` report or omit repeated lines

* `cut` remove sections from each line of files

* `paste` merge lines of files

* `join` join lines of 2 files on a common field

* `comm` compare 2 sorted files line by line

* `diff` compare files line by line

* `patch` apply a diff file to an original

* `tr` translate or delete characters

* `sed` stream editor for filtering & transforming text

* `aspell` interactive spell checker

## Ch 22 - Formatting output (323)

## Ch 23 - Printing (344)

## Ch 24 - Compiling programs (358)

## Ch 25 - Writing your first shell script (372)

## Ch 26 - Starting a project (379)

## Ch 27 - Top-down design (390)

## Ch 28 - Flow control: branching with `if` (399)

## Ch 29 - Reading keyboard input (415)

## Ch 30 - Flow control: looping with `while` / `until` (426)

## Ch 31 - Troubleshooting (433)

## Ch 32 - Flow control: branching with `case` (446)

## Ch 33 - Positional parameters (451)

## Ch 34 - Flow control: looping with `for` (465)

## Ch 35 - Strings and numbers (471)

## Ch 36 - Arrays (490)

## Ch 37 - Exotica (499)

(510)
