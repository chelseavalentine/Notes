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
