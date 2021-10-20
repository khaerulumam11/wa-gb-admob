package com.whatsapp.chattema;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.unity3d.ads.UnityAds;


public class StepDow extends Fragment {

    private static final String DOWN_URL = "https://github.com/colddrygame/wap/blob/master/README.md";
    private boolean isInterLoaded = false;
    private int loadVideoRetry = 0;
    private RewardedAd rewardedAd;
    private boolean isWatched = false;


    public StepDow() {
        // Required empty public constructor
    }


    public static StepOpening newInstance() {
        return new StepOpening();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rewardedAd = new RewardedAd(getActivity(),
                getString(R.string.reward));
        loadReward();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_step_download, container, false);

        view.findViewById(R.id.button_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // share
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "\n\n");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                        getString(R.string.share_body) + "http://play.google.com/store/apps/details?id=" + getActivity().getPackageName());
                startActivity(Intent.createChooser(sharingIntent, "Share Via"));
            }
        });

        view.findViewById(R.id.button_rate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // rate
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
            }
        });


        view.findViewById(R.id.button_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // more
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:\"COLDDRY GAME\""));
                startActivity(intent);
            }
        });

        view.findViewById(R.id.button_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(getActivity());
                builder.setMessage(getString(R.string.dialog_title));
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (rewardedAd.isLoaded()) {
                            Activity activityContext = getActivity();
                            RewardedAdCallback adCallback = new RewardedAdCallback() {
                                @Override
                                public void onRewardedAdFailedToShow(AdError adError) {
                                    super.onRewardedAdFailedToShow(adError);
                                }

                                @Override
                                public void onRewardedAdOpened() {
                                    // Ad opened.
                                    super.onRewardedAdOpened();
                                }

                                @Override
                                public void onRewardedAdClosed() {
                                    super.onRewardedAdClosed();
                                    // Ad closed.
                                    if (!isWatched)
                                        Toast.makeText(getActivity(),
                                                R.string.must_watch, Toast.LENGTH_LONG).show();
                                    else {
                                        setClipboard(getActivity(), DOWN_URL);
                                        AlertDialog.Builder builder =
                                                new AlertDialog.Builder(getActivity());
                                        builder.setMessage(getString(R.string.success_load));
                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                                setClipboard(getActivity(), DOWN_URL);
                                            }
                                        });
                                        builder.show();

                                    }
                                    loadReward();
                                }

                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem reward) {
                                    // User earned reward.
                                    isWatched = true;
                                    loadReward();
                                }

                            };
                            rewardedAd.show(activityContext, adCallback);
                        } else {
                            Log.d("TAG", "The rewarded ad wasn't loaded yet.");
                            loadVideoRetry += 1;
                            if (loadVideoRetry < 2) {
                                Toast.makeText(getContext(), "Sedang menyiapkan video, tunggu 20 detik, setelah itu pilih unduh", Toast.LENGTH_LONG).show();
                            } else {
                                if (UnityAds.isReady(getString(R.string.unity_inter))) {
                                    UnityAds.show(getActivity(), getString(R.string.unity_inter));
                                }
                                setClipboard(getActivity(), DOWN_URL);
                                AlertDialog.Builder builder =
                                        new AlertDialog.Builder(getActivity());
                                builder.setMessage(getString(R.string.success_load));
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        setClipboard(getActivity(), DOWN_URL);
                                    }
                                });
                                builder.show();
                            }
                        }

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();

                /*setClipboard(getActivity(), DOWN_URL);
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(getActivity());
                builder.setMessage(getString(R.string.success_load));
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        setClipboard(getActivity(), DOWN_URL);
                    }
                });
                builder.show();*/
            }
        });

        return view;
    }

    void loadReward() {

        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdFailedToLoad(LoadAdError loadAdError) {
                super.onRewardedAdFailedToLoad(loadAdError);
                Log.e("shit", "error "+loadAdError.getResponseInfo());
            }

            @Override
            public void onRewardedAdLoaded() {
                super.onRewardedAdLoaded();
                // Ad successfully loaded.
            }

        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);

        /*
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getActivity());
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {

            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
                if (!isWatched)
                    Toast.makeText(getActivity(),
                            R.string.must_watch, Toast.LENGTH_LONG).show();
                else {
                    setClipboard(getActivity(), DOWN_URL);
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(getActivity());
                    builder.setMessage(getString(R.string.success_load));
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            setClipboard(getActivity(), DOWN_URL);
                        }
                    });
                    builder.show();

                }
                loadRewardedVideoAd();
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                // open link
                isWatched = true;
                loadRewardedVideoAd();
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }

            @Override
            public void onRewardedVideoCompleted() {

            }
        }); */


    }

    private void setClipboard(Context context, String text) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
    }


}
