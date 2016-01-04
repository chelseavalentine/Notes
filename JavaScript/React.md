# React


## Properties

### PropTypes

You can make sure that you get a certain data type for your properties.

```jsx
import React, {Component, PropTypes} from 'react';

class MyComponent extends Component {
    propTypes: {
        property: PropTypes.string,
        strictProperty: PropTypes.string.isRequired,
        interestingProperty: PropTypes.oneOf([
            'hey',
            'whats',
            'up?'
        ]).isRequired
    }
}
// alternatively

MyComponent.propTypes = {
    ...
}
});
```


### Default property values
```jsx
...
getDefaultProps: function() {
    return {
        property: 'value'
    };
},
...
```
