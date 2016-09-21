# The Linux Command Line

## Ch 25 - Writing your first shell script

* first line of every shell script: `#!/bin/bash`
  - tells system the name of the interpreter to execute the script; called a "shebang"

* when writing scripts, use the longer form of the option names for readability

## Ch 27 - Top-down design

*  for function calls to be recognized as shell functions and not external programs, function definitions must appear before they are called

* local variables are declared with `local`, eg. `local foo=1`

## Ch 28 - Flow control: branching with `if`

* `if`

```shell
if [commands]; then
  [commands]
[[elif [commands]; then
  [commands]]]
[[else
  [commands]]]
fi
```

* `test` has two forms: `test [expression]` or `[[expression]]`

* exit status: indicator of success/failure of a command; 0 = success, others = fail; 0-255
  - `echo $?` after executing a command to check its exit status

* test file expressions
  - `-e [file]` file exists
  - ... there are way more

* test String expressions
  - `[string]` string isn't null
  - `-n [string]` length of string is greater than 0
  - `-z [string]` length of string is 0
  - `[string1] = [string2]`; `[string1] == [string2]` strings are equal
  - `[string1] != [string2]` strings aren't equal
  - `[string1] > [string2]` string1 sorts after string2
  - `[string1] < [string2]` string 1 sorts before string2

* `>` & `<` operators must be quoted or escaped w/ `\` when used w/ test, otherwise they're considered redirection operators

* test Integer epressions
  - `[int1] -eq [int2]` equality
  - `[int1] -ne [int2]` not equal
  - `[int1] -le [int2]` less than or equal
  - `[int1] -lt [int2]` less than
  - `[int1] -ge [int2]` greater than or equal
  - `[int1] -gt [int2]` greater than

* `(( ))` performs arithmetic truth tests (designed for integers)

* logical operators
  - and: w/ test: `-a`; w/ [[ ]] & (( )): `&&`
  - or: w/ test: `-o`; w/ [[ ]] & (( )): `||`
  - not: `!`

## Ch 29 - Reading keyboard input

* `read` read values from standard input
  - `read [[-options]] [[variable...]]`
    + variable: the name of 1+ variables used to hold the input value
  - if no variable is specified, `$REPLY` contains the line of data
  - if it receives more than the expected variables, it puts all the extra input in the final variable

* `read` options:
  - `-a [array]` assign the input to _array_ starting w/ index 0
  - `-d [delimiter]` indicator of end of input, rather than newline char
  - `-e` use Readline to handle input
  - `-n [num]` read _num_ chars of input, rather than entire line
  - `-p [prompt]` display prompt for input using the string _prompt_
  - `-r` raw mode; don't interpret backslash chars are escapes
  - `-s` silent mode; don't display chars as they're typed (good for passwords)
  - `-t [seconds]` timeout; terminate input after _seconds_
    + non-zero exit is returned by `read` if times out
  - `-u [fd]` use input from file descriptor _fd_ rather than standard input

## Ch 30 - Flow control: looping with `while` / `until`

* __`while`__: `while [commands]; do [commands]; done
  - `break` & `continue`

```bash
count=1

while [ $count -le 5 ]; do
  echo $count
  count=$((count + 1))
done
```

* __`until`__:

```bash
count=1

until [ $count -gt 5 ]; do
  echo $count
  count=$((count + 1))
done
```
