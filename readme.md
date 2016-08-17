#MKAppTunnelPlugin调用说明

##MKAppTunnelPlugin提供了3个获取userId的接口，目前使用哪个都可以获取userId

###调用方式


#### cordova.plugins.MKAppTunnelPlugin.getIdByHttpUrlConnection(success,error,arg)
###参数说明：
* success 成功回调
* error 失败回调
* arg 此处可以不传

##调用示例：
	<button onclick="cordova.plugins.MKAppTunnelPlugin.getIdByHttpUrlConnection(function(msg){
	                 alert(msg);
	               },function(msg){
	                  alert(msg);
	                },'')">HttpUrlConnection</button>



#### cordova.plugins.MKAppTunnelPlugin.getIdByDefaultHttpClient(success,error,arg)
###参数说明：
* success 成功回调
* error 失败回调
* arg 此处可以不传

##调用示例：
	<button onclick="cordova.plugins.MKAppTunnelPlugin.getIdByDefaultHttpClient(function(msg){
	                  alert(msg);
	               },function(msg){
	                  alert(msg);
	                },'')">DefaultHttpClient</button>

### cordova.plugins.MKAppTunnelPlugin.getIdByAndroidHttpClient(success,error,arg)
###参数说明：
* success 成功回调
* error 失败回调
* arg 此处可以不传

##调用示例：
	<button onclick="cordova.plugins.MKAppTunnelPlugin.getIdByAndroidHttpClient(function(msg){
		                 alert(msg);
		             },function(msg){
		                alert(msg);
		          },'')">AndroidHttpClient</button>