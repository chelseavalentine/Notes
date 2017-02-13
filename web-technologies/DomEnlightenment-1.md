# DOM Enlightenment

### Chapter 1: Node Overview

* main `Node` object types
  - `DOCUMENT_NODE` (eg. `window.document`) [value: `9`]
  - `ELEMENT_NODE` (eg. html tags like `<body>`) [value: `1`]
  - _(deprecated)_ `ATTRIBUTE_NODE` (eg. `class="track"`)
  - `TEXT_NODE` (text characters including carriage returns and whitespace) [value: `3`]
  - `DOCUMENT_FRAGMENT_NODE` (eg. `document.createDocumentFragment()) [value: `11`]
  - `DOCUMENT_TYPE_NODE` (eg. `<!DOCTYPE html>`) [value: `10`]

* `Node` properties
  - `childNodes`
    + only gives you the immediate child nodes
    + can contain `Element`, `Text`, and `Comment` nodes
    + returns a `NodeList`, which is a live list (tied to the live DOM)
      - need to create an array with the nodes if want a nonchanging list
  - `firstChild`, `lastChild`
  - `nextSibling`, `previousSibling`
  - `nodeName`
  - `nodeType`
  - `nodeValue`
  - `parentNode`

* `Node` methods
  - `appendChild()`
  - `cloneNode()`
    + passing it `true` will clone the node and all of its child nodes, otherwise it only clones the node without its children
    + only the attributes and values are cloned, not the event handlers
  - `compareDocumentPosition()`
    + returns `0` when elements are identical
    + returns `1` when aren't in the same document
    + returns `2` when passed-in node precedes selected node
    + returns `3` when passed-in node follows selected node
    + returns `8` when passed-in node is selected node's ancestor
    + returns `16`, `10` when passed-in node is selected node's descendant
  - `contains()`
  - `hasChildNodes()`
  - `insertBefore()`
  - `isEqualNode()`
  - `removeChild()`
  - `replaceChild()`

* `document` methods
  - `document.createElement()`
  - `document.createTextNode()`

* `HTML*Element` properties
  - `innerHTML`, `outerHTML`
    + invokes a heavy and expensive HTML parser, whereas `TextNode` generation is trivial
  - `textContent`
    + returns all text, even if it's in child element nodes, like `innerText`
  - `innerText`, `outerText`
    + is aware of style, so doesn't return content of hidden elements, whereas `textContent` does
  - `firstElementChild`, `lastElementChild`, `nextElementChild`, `previousElementChild`
  - `children`

* `HTMLElement` methods
  - `insertAdjacentHTML()`
    + options `beforebegin` and `afterbegin` only work for elements in the DOM with a parent
    + there's also `insertAdjacentElement()` and `insertAdjacentText()`, but Firefox doesn't support it


### Chapter 2: Document Nodes

* `window.document` is the starting point for the DOM interface

* noteworthy `document` properties and methods
  - `activeElement`, `hasFocus()`
  - `body`, `head`, `title`
  - `lastModified`
  - `referrer`
  - `URL`
  - `defaultView`
    + tells you the default JS global object, eg. `this` refers to `window` in global scope

* you can also use `ownerDocument` on nodes to get its `Document` reference


### Chapter 3: Element Nodes

* elements are created from unique JS interfaces/constructors, some examples:
  - `HTMLLinkELement`, `HTMLStyleElement`, `HTMLAnchorElement`, `HTMLButtonElement`

* noteworthy `Element` properties and methods
  - `getAttribute()`, `setAttribute()`, `hasAttribute()`, `removeAttribute()`
  - `classList()`
    + `add()`, `remove()`, `toggle()`, `contains()`
  - `className`
  - `dataset`
  - `attributes`


### Chapter 4: Element node selecting

* selecting a node
  - `querySelector()`
  - `getElementById()`
  - `querySelectorAll()`
    + doesn't return a live list of elements; it's static
  - `getElementsByTagName()`, `getElementsByClassName()`
    + returns a live list of elements
  - `getElementsByName()`

* preconfigured (live) lists of element nodes
  - `document.all`
  - `document.forms`
  - `document.images`
  - `document.links`
  - `document.scripts`
  - `document.styleSheets` (all `link` and `style` objects)

* check whether a node matches a selector with `.matches()`
