# Code Guide: HTML & CSS

Notes taken on [Code Guide: HTML & CSS](http://codeguide.co/)

### HTML

* always specify the `lang` attribute on the `<html>` element to help tools for speech, translation, etc.
  - eg. `<html lang="en-us">`

* specify which version of IE your site is compatible with
  - `<meta http-equiv="X-UA-Compatible" content="IE=Edge">`

* attribute order:
  - `class`
  - `id`, `name`
  - `data-*`
  - `src`, `for`, `type`, `href`, `value`
  - `title`, `alt`
  - `role`, `aria-*`

* for boolean attributes, don't specify a value; its presence represents the `true` value

### CSS

* don't include spaces after commas within rgb(), rgba(), hsl(), hsla(), or rect() values
  - helps to differentiate multiple color values from multiple property values

* they suggest to group related property directions in this order: [1] positioning, [2] box model, [3] typographic, [4] visual

* don't use `@import`, it is slower than `<link>`s, and adds extra page requests

* consider putting the rule on one line for single declarations
