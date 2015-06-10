# Chapter 1: Getting Started with LaTeX

####  What is Latex?

Software to typeset documents. Prepares documents.

What is typesetting?: Arranging the type for printing.



# Chapter 2: Formatting Words, Lines, and Paragraphs

#### Exploring the document structure

* documents are based on a __class__
    - __class__: a fundamental template that provides customizable features and styles, built for a certain purpose (eg. books, articles, etc.)
* __preamble__ of the document: place where you define class/properties/definitions;
    - ends at `\begin{document}` & doesn't produce output
* __environment__: code that's framed by a `\begin...` & `\end` command pair


#### Understanding LaTeX commands

* different ways commands are written:
    - `\command`
    - `\command{argument}`
    - `\command[optional argument]{argument}`


#### Comments

Comments look like

```latex
% comment
```


#### Symbols

* `\$` $, `\%` %, `\&` &
* `\{` {, `\}` }, `\_` _


### Formatting text: fonts, shapes, and styles

#### Font shape

* `\emph{emphasized text}`
    - this is called __semantic markup__ since it refers to the meaning of the text, in addition to the appearance
* `\textit{italicized text}`
* `\textbf{bolded text}`
* `\textsl{slanted text}`
* `\textsc{text in small caps}`
* `\upshape` upright shape
* `mdseries` medium
* `\bfseries` bold-face
* `\normalfont` default font

You can next font shape commands like so:

```latex
\emph{When you \emph{emphasize text} within emphasized text, the nested emphasized text looks normal.}
```


#### Choosing the font family

Choosing a sans-serif font:

```latex
% sans-serif font
\section{\textsf{I'm reading a book on \LaTeX\}}

% vs typewriter font
\texttt{some typewriter-looking text}
```

Serif fonts are used b/c easier reading. Called __Roman__ fonts.
`\textrm` creates Roman text.

* `\rmfamily` roman family
* `\sffamily` sans-serif family
* `\ttfamily` typewriter family


#### Switching the font family

Use `\sffamily` within an argument to switch to sans-serif font. It affects the following text.

```latex
\section{\sffamily Uh, so the font family was switched.}
```

__declaration__: commands that don't produce output, but affect following text

Keep in mind that not all font properties can be combined, depending on font.


#### Delimiting the effect of commands

Can use curly braces to make sure that only the text you want to be changed will be changed.

Curly braces tells LaTeX to begin a __group__. The closing brace stops the commands/declarations made within the group. The braces create the __scope__ of the commands/declarations.


#### Exploring font sizes

* `\noindent`, `\bigskip`
* `\tiny`, `\scriptsize`, `\footnotesize`, `\small`, `\normalsize`, `\large`, `\Large`, `\LARGE`, `\huge`, `\Huge`

The result of using these commands depends on what the base font's size. However, the commands like `\footnotesize` and `\scriptsize` will force the font to take on a LaTeX's fixed size.


#### Using environments

Environments increase readability of your code: eg.

```latex
\begin{huge}
\bfseries
Some huge bold text lives in here.
\end{huge}
```


#### Creating your own commands

__Macros__: custom commands that you've named and defined

```latex
\documentclass{article}
\newcommand{\yo}{yo...}
\begin{document}
\section{The \yo section}
\yo \yo \yo These are a bunch of "yo...", yo. Why couldn't I come up with a better example. What am I doing \yo
\end{document}
```


#### Gentle spacing after commands

`\xspace`. You can automate adding a backspace after commands like so:

```latex
\documentclass{article}
\usepackage{xspace}
\newcommand{\yo}{{yo...}\xspace}
\begin{document}
\section{The \yo section}
Why couldn't I come up with a better example. What am I doing \yo
\end{document}
```


#### Creating more universal commands: using arguments

```latex
\documentclass{article}
\newcommand{\keyword}[1]{\textbf{#1}}
\begin{document}
\section{Let's see how arguments work}
\keyword{This command will} only make the first argument within the curly
\keyword{braces bold}, as specified by [1]. The first argument happens to be the entirety of the brackets' content. I wonder why.
\end{document}
```

We have {#1} which refers to the first argument. #2 refers to the second, and so on.


#### Using optional arguments

With __optional arguments__, the arguments may be given. If they aren't, then the default values are used.

To create your own optional arguments:

```latex
\documentclass{article}
\newcommand{\keyword}[2][\bfseries]{{#1#2}}
\begin{document}
\keyword{Grouping} by curly braces limits the 
\keyword{scope} of \keyword[\itshape]{declarations}.
\end{document}
```

This code produces the following effect: 'Grouping' and 'scope' are bolded, whereas 'declarations' is italicized. By passing in the optional argument of `[\itshape]`, the default formatting is changed to italics.


#### `\newcommand`

* command: name of command, starting w/ a backslash
    - can't begin with `\end`, must consist of letters, or consist of a single non-letter symbol
* arguments: integer from 1-9; the # of arguments of the command; is 0 by default
* optional: the presence of an optional command makes it so that the arguments are optional, and the default value is given here
    - otherwise, all arguments are mandatory
* definition: 
    - every occurence of the form `#n` within the definition will be replaced by the nth argument

```latex
\newcommand{command}[arguments][optional]{definition}
```


#### URLs

`\usepackage{url}` provides the `\url` command to print URLs in typewriter font


#### Using boxes to limit the width of paragraphs

```latex
\parbox{5cm}{Text that fits in a 5cm-wide column}
```

By default, `\parbox` justifies the text


#### Common paragraph boxes

```latex
% General format:
\parbox[alignment]{width}{text}
```

* Alignments
    - `[t]` align parbox's bottom to the top of the text's baseline
        + think `vertical-align: top`
    - `[b]` align parbox's top to the top of the text line
        + think `vertical-align: bottom`
    - default behavior: parbox is vertically centered

An extended usage of `\parbox`:

```latex
\parbox[alignment][height][inner alignment]{width}{text}
```

* inner alignments
    - mainly used when the text's height isn't the same as the box's height
    - `c`: vertically center within box
    - `t`: place at top of box (think `top: 0`)
    - `b`: place text at bottom (think `bottom: 0`)
    - `s`: stretch the text out vertically, if possible


#### Boxes containing more text

For larger pieces of text, use `minipage`s. This is helpful in terms of code organization. Note that there aren't page breaks in minipages. Also note that _`minipage` uses the same arguments as `\parbox`._

```latex
\begin{minipage}{3cm}
text
\footnote{You can put footnotes in here, too}
\end{minipage}

```


### Breaking lines and paragraphs

You can define how a word is hyphenated when it is word-wrapped.

```latex
\hyphenation{acro-nym}

% You can pass in multiple words, separated by spaces
\hyphenation{acro-nym ac-ro-nym-ic a-cro-nym-i-cal-ly}
```

While it isn't recommended, just as inline-styles aren't, you can define division points in the body text:

```latex
ac\-ro\-nym
```


#### Preventing hyphenation

Declare it without break points, or protect it inside the text using the `\mbox` command

```latex
\hyphenation{youcantdividethiseventhoughyoushould}

...

can't break this \mbox{youcantdividethiseventhoughyoushould}
% not even line breaks will break it
```


#### Relevant hyphenation packages

* `\usepackage[none]{hyphenat}` prevents hyphenation throughout the document
    - `\nohypens{text}` does the same for text snippets
* `\usepackage[htt]{hyphenat}` enables hyphenation for typewriter text (this isn't the norm for monospace fonts)
* `\usepackage{microtype}`
    - super cool because it automatically expands and contracts text, and plays with the margin, to create visually-pleasing aligned text

Note that you can pass in _package options_ to packages. Separate multiple options with commas within one set of square brackets.


#### Breaking lines manually
 * `\\` ends a line and pushes the text to the next line, staying within the same paragraph
    - can also use `\newline`
* `\linebreak` will end the line, but keep full justification
    - could end up with huuuge unnatural spaces in your text.


#### Line break options

* `\\[value w/ units]` adds 'value w/ units' vertical space after the break
    - eg. `\\[10cm]` adds a 10cm vertical break
* `\\*[value]` adds vertical space, but it also prevents a page break before the next line of text
* `\linebreak[number]` influence a line break based on the option you set
    - `0` a line break is allowed
    - `1` a line break is desired
    - `2`, `3` yes please with that line break
    - `4` forced line break; (default if no option given)
* `\nolinebreak` has the same options
