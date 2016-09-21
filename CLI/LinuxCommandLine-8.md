# The Linux Command Line

## Ch 31 - Troubleshooting

* turn on __tracing__ to aee the commands performed with the expansions applied
  ```bash
  # ... code ...
  set -x # turns on tracing

  # ... code ...

  set +x # turns off tracing
  ```

## Ch 32 - Flow control: branching with `case` (446)

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

## Ch 33 - Positional parameters

* positional parameters are named 0 through 9 (`$0`, `$1`, ..., `$9`)
  - specify # > 9 by putting it in braces (eg. `${10}`)

* shell provides `$#` representing number of args

* __`shift`__ gets you access to many arguments (increments the parameter)
  ```bash
  while [[ $# -gt 0 ]]; do
    echo "Argument $count = $1"
    count=$((count + 1))
    shift
  done
  ```

* `$*` & `$@` expand into the list of params, starting w/ 1
  - there's a difference, but it'll be clearer once you use it

## Ch 34 - Flow control: looping with `for` (465)

## Ch 35 - Strings and numbers (471)

## Ch 36 - Arrays (490)

## Ch 37 - Exotica (499)

(510)
