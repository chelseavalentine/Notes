# The Art of Node

Notes taken on [_The Art of Node_](https://github.com/maxogden/art-of-node).

### Modules and npm

#### How `require` works

Using the example of `require('module')`,

1. if `module.js` exists in the same directory, node loads that
2. node looks in the current directory for a `node_modules` directory with a `module` directory in it
3. goes up 1 directory and repeats #2

To verify the correct module is getting loaded in, you can use `require.resolve('some_module')` to get the path
