# The Linux Command Line

## Ch 2 | What is the Shell?

* __`bash`__: Bourne Again SHell

##### Just so you know

* `df`: Disk Free space
* `free`: free memory

Miscellaneous

`date`, `cal`, `exit`



## Ch 3 | Navigation

* __`pwd`__: Print Working Directory
* __`cd`__: Change Directory
  * `cd -` change to previous working directory
* __`ls`__: LiSt directory contents
  * `ls [path]`: list contents without having to go to it
  * `-lh`: long format & human-readable



## Ch 4 | Exploring the system

* __`file [filename]`__: brief description of file's contents
* __`less [filename]`__: examine text file
  - Commands: `q` quit, `b` page back, `[space]` page forward, `g` file start, `G` file end, `/[characters]` find, `n` find next
* directories: /dev, /etc, /opt, /tmp
* __symbolic link__ (symlink, soft link): a reference to a file by another name
  - lets you refer to a file by many names



## Ch 5 | Manipulating files and directories

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



## Ch 6 | Working with commands

* __`type`__:
* __`which`__:
* __`man`__:
* __`apropos`__:
* __`info`__:
* __`whatis`__:
* __`alias`__: give commands nicknames using aliases `alias ls="ls -Gf"`

