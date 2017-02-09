# DOM Enlightenment

### Chapter 5: Element node & scrolling geometry

* element position relative to the `offsetParent`
  - `offsetParent`: nearest ancestor element w/ a CSS position != `static`
    + exceptions: `td`, `th`, `table` with `static` can be used
  - `offsetTop`
  - `offsetLeft`

* element position relative to the viewport
  - `getBoundingClientRect()`: position of elements outside border edges, rleating to the top and lfet edge of the viewport
    + eg. from outside the border edge of an element, to the left edge of the viewport
    + also returns `height`, and `width` which include border + padding + content

* element position size
  - `offsetHeight` and `offsetWidth` include the border + padding + content
  - `clientWidth` and `clientHeight` only include the padding + content

* `elementFromPoint()` gets topmost element at a certain point

* size of the node being scrolled
  - `scrollHeight`
  - `scrollWidth`
  - eg. if you do `div.scrollWidth`, it'll give the measurements of the node within being scrolled

* pixels scrolled from the top and left that aren't currently visible in the scrollable viewport
  - `scrollTop`
  - `scrollLeft`

* `scrollIntoView()`
  - `true` (default behavior) scrolls it to the top of the element
  - `false` aligns the scroll to the bottom of the element


### Chapter 6: Element node inline styles

* you can modify an element's `style` via methods instead
  - `setPropertyValue()`
  - `getPropertyValue()`
  - `removePropertyValue()`

* window-level `getComputedStyle()`
  - color values are in rgb() form regardless


### Chapter 7: Text nodes

* whitespace and text characters create `Text` nodes
* there's a `Text` constructor

* text-related properties and methods
  - `splitText()`
    + split a Text node at a particular point
  - `appendData()`, `insertData()`
  - `deleteData()`, `replaceData()`
    + uses indices you want to replace/delete, and the text you want to replace it with if applicable
  - `subStringData()`
  - `normalize()`
    + `normalize` can be used on an element to combine adjacent Text nodes
  - `data`
    + gives you the value of a Text node, as does `nodeValue`

* `innerText` vs `textContent`
  - `innerText` normalizes the returned text, whereas `textContent` returns exactly what's in the document w/ the removed markup, which may include carriage returns, etc.
  - `innerText` is browser-specific

