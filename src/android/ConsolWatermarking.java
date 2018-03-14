/**
 */
package com.yarris.cordova.consol.watermarking;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.util.Log;

import java.util.Date;

public class ConsolWatermarking extends CordovaPlugin {
  private static final String TAG = "ConsolWatermarking";

  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    if(action.equals("echo")) {
      String phrase = args.getString(0);
      // Echo back the first argument
      Log.d(TAG, phrase);
    } else if(action.equals("getDate")) {
      // An example of returning data back to the web layer
      final PluginResult result = new PluginResult(PluginResult.Status.OK, (new Date()).toString());
      callbackContext.sendPluginResult(result);
    } else if(action.equals("watermark")) {
      String url = args.getString(0);

      Log.d(TAG, "url: " + url);

      WatermarkLines details = new WatermarkLines("B1427145",
              "44, AARONS PASS NSW 2850",
              "TEST_PROJ5",
              "37.81671 "+(char) 0x00B0+" S",
              "144.95625"+(char) 0x00B0+" E",
              "2018-02-21",
              "11:30:54 + 11:00");

      Watermarking watermarking = new Watermarking(context(), url, details);

      final PluginResult result = new PluginResult(PluginResult.Status.OK, url);

      callbackContext.sendPluginResult(result);
    }

    return true;
  }

  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);

    Log.d(TAG, "Initializing ConsolWatermarking");
  }

  private Context context() {
    return this.cordova.getActivity().getApplicationContext();
  }

}
