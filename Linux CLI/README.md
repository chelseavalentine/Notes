# The Linux Command Line

## Ch 2 – What is the Shell?

* __bash__: Bourne Again SHell

##### Just so you know

* `df`: Disk Free space
* `free`: free memory

Miscellaneous

`date`, `cal`, `exit`



## Ch 3 – Navigation

* __`pwd`__: Print Working Directory
* __`cd`__: Change Directory
  * `cd -` change to previous working directory
* __`ls`__: LiSt directory contents
  * `ls [path]`: list contents without having to go to it
  * `-lh`: long format & human-readable



## Ch 4 – Exploring the system

* __`file [filename]`__: brief description of file's contents
* __`less [filename]`__: examine text file
  - Commands: `q` quit, `b` page back, `[space]` page forward, `g` file start, `G` file end, `/[characters]` find, `n` find next
* directories: /dev, /etc, /opt, /tmp
* __symbolic link__ (symlink, soft link): a reference to a file by another name
  - lets you refer to a file by many names



## Ch 5 – Manipulating files and directories

* __`cp`__: CoPy files & directories
  * `cp -u [pattern] [destination]` - copy files that don't exist in the destination over
  * `cp item… directory` copy multiple items (files/directories) into a directory
  * `cp [i1] [i2]`:  copy item 1 to item2
  * `cp -r [dir1] [dir2]`: copy dir1 contents into dir2 & create dir2 if it doesn't exist already
  * _options_ – `-u` update (add diff), `-i` ask before changes, `-v` explain what's happening
* __`mv`__: MoVe/rename files & directories
* __`mkdir`__: create directories
* __`rm`__: ReMove files & directories
  * `rm [items…]`
  * options: `-r` recursively (needed for dirs), `-f` force, `-i` interactive (confirm delete)
* __`ln`__: create hard & symbolic LiNks
  * __hard link__: `ln [file] [link]`
    * creates an additional file–but has some limitations:
      * can't be made for a directory
      * can't be made on a file outside its file system
    * no indication that something's a hard link;
    * deleting these links doesn't deallocate space until all hard links deleted
  * __symbolic link__: `ln -s [item] [link]`
    * text pointer to a referenced file/directory
    * deleting a symlink means deleting only the link, not also the file
      * but deleting the file & not the link will keep the link… broken link! :( (in red)



### Wildcards

* `*`: match any character
* `?`: match any single character
* `[characters~]`: match any character in this set
* `[!characters~]`: match any character not in this set
* `[[:class:]]`: match any character not in the class
  * _classes_ – [:alnum:] alphanumeric, [:alpha:] alphabetic, [:digit:], [:lower:], [:upper:]



## Ch 6 – Working with commands

* __`type`__: how a command name is interpreted
  * executable program, shell builtin, shell function, alias
* __`which`__: where a executable program is located
* __`man`__: a command's manual page
  * __`help`__ for shell builtins, or `[cmd] --help`
  * `man [section] [search_term]` where:
    1. User commands
    2. Programming interfaces kernel system calls
    3. Programming interfaces to the C library
    4. Special files (device nodes & drivers, etc)
    5. File formats
    6. Games & amusements (eg. screen savers)
    7. Miscellaneous
    8. System admin commands
* __`apropos`__: list appropriate commands (gives you possible matches for a search term)
* __`info`__: a command's info entry 
  * have hyperlinks to go from topic (node) to topic (node), indicated by * & activated by putting the cursor on it & pressing enter
  * `n` next node, `p` prev node, `?` cmd help, `[delete]` prev page, `[space]` next page
* __`whatis`__: a brief description of a command
* __`alias`__: give commands nicknames using aliases `alias ls="ls -Gf"`
  * `alias name='string'`
  * __`unalias`__


* `[-op1|-op2]` indicate that op1 & op2 are mutually exclusive



## Ch 7 – I/O redirection

* __`cat`__: concatenate files
* __`sort`__: sort lines of text
* __`uniq`__: report/omit repeated lines
* __`grep`__: print lines matching a RegEx pattern
* __`wc`__: print newline, word, & byte counts for each file
* __`head`__: output the first part of a file
* __`tail`__: output the last part of a file
* __`tee`__: read from standard input & write to standard output and files

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
* ​

#### Standard input, output, & error

## Ch 8 – The world as the Shell

## Ch 9 – Advanced keyboard tricks

## Ch 10 – Permissions

## Ch 11 – Processes

