# Chapter 1: Getting Started with LaTeX

##### What is Latex?

Software to typeset documents. Prepares documents.


# Chapter 2: Formatting Words, Lines, and Paragraphs

##### Exploring the document structure

* documents are based on a __class__
    - __class__: a fundamental template that provides customizable features and styles, built for a certain purpose (eg. books, articles, etc.)
* __preamble__ of the document: place where you define class/properties/definitions;
    - ends at `\begin{document}` & doesn't produce output
* __environment__: code that's framed by a `\begin...` & `\end` command pair


##### Understanding LaTeX commands

* different ways commands are written:
    - `\command`
    - `\command{argument}`
    - `\command[optional argument]{argument}`


##### Comments

Comments look like

```latex
% comment
```


##### Symbols

* `\$` $, `\%` %, `\&` &
* `\{` {, `\}` }, `\_` _


### Formatting text: fonts, shapes, and styles

##### Font shape

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


##### Choosing the font family

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


##### Switching the font family

Use `\sffamily` within an argument to switch to sans-serif font. It affects the following text.

```latex
\section{\sffamily Uh, so the font family was switched.}
```

__declaration__: commands that don't produce output, but affect following text

Keep in mind that not all font properties can be combined, depending on font.


##### Delimiting the effect of commands

Can use curly braces to make sure that only the text you want to be changed will be changed.

Curly braces tells LaTeX to begin a __group__. The closing brace stops the commands/declarations made within the group. The braces create the __scope__ of the commands/declarations.


##### Exploring font sizes

* `\noindent`, `\bigskip`
* `\tiny`, `\scriptsize`, `\footnotesize`, `\small`, `\normalsize`, `\large`, `\Large`, `\LARGE`, `\huge`, `\Huge`

The result of using these commands depends on what the base font's size. However, the commands like `\footnotesize` and `\scriptsize` will force the font to take on a LaTeX's fixed size.


##### Using environments

Environments increase readability of your code: eg.

```latex
\begin{huge}
\bfseries
Some huge bold text lives in here.
\end{huge}
```


##### Creating your own commands

__Macros__: custom commands that you've named and defined

```latex
\documentclass{article}
\newcommand{\yo}{yo...}
\begin{document}
\section{The \yo section}
\yo \yo \yo These are a bunch of "yo...", yo. Why couldn't I come up with a better example. What am I doing \yo
\end{document}
```


##### Gentle spacing after commands

You can automate adding a backspace after commands like so:

```latex
\documentclass{article}
\usepackage{xspace}
\newcommand{\yo}{{yo...}\xspace}
\begin{document}
\section{The \yo section}
Why couldn't I come up with a better example. What am I doing \yo
\end{document}
```


##### Creating more universal commands: using arguments

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


##### Using optional arguments (43)


# Chapter 3: Designing Pages

# Chapter 4: Creating Lists

# Chapter 5: Creating Tables and Inserting Pictures

# Chapter 6: Cross-Referencing

# Chapter 7: Listing Content and References

# Chapter 8: Typing Math Formulas

# Chapter 9: Using Fonts

# Chapter 10: Developing Large Documents

# Chapter 11: Enhancing Your Documents Further

# Chapter 12: Troubleshooting
