package com.example.hyongdonjeong_juheekim_comp304lab4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientHolder> {
    private List<Patient> patients = new ArrayList<>();

    @NonNull
    @Override
    public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_patient_info, parent, false);
        return new PatientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientHolder holder, int position) {
        Patient currentPatient = patients.get(position);
        holder.textVIewPatientId.setText(currentPatient.getPatientId());
        holder.textViewFirstName.setText(currentPatient.getFirstname());
        holder.textViewLastName.setText(currentPatient.getLastname());
        holder.textViewDepartment.setText(String.valueOf(currentPatient.getDepartment()));
        holder.textViewNurseId.setText(String.valueOf(currentPatient.getNurseId()));
        holder.textViewRoom.setText(String.valueOf(currentPatient.getRoom()));

    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
        notifyDataSetChanged();
    }

    class PatientHolder extends RecyclerView.ViewHolder {
        private TextView textVIewPatientId;
        private TextView textViewFirstName;
        private TextView textViewLastName;
        private TextView textViewDepartment;
        private TextView textViewNurseId;
        private TextView textViewRoom;

        public PatientHolder(View itemView) {
            super(itemView);
            textVIewPatientId = itemView.findViewById(R.id.textView_p_patientId);
            textViewFirstName = itemView.findViewById(R.id.textView_p_fName);
            textViewLastName = itemView.findViewById(R.id.textView_p_lName);
            textViewDepartment = itemView.findViewById(R.id.textView_p_dept);
            textViewNurseId = itemView.findViewById(R.id.textView_p_nurseId);
            textViewRoom = itemView.findViewById(R.id.textView_p_room);
        }
    }
}