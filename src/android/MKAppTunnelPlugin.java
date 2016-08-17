package com.hand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.net.http.AndroidHttpClient;
import android.text.TextUtils;
import android.util.Log;

public class MKAppTunnelPlugin extends CordovaPlugin{
	
	private CallbackContext mCallbackContext;
	private String urlString = "http://wzdcbdbpm63/sites/OA2/SitePages/MaryKayK2Pages/HLYTest.aspx?mobile=0";
	private String responseStr = null;
	private final static String DEFAULT_ENCODING = "UTF-8";
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		this.mCallbackContext = callbackContext;
		if("getIdByHttpUrlConnection".equals(action)){
			Log.e("399", "getIdByHttpUrlConnection");
			getResonseFromeServer("HttpUrlConnection");
			return true;
		}else if("getIdByDefaultHttpClient".equals(action)){
			Log.e("399", "getIdByDefaultHttpClient");
			getResonseFromeServer("DefaultHttpClient");
			return true;
		}else if("getIdByAndroidHttpClient".equals(action)){
			Log.e("399", "getIdByAndroidHttpClient");
			getResonseFromeServer("AndroidHttpClient");
			return true;
		}
		
		mCallbackContext.error("error");
		return false;
	}
	
	private void getResonseFromeServer(final String requestMode) {
		
		new Thread(){
			@Override
			public void run() {
				try{
					if("HttpUrlConnection".equals(requestMode)){
						URL url = new URL(urlString);
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						connection.setRequestMethod("GET");
						connection.setDoInput(true);
						responseStr = readInputStreamToString(connection.getInputStream());
					}else if("DefaultHttpClient".equals(requestMode)){
						HttpClient defaultHttpClient = new DefaultHttpClient();
						HttpGet request = new HttpGet(urlString);
						HttpResponse response = defaultHttpClient.execute(request);
						responseStr = readInputStreamToString(response.getEntity().getContent());
					}else if("AndroidHttpClient".equals(requestMode)){
						AndroidHttpClient androidHttpClient = AndroidHttpClient.newInstance(null);
						HttpGet request = new HttpGet(urlString);
						HttpResponse response = androidHttpClient.execute(request);
						responseStr = readInputStreamToString(response.getEntity().getContent());
						androidHttpClient.close();
					}
					Log.e("399", "responseStr :" + responseStr);
					mCallbackContext.success(responseStr);
					} catch (MalformedURLException e) {
						mCallbackContext.error("MalformedURLException");
						e.printStackTrace();
					} catch (ProtocolException e) {
						mCallbackContext.error("ProtocolException");
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						mCallbackContext.error("UnsupportedEncodingException");
						e.printStackTrace();
					} catch (IOException e) {
						mCallbackContext.error("IOException");
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						mCallbackContext.error("IllegalArgumentException");
						e.printStackTrace();
					}
			};
		}.start();
	}

	private String readInputStreamToString(InputStream is) throws IOException {
		if (is == null) {
			return "";
		}

		Log.d("399", "InputStream Available? " + is.available());

		BufferedReader rd = new BufferedReader(new InputStreamReader(is, DEFAULT_ENCODING));
		String line;
		StringBuffer responseBuf = new StringBuffer();
		while ((line = rd.readLine()) != null) {
			responseBuf.append(line);
			responseBuf.append('\r');
		}
		rd.close();

		return responseBuf.toString();
	}
}