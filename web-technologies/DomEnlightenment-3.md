# DOM Enlightenment

### Chapter 11: DOM events

* user interface events
  - `load`, `unload`
  - `abort`
  - `error`
  - `resize`
  - `scroll`
  - `contextmenu` (fires by right-clicking an element)

* focus events
  - `blur`
  - `focus` (when element receives focus)
  - `focusin`, `focusout`

* form events
  - `change`
  - `reset`
  - `submit`
  - `select` (upon selecting text in a text field)

* mouse events
  - `click`, `dblclick`
  - `mousedown`, `mouseup`
  - `mouseenter`
  - `mouseleave`, `mouseout`
    + `mouseleave` doesn't bubble, it deals with moving off an element and _all_ of its descendant elements
    + `mouseout` bubbles & fires when it moves from an element onto the boundaries of another one of its descendant elements
  - `mousemove`
  - `mouseover`
  - `mousewheel`

* keyboard events
  - `keydown` (on initial press, for any key even if doesn't produce a character)
  - `keyup`
  - `keypress` (when a key is initally pressed, iff it produces a character value)

* touch events
  - `touchstart`, `touchend`
  - `touchmove`
  - `touchenter`: touch point moves into interactive area defined by a DOM element
  - `touchleave`
  - `touchcancel`

* `window`-, `body`-, and frame-specific events
  - `afterprint`, `beforeprint`
  - `beforeunload`
  - `hashchange`
  - `message`
  - `offline`, `online`
  - `pagehide`, `pageshow`

* `document`-specific events
  - `readystatechange`
  - `DOMContentLoaded`

* drag events
  - `drag`
  - `dragstart`, `dragend`
  - `dragenter`, `dragleave`, `dragover` deal with valid drop targets
  - `drop`

* when an event invokes, it flows/propagates through the DOM, via first the capture phase and then the target phase
  - capture phase: starting from the `window` -> `document` -> `html` -> `body` -> ... -> target
  - target phase: event fires on the darget, and then propogates up to the `window`
  - can prevent this event flow with `stopPropagation()`, but it won't stop default events, nor other events
  - `stopImmediatePropagation()` can stop the event flow and other like events on the target
    + also doesn't prevent default events

* `addEventListener()`
* `removeEventListener()`
  - only removes non-anonymous functions

* `preventDefault()`
  - returning `false` at the end of an event listener has the same effect as `preventDefault`

* custom events
  - `createEvent()`
  - `initCustomEvent()`
  - `dispatchEvent()`

* event delegation allows new content added to the DOM to get the same event, because you're adding it to the parent rather than each individual one
  - requires checking what the `event.target` is within the function body
