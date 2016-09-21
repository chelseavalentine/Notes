# The Linux Command Line

## The environment

* `printenv`: print part or all of the environment

* `set`: set shell options

* `export`: export environment to subsequently executed programs

* `alias`

## A gentle introduction to vi

* Cursor movements (in command mode)
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
  -  `G` last line of file
  - `a` moves cursor to end of line before starting insert mode
  - `u` undo

* opening a line
  - `o` opens a line below current line
  - `O` opens a line above current line

* deleting (cutting) text
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

* to yank text, replace all above instaces of 'd' with 'y'

* `p` to paste after cursor

* `P` to paste before the cursor

* `J` joins the current and next line together

#### Search and replace

* `f` searches a line & moves cursor to next instance of specified char; repeat search by typing `;`

* `/[query here]` moves cursor to next occurrence of word/phrase

* global search & replace: `%s/Line/line/gc` replaces instances of "Line" with "line" globally
  - `:` starts an ex cp,,amd
  - `%` specifies range of lines for the operation (`%` = `1,$` = first to last line); could use a number instead
  - `s` specifies operation, s = substitution
  - `g` = global, so performed on every instance; if ommitted, only first instance is substituted
  - `c` asks user confirmation before replace

* replace confirmation = `y/n/a/q/l/^E/^Y
  - `y` yes
  - `n` skip this instance
  - `a` yes to all
  - `q` quit; `ESC` works too
  - `l` perform this substitution (last) & then quit
  - `ctrl-e` & `ctrl-y` allows you to scroll up and down for context

#### Editing multiple files

* can use `vim [file1] [file2] [...]`

* `:n` to go to next file

* `:N` to go to previous file

* `:buffers` will display a list of files being editted
  - `:buffer [#]` will take you to the # of the specified buffer

* `:e [file name]` adds the file to the buffer (but you can't use `:n` with files added this way)

* `:r [file name]` copy an entire file into current file

* `ZZ` saves the current file & exits

* `:w [[file name to save as]]` can act as save as if you specify file name
