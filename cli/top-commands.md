# Linux Commands

A collection of useful Linux commands for on-call investigations or daily productivity boosts.

This'll be organized by purpose and then alphabetically.

* __`awk`__: a programming language tool to manipulate text
* __`declare`__: states all of the set variables, and can be used to give them attributes or modify the properties of variables
* __`df`__: the amount of disk space on the file system
* `env`: displays the current environment and its variables, or can allow you to run a program in a modified environment
* __`eval`__: analyzes several arguments and concatenates them into a single command, and then reports on that argument's status
* __`find`__: searches the directory tree to locate files meeting specified conditions (e.g. name, type, exec, size, mtime, & user
* `free`: shows you the total free & used physical memory and swap space in the system
* `iptables`: allows or blocks traffic on a Linux host & can prevent certain apps from receiving or transmitting a request
* `lsof`: list all open files, can use `-u` to find it by user
* `passwd`: can be used to update a user's password
* `ping`: verifies that a particular IP address exists and can accept requests
* `ps`: used to report the statuses of current processes in the system
* `sed`: a stream editor that filters text in a pipeline and reports the modified text
* `service`: a way to start/stop a service, such as networking
* `sort`: sorts lines alphabetically or numerically
* `traceroute`: determines & records a route through the internet between 2 computers, to troubleshoot network/router issues
* `uniq`: compare adjacent lines in a file & remove or identify any duplicate lines
* `top`: displays all top processes in the system, sorted by the CPU usage
* `uptime`: reports how long the system has been running since it was last boot
* `su`: switch to a different user account to run a command from a different account name

### Favorites

* `htop`: Shows top processes, uptime, load, CPU
* `tldr`: community-driven `man` pages
* `git-extras`: additional info on a git repo (e.g. `git summary`, `git effort`, `git authors`, `git delete-merged-branches`, `git info`)


### Linux Performance Tools

* `uptime`: view load averages (the number of tasks/processes wanting to run); worth a quick look only since it can't be understood without other tools
* `dmesg | tail`: the last 10 system messages, if any
* `vmstat 1`: summary of key server stats, printed every second
  - if `si, so` are nonzero, you're out of memory
  - `r` = number of processes running on CPU and waiting for a turn; if it is greater than the number of CPU, then it's super saturated
  - high system time average (e.g. over 20%) can show inefficiencies in I/O processing
* `mpstat -P ALL 1`
  - CPU breakdown per CPU, used to check for imbalance & single-threaded applications
* `pidstat 1`
  - process stats with rolling averages
* `iostat -xz 1`
  - helps you understand disk via the workload applied & the resulting performance
* `free -m`
* `sar -n DEV 1`
  - check for network interface throughput as a measure of workload & to check whether limits have been reached
* `sar -n TCP,ETCP 1`
  - shows summarized view of key TCP metrics, including:
    * `active/s`: number of locally-initiated TCP connections per second (outbound)
    * `passive/s`: number of remotely-initiated TCP connections per second (inbound)
    * `retrans/s`: number of TCP retransmits per second
      - a sign of a network or server issue, due to an unreliable network or the server being overloaded and dropping packets
* `top`
