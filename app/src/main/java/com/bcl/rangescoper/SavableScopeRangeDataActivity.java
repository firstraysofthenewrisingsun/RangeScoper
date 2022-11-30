package com.bcl.rangescoper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class SavableScopeRangeDataActivity extends Activity {
    Button backButton;
    Button clockwiseLeftToggleButton;
    Button clockwiseUpToggleButton;
    Button deleteRangeButton;
    Button deleteScopeButton;

    /* renamed from: df */
    DecimalFormat f26df = new DecimalFormat("@####");
    EditText feetToTargetEditText;
    EditText oneClickEqualsEditText;
    EditText rangeNameEditText;
    Spinner rangeSpinner;
    Button saveRangeButton;
    Button saveScopeButton;
    EditText scopeNameEditText;
    Spinner scopeSpinner;
    ScopeSightApp ssapp;
    EditText targetDiameterEditText;
    EditText yardsForAdjustEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.ssapp = (ScopeSightApp) getApplication();
        setContentView(R.layout.activity_savable_scope_range_data);

        this.scopeSpinner = findViewById(R.id.spin_scope);
        this.scopeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SavableScopeRangeDataActivity.this.ssapp.setActiveScope((String) adapterView.getItemAtPosition(i));
                SavableScopeRangeDataActivity.this.updateScopeControls();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        this.rangeSpinner = findViewById(R.id.spin_range);
        this.rangeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SavableScopeRangeDataActivity.this.ssapp.setActiveRange((String) adapterView.getItemAtPosition(i));
                SavableScopeRangeDataActivity.this.updateRangeControls();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        this.clockwiseUpToggleButton = findViewById(R.id.btn_elev_change);
        this.clockwiseUpToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SavableScopeRangeDataActivity.this.clockwiseUpToggleButton.getText().toString().equals("Up")) {
                    SavableScopeRangeDataActivity.this.clockwiseUpToggleButton.setText("Down");
                } else {
                    SavableScopeRangeDataActivity.this.clockwiseUpToggleButton.setText("Up");
                }
            }
        });

        this.clockwiseLeftToggleButton = findViewById(R.id.btn_wind_change);
        this.clockwiseLeftToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SavableScopeRangeDataActivity.this.clockwiseLeftToggleButton.getText().toString().equals("Left")) {
                    SavableScopeRangeDataActivity.this.clockwiseLeftToggleButton.setText("Right");
                } else {
                    SavableScopeRangeDataActivity.this.clockwiseLeftToggleButton.setText("Left");
                }
            }
        });

        this.saveScopeButton = findViewById(R.id.btn_save_scope);
        this.saveScopeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SavableScopeRangeDataActivity.this.saveScope();
            }
        });

        this.saveRangeButton = findViewById(R.id.btn_save_range);
        this.saveRangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SavableScopeRangeDataActivity.this.saveRange();
            }
        });

        this.deleteScopeButton = findViewById(R.id.btn_del_scope);
        this.deleteScopeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SavableScopeRangeDataActivity.this.deleteScope();
            }
        });

        this.deleteRangeButton = findViewById(R.id.btn_del_range);
        this.deleteRangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SavableScopeRangeDataActivity.this.deleteRange();
            }
        });

        this.backButton = findViewById(R.id.btn_bck);
        this.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SavableScopeRangeDataActivity.this.backButtonPressed();
            }
        });

        this.scopeNameEditText = findViewById(R.id.txt_scope_name);
        this.oneClickEqualsEditText = findViewById(R.id.txt_shotplcmntchge);
        this.yardsForAdjustEditText = findViewById(R.id.txt_targetdistance);
        this.rangeNameEditText = findViewById(R.id.txt_range_name);
        this.feetToTargetEditText = findViewById(R.id.txt_ft_to_target);
        this.targetDiameterEditText = findViewById(R.id.txt_target_diameter);

        updateSpinners();
        setUnitLabels();
    }

    public void onResume() {

        super.onResume();

        setUnitLabels();
        updateScopeControls();
        updateRangeControls();
    }

    private void setUnitLabels() {
        //Updates labels with instructional text

    }

    public void updateScopeControls() {

        Scope activeScope = this.ssapp.getActiveScope();
        this.scopeNameEditText.setText(activeScope.getName());
        if (activeScope.getClockwiseOffsetsLeft()) {
            this.clockwiseLeftToggleButton.setText(getString(R.string.toggleLeft));
        } else {
            this.clockwiseLeftToggleButton.setText(getString(R.string.toggleRight));
        }
        if (activeScope.getClockwiseOffsetsUp()) {
            this.clockwiseUpToggleButton.setText(getString(R.string.toggleUp));
        } else {
            this.clockwiseUpToggleButton.setText(getString(R.string.toggleDown));
        }
        this.oneClickEqualsEditText.setText(this.f26df.format(activeScope.getOffsetPerClick()));
        this.yardsForAdjustEditText.setText(this.f26df.format(activeScope.getDistanceForAdjust()));

    }

    public void updateRangeControls() {
        Range activeRange = this.ssapp.getActiveRange();
        this.rangeNameEditText.setText(activeRange.getName());
        this.feetToTargetEditText.setText(this.f26df.format((double) activeRange.getDistanceToTarget()));
        this.targetDiameterEditText.setText(this.f26df.format((double) activeRange.getTargetDiameter()));
    }

    private void updateSpinners() {
        ArrayList<Range> ranges = this.ssapp.getRanges();
        ArrayList<Scope> scopes = this.ssapp.getScopes();
        ArrayList arrayList = new ArrayList();
        Iterator<Range> it = ranges.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getName());
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<Scope> it2 = scopes.iterator();
        while (it2.hasNext()) {
            arrayList2.add(it2.next().getName());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, 17367048, arrayList);
        arrayAdapter.setDropDownViewResource(17367049);
        this.rangeSpinner.setAdapter(arrayAdapter);
        this.rangeSpinner.setSelection(arrayList.indexOf(this.ssapp.getActiveRange().getName()));
        updateRangeControls();
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, 17367048, arrayList2);
        arrayAdapter2.setDropDownViewResource(17367049);
        this.scopeSpinner.setAdapter(arrayAdapter2);
        this.scopeSpinner.setSelection(arrayList2.indexOf(this.ssapp.getActiveScope().getName()));
        arrayAdapter2.notifyDataSetChanged();
        updateScopeControls();
    }

    private boolean validateScopeAttributes() {
        if (!this.scopeNameEditText.getText().toString().equals("") && !this.yardsForAdjustEditText.getText().toString().equals("") && !this.oneClickEqualsEditText.getText().toString().equals("")) {
            return true;
        }
        Toast.makeText(getApplicationContext(), "Check Scope Inputs", 1).show();
        return false;
    }

    private boolean validateRangeAttributes() {
        if (!this.rangeNameEditText.getText().toString().equals("") && !this.feetToTargetEditText.getText().toString().equals("") && !this.targetDiameterEditText.getText().toString().equals("")) {
            return true;
        }
        Toast.makeText(getApplicationContext(), "Check Range Inputs", 1).show();
        return false;
    }

    public void backButtonPressed() {
        startActivity(new Intent(this, Home.class));
    }

    public void saveScope() {
        if (validateScopeAttributes()) {
            boolean equals = this.clockwiseUpToggleButton.getText().equals("Up");
            Scope scope = new Scope(this.scopeNameEditText.getText().toString(), Double.parseDouble(this.oneClickEqualsEditText.getText().toString()), Double.parseDouble(this.yardsForAdjustEditText.getText().toString()), this.clockwiseLeftToggleButton.getText().equals("Left"), equals);
            Iterator<Scope> it = this.ssapp.getScopes().iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (scope.equals(it.next())) {
                    z = true;
                }
            }
            if (z) {
                Toast.makeText(getApplicationContext(), "Scope Saved", 0).show();
                return;
            }
            this.ssapp.updateSavables(scope);
            updateSpinners();
            Toast.makeText(getApplicationContext(), "Scope Saved", 0).show();
        }
    }

    public void deleteScope() {
        updateScopeControls();
        if (validateScopeAttributes()) {
            boolean equals = this.clockwiseUpToggleButton.getText().equals("Up");
            Scope scope = new Scope(this.scopeNameEditText.getText().toString(), Double.parseDouble(this.oneClickEqualsEditText.getText().toString()), Double.parseDouble(this.yardsForAdjustEditText.getText().toString()), this.clockwiseLeftToggleButton.getText().equals("Left"), equals);
            Iterator<Scope> it = this.ssapp.getScopes().iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (scope.equals(it.next())) {
                    z = true;
                }
            }
            if (!z) {
                Toast.makeText(getApplicationContext(), "No scope with specified attributes exists. Reselect scope and press delete.", 0).show();
                return;
            }
            this.ssapp.updateSavables(scope);
            updateSpinners();
            Toast.makeText(getApplicationContext(), "Scope Deleted", 0).show();
        }
    }

    public void saveRange() {
        if (validateRangeAttributes()) {
            Range range = new Range(Float.parseFloat(this.feetToTargetEditText.getText().toString()), Float.parseFloat(this.targetDiameterEditText.getText().toString()), this.rangeNameEditText.getText().toString());
            Iterator<Range> it = this.ssapp.getRanges().iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (range.equals(it.next())) {
                    z = true;
                }
            }
            if (z) {
                Toast.makeText(getApplicationContext(), "Range Saved", 0).show();
                return;
            }
            this.ssapp.updateSavables(range);
            updateRangeControls();
            updateSpinners();
            Toast.makeText(getApplicationContext(), "Range Saved", 0).show();
        }
    }

    public void deleteRange() {
        updateRangeControls();
        if (validateRangeAttributes()) {
            Range range = new Range(Float.parseFloat(this.feetToTargetEditText.getText().toString()), Float.parseFloat(this.targetDiameterEditText.getText().toString()), this.rangeNameEditText.getText().toString());
            Iterator<Range> it = this.ssapp.getRanges().iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (range.equals(it.next())) {
                    z = true;
                }
            }
            if (!z) {
                Toast.makeText(getApplicationContext(), "No range with specified attributes exists. Reselect range and press delete.", 0).show();
                return;
            }
            this.ssapp.updateSavables(range);
            updateSpinners();
            Toast.makeText(getApplicationContext(), "Range Deleted", 0).show();
        }



}