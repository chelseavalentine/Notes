# The Linux Command Line

## Customizing the prompt

* escape codes
  - `\d` current date in day, month, date format
  - `\h` host of local machine, w/o trailing domain name
  -  `\H` full host name
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

* escaped sequences text color codes
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

* escaped sequences background color codes
  - black `\033[0;40m`
  - red `\033[0;41m`
  - green `\033[0;42m`
  - brown `\033[0;43m`
  - blue `\033[0;44m`
  - purple `\033[0;45m`
  - cyan `\033[0;46m`
  - light grey `\033[0;47m`

## Networking

* IP (Internet Protocol) address; URI (Uniform Resource Identifier)

* `ping` send an ICMP ECHO_REQUEST to network host
  - sends packets until interrupted, then prints performance statistics (no packets should be lost)

* `traceroute` print the route packets trace to a network host

* `netstat` print network connections, routin tables, interface statistics, masquerade connections, & multicast memberships

* `ftp` communicates w/ FTP servers, which're machines containing files that can be uploaded & downloaded over a network
  - insecure since usernames & passwords are sent in cleartext, so usually anonymous servers w/ dummy info is used

* `ssh` (Secure SHell) creates a secure connection w/ a remote host
  - first: ssh authenticates the host, second: encrypts all communications between the hosts
  - has `scp` (secure copy) To copy files across a network
  - has `sftp`

## Searching for files

* `locate` find files by name

* `find` search for files in a directory hierarchy
  - can find by size & name with additional tests (eg. `find [query] -type f - name "*.JPG" -size +1M)
    + sizes: `b` 512 byte blocks; `c` byes; `w` 2 byte words; `k` kilobytes; `M` megabytes; `G` gigabytes
  - there are also operators you can use for conditional file searching, but that's advanced usage; look it up if you need it
  - predefined find actions:
    + `-delete` delete currently matching file
    + `-ls` perform the equivalent of `ls -dils` on matching file(s)
    + `-print` output full pathname
    + `-quit` quit once a match has been made

* user defined actions for `find` are in the form `-exec [command] '{}' ';'`
  - use `ok` instead of `exec` for the interactive version
  - `{}` represents the current pathname
  - `;` delimits the end of the command
  - eg. `-exec rm '{}' ';'`

* `xargs` build & execute command lines from standard input
  - accepts input & converts it into an arg list for a specified command
  - eg. `find ~ -type f -name 'foo*' - print | xargs ls -l`

* you can use options to control the depth of `find`
  - `-depth`, `-maxdepth [levels]`, `-mindepth [levels]`, `-noleaf`
* `touch` change file times

* `stat` display file or file system status

## Archiving and backup

#### File compression

* `gzip` compress or expand files
  - corresponding program is called `gunzip`

* `bzip2` a block sorting file compressor

#### File archiving

* `tar` tape archiving (bundling several files & compressing them) utility

* `zip` package and compress files

#### File synchronization

* `rsync` remote file and directory synchronization
  - `rsync [options] [source] [destination]`
