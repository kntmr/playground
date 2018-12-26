var path = require('path');
module.exports = {
  entry: "./src/main/resources/js/app.js",
  output: {
    path: path.resolve(__dirname, 'build/js'),
    publicPath: "/js/",
    filename: 'bundle.js'
  },
  module: {
    rules: [
      {
        test: /\.vue$/,
        loader: 'vue-loader'
      }
    ]
  },
  resolve: {
    alias: {
      'vue$': 'vue/dist/vue.esm.js'
    }
  },
  devServer: {
    contentBase: 'build'
  }
}