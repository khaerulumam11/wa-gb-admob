package com.whatsapp.chattema;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro2;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;


public class DowActivity extends AppIntro2 {

    private View bannerView;
    private boolean isInterLoaded = false;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);


        addSlide(new StepOpening());
        addSlide(new StepShow1());
        addSlide(new StepShow2());
        addSlide(new StepShow3());
        addSlide(new StepShow4());
        addSlide(new StepDow());

        setBarColor(getResources().getColor(R.color.colorPrimaryDark));


        showSkipButton(false);


        UnityBanners.setBannerListener(new IUnityBannerListener() {
            @Override
            public void onUnityBannerLoaded(String s, View view) {
                bannerView = view;
                if (getAddholder().getChildCount() == 0)
                    getAddholder().addView(view);
                else {
                    getAddholder().removeViewAt(0);
                    getAddholder().addView(view);
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
        UnityBanners.loadBanner(this, getString(R.string.unity_banner));


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


        loadInter3();

    }

    private void loadInter3() {
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


    }


    @Override
    public void onDestroy() {
        if (bannerView != null) UnityBanners.destroy();
        super.onDestroy();
    }


    @Override
    protected int getLayoutId() {
        return super.getLayoutId();
    }


    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        //if (mInterstitialAd.isLoaded()) {
        //    mInterstitialAd.show();
        // }
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        if (newFragment instanceof StepShow1 || newFragment instanceof StepShow3 || newFragment instanceof StepDow) {
            /*if (UnityAds.isReady(getString(R.string.unity_inter))) {
                UnityAds.show(this, getString(R.string.unity_inter));
            }*/
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                loadInter3();
            } else {
                if (UnityAds.isReady(getString(R.string.unity_inter))) {
                    UnityAds.show(this, getString(R.string.unity_inter));
                }
            }
        }

    }

}
