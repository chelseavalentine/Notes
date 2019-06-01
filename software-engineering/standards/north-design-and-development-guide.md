# North: Design and Development Guide

### Development process

#### Agile scrum

- benefit statement: _As [persona], I want [desire] so that [rationale]._

#### Preprocessed languages

- the compiled output of preprocessed languages (eg. Sass) should be gitignored, and built upon deployment

### Content strategy

- __content strategy__: the processes of analyzing, sorting, constructing, and placing content

#### Project vision

- vision statements state the goal and the features that'll best serve the content
  - answers the following questions:
    - target customer? product consumer? product administer and maintainer?
    - needs the product addresses?
    - attributes critical to success?
    - comparison to existing products, unique selling points?

#### User stories

- to get a better understanding of users, and how your product could relate, ask questions like:
  - what do you find most valuable about the existing product/similar ones/competitors' products?
  - is anything of value missing from the existing product & similar products?
  - how do you most often access the product?
  - pain points?
  - what's the target demographic information?
- don't use stereotypes, only use the statistical analysis of the interviews
- user personas comprise of:
  - name
  - description of typical use of product
  - motivator for use of product (primary, secondary, tertiary needs)
  - pain points with the product

#### Content inventory

- analysis of existing content (or an inventory based on perceived content needs, if non-existent)
  - create a spreadsheet with the intrinsic (title, owner, last updated) and analytics (page views, rank, notes) data
  - content = images, videos, charts, forms, text, etc.
- doesn't need to be all content, but a statistically significant amount
- create limits for each content type, depending on the type
  - - = required
  - (0) = soft limit, when a known minimum exists but no known limit
  - 000x00 = dimensions, w x h
  - ^foo = begins with
  - bar$ = ends with
  - .jpg|.png = multiple options for file extensions

#### Content audit

- determining whether the current content is worth keeping, editing, or removing

- questions to ask:
  - is it too long? short? just right? Can longer content be cut into shorter chunks and still make sense?
  - is the copy wordy? rambling? Can it be cleaned up and hold its meaning?
  - does each page/chunk get to the point quickly?
  - is the content evenly broken up?
  - is the content relevant and important?
- do a __gap analysis__ of the content based on the answers (divided into following categories:)
  - _keep_: as is
  - _revise_: edit to tighten up copy & content types
  - _delete_: outdated, irrelevant, not useful
  - _create new_: based on business needs, user interviews

#### Content modeling

- __content model__: overview of different types of content, modeled to include its (visible and structural) attributes, containing this info:
  - content type _title_
  - _description_
  - _benefit statement_
  - _value_ of the content type
    - additional aspects: resources required to build content, like advertising revenue
  - _attributes_, each with a data limit (eg. character limit, date format)
  - _relationships_ the content type has to other types of content

#### Information architecture

- __information architecture (IA)__: process of determining what content gets used when, where, and why
- things to keep in mind while building your IA:
  - truncation isn't a content strategy
    - content requiring truncation usually isn't written for summary or reuse
    - usually doesn't contain trigger words
    - never truncate headlines
    - always provide summaries for long copy
    - provide alternate copy when needed
  - build systems of content
    - allow for different sizes and styles of content attributes
      - eg. small, medium, large images
      - eg. short & long human readable, and SEO-friendly headlines
    - do _not_ build content for specific contexts (eg. iPhones, Androids, tablets, desktops)
  - content should be easily navigable
    - don't paginate unnecessarily
    - make navigating to sections in long pieces of content easy
    - provide enough context for a user to make their own navigation decisions
      - provide plenty of trigger words
    - keep navigation uncluttered
  - content should be available
    - don't restrict content, especially based on device
    - provide alternate formats of content if one format can't be made available
    - don't store content as HTML, but rather as attributes that can be presented in multiple ways
    - make content available and in a way best suited for the device

### Visual design

- "concept of page is ruining the web", move toward designing reusable components and layouts instead of pages

#### Consistency and predictability

- consistency (reuse) allows users to feel confident as they navigate
  - using components w/ same aspect consistently & roughly in the same place
  - using few layouts as possible
  - don't change components based on what layout they're in
  - don't hide content based on screen size/device

#### Anti patterns

- outdated UX patterns
  - carousels
  - large background images
    - screen real estate shouldn't be assumed
  - hover states for additional information
  - mega menus
    - with multiple levels of menus
  - mega footers
    - usually used for SEO optimization, but best way to improve SEO is good content
  - large sticky headers/footers
  - text in/as images
  - text over images
  - overlays
  - app-like interfaces
  - back buttons
  - page preloaders
  - social integration

#### Design in browser

- suggests using rapid prototyping and style prototyping
- mobile first
  - instead of thinking of mobile first, think about content first, and mobile as a lens

### Responsive web design

#### Device detection

- use feature detection (eg. via [Modernizr](http://modernizr.com/)) rather than device detection
  - allow & encourage users to use enhanced experiences rather than forcing them based on user agent string

#### Resolution independence

- don't fall into the trap of making your breakpoints devices
- components should have their own breakpoints allowing them to flow as needed
  - it isn't uncommon for projects to have 50+ breakpoints
- create svg fallbacks for icons, and png/jpg fallbacks for svgs

### Performance

#### Testing and grading performance

- ensure your site reaches the minimum target scores for each resource
  - [Page Speed](https://developers.google.com/speed/pagespeed/insights): 85
  - [Web Page Test](http://www.webpagetest.org/)
    - first byte time: 85
    - use persistent connection: 85
    - use gzip compression for transferring compressible responses: 90
    - compress images: 90
    - use progressive jpegs: 90
    - leverage browser caching of static assets: 90
    - use a CDN for all static assets: 85
  - [YSlow](http://developer.yahoo.com/yslow/): 85

#### Page performance

- rules of thumb to avoid slow UIs
  - don't bind expensive processes to `document`/`window` events (eg. scroll, resize, etc.)
  - use CSS3 translate instead of absolute position w/ top & left properties
  - don't emulate fixed positioning using JS
  - animate through CSS3 instead of JS
  - group JS document reads & writes separately
    - use `requestAnimationFrame` to reduce layout thrashing when reading & writing to the DOM
  - avoid IE's CSS expression selectors

#### Front-end optimizations

- critical optimizations
  - avoid page redirects
  - CSS & JS should be minified and aggregated
  - use a CDN
    - when utilizing a CDN such as Akamai, use it to serve scripts such as jQuery instead of Google's CDN as it will be faster on a cold cache
  - cache page requests
  - always load CSS before JS (hmm... React?)
- recommended optimizations
  - lazy load non-critical content
  - in-line small but important files (generally < 3kb) to reduce HTTP requests
  - aggregate other small files to reduce HTTP requests
- experimental optimizations
  - in-line above-the-fold CS into the HTML, push additional CSS to the footer
  - utilize [.webp](https://developers.google.com/speed/webp/) and [.webm](http://www.webmproject.org/) file formats
  - employ the [spdy](http://www.chromium.org/spdy/spdy-whitepaper) protocol
  - dynamically serve appropriately sized images from server-side rather than relying on client-side techniques

