package com.bcl.rangescoper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DecimalFormat;

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

    private void setUnitLabels(){
        //Updates labels with instructional text

    }



}