package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private NurseViewModel nurseViewModel;
    private Button btnLogin;
    private EditText etUserId, etPassword;

    private AppDatabase db;
    private NurseDao nurseDao;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            nurseViewModel = ViewModelProviders.of(this).get(NurseViewModel.class);

            db = Room.databaseBuilder(this, AppDatabase.class, "mi-database.db")
                    .allowMainThreadQueries()
                    .build();

            nurseDao = db.nurseDao();


            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    etUserId = findViewById(R.id.editText_username);
                    etPassword = findViewById(R.id.editText_password);

//                    Nurse nurse = nurseViewModel.getNurse(Integer.parseInt(etUserId.getText().toString()), etPassword.getText().toString());
                    Nurse nurse = nurseDao.getNurse(Integer.parseInt(etUserId.getText().toString()));
                    if(nurse!=null){
                        Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class );
    //                        intent.putExtra("Nurse", nurse);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this, "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        public void register (View v){
            Intent intent = new Intent(MainActivity.this, Main2Activity.class );
            startActivity(intent);
        }

        public void logIn (View v){
            Intent intent = new Intent(MainActivity.this, PatientInfoActivity.class );
            startActivity(intent);
        }
    }
