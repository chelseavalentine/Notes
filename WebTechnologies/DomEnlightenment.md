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
  - `firstChild`, `lastChild`
  - `nextSibling`, `previousSibling`
  - `nodeName`
  - `nodeType`
  - `nodeValue`
  - `parentNode`

* `Node` methods
  - `appendChild()`
  - `cloneNode()`
  - `compareDocumentPosition()`
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
  - `innerText`, `outerText`
    + is aware of style, so doesn't return content of hidden elements, whereas `textContent` does
  - `firstElementChild`, `lastElementChild`, `nextElementChild`, `previousElementChild`
  - `children`

* `HTMLElement` methods
  - `insertAdjacentHTML()`
    + options `beforebegin` and `afterbegin` only work for elements in the DOM with a parent
    + there's also `insertAdjacentElement()` and `insertAdjacentText()`, but Firefox doesn't support it
