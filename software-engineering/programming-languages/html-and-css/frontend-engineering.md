# Front-end engineering

## CSS

>If you are starting from the assumption that your CSS isn't reusable, your first instinct is to write new CSS.

Things to keep in mind while writing CSS:

* "object oriented css" => essentially modular, reusable, maintainable, portable styles
* selector's location dependency
* portability of styles
* selector efficiency
  - only to some extent; browser engines optimize your selectors  a lot, but still worthy of keeping in mind
* specificity
  - is your selector too broad? is it too specific?
  - avoid `!important`
* reusability
  - IDs can be bad for this reason, because they are higher in the hierarchy, so if you were to move the element, it could fail to take on some of the intended styles of its new parent
  - future-proof
* predictability
  - rule additions/updates shouldn't affect parts you didn't intend it to

### Selector hierarchy, from most to least efficient

1. ID, e.g. `#header`
2. Class, e.g. `.promo`
3. Type, e.g. `div`
4. Adjacent sibling, e.g. `h2 + p`
5. Child, e.g. `li > ul`
6. Descendant, e.g. `ul a`
7. Universal, i.e. `*`
8. Attribute, e.g. `[type="text"]`
9. Pseudo-classes/-elements, e.g. `a:hover`

* browsers read selectors __right-to-left__; think about those implications

* IDs & class selectors barely have a difference in performance, rendering it irrelevant to choose ID for that speed

* the difference between IDs/classes and types/descendants is significant, but the difference between themselves aren't

### Object-oriented CSS

* __Single responsibility principle__

* __Open/closed principle__: software entities should be open for extension, but closed for modification

* __Liskov substitution principle__: objects in a program should be replaceable with instances of their subtypes without altering the correctness of that program

* __Interface segregation principle__: it’s better to have multiple specific base modules than to have a single generic one
  - if you find yourself redefining many properties every time you need to augment a certain class, your base styles are probably too generic

* __Dependency inversion principle__
  1. High-level modules should not depend on low-level modules. Both should depend on abstractions.
  2. Abstractions should not depend upon details. Details should depend upon abstractions.

### Bad practices

1. Modifying components based on who their parents are
2. Overly generic class names

### Good practices

* organizing your CSS rules into four separate categories: base, layout, modules, and state
  - modules: reusable visual elements
  - state: styling that can be toggled on/off via JS
* use `.js-` for class names used in JS
* never nest rules purely for code organization. Only nest when the outputted CSS is what you want.
* never use a mixin if you’re not passing an argument. Mixins without arguments are much better used as templates which can be extended.
* never use `@extend` on a selector that isn’t a single class. It doesn’t make sense from a design perspective and it bloats the compiled CSS.

### BEM (Block Element Modifier) model

```css
.block {}
.block__element {}
.block--modifier {}
```

### Multilayer CSS (MCSS)

* 3 layers: (reset layer), base layer, project layer, and cosmetic layer

1. Base layer
  - reusable
  - forms, buttons, navigation blocks, etc.

2. Project layer
  - isolated, project modules, which construct the page
  - eg. registration form, login block, shopping cart, etc.

3. Cosmetic layer
  - simple, slightly affecting styles
  - eg. link colors, low-level OOCSS, global modifiers

### CSS Modules

* React uses CSS modules, which allow you to use whatever class names to style the component without having to worry about name conflicts; can focus on the component, not the styling

## Shadow DOM

* has a __shadow boundary__ ,which allows for style encapsulation, because CSS styles defined w/i it are scoped to the ShadowRoot
