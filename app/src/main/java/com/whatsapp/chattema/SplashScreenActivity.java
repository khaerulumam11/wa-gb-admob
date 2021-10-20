package com.whatsapp.chattema;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    public static final String URL = "https://raw.githubusercontent.com/colddrygame/wap/master/raw/x.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        UnityAds.initialize(this, getString(R.string.unity_app_id), new IUnityAdsListener() {
            @Override
            public void onUnityAdsReady(String s) {
                Log.i("Unity", "ads ready "+s);
            }

            @Override
            public void onUnityAdsStart(String s) {
                Log.i("Unity", "ads start "+s);
            }

            @Override
            public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
                Log.i("Unity", "ads finish "+s);
            }

            @Override
            public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
                Log.i("Unity", "ads error "+s);
            }
        }, false);

        checkPre(savedInstanceState);
    }

    @Override
    public void onBackPressed(){

    }

    private void checkPre(Bundle savedInstanceState) {
        AndroidNetworking.get(URL)
                .setPriority(Priority.HIGH)
                .doNotCacheResponse()
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.e("ee", response.toString());
                            App.saveXstatus(response.getBoolean("x"));
                            startActivity(new Intent(SplashScreenActivity.this,
                                    HomeActivity.class));
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText(SplashScreenActivity.this,
                                "Connection error, please check internet connection",
                                Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

}
