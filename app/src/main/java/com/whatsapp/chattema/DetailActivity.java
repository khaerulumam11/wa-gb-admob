package com.whatsapp.chattema;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.os.Bundle;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.edmodo.cropper.CropImageView;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity {

    public static final String INTENT_IMAGE = "intent.image";
    private View bannerView;
    private boolean isInterLoaded = false;


    @Override
    protected void onDestroy() {
        if(bannerView != null) UnityBanners.destroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        // RelativeLayout adHolder = findViewById(R.id.adView);

        UnityBanners.setBannerListener(new IUnityBannerListener() {
            @Override
            public void onUnityBannerLoaded(String s, View view) {
                bannerView = view;
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

        CropImageView cropImageView = findViewById(R.id.cropImageView);

        Glide.with(this)
                .asBitmap()
                .load(getIntent().getStringExtra(INTENT_IMAGE).replace("_thumb", ""))
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        cropImageView.setImageBitmap(resource);
                    }
                });

        findViewById(R.id.apply_button).setOnClickListener(view -> {
            WallpaperManager wpm = WallpaperManager.getInstance(DetailActivity.this);
            try {
                Toast.makeText(DetailActivity.this, "Applying...", Toast.LENGTH_SHORT).show();
                wpm.setBitmap(cropImageView.getCroppedImage());
                Toast.makeText(DetailActivity.this, "Wallpaper applied", Toast.LENGTH_SHORT).show();
                if (UnityAds.isReady (getString(R.string.unity_inter))) {
                    UnityAds.show (this, getString(R.string.unity_inter));
                }
            } catch (IOException e) {
                e.printStackTrace();
                ;
                Toast.makeText(DetailActivity.this, "Failed to change Wallpaper", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadInter() {

    }

}
