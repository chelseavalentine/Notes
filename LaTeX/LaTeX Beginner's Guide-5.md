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

189