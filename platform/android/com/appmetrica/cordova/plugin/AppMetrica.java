package com.appmetrica.cordova.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.lang.Thread;

import android.widget.Toast;

import com.yandex.metrica.YandexMetrica;

import android.content.Context;
import android.util.Log;

public class AppMetrica extends CordovaPlugin {
    @Override
    public boolean execute(final String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
  		if("activate".equals(action))
      {
          activate(args, callbackContext);
          return true;
      }
      if("reportEvent".equals(action)){
        reportEvent(args, callbackContext);
      }
      return false;
    }

    @Override
    public void onPause(boolean multitasking) {
        YandexMetrica.onPauseActivity(cordova.getActivity());
    }

    @Override
    public void onResume(boolean multitasking) {
        YandexMetrica.onResumeActivity(cordova.getActivity());
    }

    @Override
    public void onDestroy() {
        YandexMetrica.onPauseActivity(cordova.getActivity());
    }

    private void activate(JSONArray parameters, final CallbackContext callbackContext) {
        try
        {
            final String devKey = parameters.getString(0);
            if(devKey != null){
				cordova.getThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        YandexMetrica.activate(cordova.getActivity().getApplicationContext(), devKey);
                        YandexMetrica.onResumeActivity(cordova.getActivity());
                        YandexMetrica.reportEvent("activated");
					}
				});
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return;
        }
    }

    private void reportEvent(JSONArray parameters, final CallbackContext callbackContext) {
        try
        {
            final String eventName = parameters.getString(0);
            if(eventName != null){
				cordova.getThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        YandexMetrica.reportEvent(eventName);
					}
				});
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return;
        }
    }
}
