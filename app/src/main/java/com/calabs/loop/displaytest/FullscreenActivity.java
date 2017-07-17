package com.calabs.loop.displaytest;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private static final String TAG = FullscreenActivity.class.getSimpleName();

    private FrameLayout mContentView;
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mContentView = (FrameLayout)findViewById(R.id.main_container);
        fillContainer(mContentView);
    }

    private void fillContainer(FrameLayout containerToFill) {
        final FrameLayout newView = createAndAddContainerToParent(containerToFill);
        mContentView.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Log.i(TAG, "New Height: " + newView.getHeight());
                if (newView.getHeight() > 5) {
                    fillContainer(newView);
                }
            }
        },16);
    }

    private FrameLayout createAndAddContainerToParent(ViewGroup parent) {
        FrameLayout fl = new FrameLayout(this);
        fl.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        fl.setPadding(5, 5, 5, 5);
        // If you want to switch back to greys
        // int value = (int) Math.floor(Math.random() * 256);
        // fl.setBackgroundColor(Color.rgb(value, value, value));
        fl.setBackgroundColor(Color.rgb((int) Math.floor(Math.random() * 256), (int) Math.floor(Math.random() * 256), (int) Math.floor(Math.random() * 256)));
        parent.addView(fl);
        return fl;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

}
