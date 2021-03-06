package com.github.paolorotolo.appintro;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.github.paolorotolo.appintro.internal.LogHelper;

import androidx.annotation.ColorInt;

public abstract class AppIntro2 extends AppIntroBase {
    private static final String TAG = LogHelper.makeLogTag(AppIntro2.class);

    protected View customBackgroundView;
    protected FrameLayout backgroundFrame;
    protected LinearLayout addHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backgroundFrame = findViewById(R.id.background);
        addHolder = findViewById(R.id.adholder);
    }


    public LinearLayout getAddholder(){
        return addHolder;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.appintro_intro_layout2;
    }

    /**
     * Shows or hides Done button, replaced with setProgressButtonEnabled
     *
     * @deprecated use {@link #setProgressButtonEnabled(boolean)} instead.
     */
    @Deprecated
    public void showDoneButton(boolean showDone) {
        setProgressButtonEnabled(showDone);
    }

    /**
     * Override viewpager bar color
     *
     * @param color your color resource
     */
    public void setBarColor(@ColorInt final int color) {
        LinearLayout bottomBar = findViewById(R.id.bottom);
        bottomBar.setBackgroundColor(color);
    }

    /**
     * Override Next button
     *
     * @param imageSkipButton your drawable resource
     */
    public void setImageSkipButton(final Drawable imageSkipButton) {
        final ImageButton nextButton = findViewById(R.id.skip);
        nextButton.setImageDrawable(imageSkipButton);

    }

    public void setBackgroundView(View view) {
        customBackgroundView = view;
        if (customBackgroundView != null) {
            backgroundFrame.addView(customBackgroundView);
        }
    }
}
