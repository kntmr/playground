var path = require('path');
module.exports = {
  entry: './src/main.js',
  output: {
    path: path.resolve(__dirname, 'static'),
    filename: 'bundle.js'
  },
  resolve: {
    extensions: ['*', '.webpack.js', '.web.js', '.js', '.yml']
  },
  module: {
    loaders: [
      { test: /\.yml$/, loader: 'json-loader!yaml-loader' }
    ]
  }
}