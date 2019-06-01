# SASS: Best practices

### Declaration ordering

From [thoughtbot's guide to programming](https://github.com/thoughtbot/guides/tree/master/style/sass)

1. Place `@extend`s and `@include`s at the top of your declaration list.
2. Place media queries directly after the declaration list.
3. Place concatenated selectors second.
4. Place pseudo-states and pseudo-elements third.
5. Place nested elements fourth.
6. Place nested classes fifth.
