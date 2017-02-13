# The Linux Command Line

## I/O redirection

* __`cat`__: concatenate files: reads 1/+ files & copies them to std output

  * we can of course redirect the output to a file or something:

    `cat *.scss > style.scss`

    * without arguments, it copies from std input to the std output

      * we could keep std input as the keyboard, but write everything we're typing into the console to a file, then use `Ctrl-D` to stop writing to that file

      ​ `cat > output.txt`

  * redirect std input: `cat < input.txt`

* __`sort`__: sort lines of text

* __`uniq`__: report/omit repeated lines; used on _sorted_ lists of data

  * by default, removes duplicates from the list: `ls /bin /usr/bin | sort | uniq | less`
  * alternatively, see the duplicates with `-d`: `ls /bin /usr/bin | sort | uniq -d | less`

* __`grep`__: print lines within files that match a RegEx pattern

  * `-i` ignore case, `-v` print lines that don't match
  * eg. `grep zip` returns all lines w/ word 'zip'

* __`wc`__: print newline, word, & byte counts for each file

  * `-l` only report lines

* __`head`__: output the first part of a file

  * by default, last 10 files. Can be changed w/ `-n`, like:

    `head -n 5 text.txt`

* __`tail`__: output the last part of a file

  * `-f` lets you view last `n` files in real time (good for logs)

* __`tee`__: read from standard input & write to standard output and files

  * reads stdin & copies it to both stdout & another file (so can capture things in the intermediate stages)

  eg. `ls /usr/bin | tee ls.txt | grep zip`

### Standard input, output, & error

* default: stdout is screen & stdin is the keyboard, but it can be changed
* __`>`__ redirection operator
  * overwrites the file from the beginning
  * usage: `ls -l /usr/bin > ls-output.txt`
* `>>` redirection operator, which appends
  * appends to the file
  * usage: `ls -l /usr/bin >> ls-output.txt`
* file descriptors: `0>` std input, `1>` std output, `2>` std error
  * `2>` redirecting std error: `ls -l /bin/usr 2> ls-error.txt`
  * redirect multiple: `2>&1`, alternatively: `&>`
* suppress error messages from a command: `[command] 2> /dev/null`
  * sending things to __`/dev/null`__ is like sending it to the abyss

### Pipelines

* __pipelines__: commands' ability to read data from stdin & send to stdout

* __`|`__ pipeline operator: allows us to pipe the stdout of one command into the stdin of another

  `[command 1] | [command2]`

  eg. `ls -l /usr/bin | less` to view the full contents of all files returned by `ls`

##### Filters

* can use pipelines to filter

  eg. `ls /usr/bin /usr/documents | sort | less`

## The world as the shell sees it

* `echo` displays a line of text

* __expansion__: certain characters sequences mean more to the shell than face value; the shell expands it to something else before executing
  - eg. `echo *` will first expand `*`, replacing `*` with all the filenames in the directory, and then echo that

* __tilde expansion__: expands to the home directory of the named user (if it exists), or if no user was named, the current user
  - eg. `echo ~`, `echo ~mark` (no user named mark, so it's literal)

* __arithmetic expansion__'s form: `$((expression))`
  - only supports integers, & operators [`+`, `-`, `*`, `/`, `%`, `**`]

* __brace expansion__: create multiple text strings from a pattern containing braces
  - you can also have specify a range with `..` (eg. `{Z..A})
  - eg. `echo Front-{A,B,C}-Back` --> `Front-A-Back Front-B-Back Front-C-Back`
  - eg. `echo Number_{1..5}` --> `Number_1 Number_2 Number_3 Number_4 Number_5`

* __parameter expansion__: `$[variable name here]`
  - see a list of available variables with `printenv`

* __command substitution__: use `$([command here])`, which allows you to use the output of the command as an expansion
  - eg. `echo $(ls)`

* paramter expansion, arithmetic expansion, and command substitution still take place w/i double quotes
  - suppress all expansions with single quotes
