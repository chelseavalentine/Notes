# Chapter 10: Developing Large Documents

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

* `\frontmatter` pages numbered w/ lowercase Roman numbers
    - calls `\cleardoublepage`
* `\mainmatter` pages numbered w/ Arabic numbers
    - calls `\cleardoublepage`
* `\backmatter` same as main, also, just like frontmatter, chapters generate table of contents entries without numbers
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



# Chapter 11: Enhancing Your Documents Further

### Creating hyperlinks manually

* `\href{URL}{text}`
* `\url{URL}`
* `\nolinkurl{URL}`
* `\hyperref{label}{text}` links to a place where the label was set, so same place as where `\ref{label}` points

### Creating bookmarks manually

* `\currentpdfbookmark{text}{name}` puts bookmark @ current level
* `\belowpdfbookmark{text}{name}` puts bookmark @ one level below
* `\subpdfbookmark{text}{name}` increases lvl & creates a bookmark @ that deeper lvl
