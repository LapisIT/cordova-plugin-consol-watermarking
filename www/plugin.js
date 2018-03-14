
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
