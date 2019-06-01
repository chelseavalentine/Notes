# The Linux Command Line

## What is the Shell?

- __bash__: Bourne Again SHell

##### Just so you know

- `df`: Disk Free space
- `free`: free memory

Miscellaneous

`date`, `cal`, `exit`

## Navigation

- __`pwd`__: Print Working Directory
- __`cd`__: Change Directory
  - `cd -` change to previous working directory
- __`ls`__: LiSt directory contents
  - `ls [path]`: list contents without having to go to it
  - `-lh`: long format & human-readable

## Exploring the system

- __`file [filename]`__: brief description of file's contents
- __`less [filename]`__: examine text file
  - Commands: `q` quit, `b` page back, `[space]` page forward, `g` file start, `G` file end, `/[characters]` find, `n` find next
- directories: /dev, /etc, /opt, /tmp
- __symbolic link__ (symlink, soft link): a reference to a file by another name
  - lets you refer to a file by many names

## Manipulating files and directories

- __`cp`__: CoPy files & directories
  - `cp -u [pattern] [destination]` - copy files that don't exist in the destination over
  - `cp item… directory` copy multiple items (files/directories) into a directory
  - `cp [i1] [i2]`:  copy item 1 to item2
  - `cp -r [dir1] [dir2]`: copy dir1 contents into dir2 & create dir2 if it doesn't exist already
  - _options_ – `-u` update (add diff), `-i` ask before changes, `-v` explain what's happening
- __`mv`__: MoVe/rename files & directories
- __`mkdir`__: create directories
- __`rm`__: ReMove files & directories
  - `rm [items…]`
  - options: `-r` recursively (needed for dirs), `-f` force, `-i` interactive (confirm delete)
- __`ln`__: create hard & symbolic LiNks
  - __hard link__: `ln [file] [link]`
    - creates an additional file–but has some limitations:
      - can't be made for a directory
      - can't be made on a file outside its file system
    - no indication that something's a hard link;
    - deleting these links doesn't deallocate space until all hard links deleted
  - __symbolic link__: `ln -s [item] [link]`
    - text pointer to a referenced file/directory
    - deleting a symlink means deleting only the link, not also the file
      - but deleting the file & not the link will keep the link… broken link! :( (in red)

### Wildcards

- `*`: match any character
- `?`: match any single character
- `[characters~]`: match any character in this set
- `[!characters~]`: match any character not in this set
- `[[:class:]]`: match any character not in the class
  - _classes_ – [:alnum:] alphanumeric, [:alpha:] alphabetic, [:digit:], [:lower:], [:upper:]

## Working with commands

- __`type`__: how a command name is interpreted
  - executable program, shell builtin, shell function, alias
- __`which`__: where a executable program is located
- __`man`__: a command's manual page
  - __`help`__ for shell builtins, or `[cmd] --help`
  - `man [section] [search_term]` where:
    1. User commands
    2. Programming interfaces kernel system calls
    3. Programming interfaces to the C library
    4. Special files (device nodes & drivers, etc)
    5. File formats
    6. Games & amusements (eg. screen savers)
    7. Miscellaneous
    8. System admin commands
- __`apropos`__: list appropriate commands (gives you possible matches for a search term)
- __`info`__: a command's info entry
  - have hyperlinks to go from topic (node) to topic (node), indicated by * & activated by putting the cursor on it & pressing enter
  - `n` next node, `p` prev node, `?` cmd help, `[delete]` prev page, `[space]` next page
- __`whatis`__: a brief description of a command
- __`alias`__: give commands nicknames using aliases `alias ls="ls -Gf"`
  - `alias name='string'`
  - __`unalias`__
- `[-op1|-op2]` indicate that op1 & op2 are mutually exclusive

## I/O redirection

- __`cat`__: concatenate files: reads 1/+ files & copies them to std output

  - we can of course redirect the output to a file or something:

    `cat *.scss > style.scss`

    - without arguments, it copies from std input to the std output

      - we could keep std input as the keyboard, but write everything we're typing into the console to a file, then use `Ctrl-D` to stop writing to that file

       `cat > output.txt`

  - redirect std input: `cat < input.txt`

- __`sort`__: sort lines of text

- __`uniq`__: report/omit repeated lines; used on _sorted_ lists of data

  - by default, removes duplicates from the list: `ls /bin /usr/bin | sort | uniq | less`
  - alternatively, see the duplicates with `-d`: `ls /bin /usr/bin | sort | uniq -d | less`

- __`grep`__: print lines within files that match a RegEx pattern

  - `-i` ignore case, `-v` print lines that don't match
  - eg. `grep zip` returns all lines w/ word 'zip'

- __`wc`__: print newline, word, & byte counts for each file

  - `-l` only report lines

- __`head`__: output the first part of a file

  - by default, last 10 files. Can be changed w/ `-n`, like:

    `head -n 5 text.txt`

- __`tail`__: output the last part of a file

  - `-f` lets you view last `n` files in real time (good for logs)

- __`tee`__: read from standard input & write to standard output and files

  - reads stdin & copies it to both stdout & another file (so can capture things in the intermediate stages)

  eg. `ls /usr/bin | tee ls.txt | grep zip`

### Standard input, output, & error

- default: stdout is screen & stdin is the keyboard, but it can be changed
- __`>`__ redirection operator
  - overwrites the file from the beginning
  - usage: `ls -l /usr/bin > ls-output.txt`
- `>>` redirection operator, which appends
  - appends to the file
  - usage: `ls -l /usr/bin >> ls-output.txt`
- file descriptors: `0>` std input, `1>` std output, `2>` std error
  - `2>` redirecting std error: `ls -l /bin/usr 2> ls-error.txt`
  - redirect multiple: `2>&1`, alternatively: `&>`
- suppress error messages from a command: `[command] 2> /dev/null`
  - sending things to __`/dev/null`__ is like sending it to the abyss

### Pipelines

- __pipelines__: commands' ability to read data from stdin & send to stdout

- __`|`__ pipeline operator: allows us to pipe the stdout of one command into the stdin of another

  `[command 1] | [command2]`

  eg. `ls -l /usr/bin | less` to view the full contents of all files returned by `ls`

##### Filters

- can use pipelines to filter

  eg. `ls /usr/bin /usr/documents | sort | less`

## The world as the shell sees it

- `echo` displays a line of text
- __expansion__: certain characters sequences mean more to the shell than face value; the shell expands it to something else before executing
  - eg. `echo *` will first expand `*`, replacing `*` with all the filenames in the directory, and then echo that
- __tilde expansion__: expands to the home directory of the named user (if it exists), or if no user was named, the current user
  - eg. `echo ~`, `echo ~mark` (no user named mark, so it's literal)
- __arithmetic expansion__'s form: `$((expression))`
  - only supports integers, & operators [`+`, `-`, `*`, `/`, `%`, `**`]
- __brace expansion__: create multiple text strings from a pattern containing braces
  - you can also have specify a range with `..` (eg. `{Z..A})
  - eg. `echo Front-{A,B,C}-Back` --> `Front-A-Back Front-B-Back Front-C-Back`
  - eg. `echo Number_{1..5}` --> `Number_1 Number_2 Number_3 Number_4 Number_5`
- __parameter expansion__: `$[variable name here]`
  - see a list of available variables with `printenv`
- __command substitution__: use `$([command here])`, which allows you to use the output of the command as an expansion
  - eg. `echo $(ls)`
- paramter expansion, arithmetic expansion, and command substitution still take place w/i double quotes
  - suppress all expansions with single quotes

## Advanced keyboard tricks

- `clear`: clears the screen
- `history`: displays the history of commands

#### Cursor movements

- `ctrl-a`: beginning of line
- `ctrl-e`: end of line
- `alt-f`: forward one word (`alt` = `ctrl` + `option`)
- `alt-b`: back one word (`alt` = `ctrl` + `option`)

#### Cutting and pasting

- `ctrl-k`: deletes (enter the kill-ring) from cursor to end of line
- `ctrl-u`: deletes (enter the kill-ring) from cursor to beginning of line
- `ctrl-y`: yanks text from the kill-ring & inserts @ cursor location

#### Completion

- __history expansion__: `!!` = last command, `![# here]` = command at line [# here] in history
  - `!string`: repeat last history list itme starting with `string`
  - `!?string`: repeat last history list itme containing `string`
- `ctrl-r` search history
  - then use `ctrl-j` to copy the line from history to the current command line
  - `ctrl-o` execute current item in history list, & advance to the next one

## Permissions

- `ssh` secure shell
- `id`: display a user's identity
- file types (appear before the permission list w/ attributes)
  - `-` regular file
  - `d` a directory
  - `l` a symbolic link
  - `c` a character special file (a device handling data as a stream of bytes, eg. terminal or modem)
  - `b` a block special file (device handles data in blocks, eg. hard drive or CD-ROM drive)
- permission attributes
  - `r` allows file to be open & read
  - `w` allows a file to be written to/truncated; not renamed or deleted
    - directory attributes determine whether you can rename or delete a file
  - `x` allows a file to be treated as a program & executed
- `chmod`: change a file's mode
  - its arguments take the form of octal numbers, representing what the permissions will be set to
    - 0: ---
    - 1: --x
    - 2: -w-
    - 3: -wx
    - 4: r--
    - 5: r-x
    - 6: rw-
    - 7:  rwx
  - eg. `chmod 600 foo.txt` sets its file modes to rw- --- ---
- `chmod` also has symbolic notation
  - `u` file or directory owner
  - `g` group owner
  - `o` world
  - `a` all
  - allows you to specify permissions for each group individually (eg. `u+x` add execute permission for the user; `u-x` removes execute permission; `+x` add execute permission for all)
- `umask`: set the default file permissions
- `su`: run a shell as another user
- `sudo`: execute a command as another user
- `chown`: change a file's owner &/ group (`chown [owner][:[group]] file ...`)
  - `bob:users` changes ownership to `bob` & file group owner to `users`
  - `:admins` changes group ownership to admins
  - `bob:` changes ownership to bob, and group ownership to his group
- `chgrp`: change a file's group ownership
- `passwd`: change a user's password

## Processes

- __daemon program__: a program executing in the background without UI
- `ps`: prints snapshot of current processes
  - `ps -xj` or `ps aux` will list all processes, regardless of their controlling terminal, as well as additional details like the state
  - process states:
    - `R` running
    - `S` sleeping
    - `D` uninterruptible sleep (waiting for I/O, eg. disk drive)
    - `T` terminated
    - `Z` a defunct or "zombie" process (terminated, but not cleaned up by parent, child)
    - `<` high priority process
    - `N` low priority process
- `top`: displays tasks
- `jobs`: lists active jobs
- `bg`: places a job in the background
- `fg`: places a job in the foreground (eg. `fg %1` bring job 1 to foreground)
  - stop a foreground process with `ctrl-z`
- `kill`: sends a signal to a process
- common signals
  - `HUP`: hangup (due to wifi lol... obsolete? sometimes used by daemons for reinitialization)
  - `INT`: interrupt; terminates program
  - `KILL`: kill; sent to the kernel, not the process, to immediately terminate the process
    - as a result, process can't cleanup or save its work
  - `TERM`: terminate; sent  by  `kill` command
  - `CONT`: continue (used after `STOP`)
  - `STOP`: stop; signal pauses w/o terminating; signal is sent to the kernel, so can't be ignored
- `killall`: kill processes by name
- `shutdown`: shuts down or reboots the system

## The environment

- `printenv`: print part or all of the environment
- `set`: set shell options
- `export`: export environment to subsequently executed programs
- `alias`

## A gentle introduction to vi

- Cursor movements (in command mode)
  - `0` beginning of line
  - `^` first non-whitespace char on current line
  - `$` end of current lline
  - `w` beginning of next word or punctuation
  - `W` beginning of next word
  - `b` beginning of previous word or punctuation
  - `B` beginning of previous word
  - `ctrl-f` down a page
  - `ctrl-b` up a page
  - `[#]j` down # lines
  - `[#]k` up # lines
  - `[#]G` to line number #
  - `G` last line of file
  - `a` moves cursor to end of line before starting insert mode
  - `u` undo
- opening a line
  - `o` opens a line below current line
  - `O` opens a line above current line
- deleting (cutting) text
  - `x` current character
  - `[#]x` current character and next [#-1] characters
  - `dd` current line
  - `[#]dd` current line and next [#-1] lines
  - `dW` from cursor to beginning of next word
  - `d$` from cursor to end of line
  - `d0` from cursor to beginning of line
  - `d^` from cursor to first non-whitespace character in line
  - `dG` from current line to end of file
  - `d[#]G` from current line to [#]th line of file
- to yank text, replace all above instaces of 'd' with 'y'
- `p` to paste after cursor
- `P` to paste before the cursor
- `J` joins the current and next line together

#### Search and replace

- `f` searches a line & moves cursor to next instance of specified char; repeat search by typing `;`
- `/[query here]` moves cursor to next occurrence of word/phrase
- global search & replace: `%s/Line/line/gc` replaces instances of "Line" with "line" globally
  - `:` starts an ex cp,,amd
  - `%` specifies range of lines for the operation (`%` = `1,$` = first to last line); could use a number instead
  - `s` specifies operation, s = substitution
  - `g` = global, so performed on every instance; if ommitted, only first instance is substituted
  - `c` asks user confirmation before replace
- replace confirmation = `y/n/a/q/l/^E/^Y
  - `y` yes
  - `n` skip this instance
  - `a` yes to all
  - `q` quit; `ESC` works too
  - `l` perform this substitution (last) & then quit
  - `ctrl-e` & `ctrl-y` allows you to scroll up and down for context

#### Editing multiple files

- can use `vim [file1] [file2] [...]`
- `:n` to go to next file
- `:N` to go to previous file
- `:buffers` will display a list of files being editted
  - `:buffer [#]` will take you to the # of the specified buffer
- `:e [file name]` adds the file to the buffer (but you can't use `:n` with files added this way)
- `:r [file name]` copy an entire file into current file
- `ZZ` saves the current file & exits
- `:w [[file name to save as]]` can act as save as if you specify file name

## Customizing the prompt

- escape codes
  - `\d` current date in day, month, date format
  - `\h` host of local machine, w/o trailing domain name
  - `\H` full host name
  - `\t` time in 24hr hours:minutes:seconds format
  - `\T` time in 12 hr format
  - `\@` time in 12 hr AM/PM format
  - `\A` time in 24 hr hours:minutes format
  - `\u` user name
  - `\w` current working directory
  - `\W` last part of current working directory name
  - `\!` history number of current command
  - `\$` the "$" char
  - `\[` starts series of 1+ non-printing characters & `\]` signals the end
- escaped sequences text color codes
  - black `\033[0;30m`
  - red `\033[0;31m`
  - green `\033[0;32m`
  - brown `\033[0;33m`
  - blue `\033[0;34m`
  - purple `\033[0;35m`
  - cyan `\033[0;36m`
  - light grey `\033[0;37m`
  - dark grey `\033[1;30m`
  - light red `\033[1;31m`
  - light green `\033[1;32m`
  - yellow `\033[1;33m`
  - light blue `\033[1;34m`
  - light purple `\033[1;35m`
  - light cyan `\033[1;36m`
  - white `\033[1;37m`
- escaped sequences background color codes
  - black `\033[0;40m`
  - red `\033[0;41m`
  - green `\033[0;42m`
  - brown `\033[0;43m`
  - blue `\033[0;44m`
  - purple `\033[0;45m`
  - cyan `\033[0;46m`
  - light grey `\033[0;47m`

## Networking

- IP (Internet Protocol) address; URI (Uniform Resource Identifier)
- `ping` send an ICMP ECHO_REQUEST to network host
  - sends packets until interrupted, then prints performance statistics (no packets should be lost)
- `traceroute` print the route packets trace to a network host
- `netstat` print network connections, routin tables, interface statistics, masquerade connections, & multicast memberships
- `ftp` communicates w/ FTP servers, which're machines containing files that can be uploaded & downloaded over a network
  - insecure since usernames & passwords are sent in cleartext, so usually anonymous servers w/ dummy info is used
- `ssh` (Secure SHell) creates a secure connection w/ a remote host
  - first: ssh authenticates the host, second: encrypts all communications between the hosts
  - has `scp` (secure copy) To copy files across a network
  - has `sftp`

## Searching for files

- `locate` find files by name
- `find` search for files in a directory hierarchy
  - can find by size & name with additional tests (eg. `find [query] -type f - name "*.JPG" -size +1M)
    - sizes: `b` 512 byte blocks; `c` byes; `w` 2 byte words; `k` kilobytes; `M` megabytes; `G` gigabytes
  - there are also operators you can use for conditional file searching, but that's advanced usage; look it up if you need it
  - predefined find actions:
    - `-delete` delete currently matching file
    - `-ls` perform the equivalent of `ls -dils` on matching file(s)
    - `-print` output full pathname
    - `-quit` quit once a match has been made
- user defined actions for `find` are in the form `-exec [command] '{}' ';'`
  - use `ok` instead of `exec` for the interactive version
  - `{}` represents the current pathname
  - `;` delimits the end of the command
  - eg. `-exec rm '{}' ';'`
- `xargs` build & execute command lines from standard input
  - accepts input & converts it into an arg list for a specified command
  - eg. `find ~ -type f -name 'foo*' - print | xargs ls -l`
- you can use options to control the depth of `find`
  - `-depth`, `-maxdepth [levels]`, `-mindepth [levels]`, `-noleaf`
- `touch` change file times
- `stat` display file or file system status

## Archiving and backup

#### File compression

- `gzip` compress or expand files
  - corresponding program is called `gunzip`
- `bzip2` a block sorting file compressor

#### File archiving

- `tar` tape archiving (bundling several files & compressing them) utility
- `zip` package and compress files

#### File synchronization

- `rsync` remote file and directory synchronization
  - `rsync [options] [source] [destination]`

## Regular expressions

- `grep` "global regular expression print": searches text files for occurrence & prints matching lines
  - `grep [[options]] [regex] [[file...]]
  - options:
    - `-i` ignore case
    - `-v` nonmatching lines
    - `-c` number of matches
    - `-l` name of file w/ matches, not the lines themselves
    - `-L` print only names of nonmatching files
    - `-n` prefix each line w/ # of line w/i the file
    - `-h` suppress the output of filenames (For multi-file searches)
- regex metacharacters: `^ $ . [ ] { } - ? * + ( ) | \`
- `.` the any character
- `^` & `$` are treated as anchors, thus the match only occurs if the match is found @ the beginning of the line (`^`) or at the end of the line (`$`)
- bracket expressions make it so that the match needs to include one or more of the characters
  - within a bracket expression, `^` indicates negation & `-` indicates range
  - you can have multiple ranges, eg. `grep -h '^[A-Za-z0-9]' file.txt`
- character classes
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
- `|` functions as or here, too
- quantifiers
  - `?` match an element zero or one time
  - `*` match an element zero or more times
  - `+` match an element one or more times
  - `{}` match an element a specific number of times
    - `{n}` match only if it occurs n times
    - `{n,m}` match if occurs n-m times
    - `{n,} match if occurs n or more times
    - `{,m} match if occurs up to m times

## Text processing

- `cat` concatenate files & print on the standard input
  - `-n` numbers lines
  - `-s` suppresses output of multiple blank lines
- `uniq` report or omit repeated lines
  - `-c` shows number of duplicates
- `cut` remove sections from each line of files
- `paste` merge lines of files
- `join` join lines of 2 files on a common field
- `comm` compare 2 sorted files line by line
- `diff` compare files line by line
- `patch` apply a diff file to an original
- `tr` translate or delete characters
  - character-based search&replace operation (eg. `echo "lowercase" | tr a-z A-Z` => `LOWERCASE`)
- `sed` stream editor for filtering & transforming text
- `aspell` interactive spell checker
  - `aspell check [text file]`

## Formatting output

- `nl [file]` number the lines
- `fold` wrap each line to a specified length (80 if no with specified)
  - `fold -w [width] -s` fold lines @ _width_ & break at the last available space
- `fmt` a simple text formatter for paragraph formatting and more
- `pr` prepare text for printing by paginating
- `printf` formats and prints data
  - `print "[format]" [arguments]`
- `groff` a document formatting system

## Printing

- `pr` converts text files for printing
- `lpr` prints files
- `a2ps` formats files for printing on a PostScript printer
- `lpstat` shows printer status information
- `lpq` shows printer queue status
- `lprm` cancels print jobs

## Compiling programs

- __compiling__: translating source code into the computer processor's native language
  - interpreted programs are slower b/c they're translated every time it's executed; whereas compiled programs are only translated once and then the translation is recorded
- `make` - a utility to maintain programs

## Writing your first shell script

- first line of every shell script: `#!/bin/bash`
  - tells system the name of the interpreter to execute the script; called a "shebang"
- when writing scripts, use the longer form of the option names for readability

## Top-down design

- for function calls to be recognized as shell functions and not external programs, function definitions must appear before they are called
- local variables are declared with `local`, eg. `local foo=1`

## Flow control: branching with `if`

- `if`

```shell
if [commands]; then
  [commands]
[[elif [commands]; then
  [commands]]]
[[else
  [commands]]]
fi
```

- `test` has two forms: `test [expression]` or `[[expression]]`
- exit status: indicator of success/failure of a command; 0 = success, others = fail; 0-255
  - `echo $?` after executing a command to check its exit status
- test file expressions
  - `-e [file]` file exists
  - ... there are way more
- test String expressions
  - `[string]` string isn't null
  - `-n [string]` length of string is greater than 0
  - `-z [string]` length of string is 0
  - `[string1] = [string2]`; `[string1] == [string2]` strings are equal
  - `[string1] != [string2]` strings aren't equal
  - `[string1] > [string2]` string1 sorts after string2
  - `[string1] < [string2]` string 1 sorts before string2
- `>` & `<` operators must be quoted or escaped w/ `\` when used w/ test, otherwise they're considered redirection operators
- test Integer epressions
  - `[int1] -eq [int2]` equality
  - `[int1] -ne [int2]` not equal
  - `[int1] -le [int2]` less than or equal
  - `[int1] -lt [int2]` less than
  - `[int1] -ge [int2]` greater than or equal
  - `[int1] -gt [int2]` greater than
- `(( ))` performs arithmetic truth tests (designed for integers)
- logical operators
  - and: w/ test: `-a`; w/ [[ ]] & (( )): `&&`
  - or: w/ test: `-o`; w/ [[ ]] & (( )): `||`
  - not: `!`

## Reading keyboard input

- `read` read values from standard input
  - `read [[-options]] [[variable...]]`
    - variable: the name of 1+ variables used to hold the input value
  - if no variable is specified, `$REPLY` contains the line of data
  - if it receives more than the expected variables, it puts all the extra input in the final variable
- `read` options:
  - `-a [array]` assign the input to _array_ starting w/ index 0
  - `-d [delimiter]` indicator of end of input, rather than newline char
  - `-e` use Readline to handle input
  - `-n [num]` read _num_ chars of input, rather than entire line
  - `-p [prompt]` display prompt for input using the string _prompt_
  - `-r` raw mode; don't interpret backslash chars are escapes
  - `-s` silent mode; don't display chars as they're typed (good for passwords)
  - `-t [seconds]` timeout; terminate input after _seconds_
    - non-zero exit is returned by `read` if times out
  - `-u [fd]` use input from file descriptor _fd_ rather than standard input

## Flow control: looping with `while` / `until`

- __`while`__: `while [commands]; do [commands]; done
  - `break` & `continue`

```bash
count=1

while [ $count -le 5 ]; do
  echo $count
  count=$((count + 1))
done
```

- __`until`__:

```bash
count=1

until [ $count -gt 5 ]; do
  echo $count
  count=$((count + 1))
done
```

## Troubleshooting

- turn on __tracing__ to aee the commands performed with the expansions applied

  ```bash
  # ... code ...
  set -x # turns on tracing
  
  # ... code ...
  
  set +x # turns off tracing
  ```

## Flow control: branching with `case`

- __`case`__

  ```bash
  case [word] in
    [pattern [| pattern]...]) commands ;;...
  esac
  
  # eg.
  $reply=0
  
  # assume reply is 0-3
  case $REPLY in
    0)  echo "lol wat"
        exit
        ;;
    1) echo "ok"
       ;;
    *) echo "fall back"
       ;;
  esac
  ```

## Positional parameters

- positional parameters are named 0 through 9 (`$0`, `$1`, ..., `$9`)

  - specify # > 9 by putting it in braces (eg. `${10}`)

- shell provides `$#` representing number of args

- __`shift`__ gets you access to many arguments (increments the parameter)

  ```bash
  while [[ $# -gt 0 ]]; do
    echo "Argument $count = $1"
    count=$((count++))
    shift
  done
  ```

- `$*` & `$@` expand into the list of params, starting w/ 1

  - there's a difference, but it'll be clearer once you use it

## Flow control: looping with `for`

- __`for`__:

  ```bash
  for [variable] [[in [words]]]; do
    [commands]
  done
  
  # eg.
  for i in A B C D; do echo $i; done
  
  for i in {A..D}; do echo $i; done
  ```

  alternatively

  ```bash
  for (( expression1; expression2; expression3 )); do
    [commands]
  done
  
  # eg.
  for (( i=0; i<5; i=i+1 )); do
    echo $i
  done
  ```

## Strings and numbers

### Strings

- `${parameter:-word}` substitutes the value of the param if unset

  - doesn't change value

- `${parameter:=word}` sets default value if empty

- `${parameter:?word}` causes the script to exit with an error & the message specified in _word_ variable if the parameter is empty

- `${parameter:+word}` expands into the parameter if it's set, otherwise it doesn't result in anything

- expansions returning variable names:

  - `${!prefix*}` and `${!prefix@}` return names of existing variables w/ names beginning with _prefix_

- `${#parameter}` expands into length of string contained by parameter

  - unless parameter is `@` or `*`, then it results in the number of positional parameters

- `${parameter:offset}` & `${paramater:offset:length}` extracts a portion of the string contained in _parameter_

  - starts @ _offset_ characters & continues unless specified by _length_
  - if the offset is negative, it starts from the end of the string, but the length needs to be > 0

- `${parameter#pattern}` & `${parameter##pattern}` removes a leading portion of the string in _parameter_ defined by the _pattern_

  - `${parameter##pattern}` removes longest match, whereas the other removes the shortest match

  ```bash
  > foo=file.txt.zip
  > echo ${foo#*.}
  txt.zip
  > echo ${foo##*.}
  zip
  ```

- `${parameter%pattern}` & `${parameter%%pattern}` same as above, except it removes text from the end of the string in a parameter, rather than the beginning

  ```bash
  > foo=file.txt.zip
  > echo ${foo%.*}
  file.txt
  > echo ${foo%%.*}
  file
  ```

- searches & replaces upon _parameter_'s contents with the contents of _string_

  - `${parameter/pattern/string}` only the first occurrence of _pattern_ is replaced
  - `${parameter//pattern/string}` all occurrences are replaced
  - `${parameter/#pattern/string}` requires that the match occur at the beginning of the string
  - `${parameter/%pattern/string}` match must occur @ end of string
  - if _string_ is absent, the pattern matches are just deleted

### Numbers

- arithmetic evaluation & expansion `$((expression))`
- number bases
  - `[number]` treated as decimal integers
  - `0[number]` considered octal
  - `0x[number]` hexadecimal notation
  - `[base]#[number]` _number_ is in _base_
- notable operators
  - `/` integer division
  - `**` exponentiation
  - `%` modulo (remainder)
- can assign nothing to variables like `foo=`
- logic operators: `<=`, `>=`, `==`, and so on

## Arrays

- arrays are automatically created when accessed

  - alternate way: `declare -a a`
  - assign multiple values: `[name]=([value1] [value2] [...])`
  - assign multiple values: `name=([0]=value1 [1]=value2)`

  ```bash
  $ a[1]=foo
  $ echo ${a}
  ```

- access all elements in an array w/ subscripts `*` & `@`

  - identical behavior until they're quoted: when quoted
    - `*` echos all inline whereas `@` does each on a new line

  ```bash
  $ for i in ${animals[*]}; do echo $i; done
  $ for i in ${animals[@]}; do echo $i; done
  
  $ for i in "${animals[*]}"; do echo $i; done
  $ for i in "${animals[@]}"; do echo $i; done
  ```

- `$#name[@]` tells you length of array

- `$#name[number]` tells you the length of element _number_

- add elements to end of array w/ `+=`

  ```bash
  $ foo=(a b c)
  $ foo+=(d e f)
  ```

- sorting an array by piping into `sort`

  ```bash
  a_sorted=($(for i in "${a[@]}"; do echo $i; done | sort))
  ```

- `unset` deletes a variable, including an array in this case

- if you access an array without a subscript, it refers to element 0 of the array

## Exotica

- you can get asynchronous execution by launching a child script from a parent