package com.bcl.rangescoper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SightingSessionActivity extends Activity {

    private float diameter;
    private Button dispResultButton;
    private Button cameraButton;
    private float screenHeight;
    private float screenWidth;
    private ScopeSightApp ssapp;

    public Target f30t;
    private ConstraintLayout targetLayout;
    public TargetView targetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sighting_session);

        this.targetView = new TargetView(this);
        this.ssapp = (ScopeSightApp) getApplication();
        this.targetLayout = (ConstraintLayout) findViewById(R.id.targetLayout);
        this.f30t = this.ssapp.getTarget();

        this.dispResultButton = (Button) findViewById(R.id.displayResultButton);
        this.dispResultButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SightingSessionActivity.this.dispResultButtonClicked();
            }
        });

        if (ContextCompat.checkSelfPermission(SightingSessionActivity.this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SightingSessionActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);

        }

        this.cameraButton = findViewById(R.id.camerabutton);
        this.cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 100);

            }
        });

        this.screenWidth = this.ssapp.getDeviceWidth();
        this.screenHeight = this.ssapp.getDeviceHeight();
        if (this.screenWidth < this.screenHeight) {
            this.diameter = this.screenWidth;
        } else {
            this.diameter = this.screenHeight;
        }
        this.targetView = new TargetView(this.diameter, this.screenWidth / 2.0f, this.screenWidth / 2.0f, this);
        this.targetView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getActionMasked() != 0) {
                    return true;
                }
                SightingSessionActivity.this.f30t.addHit(new Hit(motionEvent.getX(), motionEvent.getY()));
                SightingSessionActivity.this.targetView.addHit(motionEvent.getX(), motionEvent.getY());
                SightingSessionActivity.this.targetView.invalidate();
                return true;
            }
        });
        this.f30t.setCenterX(this.screenWidth / 2.0f);
        this.f30t.setCenterY((float) (this.targetLayout.getHeight() / 2));
        this.f30t.setPixelDiameter(this.diameter);
        this.targetLayout.addView(this.targetView);
        this.dispResultButton.bringToFront();
    }

    public void dispResultButtonClicked() {
        this.ssapp.setTarget(this.f30t);
        this.ssapp.calculate();
        startActivity(new Intent(this, SessionResultsActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100){
            Bitmap cameraBitmap = (Bitmap) data.getExtras().get("data");
            Drawable cameraDrawable = new BitmapDrawable(getResources(), cameraBitmap);
            this.targetView.setBackground(cameraDrawable);
        }
    }
}