# North: Design and Development Guide

#### General coding syntax

* use data attributes instead of `.js-*` classes

#### Markup

* ensure HTML5 is properly marked up with __[Resource Description Framework in Attributes (RDFa)](http://www.w3.org/TR/rdfa-primer/)__ to improve SEO and to ensure content will be highly available

* ensure accessibility
  - test it with VoiceOver, JAWS, and Lynx Browser](http://docs.webplatform.org/wiki/concepts/accessibility)

* include the viewport CSS directive for IE support (lol)

#### Styling

* layouts including viewport-based media queries (eg. `width`, `height`, etc.) should never be nested within each other

* North's naming conventions
  - aspects are the name of the components, such that the common class name is `.element--ASPECT`
  - layouts start with `_` to distinguish them from components
  - states are applied to the state data attribute (`data-state`)
  - if there're multiple states, they're separated by spaces within the `data-state` attribute

* suggests the following tools
  - [Modular scale calculator for Sass](https://github.com/modularscale/modularscale-sass)
  - [Sass Color Schemer plugin](https://github.com/at-import/color-schemer)

* use `default!` to allow default values to easily be overridden

* directory structure
  - `global`: `variables`, `functions`, `mixins`, `extendables`
    + these file types can be used per component also
  - `base`
  - `components`
  - `layout`

* global, private variables should start with a capital letter

#### Interaction

* JavaScript style and syntax
  - wrap custom scripts in anonymous, self-executing functions with any external dependencies passed in
  - declare functions before  they're used
