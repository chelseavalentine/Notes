# The Linux Command Line

## Flow control: branching with `case`

* __`case`__
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

* positional parameters are named 0 through 9 (`$0`, `$1`, ..., `$9`)
  - specify # > 9 by putting it in braces (eg. `${10}`)

* shell provides `$#` representing number of args

* __`shift`__ gets you access to many arguments (increments the parameter)
  ```bash
  while [[ $# -gt 0 ]]; do
    echo "Argument $count = $1"
    count=$((count++))
    shift
  done
  ```

* `$*` & `$@` expand into the list of params, starting w/ 1
  - there's a difference, but it'll be clearer once you use it

## Flow control: looping with `for`

* __`for`__:
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

* `${parameter:-word}` substitutes the value of the param if unset
  - doesn't change value

* `${parameter:=word}` sets default value if empty

* `${parameter:?word}` causes the script to exit with an error & the message specified in _word_ variable if the parameter is empty

* `${parameter:+word}` expands into the parameter if it's set, otherwise it doesn't result in anything

* expansions returning variable names:
  - `${!prefix*}` and `${!prefix@}` return names of existing variables w/ names beginning with _prefix_

* `${#parameter}` expands into length of string contained by parameter
  - unless parameter is `@` or `*`, then it results in the number of positional parameters

* `${parameter:offset}` & `${paramater:offset:length}` extracts a portion of the string contained in _parameter_
  - starts @ _offset_ characters & continues unless specified by _length_
  - if the offset is negative, it starts from the end of the string, but the length needs to be > 0

* `${parameter#pattern}` & `${parameter##pattern}` removes a leading portion of the string in _parameter_ defined by the _pattern_
  - `${parameter##pattern}` removes longest match, whereas the other removes the shortest match
  ```bash
  > foo=file.txt.zip
  > echo ${foo#*.}
  txt.zip
  > echo ${foo##*.}
  zip
  ```

* `${parameter%pattern}` & `${parameter%%pattern}` same as above, except it removes text from the end of the string in a parameter, rather than the beginning
  ```bash
  > foo=file.txt.zip
  > echo ${foo%.*}
  file.txt
  > echo ${foo%%.*}
  file
  ```

* searches & replaces upon _parameter_'s contents with the contents of _string_
  - `${parameter/pattern/string}` only the first occurrence of _pattern_ is replaced
  - `${parameter//pattern/string}` all occurrences are replaced
  - `${parameter/#pattern/string}` requires that the match occur at the beginning of the string
  - `${parameter/%pattern/string}` match must occur @ end of string
  - if _string_ is absent, the pattern matches are just deleted

### Numbers

* arithmetic evaluation & expansion `$((expression))`

* number bases
  - `[number]` treated as decimal integers
  - `0[number]` considered octal
  - `0x[number]` hexadecimal notation
  - `[base]#[number]` _number_ is in _base_

* notable operators
  - `/` integer division
  - `**` exponentiation
  - `%` modulo (remainder)

* can assign nothing to variables like `foo=`

* logic operators: `<=`, `>=`, `==`, and so on
