# HTML Best Practices

Notes taken on [HTML Best Practices](https://github.com/hail2u/html-best-practices).

### General

* escape `&`, `<`, `>`, `"`, and `'` with named character references

* use number character references for control or invisible characters
  - because the spec doesn't guarantee to define a human-readable name for these characters

* put whitespaces around comment contents
  - some characters can't be used immediately after the open or end tags

* you can omit namespaces (`xmlns`) (SVG and MathML can be used directly)

* don't specify `role` for elements with implicit ARIA roles

### Document metadata

* specify the MIME type of resources that're less-often used in `<link>`, eg. pdf, rss

* don't link to `favicon.ico` since almost all browsers automatically and asynchronously fetch `favicon.ico`

* specify character encoding first, within the `<html>`

### Grouping content

* use appropriate elements within `blockquote` elements
  - don't include the attribution directly withinthis element

### Text-level semantics

* use the `download` attribute on `<a>` when it's for downloading a resource

* use `rel`, `hreflang`, and `type`, on `<a>` as needed

### Embedded content

* provide a fallback `img` element for the `picture` element

### Forms

* input fields have `pattern` attributes in which you can check the value against a regex

* use placeholder attributes for short hints, not labels
