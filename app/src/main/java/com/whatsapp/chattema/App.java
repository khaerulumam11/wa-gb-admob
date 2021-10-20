package com.whatsapp.chattema;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Date;

import androidx.multidex.MultiDex;

import okhttp3.OkHttpClient;

public class App extends Application {

    private static String IS_FROM_ID = "from_valid_source";
    private static String LAST_ATTEMPT = "last.attempt.x";
    private static String IS_X = "is_x.";

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .build();
        AndroidNetworking.initialize(getApplicationContext(), okHttpClient);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.i("Admob", initializationStatus.toString());
            }
        });

        preferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        editor = preferences.edit();



    }





    public static void saveIPStatus(boolean state) {
        editor.putBoolean(IS_FROM_ID, state);
        editor.commit();
        editor.apply();
    }


    public static void saveLastAttemp(long milis) {
        editor.putLong(LAST_ATTEMPT, milis);
        editor.commit();
        editor.apply();
    }

    public static void saveXstatus(boolean s){
        editor.putBoolean(IS_X, s);
        editor.commit();
        editor.apply();
    }

    public static boolean isX(){
        return preferences.getBoolean(IS_X, false);
        //return true;
    }


    public static boolean isIPValid() {
        return preferences.getBoolean(IS_FROM_ID, false);
    }

    public static boolean isTimeToCheck() {
        final double threshould = 2;
        double diff = threshould;
        long lasMillis = preferences.getLong(LAST_ATTEMPT, 0);
        if (lasMillis > 0) {
            Date lasDate = new Date(lasMillis);
            Date currentDate = new Date();
            diff = hoursDifference(currentDate, lasDate);
        }

        return diff >= threshould;
    }



    private static double hoursDifference(Date date1, Date date2) {

        final int MILLI_TO_HOUR = 1000 * 60 * 60;
        return (date1.getTime() - date2.getTime()) / MILLI_TO_HOUR;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
