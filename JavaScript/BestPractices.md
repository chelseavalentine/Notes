# JavaScript: Best practices

#### [10up Engineering Best Practices](https://github.com/10up/Engineering-Best-Practices)

* __event delegation__: adding one event listener to a parent, which deals with the instances of whether the click was on a child, instead of an event listener for each child
  - but we still want the event to bubble up the DOM as little as possible
