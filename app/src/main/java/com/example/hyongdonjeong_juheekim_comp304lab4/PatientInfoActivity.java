package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PatientInfoActivity extends AppCompatActivity {

    private PatientViewModel patientViewModel;
    private RecyclerView recyclerView;
    private GridView gridView;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    DatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        final PatientAdapter adapter = new PatientAdapter();
        recyclerView.setAdapter(adapter);

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patientViewModel.getAllPatient().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(@Nullable List<Patient> patients) {
                adapter.setPatients(patients);
            }
        });
    }

    public void addNewPatient (View v){
        Intent intent = new Intent(PatientInfoActivity.this, UpdatePatientActivity.class );
        startActivity(intent);
    }
}
