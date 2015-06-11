# Chapter 3: Designing Pages

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

* `paperwidth=value`, `paperheight=value`
* `papersize={width, height}`
* `landscape`, `portrait`


#### Specifying the text area

Can also pass these into geometry:

* `textwidth=value`, `textheight=value`
* `lines=value` specifies text height by a number of lines
* `includehead` makes page header be included into body area (is usually false)
* `includefoot` ^ same but for footer


#### Setting the margins

* `left`, `right`, `inner`, `outer`, `top`, `bottom`, `bindingoffset` all take values
* `twoside` makes it so left & right margins are swapped on left-hand pages

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

* `landscape`, `onecolumn`, `twocolumn`
* `oneside`, `twoside`
* `openright`, `openany` decides which side of the book a chapter begins on
* `article`, `book`, `report`, `slides`, `letter`
* `titlepage`, `nottitlepage`
* `final`, `draft`
    - if `draft`, then LaTeX indicates overfull lines
* `openbib` bibliography formatted in open style, rather than compressed style
* `fleqn`: formulas are left-aligned
* `leqno`: numbered displayed formulas have their numbers on the left side, instead of the right side


#### Creating a table of contents

`\tableofcontents`

Sectioning commands for `book`, `report`, and `article`:

* `\part` divides document into major units
* `\chapter`
* `\section`, `\subsection`, `\subsubsection`
* `\paragraph`, `subparagraph`

Use the starred form (`\section*{title}`) to make it so that the numbering will be suppressed and it won't show up in table of contents


#### Designing headers and footers (88)

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

* page styles:
    - `empty`: no header/footer
    - `plain`: no header; centered page number in the footer
    - `headings`: header has chapter titles, & sections/subsections' titles. Empty footer
    - `myheadings`: heading has user-defined text & the page number. Empty footer
    - `fancy`: customizable header & footer
        + enabled by `fancyhdr` package

Changing the page style:

* `\pagestyle{name}` switch from this point onward
* `\thispagestyle{name}` only change style for current page


### Customizing header and footer

Footer & header locations:

* Header: `\lhead`, `\chead`, `\rhead`
* Footer: `\lfoot`, `\cfoot`, `\rfoot`

The structure for an alternative method is: `\fancyhead[code]{text}` and `\fancyfoot[code]{text}`

Where (case-insensitive) code can be any of the following:

* `L` left, `C` center, `R` right, `E` even page, `O` odd page, `H` header, `F`, footer


### Using decorative lines in header or footer

`\renewcommand{\headrulewidth}{width}`
`\renewcommand{\footrulewidth}{width}`


### Changing LaTeX's header marks

* `\markright{right head}` sets the right heading
* `\markboth{left head}{right head}` sets the left & right heading

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

* `\footnote[number]{text}` footmark marked by a number that you choose
* `\footnotemark[number]` makes superscripted number in the text as the footnote mark; no generated footnote
* `\footnotemark[number]`  generates footnote w/o putting footnote mark into text & doesn't increase the internal footnote counter


### Modifying the dividing line

Redefine what the line the borders the footnotes looks like. In the preamble:

```latex
\renewcommand{\footnoterule}
    {\noindent\smash{\rule[3pt]{\textwidth}{0.4pt}}}

% or omit the line completely
\renewcommand{\footnoterule}{}
```

* `\smash` gives the line a height and depth of 0, so it occupies no vertical space


#### Related footnote packages

* `endnotes`: footnotes at end of document
* `manyfoot`: nested footnotes
* `bigfoot`: replaces & extends manyfoot; improves page break handling w/ footnotes
* `savefnmark`: easier to use footnotes several times
* `footmisc`: a lot of visual customization
