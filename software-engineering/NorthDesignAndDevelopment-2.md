# North: Design and Development Guide

### Visual design

* "concept of page is ruining the web", move toward designing reusable components and layouts instead of pages

#### Consistency and predictability

* consistency (reuse) allows users to feel confident as they navigate
  - using components w/ same aspect consistently & roughly in the same place
  - using few layouts as possible
  - don't change components based on what layout they're in
  - don't hide content based on screen size/device

#### Anti patterns

* outdated UX patterns
  - carousels
  - large background images
    + screen real estate shouldn't be assumed
  - hover states for additional information
  - mega menus
    + with multiple levels of menus
  - mega footers
    + usually used for SEO optimization, but best way to improve SEO is good content
  - large sticky headers/footers
  - text in/as images
  - text over images
  - overlays
  - app-like interfaces
  - back buttons
  - page preloaders
  - social integration

#### Design in browser

* suggests using rapid prototyping and style prototyping

* mobile first
  - instead of thinking of mobile first, think about content first, and mobile as a lens


### Responsive web design

#### Device detection
* use feature detection (eg. via [Modernizr](http://modernizr.com/)) rather than device detection
  - allow & encourage users to use enhanced experiences rather than forcing them based on user agent string

#### Resolution independence

* don't fall into the trap of making your breakpoints devices

* components should have their own breakpoints allowing them to flow as needed
  - it isn't uncommon for projects to have 50+ breakpoints

* create svg fallbacks for icons, and png/jpg fallbacks for svgs


### Performance

#### Testing and grading performance

* ensure your site reaches the minimum target scores for each resource
  - [Page Speed](https://developers.google.com/speed/pagespeed/insights): 85
  - [Web Page Test](http://www.webpagetest.org/)
    + first byte time: 85
    + use persistent connection: 85
    + use gzip compression for transferring compressible responses: 90
    + compress images: 90
    + use progressive jpegs: 90
    + leverage browser caching of static assets: 90
    + use a CDN for all static assets: 85
  - [YSlow](http://developer.yahoo.com/yslow/): 85

#### Page performance

* rules of thumb to avoid slow UIs
  - don't bind expensive processes to `document`/`window` events (eg. scroll, resize, etc.)
  - use CSS3 translate instead of absolute position w/ top & left properties
  - don't emulate fixed positioning using JS
  - animate through CSS3 instead of JS
  - group JS document reads & writes separately
    + use `requestAnimationFrame` to reduce layout thrashing when reading & writing to the DOM
  - avoid IE's CSS expression selectors

#### Front-end optimizations

* critical optimizations
  - avoid page redirects
  - CSS & JS should be minified and aggregated
  - use a CDN
    + when utilizing a CDN such as Akamai, use it to serve scripts such as jQuery instead of Google's CDN as it will be faster on a cold cache
  - cache page requests
  - always load CSS before JS (hmm... React?)

* recommended optimizations
  - lazy load non-critical content
  - in-line small but important files (generally < 3kb) to reduce HTTP requests
  - aggregate other small files to reduce HTTP requests

* experimental optimizations
  - in-line above-the-fold CS into the HTML, push additional CSS to the footer
  - utilize [.webp](https://developers.google.com/speed/webp/) and [.webm](http://www.webmproject.org/) file formats
  - employ the [spdy](http://www.chromium.org/spdy/spdy-whitepaper) protocol
  - dynamically serve appropriately sized images from server-side rather than relying on client-side techniques

