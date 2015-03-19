#VelocityJS
These notes assume that you're using VelocityJS with jQuery. Some things are different when you use Velocity without jQuery.

### Basics
Basic syntax:
```Javascript
$element.velocity ({
CSS properties}, {
Velocity options like duration, easing, queue, loop, etc.
});

// Example

$element.velocity( {
width: 500,
left: 50,
height: "+=5px", //Add 5px to current value
}, {
duration: 1000
});
```
