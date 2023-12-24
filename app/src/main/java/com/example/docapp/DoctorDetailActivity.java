package com.example.docapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.bumptech.glide.Glide;
import java.util.List;
import java.util.Calendar;
import java.util.Date;


public class DoctorDetailActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String idDokter;
    private TextView textDokterNama, textRumahSakit, textKonfirmasi, tanggal1, tanggal2, tanggal3, tanggal4, tanggal5;
    private ImageView imgDrlaki;
    private View shapeView1, shapeView2, shapeView3, shapeView4, shapeView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_doctor);

        idDokter = getIntent().getStringExtra("idDokter");

        textDokterNama = findViewById(R.id.textDokterNama);
        textRumahSakit = findViewById(R.id.textRumahSakit);
        textKonfirmasi = findViewById(R.id.textKonfirmasi);
        imgDrlaki = findViewById(R.id.imgDrlaki);
        shapeView1 = findViewById(R.id.shapeView1);
        shapeView2 = findViewById(R.id.shapeView2);
        shapeView3 = findViewById(R.id.shapeView3);
        shapeView4 = findViewById(R.id.shapeView4);
        shapeView5 = findViewById(R.id.shapeView5);
        tanggal1 = findViewById(R.id.tanggal1);
        tanggal2 = findViewById(R.id.tanggal2);
        tanggal3 = findViewById(R.id.tanggal3);
        tanggal4 = findViewById(R.id.tanggal4);
        tanggal5 = findViewById(R.id.tanggal5);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        loadDoctorData();
    }

    private void loadDoctorData() {
        db.collection("doctors").document(idDokter)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            textDokterNama.setText(document.getString("nama"));
                            textRumahSakit.setText(document.getString("hospital"));
                            textKonfirmasi.setText(document.getString("profil"));
                            Glide.with(this).load(document.getString("avatar")).into(imgDrlaki);

                            // Handle bookSchedule field
                            List<com.google.firebase.Timestamp> bookSchedule = (List<com.google.firebase.Timestamp>) document.get("bookSchedule");
                            if (bookSchedule != null) {
                                for (com.google.firebase.Timestamp timestamp : bookSchedule) {
                                    java.util.Date date = timestamp.toDate(); // Convert Timestamp to Date
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(date);
                                    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                                    int dateOfMonth = calendar.get(Calendar.DATE);

                                    switch (dayOfWeek) {
                                        case Calendar.MONDAY:
                                            shapeView1.setBackgroundColor(getResources().getColor(R.color.primaryColor));
                                            tanggal1.setText(String.valueOf(dateOfMonth));
                                            break;
                                        case Calendar.TUESDAY:
                                            shapeView2.setBackgroundColor(getResources().getColor(R.color.primaryColor));
                                            tanggal2.setText(String.valueOf(dateOfMonth));
                                            break;
                                        case Calendar.WEDNESDAY:
                                            shapeView3.setBackgroundColor(getResources().getColor(R.color.primaryColor));
                                            tanggal3.setText(String.valueOf(dateOfMonth));
                                            break;
                                        case Calendar.THURSDAY:
                                            shapeView4.setBackgroundColor(getResources().getColor(R.color.primaryColor));
                                            tanggal4.setText(String.valueOf(dateOfMonth));
                                            break;
                                        case Calendar.FRIDAY:
                                            shapeView5.setBackgroundColor(getResources().getColor(R.color.primaryColor));
                                            tanggal5.setText(String.valueOf(dateOfMonth));
                                            break;
                                        // Add cases for other days if needed
                                    }
                                }
                            }

                            // Hide views if no schedule is available for a particular day
                            hideViewIfNoSchedule(shapeView1, tanggal1);
                            hideViewIfNoSchedule(shapeView2, tanggal2);
                            hideViewIfNoSchedule(shapeView3, tanggal3);
                            hideViewIfNoSchedule(shapeView4, tanggal4);
                            hideViewIfNoSchedule(shapeView5, tanggal5);
                        }
                    }
                });
    }


    private void hideViewIfNoSchedule(View shapeView, TextView tanggalView) {
        // Check if the TextView has no date set (or you can check for a default value if you set one)
        if (tanggalView.getText().toString().isEmpty() || tanggalView.getText().toString().equals("default")) {
            shapeView.setVisibility(View.GONE);
            tanggalView.setVisibility(View.GONE);
        }
    }


}
