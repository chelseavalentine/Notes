# The Linux Command Line

## Advanced keyboard tricks

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

## Permissions

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

## Processes

* __daemon program__: a program executing in the background without UI

* `ps`: prints snapshot of current processes
  - `ps -xj` or `ps aux` will list all processes, regardless of their controlling terminal, as well as additional details like the state
  - process states:
    + `R` running
    + `S` sleeping
    + `D` uninterruptible sleep (waiting for I/O, eg. disk drive)
    + `T` terminated
    + `Z` a defunct or "zombie" process (terminated, but not cleaned up by parent, child)
    + `<` high priority process
    + `N` low priority process

* `top`: displays tasks

* `jobs`: lists active jobs

* `bg`: places a job in the background

* `fg`: places a job in the foreground (eg. `fg %1` bring job 1 to foreground)
  - stop a foreground process with `ctrl-z`

* `kill`: sends a signal to a process

* common signals
  - `HUP`: hangup (due to wifi lol... obsolete? sometimes used by daemons for reinitialization)
  - `INT`: interrupt; terminates program
  - `KILL`: kill; sent to the kernel, not the process, to immediately terminate the process
    - as a result, process can't cleanup or save its work
  - `TERM`: terminate; sent  by  `kill` command
  - `CONT`: continue (used after `STOP`)
  - `STOP`: stop; signal pauses w/o terminating; signal is sent to the kernel, so can't be ignored

* `killall`: kill processes by name

* `shutdown`: shuts down or reboots the system
