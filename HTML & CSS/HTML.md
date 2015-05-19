## HTML

### General

* URL = Uniform Resource Locator

### Forms

```HTML

```


### Tables
A table is written out by row.


* `thead` table header: holder of the table's headings
* `th` table heading
* `tbody` table's body: holds all of the rows w/ info
* `tr` table row
* `td` each cell of a table (many used w/i a `tr`)
* * `colspan` td attribute to indicate how many columns the cell spans
* `rowspan` td attribute to indicate how many rows the cell spans
* `tfoot` table footer

```HTML
<table>
  <thead>
    <tr>
      <th></th>
      <th scope="col">Odd</th>
      <th scope="col">Even</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">First</th>
      <td colspan="2">Item 1 & 2</td>
    </tr>
    <tr>
      <th scope="row">Second</th>
      <td>Item 3</td>
      <td>Item 4</td>
    </tr>
    <tr>
      <th scope="row" rowspan="2"></tr>
      <td rowspan="2">Item 5 <br/> Item 7</td>
      <td rowspan="2">Item 5 <br/> Item 8</td>
    </tr>
  </tbody>
  <tfoot>
    <tr>
      <td rowspan="3">This is the footer.</td>
    </tr>
  </tfoot>
</table>
```

#### Some things you don't (need to) use often

##### Subscript & superscript
```HTML
5<sup>th</sup>
```

```HTML
H<sub>2</sub>
```

##### Quotes
```HTML
Foo said, <q cite="http://bar.bar">Yo, wut up.</q>
```

##### Acronyms & abbreviations
```HTML
<acronym title="National Aeronautics and Space Administration">NASA</acroynm>
```

```HTML
<abbr title="Professor">Prof</abbr>
```

##### Cite & define
Use cite to cite things like books. Texts. The inner HTML will appear italicized.
```HTML
<cite>HTML & CSS Book</cite> by Duckett
```

Use cite to introduce new terms (eg. scientific). Some browsers italicize them.
```HTML
A <dfn>duck</dfn> is a duck.
```

##### Address
Display emails, location addresses, etc.
```HTML
<address>
  <a href="mailto:email@email.email">Email owner</a><br>
  <p>79 Their address; City, State 51PC0D3.</p>
</address>
```

##### Edits: insertions, deletions, etc.
Show what was inserted & what was deleted.
```HTML
<p>You are a <del>cat</del> <ins>duck</ins>.</p>
```

Show that something is no longer accurate/relevant, but shouldn't be deleted
```HTML
<p><s>You're cool</s></p>
<p>Uh... what do I put here?</p>
```

##### Definition lists
* `dt` definition term
* `dd` definition definition lool.

```HTML
<dl>
  <dt>Duck</dt>
  <dd>An animal that is a duck.</dd>
  
  <dt>Elephant</dt>
  <dd>An animal that is an elephant.</dd>
</dl>
```

##### Img attributes
* `alt` description of what's in the image
* `title` info shown when hovering over image

##### Figures & figure captions (Images w/ subtitles)
```HTML
<figure>
  <img src="duckie.svg" alt="a duckie" title="duckie">
  <br />
  <img src="anotherduckie.svg" alt="another duckie" title="duckie2">
  <br />
  <figcaption>Fig tree. Duckie.</figcaption>
</figure>
```



##### E
##### E
##### E
##### E
##### E
##### E
##### E
##### E
##### E
##### E
##### E
##### E
##### E
##### E
##### E
##### E
##### E
##### E
