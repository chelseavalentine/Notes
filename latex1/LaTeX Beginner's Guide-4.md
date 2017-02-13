# Chapter 5: Creating Tables and Inserting Pictures

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

* `\+` forces subsequent lines to start @ the first tab
    - `\+\+` & more, make subsequent lines start @ second tab, etc.
* `\-` cancels a preceding `\+`
* `\<` @ beginning of line, this cancels the effect of one previous `\+` command for that line


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

* `t` align at top row
* `b` align at bottom row
* default: vertically centered


#### Drawing lines in tables

* `\hline` horizontal line over the whole width of the table
* `\cline{m-n}` draws horizontal line from column m, which ends at column n
* `\vline` vertical line over full height & depth of current row


#### Understanding formatting arguments

There's even more stuff...

```latex
\begin{tabular}{|l|c|r|p{1.7cm}|}
\end{tabular}
```

Each symbol/letter defines a column, or an aspect of the column.

* `l` left alignment; `c` centered alignment; `r` right alignment;
* `p{width}` paragraph cell of a specified width
    - same as using `\parbox[t]{width}` w/i a table cell
* `@{code}` inserts code before/after a column, rather than empty space
* `|` vertical line
* `*{n}{options}` n copies of options; so let's say you wanted to define 3 columns, which you duplicate 30 times, just define it once & use n = 10

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

* `\toprule[thickness]`: draw horiz line at top of table
* `\midrule[thickness]`: draws horizontal dividing line b/t table rows
* `\bottomrule[thickness]`: darks horiz line at bottom of table
* `\cmidrule[thickness](trim){m-n}`
    - horizontal line from column m to n;
    - trim can be `l` or `r` to trim line at right or left end, or `lr`
        + can also trim at a width: `(trim{width})`


#### Adjusting lengths

Lengths you can adjust:

* `\heavyrulewidth` top & bottom lines' thickness
* `\lightrulewidth` middle lines' thickness by `\midrule`
* `\cmidrulewidth` thickness of `\cmidrule`
* `\cmidrulekern` trimming in `\cmidrule`
* `\abovetopsep` space above top rule
* `\belowbottomsep` space below bottom rule
* `\aboverulesep` space above `midrule`, `\cmidrule`, & `\bottomrule`
* `\belowrulesep` space below `midrule`, `\cmidrule`, & `\bottomrule`


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

* `longtable`, `ltxtable`, `ltablex`, `supertabular`, `xtab`, `stabular`


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

* `width`, `height`, `scale`, `angle`


### Including whole pages

Use the `pdfpages` packages and the `\includepdf` command to include pdf pages, and png and jpg files.


#### Putting images behind the text

`eso-pic` `textpos` packages.


#### Managing floating environments

There're 2 floating environments (floats): `figure` and `table`. They go wherever is optimum for the page layout.

Float placement options:

* `h` here (where it's in the source code)
* `t` top, `b` bottom
* `p` page (separate page with only floats)

You can add `!` before a placement option to tell LaTeX that that positioning is important for it to execute.


#### Forcing the output of floats

Multiple ways  of going about this:

* `\clearpage` ends current page & forces output of floats on next page
    - `\cleardoublepage` is similar
* the `afterpage` package defers the execution of `\clearpage` until the current page has ended

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

* placement
    - `r` right, `l` left, `i` inner, `o` outer
    - `R`, `L`, `I`, `O` does the same, but allows figure to float

Overhang specifies a width that the figure can st ick into the margin

Can use `{wraptable}` in the same way.


#### Breaking figures and tables into pieces

Additional packages you could look into:

* `subfig` small figures & tables;
* `subcaption` is used with subfig;
