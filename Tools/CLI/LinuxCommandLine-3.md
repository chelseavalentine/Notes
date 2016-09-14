# The Linux Command Line

## Ch 9 – Advanced keyboard tricks

* `clear`: clears the screen

* `history`: displays the history of commands

#### Cursor movements

* `ctrl-a`: beginning of line

* `ctrl-e`: end of line

* `alt-f`: forward one word (`alt` = `ctrl` + `option`)

* `alt-b`: back one word (`alt` = `ctrl` + `option`)

#### Cutting and pasting

* `ctrl-k`: deletes (enter the kill-ring) from cursor to end of line

* `ctrl-u`: deletes (enter the kill-ring) from cursor to beginning of line

* `ctrl-y`: yanks text from the kill-ring & inserts @ cursor location

#### Completion

* __history expansion__: `!!` = last command, `![# here]` = command at line [# here] in history
  * `!string`: repeat last history list itme starting with `string`
  * `!?string`: repeat last history list itme containing `string`

* `ctrl-r` search history
  - then use `ctrl-j` to copy the line from history to the current command line
  - `ctrl-o` execute current item in history list, & advance to the next one

## Ch 10 – Permissions (107)

* `ssh` secure shell

* `id`: display a user's identity

* file types (appear before the permission list w/ attributes)
  - `-` regular file
  - `d` a directory
  - `l` a symbolic link
  - `c` a character special file (a device handling data as a stream of bytes, eg. terminal or modem)
  - `b` a block special file (device handles data in blocks, eg. hard drive or CD-ROM drive)

* permission attributes
  - `r` allows file to be open & read
  - `w` allows a file to be written to/truncated; not renamed or deleted
    - directory attributes determine whether you can rename or delete a file
  - `x` allows a file to be treated as a program & executed

* `chmod`: change a file's mode
  - its arguments take the form of octal numbers, representing what the permissions will be set to
    + 0: ---
    + 1: --x
    + 2: -w-
    + 3: -wx
    + 4: r--
    + 5: r-x
    + 6: rw-
    + 7:  rwx
  - eg. `chmod 600 foo.txt` sets its file modes to rw- --- ---

* `chmod` also has symbolic notation
  - `u` file or directory owner
  - `g` group owner
  - `o` world
  - `a` all
  - allows you to specify permissions for each group individually (eg. `u+x` add execute permission for the user; `u-x` removes execute permission; `+x` add execute permission for all)

* `umask`: set the default file permissions

* `su`: run a shell as another user

* `sudo`: execute a command as another user

* `chown`: change a file's owner &/ group (`chown [owner][:[group]] file ...`)
  * `bob:users` changes ownership to `bob` & file group owner to `users`
  * `:admins` changes group ownership to admins
  * `bob:` changes ownership to bob, and group ownership to his group

* `chgrp`: change a file's group ownership

* `passwd`: change a user's password

## Ch 11 – Processes (127)

## Ch 12 - The environment (142)

## Ch 13 - A gentle introduction to vi (154)

## Ch 14 - Customizing the prompt (174)

## Ch 15 - Package management (184)

## Ch 16 - Storage media (194)

## Ch 17 - Networking (213)

## Ch 18 - Searching for files (227)

## Ch 19 - Archiving and backup (244)

## Ch 20 - Regular expressions (261)
