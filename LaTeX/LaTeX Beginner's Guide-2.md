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


#### Designing headers and footers

# Chapter 4: Creating Lists

