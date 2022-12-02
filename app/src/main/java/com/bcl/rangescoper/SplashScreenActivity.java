package com.bcl.rangescoper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    private Animation topAnim, bottomAnim;
    private ImageView logo;
    private TextView name;
    private static int SPLASH_SCREEN = 2500;
    private Handler mHandler;
    private Runnable mUpdateTask;
    private Intent homeIntent;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Removes top bar
        setTheme(R.style.Theme_RangeScoper);

        //enables delayed activity load
        mHandler = new Handler();

        //Top & bottom anims for splash screen logo & text
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        // UI elements
        logo = findViewById(R.id.splash_logo);
        name = findViewById(R.id.splash_name);
        progressBar = findViewById(R.id.splash_progressBar);
        progressBar.setIndeterminate(true);

        //Enabling anims on UI elements
        logo.setAnimation(topAnim);
        name.setAnimation(bottomAnim);
        progressBar.setAnimation(bottomAnim);




        //Verifies user status and launches main activity if signed in, auth ui if not
        mUpdateTask = new Runnable() {
            @Override
            public void run() {

                    homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(homeIntent);

                finish();
            }
        };

        //Delayed the start of thr next activity
        mHandler.postDelayed(mUpdateTask, SPLASH_SCREEN);

    }
}