# Chapter 4: Creating Lists

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

* With values
    - `font`; any font command
    - `label`; can use text styling + `\arabic*`, `\alph*`, `\Alph*`, `\roman*`, `\Roman*`
        + or use `(i)`, `(1)`, `(A)`, `(a)`, `(I)`
    - `label*`; same as above, but appended to current label
    - `align`; `left` or `right`
* Without values
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

* `paralist` has `compactdesc`, `inparadesc`, & `asparadesc`

* `enumitem` has global command `\setdescription{format}`

* `layouts` allows you to adjust a list's dimensions



# Chapter 5: Creating Tables and Inserting Pictures (121)

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


# Chapter 6: Cross-Referencing (153)

# Chapter 7: Listing Content and References (165)

# Chapter 8: Typing Math Formulas (189)

# Chapter 9: Using Fonts (213)

# Chapter 10: Developing Large Documents (227)

# Chapter 11: Enhancing Your Documents Further (243)

# Chapter 12: Troubleshooting (265)
