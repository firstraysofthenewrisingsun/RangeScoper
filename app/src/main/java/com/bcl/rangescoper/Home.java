package com.bcl.rangescoper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class Home extends Activity {

    Button aboutButton;
    Context context;
    Button helpButton;
    Button newSessionButton;
    ArrayList<Range> rangeList;
    Spinner rangeSpinner;
    Button scopeAndRangeManagerButton;
    ArrayList<Scope> scopeList;
    Spinner scopeSpinner;
    /* access modifiers changed from: private */
    public ScopeSightApp ssapp;
    TextView title;
    Button unitsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context = this;
        this.ssapp = (ScopeSightApp) getApplication();
        setContentView(R.layout.activity_home);

        this.scopeAndRangeManagerButton = (Button) findViewById(R.id.scopeAndRangeManagerButton);
        this.scopeAndRangeManagerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Home.this.launchScopeAndRangeManager();
            }
        });



        this.newSessionButton = (Button) findViewById(R.id.newSessionButton);
        this.newSessionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Home.this.launchNewSession();
            }
        });

        this.ssapp.ensurePopulated();
    }

    public void launchNewSession() {
        if (this.ssapp.getActiveScope().getName().equals("Add a Scope") || this.ssapp.getActiveRange().getName().equals("Add a Range")) {
            Toast.makeText(getApplicationContext(), "Please choose or create your scope and range!", 0).show();
            return;
        }
        TableLayout tableLayout = (TableLayout) findViewById(R.id.mainTableLayout);
        this.ssapp.setDeviceWidth((float) tableLayout.getWidth());
        this.ssapp.setDeviceHeight((float) tableLayout.getHeight());
        this.ssapp.setTarget(new Target());
        startActivity(new Intent(this, SightingSessionActivity.class));
    }



    /* access modifiers changed from: private */
    public void launchScopeAndRangeManager() {
        startActivity(new Intent(this, SavableScopeRangeDataActivity.class));
    }

    public void onResume() {
        super.onResume();

    }
}