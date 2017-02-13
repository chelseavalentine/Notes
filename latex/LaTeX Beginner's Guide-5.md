# Chapter 6: Cross-Referencing

### Setting labels and referencing

Mark items (`\label`) that you can refer to (`\ref`). Example usage:

```latex
\begin{enumerate}
    \item item1 \label{item:zzzz}
\end{enumerate}

Did you see that item in position \ref{item:zzzz}?
```


#### Assuming a key

* `\label{name}` assigns current position to the key 'name'
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


# Chapter 7: Listing Content and References

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
* 4: paragraph
* 5: subparagraph

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

* Styles:
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

* Table of Contents `\contentsname`
* List of figures `\listfigurename`
* List of tables `\listtablename`
* Bibliography `\bibname` in book & report
    - `\refname` in article
* Index `\indexname`
