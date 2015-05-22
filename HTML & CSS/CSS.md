## CSS

### General

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

### Inline elements

##### `<b>`, ``

#####


### Block elements

##### `<h1>` ... `<h6>`

##### `<p>`

##### `<div>`