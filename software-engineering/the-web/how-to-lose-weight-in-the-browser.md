# How to Lose Weight (in the Browser)

Notes from the guide, [_How to Lose Weight (in the Browser)](https://browserdiet.com/)

### HTML

* avoid inline/embedded code
  - embedded code: code defined inside a `<style>` or `<script>` tag
  - why? Increases size of your HTML
  - run tests to ensure that there're gains in speed before doing so
  - determine whether the audience will visit once (thus embedding is fine), or whether it's better to let the browser cache it

* prefer `<link>` over `@import` because the browser is incapable of downloading `@import`ed stuff in parallel

### JavaScript

* load 3rd party ocntent asynchronously

* cache array lengths, otherwise t needs to be recalculated on each loop iteration
  - `for (i = 0, len = arr.length; i < len; i++)`
  - some browsers already do this optimization on your code
  - more important when dealing with live lists (eg. html elements)

* minimize reflows and repaints
  - repaints are triggered when you change the appearance of an element without changing the layout
  - reflows are more costly, and caused by changing the page layout (eg. width of element)
  - if possible, instead of accessing a style attribute multiple times in a for-loop, cache it in a variable once, and then use it
    + this minimizes the number of layout recalculations
  - avoid unnecessary DOM manipulations

### Images

* use CSS sprites instead
  - reduce various images into 1 file & using CSS to change the background position
  - [tools for this](https://github.com/zenorocha/browser-diet/wiki/Tools#use-css-sprites)

* always define the width & height attributes of an image to avoid unnecessary repaints and reflows

* use progressive JPEGs
