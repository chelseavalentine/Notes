# The Linux Command Line

## Regular expressions

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

## Text processing

* `cat` concatenate files & print on the standard input
  - `-n` numbers lines
  - `-s` suppresses output of multiple blank lines

* `uniq` report or omit repeated lines
  - `-c` shows number of duplicates

* `cut` remove sections from each line of files

* `paste` merge lines of files

* `join` join lines of 2 files on a common field

* `comm` compare 2 sorted files line by line

* `diff` compare files line by line

* `patch` apply a diff file to an original

* `tr` translate or delete characters
  - character-based search&replace operation (eg. `echo "lowercase" | tr a-z A-Z` => `LOWERCASE`)

* `sed` stream editor for filtering & transforming text

* `aspell` interactive spell checker
  - `aspell check [text file]`

## Formatting output

* `nl [file]` number the lines

* `fold` wrap each line to a specified length (80 if no with specified)
  - `fold -w [width] -s` fold lines @ _width_ & break at the last available space

* `fmt` a simple text formatter for paragraph formatting and more

* `pr` prepare text for printing by paginating

* `printf` formats and prints data
  - `print "[format]" [arguments]`

* `groff` a document formatting system

## Printing

* `pr` converts text files for printing

* `lpr` prints files

* `a2ps` formats files for printing on a PostScript printer

* `lpstat` shows printer status information

* `lpq` shows printer queue status

* `lprm` cancels print jobs

## Compiling programs

* __compiling__: translating source code into the computer processor's native language
  - interpreted programs are slower b/c they're translated every time it's executed; whereas compiled programs are only translated once and then the translation is recorded

* `make` - a utility to maintain programs
