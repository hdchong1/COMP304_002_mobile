package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePatientActivity extends AppCompatActivity {
    private PatientViewModel patientViewModel;
    private Button button_update;
    private EditText editText_fname, editText_lname, editText_p_dept, editText_p_room;
    Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient);
        
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patient = new Patient();
        
        patientViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1){
                    Toast.makeText(UpdatePatientActivity.this,"Patient successfully saved", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UpdatePatientActivity.this,"Error saving patient", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_update = findViewById(R.id.button_update);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_fname = findViewById(R.id.editText_fname);
                editText_lname = findViewById(R.id.editText_lname);
                editText_p_dept = findViewById(R.id.editText_p_dept);
                editText_p_room = findViewById(R.id.editText_p_room);

                patient.setFirstname(editText_fname.getText().toString());
                patient.setLastname(editText_lname.getText().toString());
                patient.setDepartment(editText_p_dept.getText().toString());
                patient.setNurseId(1);
                patient.setRoom(editText_p_room.getText().toString());

                patientViewModel.insert(patient);
            }
        });        
        
    }
//    public void updatePatient (View v){
//        displayToast("The patient information has been updated!");
//    }
//
//    public void displayToast(String message){
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//    }

}
