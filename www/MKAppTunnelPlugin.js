var exec = require('cordova/exec');

exports.getIdByHttpUrlConnection = function(success, error,arg0) {
    exec(success, error, "MKAppTunnelPlugin", "getIdByHttpUrlConnection", [arg0]);
};
exports.getIdByDefaultHttpClient = function(success, error,arg0) {
    exec(success, error, "MKAppTunnelPlugin", "getIdByDefaultHttpClient", [arg0]);
};
exports.getIdByAndroidHttpClient = function(success, error,arg0) {
    exec(success, error, "MKAppTunnelPlugin", "getIdByAndroidHttpClient", [arg0]);
};