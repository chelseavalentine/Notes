## CSS

### General

* `general-property: (Top) (Right) (Bottom) (Left);` (clockwise)

##### Importing your CSS

```html
<link rel="stylesheet" type="text/css" href="" />
```

##### Rule precendence

* The rules that are defined last take precedence
    - Unless there's a more specific rule, then the more specific rule will be applied
* Can add `!important` after any property value to say it's more important than any of the element's other values
```css
p b #yo {
    color: blue !important;
}
```

### Selectors

* __Universal selector__  `* {}` Target all elements in the document.
* __Child selector__ `tag1>tag2 {}` Targets any tag2 elements that're direct children of a tag1 element.
* __Descendant selector__ `tag1 tag2 {}` Targets any tag2 element that sits inside a tag1 element, even if there're other elements nested between them.
* __Adjacent sibling selector__ `tag1+tag2 {}` Targets the first tag2 element adjacent to any tag1 element.
* __General sibling selector__ `tag1~tag2 {}` Targets all of the tag2 children that tag1 has.

### Inheritance

You can use the property value `inherit` if you want an element to have a property value of its parents. Some things are automatically inherited, but others, like margin and padding, aren't.

```css
div {
    font-family: fontiness;
    color: colorness;
    padding: 10px;
}

.div-child {
    padding: inherit;
}
```

### Color

Several ways to define your color choice.

1. The color's name. `red`.
2. The rgb value. `rgb(255, 255, 255)`.
3. The hex  code. `#eee030`; `#eee`.
4. The rgba value. `rgba(255, 255, 255, 0.1)`
5. The HSL value. `hsl(25, 30%, 16%)`
    i. _hue_: angle between 0 & 360 degrees
    ii. _saturation_: percentage
    iii. _lightness_: percentage
6. The HSLA value. `hsla(25, 30%, 16%, 0.4)`

##### Color tools

[Color contrast checker](http://www.snook.ca/technical/colour_contrast/colour.html)

### Text

##### Font-weight

Can add emphasis, and can also affect amount of whitespace/contrast on a page.

* Fixed: `normal`, `bold`
* Relative to the parent: `lighter`, `bolder`
* Absolute weights: `100` ... `900`, incrementing by 100

##### Font-stretch
* Condense: `ultra-condensed`, `extra-condensed`, `condensed`, `semi-condensed`
* Normal: `normal`
* Expand: `semi-expanded`, `expanded`, `extra-expanded`, `ultra-expanded`

##### Font-style
* `Normal`
* `Italic`: A cursive version of the font.
* `Oblique`: The normal style, put on an angle.

##### Font-face

If the user doesn't/won't have the font on their computer, you can link to the font. You can also specify many sources, so the next source will only be used if the first/preceding one doesn't exist.

```css
@font-face {
    font-family: "Your name choice";
    src: local("Your name choice"),
         local("Yo name choyce"),
         url("http://your.url/font.ttf") format("truetype");
}

.fonty {
    font-family: "Your name choice", sans-serif;
}
```

##### Text-decoration

* `none` & `underline`
* `overline`: adds line over top of text
* `line-through`: adds line through words
* `blink`: animates the text to flash on and off (lol so troll)

##### Line-height

A way to control leading (vertical space between lines of text) via CSS.

Leading includes the font-size + leading. Best used in `em` so it changes with text scale.

##### Letter-spacing & word-spacing

A way to control the kerning (space between each letter). Default word-spacing is around 0.25em.

##### Vertical-align

Used with inline elements (not block, like div)

* Values: `baseline`, `sub`, `super`, `text-top`, `middle`, `bottom`, `text-bottom`
    - can also use a numeric value in `em` or `px`, or a percentage of the line height

##### Pseudo-elements & -classes

__First-letter and :first-line__ (p-e)
Change how the first line or letter looks.
`p:first-letter` & `p:first-line`

__Styling links | :link, :visited, :hover, :active__ (p-c)

__:hover, :active, :focus__ (p-c)
These change how an element behaves when the user interacts with it, and it  can apply to any element.

* `active`: when the element is being pressed/clicked
* `focus`: when a browser detects you're ready to interact w/ the element

__Ordering pseudo-classes__
:link, :visited, :hover, :focus, :active.


### Attribute selectors

* __Existence__ `element[attribute]` Targets any `<element>` elements with an attribute called 'attribute'
    * `p[class]`
* __Equality__ `element[attribute="value"]` Targets any `<element>` elements with an attribute called 'attribute' whose value is 'value'
    - `p[class="dog"]`
* __Space__ `element[attribute~="value"]` Targets any `<element>` elements with an attribute called 'attribute' whose value is a list of space-separated words, once of which is 'value'
    - `p[class~="dog"]`
* __Prefix__ `element[attribute^"l"]` Targets any `<element>` elements with an attribute whose value begins with 'l'
    - `p[attr*"d"]`
* __Substring__`element[attribute*"yo"]` Targets any `<element>` elements with an attribute whose value contains the letters 'yo'
    - `p[attr*"do"]`
* __Suffix__ `element[attribute$"d"]` Targets any `<element>` elements with an attribute whose value ends with the letter 'd'
    - `p[attr$"g"]`

### Boxes

##### Centering elements

Set an elements `right-margin` and `left-margin` to `auto` to center it within its parent.

##### Visibility

Allows you to hide an element while still reserving its space on the page.

* Values: `hidden`. `visible`.

##### Border-image

The numbers indicate where to slice the image. Each image is cut into 9 slices, and all slices, except the center one, are used.

```css
p {
    border-width: 50px;
    border-image: url("url.png") 11 11 11 11 stretch;
}

p.nostretchy {
    border-image: url("url.png") 11 11 11 11 round; /*alt value: repeat*/
}
```

### Lists

##### List-style-type

Unordered lists:
`list-style-type: none | disc | circle | square`

Ordered lists:
`list-style-type: decimal | decimal-leading-zero | lower-alpha | upper-alpha | lower-roman | upper-roman`

Or you can use an image, for either `<ul>` or `<ol>`:
`list-style-image: url("img.png);`

##### List-style-position

Values:
* `outside`: the marker sits outside the block of text (default)
* `inside`: marker sits inside text block, so text wraps

### Table

__Decide what happens to empty cells__
`empty-cells: show | hide;`

__Gaps between cells or not?__

Put spaces between borders `border-spacing: [horizontal] [vertical]`

Or get rid of spaces between borders
`border-collapse: collapse | separate`

### Cursor

`cursor: auto | crosshair | default | pointer | move | text | wait | help | url(cursor.gif);`

### Layouts

##### Position

* __Static__: every block element appears on new line, regardless of whether you specify a size that'd allow them to sit side-by-side
* __Relative__: elements are relative to other elements in the flow; you can use offset properties (top, bottom, left, right)
* __Fixed__
* __Float__: other blocks will wrap around this floated element
    - `float: right`
    - `float: left`
    - `clear: left | right | both | none`
