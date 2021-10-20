package com.whatsapp.chattema;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {


    private ProgressDialog progressDialog;
    LinearLayout lDownload;
    LinearLayout lHowDownload;
    LinearLayout lHowInstall;
    LinearLayout lYoutube;


    @Override
    protected void onDestroy() {
        //adView.destroy();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");


        lDownload = findViewById(R.id.l_download);
        lHowDownload = findViewById(R.id.l_how_download);
        lHowInstall = findViewById(R.id.l_how_install);
        lYoutube = findViewById(R.id.l_youtube);

        LinearLayout lWallpaper = findViewById(R.id.wall);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getString(R.string.banner));
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
            }
        });
        adView.loadAd(adRequest);
        RelativeLayout rl = findViewById(R.id.adView);
        rl.addView(adView);


        lDownload.setOnClickListener(view ->
                startActivity(new Intent(HomeActivity.this, DowActivity.class)));

        lHowDownload.setOnClickListener(view ->
                startActivity(new Intent(HomeActivity.this, HowDowActivity.class)));

        lHowInstall.setOnClickListener(view ->
                startActivity(new Intent(HomeActivity.this, HowInstActivity.class)));

        lWallpaper.setOnClickListener(view ->
                startActivity(new Intent(HomeActivity.this, WallActivity.class)));

        lYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://youtu.be/8saD3QxewVo";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


        //set
        lDownload.setVisibility(View.GONE);
        lHowDownload.setVisibility(View.GONE);
        lHowInstall.setVisibility(View.GONE);
        lYoutube.setVisibility(View.GONE);


        if (App.isX() && App.isTimeToCheck()) {
            getIp();
        } else setX();

    }


    void getIp() {
        progressDialog.show();
        AndroidNetworking.get("https://geoip-db.com/json/")
                .setPriority(Priority.HIGH)
                .doNotCacheResponse()
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("ee", response.toString());
                        progressDialog.dismiss();
                        try {

                            if (response.getString("country_code").equals("US")
                                    || response.getString("country_code").equals("SG")
                                    || response.getString("country_code").equals("CA")) {
                                App.saveIPStatus(false);
                                App.saveLastAttemp(new Date().getTime());
                            } else {
                                App.saveIPStatus(true);
                                App.saveLastAttemp(new Date().getTime());
                                setX();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        setX();
                        App.saveIPStatus(true);
                        Toast.makeText(HomeActivity.this,
                                "Connection error, please check internet connection",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    void setX() {
        Log.e("ee", Locale.getDefault().getLanguage().toUpperCase());
        if (App.isIPValid() && !Locale.getDefault().getLanguage().toUpperCase().equals("EN")) {
            //if (App.isIPValid()) {
            lDownload.setVisibility(View.VISIBLE);
            lHowDownload.setVisibility(View.VISIBLE);
            lHowInstall.setVisibility(View.VISIBLE);
            lYoutube.setVisibility(View.VISIBLE);
        }
    }

}
