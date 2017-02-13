# The Linux Command Line

## Arrays

* arrays are automatically created when accessed
  - alternate way: `declare -a a`
  - assign multiple values: `[name]=([value1] [value2] [...])`
  - assign multiple values: `name=([0]=value1 [1]=value2)`

  ```bash
  $ a[1]=foo
  $ echo ${a}
  ```

* access all elements in an array w/ subscripts `*` & `@`
  - identical behavior until they're quoted: when quoted
    + `*` echos all inline whereas `@` does each on a new line
  ```bash
  $ for i in ${animals[*]}; do echo $i; done
  $ for i in ${animals[@]}; do echo $i; done

  $ for i in "${animals[*]}"; do echo $i; done
  $ for i in "${animals[@]}"; do echo $i; done
  ```

* `$#name[@]` tells you length of array

* `$#name[number]` tells you the length of element _number_

* add elements to end of array w/ `+=`
  ```bash
  $ foo=(a b c)
  $ foo+=(d e f)
  ```

* sorting an array by piping into `sort`
  ```bash
  a_sorted=($(for i in "${a[@]}"; do echo $i; done | sort))
  ```

* `unset` deletes a variable, including an array in this case

* if you access an array without a subscript, it refers to element 0 of the array

## Exotica

* you can get asynchronous execution by launching a child script from a parent
