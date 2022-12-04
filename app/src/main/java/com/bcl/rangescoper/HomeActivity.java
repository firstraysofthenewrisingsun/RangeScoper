package com.bcl.rangescoper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class HomeActivity extends AppCompatActivity {

    Button aboutButton;
    Context context;
    Button helpButton;
    Button newSessionButton;
    ArrayList<Range> rangeList;
    Spinner rangeSpinner;
    Button scopeAndRangeManagerButton;
    ArrayList<Scope> scopeList;
    Spinner scopeSpinner;
    private ScopeSightApp ssapp;
    TextView title;
    Button unitsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        this.ssapp = (ScopeSightApp) getApplication();
        setContentView(R.layout.activity_home);

        this.scopeAndRangeManagerButton = (Button) findViewById(R.id.scopeAndRangeManagerButton);
        this.scopeAndRangeManagerButton.setOnClickListener(new View.OnClickListener() { // from class: com.reddington.scopesighter.HomeActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeActivity.this.launchScopeAndRangeManager();
            }
        });
        this.rangeSpinner = (Spinner) findViewById(R.id.rangeSpinner);
        this.rangeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.reddington.scopesighter.HomeActivity.3
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                HomeActivity.this.setSavableActive(HomeActivity.this.rangeList.get(i));
            }
        });
        this.scopeSpinner = (Spinner) findViewById(R.id.scopeSpinner);
        this.scopeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.reddington.scopesighter.HomeActivity.4
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                HomeActivity.this.setSavableActive(HomeActivity.this.scopeList.get(i));
            }
        });
        this.newSessionButton = (Button) findViewById(R.id.newSessionButton);
        this.newSessionButton.setOnClickListener(new View.OnClickListener() { // from class: com.reddington.scopesighter.HomeActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeActivity.this.launchNewSession();
            }
        });

        this.ssapp.ensurePopulated();
        updateSpinners();
    }

    public void launchNewSession() {
        if (!this.ssapp.getActiveScope().getName().equals("Add a Scope") && !this.ssapp.getActiveRange().getName().equals("Add a Range")) {
            ConstraintLayout tableLayout = (ConstraintLayout) findViewById(R.id.mainTableLayout);
            this.ssapp.setDeviceWidth(tableLayout.getWidth());
            this.ssapp.setDeviceHeight(tableLayout.getHeight());
            this.ssapp.setTarget(new Target());
            startActivity(new Intent(this, SightingSessionActivity.class));
            return;
        }
        Toast.makeText(getApplicationContext(), "Please choose or create your scope and range!", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSavableActive(Savable savable) {
        if (savable instanceof Range) {
            this.ssapp.setActiveRange(savable.getName());
        } else {
            this.ssapp.setActiveScope(savable.getName());
        }
    }

    private void updateSpinners() {
        this.rangeList = this.ssapp.getRanges();
        this.scopeList = this.ssapp.getScopes();
        ArrayList arrayList = new ArrayList();
        Iterator<Range> it = this.rangeList.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getName());
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<Scope> it2 = this.scopeList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(it2.next().getName());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.rangeSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        this.rangeSpinner.setSelection(arrayList.indexOf(this.ssapp.getActiveRange().getName()));
        this.rangeSpinner.invalidate();
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.scopeSpinner.setAdapter((SpinnerAdapter) arrayAdapter2);
        this.scopeSpinner.setSelection(arrayList2.indexOf(this.ssapp.getActiveScope().getName()));
        this.scopeSpinner.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchScopeAndRangeManager() {
        startActivity(new Intent(this, SavableScopeRangeDataActivity.class));
    }
}