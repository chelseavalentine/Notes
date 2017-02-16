# Google's Web Fundamentals

Notes taken on Google's [Web Fundamentals](https://github.com/google/WebFundamentals/) guide.

## Getting started

### Service workers: an introduction

* __service worker__: a background script the browser runs, which have several features like push notifications or background sync
  - it's a JS worker, so can't access DOM directly
  - communicates with pages by responding to messages sent via the [`postMessage`](https://html.spec.whatwg.org/multipage/workers.html#dom-worker-postmessage) interface
  - they allow you to control how network requests from your page are handled, because service workers are programmable network proxies
  - can't rely on global state w/ `onfetch` & `onmessage` handlers
    + service workers are terminated when not in use
    + need to use [IndexedDB API](https://developer.mozilla.org/en-US/docs/Web/API/IndexedDB_API)


### JavaScript promises: an introduction

* you can make functions asynchronous with the `async` keyword
  - `async function [name]() { ... }`
  - `async () => { ... }`

* you can use `await` within the function, to await a promise
  - this effectively pauses the function in a non-blocking way until the promise settles


### Shadow DOM v1: self-contained web components

### Custom elements v1: reusable web components

### Media source extensions
