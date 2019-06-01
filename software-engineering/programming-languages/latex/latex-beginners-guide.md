# LaTeX Beginner's Guide

## Chapter 1: Getting Started with LaTeX

#### What is Latex?

Software to typeset documents. Prepares documents.

What is typesetting?: Arranging the type for printing.

## Chapter 2: Formatting Words, Lines, and Paragraphs

#### Exploring the document structure

- documents are based on a __class__
  - __class__: a fundamental template that provides customizable features and styles, built for a certain purpose (eg. books, articles, etc.)
- __preamble__ of the document: place where you define class/properties/definitions;
  - ends at `\begin{document}` & doesn't produce output
- __environment__: code that's framed by a `\begin...` & `\end` command pair

#### Understanding LaTeX commands

- different ways commands are written:
  - `\command`
  - `\command{argument}`
  - `\command[optional argument]{argument}`

#### Comments

Comments look like

```latex
% comment
```

#### Symbols

- `\$` $, `\%` %, `\&` &
- `\{` {, `\}` }, `\_` _

### Formatting text: fonts, shapes, and styles

#### Font shape

- `\emph{emphasized text}`
  - this is called __semantic markup__ since it refers to the meaning of the text, in addition to the appearance
- `\textit{italicized text}`
- `\textbf{bolded text}`
- `\textsl{slanted text}`
- `\textsc{text in small caps}`
- `\upshape` upright shape
- `mdseries` medium
- `\bfseries` bold-face
- `\normalfont` default font

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

- `\rmfamily` roman family
- `\sffamily` sans-serif family
- `\ttfamily` typewriter family

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

- `\noindent`, `\bigskip`
- `\tiny`, `\scriptsize`, `\footnotesize`, `\small`, `\normalsize`, `\large`, `\Large`, `\LARGE`, `\huge`, `\Huge`

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

- command: name of command, starting w/ a backslash
  - can't begin with `\end`, must consist of letters, or consist of a single non-letter symbol
- arguments: integer from 1-9; the # of arguments of the command; is 0 by default
- optional: the presence of an optional command makes it so that the arguments are optional, and the default value is given here
  - otherwise, all arguments are mandatory
- definition:
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

- Alignments
  - `[t]` align parbox's bottom to the top of the text's baseline
    - think `vertical-align: top`
  - `[b]` align parbox's top to the top of the text line
    - think `vertical-align: bottom`
  - default behavior: parbox is vertically centered

An extended usage of `\parbox`:

```latex
\parbox[alignment][height][inner alignment]{width}{text}
```

- inner alignments
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

- `\usepackage[none]{hyphenat}` prevents hyphenation throughout the document
  - `\nohypens{text}` does the same for text snippets
- `\usepackage[htt]{hyphenat}` enables hyphenation for typewriter text (this isn't the norm for monospace fonts)
- `\usepackage{microtype}`
  - super cool because it automatically expands and contracts text, and plays with the margin, to create visually-pleasing aligned text

Note that you can pass in _package options_ to packages. Separate multiple options with commas within one set of square brackets.

#### Breaking lines manually

- `\\` ends a line and pushes the text to the next line, staying within the same paragraph
  - can also use `\newline`
- `\linebreak` will end the line, but keep full justification
  - could end up with huuuge unnatural spaces in your text.

#### Line break options

- `\\[value w/ units]` adds 'value w/ units' vertical space after the break
  - eg. `\\[10cm]` adds a 10cm vertical break
- `\\*[value]` adds vertical space, but it also prevents a page break before the next line of text
- `\linebreak[number]` influence a line break based on the option you set
  - `0` a line break is allowed
  - `1` a line break is desired
  - `2`, `3` yes please with that line break
  - `4` forced line break; (default if no option given)
- `\nolinebreak` has the same options

When you want a phrase/title/etc. to be displayed together (i.e. not get split by a line break), do:

```latex
Dr.~Watson %~ is interpreted as a space
```

#### Exploring the fine details

__ligatures__: when 2+ letters are joined into one

You can force letters/symbols to stay separate by doing something like

```latex
% this
f\/f f\/i -\/-

% or this
f{}f f{}i -{}-

% instead of
ff fi --
```

Another way to disable ligatures, except globally:

`\usepackage[noligatures]{microtype}`

#### Setting dots

`\frenchspacing` can be used in the preamble. It uses the same spacing for everything. `\nonfrenchspacing` is used by default.

LaTeX can't know everything. Sometimes you'll get an 'end-of-sentence' space where you desire an interword space. You can manually fix it.

- `.\ ` produces a normal interword space
- `\@.` says that the following dot stands at the end of a sentence

Just for fun: `\ldots` produces an ellipse.

#### Setting accents

Just for fun: this is how you produce accents:

- `\'a` á

There are several more that you can find. You can add accented letters directly to the editor, too. But you have to include `\usepackage[utf8]{inputenc}`

### Turning off full justification

`\raggedright`, `\raggedleft`

```latex
\parbox{10cm}{\raggedright
    This text is left-aligned.
}
\parbox{15cm}{\raggedleft
    This text is right-aligned.
}
\parbox{15cm}{\centering
    This text is centered.
}
```

Random: You can use `\today` to have today's date automatically inserted.

#### Text justification and environments

`{center}`, `{flushleft}`, `{flushright}`

```latex
\begin{center}
    \emph{Chelsea}
\end{center}
```

### Displaying quotes

```latex
\begin{quote}
This is a quote.
\end{quote}
``This is also a quote'' Where `` makes the left double quotation mark, and '' makes the right double quotation mark.
```

For longer quotes, try the quotation environment...

```latex
\begin{quotation}
Just pretend that this is a long quotation. Even though it isn't. This looks like a block quote.
\end{quotation}
```

#### Spacing between paragraphs instead of indentation

Avoid indentation. Kinda like HTML's default formatting for paragraphs. You indicate new paragraphs via increased vertical space. Use this in the preamble:

```latex
\usepackage{parskip}
```

## Chapter 3: Designing Pages

### Defining the overall layout

#### A book with chapters

```latex
\documentclass[a4paper,12pt]{book}
\usepackage[english]{babel} % gives more specific English typesetting; we're using it to load blindtext in English
\usepackage{blindtext} % lets us add filler text
\begin{document}
\chapter{Chapter title} % Says Chapter #\nChapter title
What we're going to do in the chapter.
\section{Filler section}
\blindtext
\subsection{Filling sections}
\blindtext[10]
\end{document}
```

#### Defining margins yourself

Use the geometry package.

```latex
\usepackage[a4paper, inner=1.5cm, outer=3cm, top=2cm, bottom=3cm, bindingoffset=1cm]{geometry}
```

More geometry package options:

- `paperwidth=value`, `paperheight=value`
- `papersize={width, height}`
- `landscape`, `portrait`

#### Specifying the text area

Can also pass these into geometry:

- `textwidth=value`, `textheight=value`
- `lines=value` specifies text height by a number of lines
- `includehead` makes page header be included into body area (is usually false)
- `includefoot` ^ same but for footer

#### Setting the margins

- `left`, `right`, `inner`, `outer`, `top`, `bottom`, `bindingoffset` all take values
- `twoside` makes it so left & right margins are swapped on left-hand pages

can use margin ratios like: `left:right = 1:1`

#### Changing the line spacing (leading)

Increase it.

```latex
\usepackage[onehalfspacing]{setspace}
```

Other package options: `singlespacing`, `onehalfspacing`, `doublespacing`

Also checkout the spacing environment.

```latex
\begin{spacing}{2.4}
This text stretched by factor of 2.4
\end{spacing}
```

#### Using class options to configure the document style

Options you can use with `\documentclass`:

- `landscape`, `onecolumn`, `twocolumn`
- `oneside`, `twoside`
- `openright`, `openany` decides which side of the book a chapter begins on
- `article`, `book`, `report`, `slides`, `letter`
- `titlepage`, `nottitlepage`
- `final`, `draft`
  - if `draft`, then LaTeX indicates overfull lines
- `openbib` bibliography formatted in open style, rather than compressed style
- `fleqn`: formulas are left-aligned
- `leqno`: numbered displayed formulas have their numbers on the left side, instead of the right side

#### Creating a table of contents

`\tableofcontents`

Sectioning commands for `book`, `report`, and `article`:

- `\part` divides document into major units
- `\chapter`
- `\section`, `\subsection`, `\subsubsection`
- `\paragraph`, `subparagraph`

Use the starred form (`\section*{title}`) to make it so that the numbering will be suppressed and it won't show up in table of contents

#### Designing headers and footers

In the preamble:

```latex
\usepackage{fancyhdr}
\fancyhf{} % clear headers & footers
fancyyhead[LE]{\leftmark} % store chapter number & title together. LE: left even (refers to location of header on even-numbered pages)
\fancyhead[RO]{\nouppercase{\rightmark}} % right mark: store section number and title together
\fancyfoot[LE,RO]{\thepage} % RO = right-odd; display number in footer.
\pagestyle{fancy} % tell latex to use fancy style throughout
```

### Understanding page styles

- page styles:
  - `empty`: no header/footer
  - `plain`: no header; centered page number in the footer
  - `headings`: header has chapter titles, & sections/subsections' titles. Empty footer
  - `myheadings`: heading has user-defined text & the page number. Empty footer
  - `fancy`: customizable header & footer
    - enabled by `fancyhdr` package

Changing the page style:

- `\pagestyle{name}` switch from this point onward
- `\thispagestyle{name}` only change style for current page

### Customizing header and footer

Footer & header locations:

- Header: `\lhead`, `\chead`, `\rhead`
- Footer: `\lfoot`, `\cfoot`, `\rfoot`

The structure for an alternative method is: `\fancyhead[code]{text}` and `\fancyfoot[code]{text}`

Where (case-insensitive) code can be any of the following:

- `L` left, `C` center, `R` right, `E` even page, `O` odd page, `H` header, `F`, footer

### Using decorative lines in header or footer

`\renewcommand{\headrulewidth}{width}`
`\renewcommand{\footrulewidth}{width}`

### Changing LaTeX's header marks

- `\markright{right head}` sets the right heading
- `\markboth{left head}{right head}` sets the left & right heading

Can use `\chapter*` and `\section*`, et al. in order to get rid of the header entries

### Breaking pages

`\pagebreak`
`\newpage`, `\nopagebreak`

Note that pagebreak doesn't break a line. It applies to the end of the current line. It also just means new column if you're using the 2-column mode

`\clearpage` creates a new page too, even when in 2-column mode

Use `\raggedbottom` to turn off vertical justification. `\flushbottom` turns it on.

### Enlarging a page

`\enlargethispage{n\baselineskip}` lets us put a bit more text on the page. _The value you provide for n will determine how many additional lines are squeezed in_. You can use lines, or measurements with units. And n is any rational number.

`\enlargethispage*` shrinks all of the page's vertical spaces to their minimum

### Using footnotes

Use `\footnote{text}` within the body.

If you use a footnote within an argument that also does processing on its arguments, like `\section{}`, then you want to place `\protect` before the footnote.

```latex
\section{The section title\protect\footnote{our footnote}}
```

_Note:_ Footnotes within headings also appear in the table of contents, & maybe in page headers; you can prevent this:

```latex
\section[titlewithoutfootnote]{title with a\protect\footnote{footnote}}
```

#### Definition of footnote

- `\footnote[number]{text}` footmark marked by a number that you choose
- `\footnotemark[number]` makes superscripted number in the text as the footnote mark; no generated footnote
- `\footnotemark[number]`  generates footnote w/o putting footnote mark into text & doesn't increase the internal footnote counter

### Modifying the dividing line

Redefine what the line the borders the footnotes looks like. In the preamble:

```latex
\renewcommand{\footnoterule}
    {\noindent\smash{\rule[3pt]{\textwidth}{0.4pt}}}

% or omit the line completely
\renewcommand{\footnoterule}{}
```

- `\smash` gives the line a height and depth of 0, so it occupies no vertical space

#### Related footnote packages

- `endnotes`: footnotes at end of document
- `manyfoot`: nested footnotes
- `bigfoot`: replaces & extends manyfoot; improves page break handling w/ footnotes
- `savefnmark`: easier to use footnotes several times
- `footmisc`: a lot of visual customization

## Chapter 4: Creating Lists

### Building a bulleted list

```latex
\begin{itemize}
    \item whatever bullet1 is
    \begin{itemize}
        \item and now we're nesting lists
        \item can nest up to 4 levels
    \end{itemize}
\end{itemize}
```

### Creating a numbered list

```latex
\begin{enumerate}
    \item first
    \item second
\end{enumerate}
```

item is defined as `\item[text]`, so you can replace a number or bullet with whatever your text is

### Customizing lists

#### Saving space with compact lists

Removing white space around list items, and before/after the list, with `paralist`

```latex
% in preamble
\usepackage{paralist}

% in document
\begin {compactenum} % 1, 2, 3, 4 ... etc
    \item this will be compact
    \item so will this
    \begin{compactitem}
        \item now we have a compact
        \item nested bulleted list too
    \end{compactitem}
    \item We can list multiple things on one line:
        \begin{inparaenum}
            \item case 1
            \item case 2
        \end{inparaenum}
\end{compactenum}
```

There's also `asparaenum` and `asparaitem` to format list items like a separate common LaTeX paragraph, w/ numbers/bullets

### Choosing bullets and numbering format

Use `enumitem` package to be get more customization of lists.

```latex
% in preamble
\usepackage{enumitem}
\setlist{nolistsep} % compact lists
\setitemize[1]{label=---} % the bullet symbol is an em dash
\setenumerate[1]{label=\textcircled{\scriptsize\Alph*}, font=\sffamily}  % creates a circled letter for bullets

% in body, you can use
\end{enumerate}
\begin{enumerate}[resume*]
```

More optional arguments:

- With values
  - `font`; any font command
  - `label`; can use text styling + `\arabic*`, `\alph*`, `\Alph*`, `\roman*`, `\Roman*`
    - or use `(i)`, `(1)`, `(A)`, `(a)`, `(I)`
  - `label*`; same as above, but appended to current label
  - `align`; `left` or `right`
- Without values
  - `noitemsep`
  - `nolistsep`
  - `resume`, `resume*`

### Producing a definition list

```latex
\begin{description}
    \item[word] definition
\end{description}
```

#### Related packages

- `paralist` has `compactdesc`, `inparadesc`, & `asparadesc`
- `enumitem` has global command `\setdescription{format}`
- `layouts` allows you to adjust a list's dimensions

## Chapter 5: Creating Tables and Inserting Pictures

### Writing in columns

By using tabs. The tabbed text is aligned. 'Info' isn't tabbed in this example.

```latex
\begin{tabbing}
\emph{Info:} \= Software \= : \= LaTeX \\
\> Author \> : \> Author's name
\end{tabbing}
```

`\=` sets a tab stop. `\\` ends the line, and `\>` moves to the next tab stop.

#### Create tabbed columns with spacing

```latex
\documentclass{article}
\newcommand{\head}[1]{\textbf{#1}}
\begin{document}
\begin{tabbing}

Family \= \verb|\textrm{...}| \= \head{Declaration} \= \kill
    \> \head{Command} \> \head{Declaration} \> \head{Example} \\
    Family \> \verb|\textrm{...}| \> \verb|\rmfamily| \> \rmfamily Example text\\

\end{tabbing}
\end{document}
```

`\verb|[command]|` allows you to write LaTeX commands in your documents.

`\kill` allows you to create a hidden row that defines the widest entries of each column

More end-of-line commands:

- `\+` forces subsequent lines to start @ the first tab
  - `\+\+` & more, make subsequent lines start @ second tab, etc.
- `\-` cancels a preceding `\+`
- `\<` @ beginning of line, this cancels the effect of one previous `\+` command for that line

### Typesetting tables

Table with horizontal centering.

```latex
\documentclass{article}
\newcommand{\head}[1]{\textnormal{\textbf{#1}}}
\begin{document}
\begin{tabular}{ccc} % ccc means 3 centered columns
\hline % inserts a horizontal line
\head{Header 1} & \head{Header 2} & \head{Header 3}\\
\hline
entry 1 & entry 2 & entry 3\\
entry 1 & entry 2 & entry 3\\
entry 1 & entry 2 & entry 3\\
\hline
\end{tabular}
\end{document}
```

#### Tabular tables: optional arguments

The format of the tabular command is:

```latex
\begin{tabular}[position]{column specifiers}
row1col1 & row1col2 & ... & row1coln\\
\end{tabular}
```

You can position the content of each row with these optional arguments:

- `t` align at top row
- `b` align at bottom row
- default: vertically centered

#### Drawing lines in tables

- `\hline` horizontal line over the whole width of the table
- `\cline{m-n}` draws horizontal line from column m, which ends at column n
- `\vline` vertical line over full height & depth of current row

#### Understanding formatting arguments

There's even more stuff...

```latex
\begin{tabular}{|l|c|r|p{1.7cm}|}
\end{tabular}
```

Each symbol/letter defines a column, or an aspect of the column.

- `l` left alignment; `c` centered alignment; `r` right alignment;
- `p{width}` paragraph cell of a specified width
  - same as using `\parbox[t]{width}` w/i a table cell
- `@{code}` inserts code before/after a column, rather than empty space
- `|` vertical line
- `*{n}{options}` n copies of options; so let's say you wanted to define 3 columns, which you duplicate 30 times, just define it once & use n = 10

You can check out the `array` package for further formatting options.

### Increasing the row height

Use the `array` package to add padding to rows.

```latex
\documentclass{article}
\usepackage{array}
\setlength{\extrarowheight}{4pt}
\begin{document}
\begin{tabular}{@{}>{\itshape}ll!{:}l<{.}@{}} % holy... look into this another time if you ever want to know what it means
    \hline
    r1c1 & r1c2 & r1c3\\
    & r2c2 & r2c3\\
    \hline
\end{tabular}
\end{document}
```

### Beautifying tables using the `booktabs` package

```latex
% in preamble
\usepackage{booktabs}
% in document
\begin{tabular}{ccc}
    \toprule[1.5pt]
    \head{header1} & \head{header2} & \head{header3}\\
    \midrule
    r1c1           & r1c2           & r1c3\\
    \bottomrule[1.5pt]
\end{tabular}
```

Rule is just another way of saying line. Let's decode what was just typed:

- `\toprule[thickness]`: draw horiz line at top of table
- `\midrule[thickness]`: draws horizontal dividing line b/t table rows
- `\bottomrule[thickness]`: darks horiz line at bottom of table
- `\cmidrule[thickness](trim){m-n}`
  - horizontal line from column m to n;
  - trim can be `l` or `r` to trim line at right or left end, or `lr`
    - can also trim at a width: `(trim{width})`

#### Adjusting lengths

Lengths you can adjust:

- `\heavyrulewidth` top & bottom lines' thickness
- `\lightrulewidth` middle lines' thickness by `\midrule`
- `\cmidrulewidth` thickness of `\cmidrule`
- `\cmidrulekern` trimming in `\cmidrule`
- `\abovetopsep` space above top rule
- `\belowbottomsep` space below bottom rule
- `\aboverulesep` space above `midrule`, `\cmidrule`, & `\bottomrule`
- `\belowrulesep` space below `midrule`, `\cmidrule`, & `\bottomrule`

#### Spanning entries over multiple columns

Think: row merge.

```latex
\begin{tabular}{@{}*3l@{}}
    \toprule[1.5pt]
    \multicolumn{2}{c}{\head{Input}} & \multicolumn{1}{c}{\head{Output}}\\
    \head{Command} & \head{Declaration} & \\
    \cmidrule(r){1-2}\cmidrule(1){3-3}
    r1c1 & r1c2 & r1c3 \\
    \bottomrule[1.5pt]
\end{tabular}
```

At this point, it's obvious what a lot of this does, but...

```latex
\multicolumn{number of columns}{formatting options}{entry text}
```

### Inserting code column-wise

```latex
\documentclass{article}
\usepackage{array}
\usepackage{booktabs}
\newcommand{\head}[1]{\textnormal{\textbf{#1}}}
\newcommand{\normal}[1]{\multicolumn{1}{1}{#1}}

\begin{document}
\begin{tabular}{@{}1*2{>{\textbackslash\ttfamily}1}1%<{example text}@{}}
\toprule[1.5pt]
& \multicolumn{2}{c}{\head{Input}} & \multicolumn{1}{c}{\head{Output}}\\
& \normal{\head{Command}} & \normal{\head{Declaration}}
& \normal{}\\
  \cmmidrule(lr){2-3}\cmidrule(1){4-4}
  ...
```

`\>{textblashslash\ttfamily}l` says: left-align a row in typewriter font, with a backslash before the entry

### Spanning entries over multiple rows

```latex
% in preamble
\usepackage{multirow}

% in document
\multirow{3}{*}{multiple-row-spanned text} & entry & entry \\
```

Structure of the multirow command:

```latex
\multirow{number of rows}{width}{entry text}
```

### Adding captions to tables

The structure of the table command:

```latex
\begin{table}[placement options]
table body
\caption[shorter table title]{table title}
\end{table}
```

Tables are in a _floating environment_, so they don't always get printed in location in which you typed it. That's why there're placement options.

### Placing captions above

You can write captions above, and then ensure good spacing by adding this in the preamble:

```latex
\setlength{\abovetopsep}{10pt}
```

#### Customizing captions

```latex
\usepackage[font=small,labelfont=bf,margin=1cm]{caption}
```

### Auto-fitting columns to the table width

```latex
% in preamble
\usepackage{tabularx}

% in table
\begin{tabularx}{table width}{column specifiers}
...
\end{tabularx}
```

Tabularx has a new column type: `X`. They use all of the available space.

Example usage:

```latex
\begin{tabularx}{0.6\textwidth}{1cX}
```

#### Generating multi-page tables

Available packages:

- `longtable`, `ltxtable`, `ltablex`, `supertabular`, `xtab`, `stabular`

#### Coloring tables

`color` package colors text. `xcolor` is an extended version.
`colortbl` colors tables.

#### Using landscape orientation

`rotating` package has `sidewaystable` environment. The table would be landscape, on its own page.

#### Aligning columns at the decimal point

Packages: `siunitx`, `dcolumn`, `rccol`

#### Hyphenation in narrow columns

First word of a line, box, or table entry doesn't get hyphenated. You can force hyphenation with `\hspace{0pt}` at the begininng.

### Inserting pictures

`graphicx` package. PNG, JPG, EPS, PDF are supported.

```latex
% in preamble
\usepackage[demo]{graphicx}
% in document
\begin{figure}
\includegraphics{filename}
\caption{insert caption if you want}
\end{figure}
```

#### Scaling pictures

Structure of includegraphics:

```latex
\includegraphics[key=value list]{file name}
```

Possible values:

- `width`, `height`, `scale`, `angle`

### Including whole pages

Use the `pdfpages` packages and the `\includepdf` command to include pdf pages, and png and jpg files.

#### Putting images behind the text

`eso-pic` `textpos` packages.

#### Managing floating environments

There're 2 floating environments (floats): `figure` and `table`. They go wherever is optimum for the page layout.

Float placement options:

- `h` here (where it's in the source code)
- `t` top, `b` bottom
- `p` page (separate page with only floats)

You can add `!` before a placement option to tell LaTeX that that positioning is important for it to execute.

#### Forcing the output of floats

Multiple ways  of going about this:

- `\clearpage` ends current page & forces output of floats on next page
  - `\cleardoublepage` is similar
- the `afterpage` package defers the execution of `\clearpage` until the current page has ended

Example usage of afterpage:

```latex
% in the preamble
\usepackage{afterpage}

% in the document
... body text ...
\afterpage{\clearpage}
```

#### Limiting floating

`placeins` package allows you to restrict the floating, so you can set `\FloatBarrier`

```latex
\usepackage[section]{placeins} % you can set which command you want to set as the barrier; here, it is 'section'
```

#### Avoiding floating at all costs

Don't use a floating environment. Just include an image rather than a floating figure that contains the image.

```latex
% in the preamble
\usepackage{capt-of} % or caption
...
\begin{center}
\begin{minipage}{\linewidth}%
\centering%
\incluegraphics{test}%
\captionof{figure}[shortened caption]{Longer/normal caption}%
\end{minipage}
\end{center}
```

Also

```latex
\captionof{table}[short text]{longer caption}
```

Also the float package gives you the `H` optional command, which makes floats appear here

```latex
% in the preamble
\usepackage{float}

% in the document
\begin{figure}[H]
...
```

#### Spanning figures and tables over text columns

`figure*` & `table*` will place those floats into one column when you're in a 2-column layout.

#### Letting text flow around figures

```latex
% in preamble
\usepackage{wrapfig}

% in document
\begin{wrapfigure}{l}{4.4cm}
...
\end{wrapfigure}
```

Definition of wrapfigure:

```latex
\begin{wrapfigure}[number of lines]{placement}[overhang]{width}
```

Options:

- placement
  - `r` right, `l` left, `i` inner, `o` outer
  - `R`, `L`, `I`, `O` does the same, but allows figure to float

Overhang specifies a width that the figure can st ick into the margin

Can use `{wraptable}` in the same way.

#### Breaking figures and tables into pieces

Additional packages you could look into:

- `subfig` small figures & tables;
- `subcaption` is used with subfig;

## Chapter 6: Cross-Referencing

### Setting labels and referencing

Mark items (`\label`) that you can refer to (`\ref`). Example usage:

```latex
\begin{enumerate}
    \item item1 \label{item:zzzz}
\end{enumerate}

Did you see that item in position \ref{item:zzzz}?
```

#### Assuming a key

- `\label{name}` assigns current position to the key 'name'
  - in text, the current section is assigned, or the number of the numbered environment
  - can't mark a section w/i a table environment

#### Referring to a page

`\pageref{name}` works the same way as `\ref`, but prints the page number

#### Producing intelligent page references

In order to get produce messages like "on the previous page", use the `varioref` package. You can even get the range of pages that is between two referrenced items.

`cleverev` package determines the type of reference and the reference's context.

#### Referring to labels in other documents

Use package `xr` (external references)

```latex
% in the preamble
\usepackage{xr}
\externaldocument[prefix]{doc} % refers to doc.txt
```

The prefix is optional, and it means that you will prefix all of your references to that document with whatever you set the prefix to be.

#### Hyperlinking your references

Load `hyperref` before the `cleveref` package, and all your references will become hyperlinks.

## Chapter 7: Listing Content and References

### Customizing the table of contents

You can add to the table of contents within your document, like:

```latex
% in the preamble
\setcounter{tocdepth}{3} % this goes down to the subsubsections

% in the document
\tableofcontents
\chapter*{Preface}
\addcontentsline{toc}{chapter}{Preface}
...
\addtocontents{toc}{\bigskip}
\addcontentsline{toc}{part}{Appendix}
```

Other depth levels of the TOC:

- 4: paragraph
- 5: subparagraph

Counter commands:

```latex
\setcounter{name}{n} % specify the depth level you want
\addtocounter{name}{n} % add an integer value to the counter name
```

#### Shortening entries

```latex
\subsubsection[Short name]{Longer name}
```

#### Adding entries manually

Starred commands don't produce TOC entries, so you can add them manually:

```latex
\addcontentsline{file extension}{sectional unit}{text}
```

File extensions: `toc` table of contents file, `lof` list of figures file, `lot` list of tables file

Sectional units: the formatting of the entry (eg. chapter, section, part, subsection)

### Creating and customizing lists of figures

`\listoffigures` & `\listoftables`

You can change the name of the List of Figures:

```latex
% change list of figures names
\renewcommand{\figurename}{Diagram}
\renewcommand{\listfigurename}{List of Diagrams}

% change list of tables names
\renewcommand{\listoftables}{New name}
\renewcommand{\listtablename}{New name}
\renewcommand{\tablename}{new name}
```

Related packages for customization: `tocloft`, `titletoc`, `multitoc`, `minitoc`, `tocbibind`

### Generating an index

```latex
% in the preamble
\usepackage{index}
\makeindex

% within the document, make index points like this
\index{keyword}...text to be indexed...

% or
\index{keyword}

% right before end of document:
\clearpage
\addcontentsline{toc}{chapter}{Index}
\printindex
```

#### Defining index entries and subentries

Subentries: `\index{keyword!subkeyword}` can go up to 3 levels

#### Specifying page ranges

Begin the page range: `\index{keyword|(}`

End the page range: `\index{keyword|)}`

#### Using symbols and macros in the index

Define a name for your symbol before using them, so the index knows how to sort them

`\index{Gamma@$\Gamma$}` for example. You use this with macros, too, so the actual name is printed. Eg. `\index{TeX@\group}`.

#### Referring to other index entries

`\index{keyword|see{other keyword}}`

#### Designing the index layout

Use the `makeindex` command. Can also look into making your own styles.

### Creating a bibliography

```latex
% within the text cite things like
\cite[optional page range]{citation key, eg. DK01} % eg [p.\,18--20]

% at end of document
\begin{thebibliography}{8}
\bibitem{DK01} The citation goes here
\end{thebibliography}
```

Structure:

```latex
\begin{thebibliography}{widest label}
\bibitem[label]{key} author, title, year, etc.
...
\end{thebibliography}
```

#### Using bibliography databases with BibTeX

Create a separate database file (.bib) for references

eg.

```
@book{DK01,
    author = "D.E. Knuth",
    title = "Title",
    publisher = "publisher",
    year = 10
}
```

#### Understanding BibTeX entry types

Check out the BibTeX reference to see fields you must provide for references. `textdoc bibtex`

#### Choosing the bibliography style

- Styles:
  - `plain` Arabic numbers for labels; sorted according to author name; number is written in square brackets
  - `unsrt` unsorted
  - `alpha` sorted according to author names; labels are shortcuts made out of author's name & year of publication; written w/ square brackets
  - `abbrv` first name & other field entries are abbreviated

Choose style (`\bibliographystyle`) before `\bibliography`, but after `\begin{document}`

#### Listing references without citing

`\nocite{key}` & `\nocite{*}`

#### Changing the headings

These are the commands that you can renew to change the name, using a format like this:

```latex
\renewcommand{\contentsname}{New name}
```

- Table of Contents `\contentsname`
- List of figures `\listfigurename`
- List of tables `\listtablename`
- Bibliography `\bibname` in book & report
  - `\refname` in article
- Index `\indexname`

## Chapter 8: Typing Math Formulas

### Writing basic formulas

LaTeX has 3 modes: `paragraph mode`, `left-to-right mode`, `math mode`. In math mode, letters are treated as symbols and are italicized.

Examples of how you'd write formulas:

```latex
\documentclass{article}
\begin{document}
\section*{Quadratic equations}
\begin{equation} % produces a numbered equation
    \label{quad}
    ax^2 + bx + c = 0
\end{equation}
where \(a, b \) and \(c \) are consonants. % small pieces of math are surrounded by \(... \) or $expression$

\begin{equation}
    \label{root}
    x_{1,2} = \frac{-b \pm \sqrt{b^2-4ac}}{2a}.
\end{equation}

\[ % unnumbered equation
    \Delta = b^2 - 4ac
\]

Look at equation (\ref{root}).
```

Also, a math environment:

```latex
\begin{math}
expression
\end{math}
```

#### Displaying formulas

For displayed formulas that need centering:

```latex
% this
\begin{displaymath}
expression
\end{displaymath}

% or this (best choice)
\[
expression
\]

% or this
$$
expression
$$
```

#### Numbering equations

This is like displaymath, but numbered.

```latex
\begin{equation}
    \label{key}
    expression
\end{equation}
```

#### Adding subscripts and superscripts

```latex
{expression}_{subscript}

{expression}^{superscript}
```

#### Extracting roots

```latex
\sqrt[order]{value}
```

You use the order if there're roots of higher order, and you need to specify that;

#### Writing fractions

```latex
\frac{numerator}{denumerator} % for larger fractions; you can do (a+b)/2
```

#### Greek letters

Surely, there's a reference too. And if you don't want italicized Greek letters, but instead want upright Greek letters, you can use the `upgreek` package.

#### Script letters

```latex
\mathcal{UPPERCASE LETTER}
```

#### Producing an ellipsis

- centered ellipsis: `\cdot`, `\cdots`
- diagonal ellipsis: `\ddots
- vertical ellipsis: `\vdots`

#### Changing the font, style, and size

- roman `\mathrm{}`
- italic `\mathit{}`
- bold `\boldmath{}` & unbold `\unboldmath{}`
  - you can use an `\mbox` w/ a bold command inside if you want to bold a small part of the text
- sans-serif `\mathsf{}`
- normal `\mathnormal{}`

#### Four math styles

- display `\displaystyle` (default for displayed formulas)
- text `\textstyle` (default for in-text formulas)
- script `\scriptstyle` (subscripts & superscripts)
- scriptscript `\scriptscriptstyle` (nested script style)

#### Customizing displayed formulas

- `\fleqn` displayed formulas are left-aligned
- `\leqno` all numbered formulas have their numbers on the left instead of the right

#### Define multiple equations at once

This'll define one numbered equation.

```latex
\begin{multiline}
\sum = a + b + c \\
    + d + e + f \\
    + g
\end{multiline}
```

First line is left-aligned, last line is right-aligned, and the rest are centered.

There'll be 2 numbered equations here:

```latex
\begin{gather} % equations are centered
    x + y + z = 0 \\
    y - z = 1
\end{gather}

% equations are aligned as you specify with an &
\begin{align}
    x + y + z &= 0 \\
    y - z &= 1
\end{align}
```

#### Aligning multi-line equations

- `flalign`: like align, but w/ more than 1 column, so columns are flushed left or right, respectively
- `alignat`: align at several places, marked by `&`
- `split`: like align, but used w/i another math environment, so it's unnumbered

#### Numbering rows in multi-line formulas

Each line is numbered as an equation in multi-line environments. You can stop this by pputting `\notag` before the end of the line.

Use `align*` or `gather*`, et al. to avoid numbering completely

#### Inserting text into formulas

- `\text{words}`
- `\intertext{text}` suspends the formula & the text follows in a separate paragraph, and then the multi-line formula is resumed.

#### Using operators

Predefined operators:

- `\arccos`, `\arcsin`, `\arctan`, `\arg`, `\cos`, `\cosh`
- `\cot`, `\coth`, `\scs`, `\deg`, `\det`, `\dim`
- `\exp`, `\gcd`, `\hom`, `\inf`, `\ker`, `\lg`
- `\lim`, `\liminf`, `\limsup`, `\ln`, `\log`, `\max`
- `\min`, `\Pr`, `\sec`, `\sin`, `\sinh`, `\sup`, `\tan`, `\tanh`
- modulo functions: `\bmod` for binary relations, or `\pmod{argument}` for modulos in parentheses

#### Binary operation symbols

Most relevant ones as of right now:

- `\cap`, `\cup`, `\vee`, `\wedge`
- `\div`, `\times`
- `\odot`, `\ominus`, `\oplus`, `\oslash`, `\otimes`
- `\pm`

#### Binary relation symbols

Most relevant ones as of right now:

- `\approx`, `\equiv`
- `\parallel`, `\perp`

#### Inequality relation symbols

- greater than or equal to `\geq`; `\leq`
- not equal to `\neq`

#### Subset and superset symbols

- `\subset`, `\subseteq` (has a line underneath)
- `\supset`, `\supseteq`

#### Variable sized operators

Used in sums, products, and set operations.

- `\bigcap`, `\bigcup`, `\bigvee`, `\bigwedge`
- `\prod`, `\sum`
- `\int`
- `\bigodot`, `\bigoplus`, `\bigotimes`

#### Arrows

Used for implications, maps, or descriptive expressions.

- `\downarrow`, `\Downarrow` (Arrow w/ parallel sides), `\uparrow`, `\Uparrow`
- `\updownarrow`, `\Updownarrow`
- `\leftarrow`, `\Leftarrow`, `\rightarrow`, `\Rightarrow`
- `\leftrightarrow`, `\Leftrightarrow`
- `\longleftarrow`, `\Longleftarrow`, `\longrightarrow`, `\Longrightarrow`
- `\longleftrightarrow`, `\Longleftrightarrow`
- `\mapsto`, `\Longmapsto`

#### Symbols derived from letters

- `\exists`, `\forall`, `\in` (element of), `\ni` (element of, mirrored)

#### Variable sized delimiters

Errr... you can just look them up.

#### Writing units

There've been packages made to make it easier to write units that look like units: `units`, `fancyunits`, `siunits`, `siunitx`.

Without those packages, you'll end up having to type `10\, \mathrm{m}` when you want to say 10m.

### Building math structures

#### Creating arrays

Array with round brackets:

```latex
\begin{math}
\[
    A = \left(
       \begin{array}{cc}
        a_{11} & a_{12} \\
        a_{21} & a_{22}
        \end{array}
      \right)
\]
\end{math}
```

You can create matrices easier with packages like `amsmath`, which typeset your matrices for you.

#### Writing binomial coefficients

```latex
\binom{n}{k} = \frac{n!}{k!(n-k)!}
```

### Stacking expressions

#### Underlining and overlining

- `\overline{argument}`
- `\underline{argument}`
- `\overbrace{argument}_thingthatgoesover`
- `\underbrace{argument}_thingthatgoesunder`

#### Setting accents

Add accents to variables with accent commands.

- `\bar{letter}`, `\vec{a}`, `\hat{a}`, `\widehat{abc}`

#### Putting a symbol above another

- `\underset{expressionbelow}{expression}` subscript size used below
- `\overset{expressionabove}{expression}` superscript size used above

#### Writing theorems and definitions

Defining a theorem environment:

```latex
\newtheorem{thm}{Theorem}
```

Declaring a definition environment:

```latex
\newtheorem{dfn}[thm]{Definition}
```

The optional argument is for stating an existing environment to share the numbering with. The package `amsthm` provides more customization & a proof environment.

## Chapter 9: Using Fonts

### Choosing the main font

For particular phrases:

```latex
\documentclass{article}
\newcommand{\commandhere}[1][\rmfamily]{{#1 The phrase here}\par}
\usepackage[T1]{fontenc}
\begin{document}
\large
\commandhere
\commandhere[\sffamily]
\commandhere[\ttffamily]
\end{document}
```

Basically, there are tons of fonts you could use. You can [look them up](http://tug.dk/FontCatalogue/).

## Chapter 10: Developing Large Documents

### Splitting the input

Can break a document down into subdocuments.

Eg. have a document with everything that the preamble would include, save it as `preamble.tex`, and then do something like this:

```latex
\documentclass{book}
\input{preamble} % the file is read as if it were typed in here
\begin{document}
\include{anotherfile} % \clearpage will automatically be inserted after this statement
\include{anotherfile2}
\end{document}
```

You can use `\includeonly{list, of, file, names}`

### Frontmatter, mainmatter, and backmatter. 

- `\frontmatter` pages numbered w/ lowercase Roman numbers
  - calls `\cleardoublepage`
- `\mainmatter` pages numbered w/ Arabic numbers
  - calls `\cleardoublepage`
- `\backmatter` same as main, also, just like frontmatter, chapters generate table of contents entries without numbers
  - calls `\clearpage`

```latex
...
\begin{document}
\frontmatter
\tableofcontents
\listoftables
\listoffigures
\mainmatter
...
\backmatter
\include{proofs}
\bibliographystyle{plainnat}
\bibliography{tex}
\enddocument
```

### Working with templates

Can checkout published ones [here](http://texblog.net/latex/templates).

## Chapter 11: Enhancing Your Documents Further

### Creating hyperlinks manually

- `\href{URL}{text}`
- `\url{URL}`
- `\nolinkurl{URL}`
- `\hyperref{label}{text}` links to a place where the label was set, so same place as where `\ref{label}` points

### Creating bookmarks manually

- `\currentpdfbookmark{text}{name}` puts bookmark @ current level
- `\belowpdfbookmark{text}{name}` puts bookmark @ one level below
- `\subpdfbookmark{text}{name}` increases lvl & creates a bookmark @ that deeper lvl

