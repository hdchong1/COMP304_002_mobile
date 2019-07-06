package com.example.hyongdonjeong_juheekim_comp304lab4;


import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PatientDao {
    @Insert
    void insert(Patient patient);

    @Update
    void update(Patient patient);

    @Query("Select * from Patient")
    LiveData<List<Patient>> getAllPatient();


//    @Query("SELECT * FROM Patient WHERE PatientId IN(:patientId)")
//    public abstract List findByIds(int[] patientId);
}
