//var cats = require('./cats.js');
//var cats = require('json-loader!./cats.json');
var cats = require('json-loader!yaml-loader!./cats.yml');
console.log(cats);