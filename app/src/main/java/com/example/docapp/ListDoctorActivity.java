package com.example.docapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListDoctorActivity extends AppCompatActivity {
    private RecyclerView rDokter;
    private FirebaseFirestore db;
    private List<Doctor> doctorList;
    private DoctorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_doctor);

        rDokter = findViewById(R.id.rDokter);
        rDokter.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        doctorList = new ArrayList<>();
        adapter = new DoctorAdapter(doctorList);
        rDokter.setAdapter(adapter);

        loadDoctors();
    }

    private void loadDoctors() {
        db.collection("doctors")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        doctorList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String avatar = document.getString("avatar");
                            String nama = document.getString("nama");
                            String profil = document.getString("profil");
                            Doctor doctor = new Doctor(avatar, nama, profil);
                            doctorList.add(doctor);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.d("ListDoctorActivity", "Error getting documents: ", task.getException());
                    }
                });
    }

    private class Doctor {
        private String avatar;
        private String nama;
        private String profil;

        public Doctor(String avatar, String nama, String profil) {
            this.avatar = avatar;
            this.nama = nama;
            this.profil = profil;
        }

        // getters
        public String getAvatar() { return avatar; }
        public String getNama() { return nama; }
        public String getProfil() { return profil; }
    }

    private class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {
        private List<Doctor> doctorList;

        public DoctorAdapter(List<Doctor> doctorList) {
            this.doctorList = doctorList;
        }

        @NonNull
        @Override
        public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_doctor, parent, false);
            return new DoctorViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
            Doctor doctor = doctorList.get(position);
            Glide.with(ListDoctorActivity.this).load(doctor.getAvatar()).into(holder.imgdokter);
            holder.drname.setText(doctor.getNama());
            holder.desc.setText(doctor.getProfil());
        }

        @Override
        public int getItemCount() {
            return doctorList.size();
        }

        class DoctorViewHolder extends RecyclerView.ViewHolder {
            ImageView imgdokter;
            TextView drname;
            TextView desc;

            public DoctorViewHolder(@NonNull View itemView) {
                super(itemView);
                imgdokter = itemView.findViewById(R.id.imgdokter);
                drname = itemView.findViewById(R.id.drname);
                desc = itemView.findViewById(R.id.desc);
            }
        }
    }
}