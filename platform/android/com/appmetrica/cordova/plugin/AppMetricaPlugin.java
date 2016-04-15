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

public class AppMetricaPlugin extends CordovaPlugin {
    @Override
    public boolean execute(final String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if("activate".equals(action))
      {
          activate(args, callbackContext);
          return true;
      }
      if("reportEvent".equals(action)){
          reportEvent(args, callbackContext);
          return true;
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

    //Тречим событие
    private void reportEvent(JSONArray parameters, final CallbackContext callbackContext)
    {
      try
      {
        final String eventName = parameters.getString(0);
        //final String jsonValue = parameters.getString(1);

        // YandexMetrica.reportEvent(eventName, jsonValue);
        // Log.v("AppMetrica ->", eventName + " " + jsonValue);

        cordova.getThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                      YandexMetrica.reportEvent(eventName);
                      Log.v("AppMetrica ->", eventName);
          }
        });

      }
      catch (JSONException e)
      {
          e.printStackTrace();
          return;
      }
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
}
