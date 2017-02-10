package com.green0.customerApp.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.ironman.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.green0.customerApp.model.Disease;
import com.green0.customerApp.model.ImageResultDisease;
import com.green0.customerApp.model.ImageResultDiseaseList;

import java.util.List;

public class DiseaseInfoActivity extends AppCompatActivity {
    TextView name,category,symptoms,symptomsText,bioctrl,bioctrlText,chemctrl,chemctrlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Sample Disease ");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("obj");
        Disease disease = (Disease) gson.fromJson(strObj, Disease.class);
        name = (TextView)findViewById(R.id.disease_name);
        category = (TextView)findViewById(R.id.disease_category);
        symptoms = (TextView)findViewById(R.id.disease_symptoms);
        symptomsText = (TextView)findViewById(R.id.disease_symptoms_text);
        bioctrl = (TextView)findViewById(R.id.disease_bioCtrl);
        bioctrlText = (TextView)findViewById(R.id.disease_bioCtrl_text);
        chemctrl = (TextView)findViewById(R.id.disease_chemCtrl);
        chemctrlText = (TextView)findViewById(R.id.disease_chemCtrl_txt);

        name.setText(disease.getName());
      //  category.setText(disease.get);
    }
}
