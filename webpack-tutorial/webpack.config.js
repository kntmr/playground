var path = require('path');
module.exports = {
  entry: './src/main.js',
  output: {
    path: path.resolve(__dirname, 'static'),
    filename: 'bundle.js'
  }
}