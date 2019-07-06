package com.example.hyongdonjeong_juheekim_comp304lab4;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PatientRepository {

    private final PatientDao patientDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    public PatientRepository(PatientDao patientDao) {
        this.patientDao = patientDao;
    }
    private LiveData<List<Patient>> patientList;

    public PatientRepository(Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        patientDao = db.patientDao();
        patientList = patientDao.getAllPatient();
    }

    LiveData<List<Patient>> getAllPatient() { return patientList; }

    //public void insert(Patient patient) { insertAsync(patient); }
    public void insert(Patient patient) {
        new InsertPatientAsyncTask(patientDao).execute(patient);
    }

    public LiveData<Integer> getInsertResult() { return insertResult; }

    private void insertAsync(final Patient patient){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    patientDao.insert(patient);
                    insertResult.postValue(1);
                } catch (Exception e){
                    insertResult.postValue(0);
                }

            }
        }).start();
    }

    private static class InsertPatientAsyncTask extends AsyncTask<Patient, Void, Void> {
        private PatientDao patientDao;

        private InsertPatientAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Patient... patients) {
            patientDao.insert(patients[0]);
            return null;
        }
    }

}
