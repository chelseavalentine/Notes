# DOM Enlightenment

### Chapter 1: Node Overview

- main `Node` object types
  - `DOCUMENT_NODE` (eg. `window.document`) [value: `9`]
  - `ELEMENT_NODE` (eg. html tags like `<body>`) [value: `1`]
  - _(deprecated)_ `ATTRIBUTE_NODE` (eg. `class="track"`)
  - `TEXT_NODE` (text characters including carriage returns and whitespace) [value: `3`]
  - `DOCUMENT_FRAGMENT_NODE` (eg. `document.createDocumentFragment()) [value: `11`]
  - `DOCUMENT_TYPE_NODE` (eg. `<!DOCTYPE html>`) [value: `10`]
- `Node` properties
  - `childNodes`
    - only gives you the immediate child nodes
    - can contain `Element`, `Text`, and `Comment` nodes
    - returns a `NodeList`, which is a live list (tied to the live DOM)
      - need to create an array with the nodes if want a nonchanging list
  - `firstChild`, `lastChild`
  - `nextSibling`, `previousSibling`
  - `nodeName`
  - `nodeType`
  - `nodeValue`
  - `parentNode`
- `Node` methods
  - `appendChild()`
  - `cloneNode()`
    - passing it `true` will clone the node and all of its child nodes, otherwise it only clones the node without its children
    - only the attributes and values are cloned, not the event handlers
  - `compareDocumentPosition()`
    - returns `0` when elements are identical
    - returns `1` when aren't in the same document
    - returns `2` when passed-in node precedes selected node
    - returns `3` when passed-in node follows selected node
    - returns `8` when passed-in node is selected node's ancestor
    - returns `16`, `10` when passed-in node is selected node's descendant
  - `contains()`
  - `hasChildNodes()`
  - `insertBefore()`
  - `isEqualNode()`
  - `removeChild()`
  - `replaceChild()`
- `document` methods
  - `document.createElement()`
  - `document.createTextNode()`
- `HTML*Element` properties
  - `innerHTML`, `outerHTML`
    - invokes a heavy and expensive HTML parser, whereas `TextNode` generation is trivial
  - `textContent`
    - returns all text, even if it's in child element nodes, like `innerText`
  - `innerText`, `outerText`
    - is aware of style, so doesn't return content of hidden elements, whereas `textContent` does
  - `firstElementChild`, `lastElementChild`, `nextElementChild`, `previousElementChild`
  - `children`
- `HTMLElement` methods
  - `insertAdjacentHTML()`
    - options `beforebegin` and `afterbegin` only work for elements in the DOM with a parent
    - there's also `insertAdjacentElement()` and `insertAdjacentText()`, but Firefox doesn't support it

### Chapter 2: Document Nodes

- `window.document` is the starting point for the DOM interface
- noteworthy `document` properties and methods
  - `activeElement`, `hasFocus()`
  - `body`, `head`, `title`
  - `lastModified`
  - `referrer`
  - `URL`
  - `defaultView`
    - tells you the default JS global object, eg. `this` refers to `window` in global scope
- you can also use `ownerDocument` on nodes to get its `Document` reference

### Chapter 3: Element Nodes

- elements are created from unique JS interfaces/constructors, some examples:
  - `HTMLLinkELement`, `HTMLStyleElement`, `HTMLAnchorElement`, `HTMLButtonElement`
- noteworthy `Element` properties and methods
  - `getAttribute()`, `setAttribute()`, `hasAttribute()`, `removeAttribute()`
  - `classList()`
    - `add()`, `remove()`, `toggle()`, `contains()`
  - `className`
  - `dataset`
  - `attributes`

### Chapter 4: Element node selecting

- selecting a node
  - `querySelector()`
  - `getElementById()`
  - `querySelectorAll()`
    - doesn't return a live list of elements; it's static
  - `getElementsByTagName()`, `getElementsByClassName()`
    - returns a live list of elements
  - `getElementsByName()`
- preconfigured (live) lists of element nodes
  - `document.all`
  - `document.forms`
  - `document.images`
  - `document.links`
  - `document.scripts`
  - `document.styleSheets` (all `link` and `style` objects)
- check whether a node matches a selector with `.matches()`

### Chapter 5: Element node & scrolling geometry

- element position relative to the `offsetParent`
  - `offsetParent`: nearest ancestor element w/ a CSS position != `static`
    - exceptions: `td`, `th`, `table` with `static` can be used
  - `offsetTop`
  - `offsetLeft`
- element position relative to the viewport
  - `getBoundingClientRect()`: position of elements outside border edges, rleating to the top and lfet edge of the viewport
    - eg. from outside the border edge of an element, to the left edge of the viewport
    - also returns `height`, and `width` which include border + padding + content
- element position size
  - `offsetHeight` and `offsetWidth` include the border + padding + content
  - `clientWidth` and `clientHeight` only include the padding + content
- `elementFromPoint()` gets topmost element at a certain point
- size of the node being scrolled
  - `scrollHeight`
  - `scrollWidth`
  - eg. if you do `div.scrollWidth`, it'll give the measurements of the node within being scrolled
- pixels scrolled from the top and left that aren't currently visible in the scrollable viewport
  - `scrollTop`
  - `scrollLeft`
- `scrollIntoView()`
  - `true` (default behavior) scrolls it to the top of the element
  - `false` aligns the scroll to the bottom of the element

### Chapter 6: Element node inline styles

- you can modify an element's `style` via methods instead
  - `setPropertyValue()`
  - `getPropertyValue()`
  - `removePropertyValue()`
- window-level `getComputedStyle()`
  - color values are in rgb() form regardless

### Chapter 7: Text nodes

- whitespace and text characters create `Text` nodes
- there's a `Text` constructor
- text-related properties and methods
  - `splitText()`
    - split a Text node at a particular point
  - `appendData()`, `insertData()`
  - `deleteData()`, `replaceData()`
    - uses indices you want to replace/delete, and the text you want to replace it with if applicable
  - `subStringData()`
  - `normalize()`
    - `normalize` can be used on an element to combine adjacent Text nodes
  - `data`
    - gives you the value of a Text node, as does `nodeValue`
- `innerText` vs `textContent`
  - `innerText` normalizes the returned text, whereas `textContent` returns exactly what's in the document w/ the removed markup, which may include carriage returns, etc.
  - `innerText` is browser-specific

### Chapter 8: `DocumentFragment` nodes

- `DocumentFragment` is a lightweight DOM only living in memory, that can be easily manipulated in memory and then appended to the live DOM
  - when you add it to the DOM, only its contents are added, not the fragment itself
  - when appended to the DOM, it's no longer in memory in the place you created it
    - if you want to keep them in memory, use `cloneNode(true)` on the fragment
  - can't contain `body` or `html` nodes

### Chapter 9: CSS style sheets and rules

- you can use the `sheet` property on `<style>` or `<link>` to access the `CSSStyleSheet` object
- `CSSStyleSheet` properties and methods
  - `disabled`
  - `href`, `title`, `type`
  - `media`
  - `ownerNode`
  - `parentStylesheet`
  - `cssRules`
  - `ownerRule`
  - `deleteRule`, `insertRule`

### Chapter 11: DOM events

- user interface events
  - `load`, `unload`
  - `abort`
  - `error`
  - `resize`
  - `scroll`
  - `contextmenu` (fires by right-clicking an element)
- focus events
  - `blur`
  - `focus` (when element receives focus)
  - `focusin`, `focusout`
- form events
  - `change`
  - `reset`
  - `submit`
  - `select` (upon selecting text in a text field)
- mouse events
  - `click`, `dblclick`
  - `mousedown`, `mouseup`
  - `mouseenter`
  - `mouseleave`, `mouseout`
    - `mouseleave` doesn't bubble, it deals with moving off an element and _all_ of its descendant elements
    - `mouseout` bubbles & fires when it moves from an element onto the boundaries of another one of its descendant elements
  - `mousemove`
  - `mouseover`
  - `mousewheel`
- keyboard events
  - `keydown` (on initial press, for any key even if doesn't produce a character)
  - `keyup`
  - `keypress` (when a key is initally pressed, iff it produces a character value)
- touch events
  - `touchstart`, `touchend`
  - `touchmove`
  - `touchenter`: touch point moves into interactive area defined by a DOM element
  - `touchleave`
  - `touchcancel`
- `window`-, `body`-, and frame-specific events
  - `afterprint`, `beforeprint`
  - `beforeunload`
  - `hashchange`
  - `message`
  - `offline`, `online`
  - `pagehide`, `pageshow`
- `document`-specific events
  - `readystatechange`
  - `DOMContentLoaded`
- drag events
  - `drag`
  - `dragstart`, `dragend`
  - `dragenter`, `dragleave`, `dragover` deal with valid drop targets
  - `drop`
- when an event invokes, it flows/propagates through the DOM, via first the capture phase and then the target phase
  - capture phase: starting from the `window` -> `document` -> `html` -> `body` -> ... -> target
  - target phase: event fires on the darget, and then propogates up to the `window`
  - can prevent this event flow with `stopPropagation()`, but it won't stop default events, nor other events
  - `stopImmediatePropagation()` can stop the event flow and other like events on the target
    - also doesn't prevent default events
- `addEventListener()`
- `removeEventListener()`
  - only removes non-anonymous functions
- `preventDefault()`
  - returning `false` at the end of an event listener has the same effect as `preventDefault`
- custom events
  - `createEvent()`
  - `initCustomEvent()`
  - `dispatchEvent()`
- event delegation allows new content added to the DOM to get the same event, because you're adding it to the parent rather than each individual one
  - requires checking what the `event.target` is within the function body