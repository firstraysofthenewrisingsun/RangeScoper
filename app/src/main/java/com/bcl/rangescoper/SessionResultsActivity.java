package com.bcl.rangescoper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SessionResultsActivity extends AppCompatActivity {

    Button homeButton;
    Button newSessionButton;

    /* renamed from: r */
    Result f29r;
    ScopeSightApp ssapp = ((ScopeSightApp) getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_results);

        this.ssapp = (ScopeSightApp) getApplication();
        this.f29r = this.ssapp.getResult();
        ((TextView) findViewById(R.id.elevationClicks)).setText("" + this.f29r.getElevationClicks());
        ((TextView) findViewById(R.id.windageClicks)).setText("" + this.f29r.getWindageClicks());
        ((TextView) findViewById(R.id.elevationDirection)).setText(this.f29r.getElevationRotationDirection());
        ((TextView) findViewById(R.id.windageDirection)).setText(this.f29r.getWindageRotationDirection());
        this.homeButton = (Button) findViewById(R.id.homeButton);
        this.homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SessionResultsActivity.this.homeButtonClicked();
            }
        });
        this.newSessionButton = (Button) findViewById(R.id.newSessionButton);
        this.newSessionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SessionResultsActivity.this.newSessionButtonClicked();
            }
        });
    }

    public void homeButtonClicked() {
        startActivity(new Intent(this, Home.class));
    }

    public void newSessionButtonClicked() {
        this.ssapp.setTarget(new Target());
        startActivity(new Intent(this, SightingSessionActivity.class));
    }
}