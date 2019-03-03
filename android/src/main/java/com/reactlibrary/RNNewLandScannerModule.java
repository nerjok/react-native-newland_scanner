
package com.reactlibrary;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNNewLandScannerModule extends ReactContextBaseJavaModule {

  private static final String TAG = "RNNewLandScanner";
  private static final String SCANNER_RESULT = "nlscan.action.SCANNER_RESULT";
  private static final String SCANNER_TRIG = "nlscan.action.SCANNER_TRIG";
  private static final String ACTION_BAR_SCANCFG = "ACTION_BAR_SCANCFG";
  private static boolean registeredTag = false;
  private String qrCode;

  private Callback successCalback;
  private Calback errorCalback;

  private BroadcastReceiver mReceiver;
  private Activity activity;
  private Context context;

  public NewLandScanner(ReactApplicationContext reactContext) {
    super(reactContext);
    this.activity = (Activity) getCurrentActivity();
    this.context = getReactApplicationContext();

    registerReceiver();
    initSetting();
  }

  @Override
  public String getName() {
    return "NewLandScanner";
  }

  private void initSetting(){
    Intent intent = new Intent (ACTION_BAR_SCANCFG);
    intent.putExtra("EXTRA_SCAN_MODE", 3);
    intent.putExtra("EXTRA_SCAN_POWER", 1);
    intent.putExtra("EXTRA_TRIG_MODE", 0);
    intent.putExtra("EXTRA_SCAN_NOTY_SND", 1);
    context.sendBroadcast(intent);
  }

  private void registerReceiver() {
    if (!registeredTag) {
      mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
          String action = intent.getAction();
          if (SCANNER_RESULT.equals(action)) {
            final String scanResult_1 = intent.getStringExtra("SCAN_BARCODE1");
            final String scanStatus = intent.getStringExtra("SCAN_STATE");
            Log.d(TAG, "scanResult_1：" + scanResult_1);
            if ("ok".equals(scanStatus)) {
              if (!TextUtils.isEmpty(scanResult_1)) {
                final String result = scanResult_1;
                qrCode = result;
                //setResultData(result);
                Log.d(TAG,"result:"+result);
                successCalback..invoke(result);
                Toast.makeText(getReactApplicationContext(), result, Toast.LENGTH_LONG).show();
/*
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.d(Tag,"result:"+result);
                                        //webView.loadUrl("javascript:nlscan.plugins.barcodescanner.show('" + result + "')");
                                        Toast.makeText(getReactApplicationContext(), result, Toast.LENGTH_LONG).show();

                                    }
                                });*/
              }
            }
          }
        }
      };
      IntentFilter intFilter = new IntentFilter(SCANNER_RESULT);
      context.registerReceiver(mReceiver, intFilter);
      registeredTag = true;
    }

  }

  /**
   * Complete receiver on finish scanns
   */
  private void unRegisterReceiver() {
    if (registeredTag) {
      try {
        context.unregisterReceiver(mReceiver);
        registeredTag = false;
      } catch (Exception e) {
      }
    }
  }

  @ReactMethod
  public void scannCodes(Callback success, Callback error) {
    //success.invoke("qrCode succesfully read");
    Log.d(TAG,"execute method is called");
    //Toast.makeText(getReactApplicationContext(), "NewLAndScannerClicked", Toast.LENGTH_LONG).show();

    this.successCalback = success;
    this.errorCalback = error;

    Intent intent = new Intent(SCANNER_TRIG);
    context.sendBroadcast(intent);
    int i = 0;
    while(qrCode == null) {
      if ( i > 5000)
        break;
      i++;
    }
    Log.d(TAG, "scannCodes: " + mReceiver.getResultExtras(true));
    Log.d(TAG, "scannCodes: " + mReceiver.getResultExtras(false));
    Log.d(TAG, "scannCodes getResultDate: "+ mReceiver.getResultData());
    Log.d(TAG, "scannCodes qrCode: " + qrCode);
  }

  @ReactMethod
  public void cancelScans() {
    if (registeredTag) {
      try {
        context.unregisterReceiver(mReceiver);
        registeredTag = false;
      } catch (Exception e) {
      }
    }
  }
}