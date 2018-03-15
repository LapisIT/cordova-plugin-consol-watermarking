// comment out for 6.3.0, remove comment for 7.0.0
//cordova.define("cordova-plugin-consol-watermarking.plugin", function(require, exports, module) {

var exec = require('cordova/exec');

var PLUGIN_NAME = 'ConsolWatermarking';

var ConsolWatermarking = {
  echo: function(phrase, cb) {
    exec(cb, null, PLUGIN_NAME, 'echo', [phrase]);
  },
  getDate: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'getDate', []);
  },
  watermark: function(lines, cb) {
    exec(cb, null, PLUGIN_NAME, 'watermark', lines);
  },
};

module.exports = ConsolWatermarking;

//});
