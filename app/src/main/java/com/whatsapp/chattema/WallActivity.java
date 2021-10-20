package com.whatsapp.chattema;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;


public class WallActivity extends AppCompatActivity implements ImageAdapter.OnDataSelected {

    private View bannerView;
    private boolean isInterLoaded = false;
    private InterstitialAd mInterstitialAd;


    @Override
    public void onBackPressed(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        super.onBackPressed();
    }

    private int toPixelUnits(int dipUnit) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dipUnit * density);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //startAppAd.onSaveInstanceState(savedInstanceState);
        setContentView(R.layout.activity_wall);

        UnityBanners.setBannerListener(new IUnityBannerListener() {
            @Override
            public void onUnityBannerLoaded(String s, View view) {
                bannerView = view;
                //((ViewGroup) findViewById(R.id.adView)).addView(view);
                if (((ViewGroup) findViewById(R.id.adView)).getChildCount() == 0)
                    ((ViewGroup) findViewById(R.id.adView)).addView(view);
                else {
                    ((ViewGroup) findViewById(R.id.adView)).removeViewAt(0);
                    ((ViewGroup) findViewById(R.id.adView)).addView(view);
                }
            }

            @Override
            public void onUnityBannerUnloaded(String s) {
                bannerView = null;
            }

            @Override
            public void onUnityBannerShow(String s) {

            }

            @Override
            public void onUnityBannerClick(String s) {

            }

            @Override
            public void onUnityBannerHide(String s) {

            }

            @Override
            public void onUnityBannerError(String s) {

            }
        });
        UnityBanners.loadBanner (this, getString(R.string.unity_banner));

        loadInter();

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<String> uris = new ArrayList<>();
        try {
            final String[] files = getResources().getAssets().list("wall");
            for (int i = 0; i < files.length; i++) {
                //Log.e("file", files[i]);
                if(files[i].contains("_thumb")) {
                    uris.add("file:///android_asset/wall/" + files[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageAdapter adapter = new ImageAdapter(this, uris, this);
        recyclerView.setAdapter(adapter);


    }

    private void loadInter() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.inter));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
            }

            @Override
            public void onAdClosed() {
                // mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
        if (UnityAds.isReady (getString(R.string.unity_inter))) {
            UnityAds.show (this, getString(R.string.unity_inter));
        }
    }


    @Override
    public void onSelected(String model) {

    }
}
