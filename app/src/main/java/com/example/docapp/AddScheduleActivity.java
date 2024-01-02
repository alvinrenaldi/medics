package com.example.docapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddScheduleActivity extends AppCompatActivity {

    private static final String TAG = "AddScheduleActivity";

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private String mondayDate1, mondayDate2, mondayDate3, mondayDate4, mondayDate5, mondayDate6, mondayDate7, mondayDate8, mondayDate9;
    private String tuesdayDate1, tuesdayDate2, tuesdayDate3, tuesdayDate4, tuesdayDate5, tuesdayDate6, tuesdayDate7, tuesdayDate8, tuesdayDate9;
    private String wednesdayDate1, wednesdayDate2, wednesdayDate3, wednesdayDate4, wednesdayDate5, wednesdayDate6, wednesdayDate7, wednesdayDate8, wednesdayDate9;
    private String thursdayDate1, thursdayDate2, thursdayDate3, thursdayDate4, thursdayDate5, thursdayDate6, thursdayDate7, thursdayDate8, thursdayDate9;
    private String fridayDate1, fridayDate2, fridayDate3, fridayDate4, fridayDate5, fridayDate6, fridayDate7, fridayDate8, fridayDate9;

    private String tanggalMonday, tanggalTuesday, tanggalWednesday, tanggalThursday, tanggalFriday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        // Mendapatkan user yang sedang login
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            loadScheduleData(userId);
        }
    }

    private void loadScheduleData(String userId) {
        DocumentReference userDocRef = db.collection("doctors").document(userId);

        userDocRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    List<Date> bookSchedule = (List<Date>) document.get("bookSchedule");

                    if (bookSchedule != null) {
                        processBookSchedule(bookSchedule);
                    }
                } else {
                    Log.d(TAG, "Document does not exist");
                }
            } else {
                Log.d(TAG, "Failed with ", task.getException());
            }
        });
    }

    private void processBookSchedule(List<Date> bookSchedule) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        for (Date date : bookSchedule) {
            String formattedDateTime = dateFormat.format(date);
            String dayOfWeek = getDayOfWeek(date);

            switch (dayOfWeek) {
                case "Monday":
                    setMondayData(formattedDateTime);
                    break;
                case "Tuesday":
                    setTuesdayData(formattedDateTime);
                    break;
                case "Wednesday":
                    setWednesdayData(formattedDateTime);
                    break;
                case "Thursday":
                    setThursdayData(formattedDateTime);
                    break;
                case "Friday":
                    setFridayData(formattedDateTime);
                    break;
            }
        }
    }

    private String getDayOfWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        return sdf.format(date);
    }

    private void setMondayData(String formattedDateTime) {
        String time = getTimeFromDateTime(formattedDateTime);

        if (mondayDate1.isEmpty()) {
            mondayDate1 = time;
        } else if (mondayDate2.isEmpty()) {
            mondayDate2 = time;
        } else if (mondayDate3.isEmpty()) {
            mondayDate3 = time;
        } else if (mondayDate4.isEmpty()) {
            mondayDate4 = time;
        } else if (mondayDate5.isEmpty()) {
            mondayDate5 = time;
        } else if (mondayDate6.isEmpty()) {
            mondayDate6 = time;
        } else if (mondayDate7.isEmpty()) {
            mondayDate7 = time;
        } else if (mondayDate8.isEmpty()) {
            mondayDate8 = time;
        } else if (mondayDate9.isEmpty()) {
            mondayDate9 = time;
        } else {
            // Handle if more than 9 appointments on Monday
        }
    }

    private void setTuesdayData(String formattedDateTime) {
        String time = getTimeFromDateTime(formattedDateTime);

        if (tuesdayDate1.isEmpty()) {
            tuesdayDate1 = time;
        } else if (tuesdayDate2.isEmpty()) {
            tuesdayDate2 = time;
        } else if (tuesdayDate3.isEmpty()) {
            tuesdayDate3 = time;
        } else if (tuesdayDate4.isEmpty()) {
            tuesdayDate4 = time;
        } else if (tuesdayDate5.isEmpty()) {
            tuesdayDate5 = time;
        } else if (tuesdayDate6.isEmpty()) {
            tuesdayDate6 = time;
        } else if (tuesdayDate7.isEmpty()) {
            tuesdayDate7 = time;
        } else if (tuesdayDate8.isEmpty()) {
            tuesdayDate8 = time;
        } else if (tuesdayDate9.isEmpty()) {
            tuesdayDate9 = time;
        } else {
            // Handle if more than 9 appointments on Tuesday
        }
    }

    private void setWednesdayData(String formattedDateTime) {
        String time = getTimeFromDateTime(formattedDateTime);

        if (wednesdayDate1.isEmpty()) {
            wednesdayDate1 = time;
        } else if (wednesdayDate2.isEmpty()) {
            wednesdayDate2 = time;
        } else if (wednesdayDate3.isEmpty()) {
            wednesdayDate3 = time;
        } else if (wednesdayDate4.isEmpty()) {
            wednesdayDate4 = time;
        } else if (wednesdayDate5.isEmpty()) {
            wednesdayDate5 = time;
        } else if (wednesdayDate6.isEmpty()) {
            wednesdayDate6 = time;
        } else if (wednesdayDate7.isEmpty()) {
            wednesdayDate7 = time;
        } else if (wednesdayDate8.isEmpty()) {
            wednesdayDate8 = time;
        } else if (wednesdayDate9.isEmpty()) {
            wednesdayDate9 = time;
        } else {
            // Handle if more than 9 appointments on Wednesday
        }
    }

    private void setThursdayData(String formattedDateTime) {
        String time = getTimeFromDateTime(formattedDateTime);

        if (thursdayDate1.isEmpty()) {
            thursdayDate1 = time;
        } else if (thursdayDate2.isEmpty()) {
            thursdayDate2 = time;
        } else if (thursdayDate3.isEmpty()) {
            thursdayDate3 = time;
        } else if (thursdayDate4.isEmpty()) {
            thursdayDate4 = time;
        } else if (thursdayDate5.isEmpty()) {
            thursdayDate5 = time;
        } else if (thursdayDate6.isEmpty()) {
            thursdayDate6 = time;
        } else if (thursdayDate7.isEmpty()) {
            thursdayDate7 = time;
        } else if (thursdayDate8.isEmpty()) {
            thursdayDate8 = time;
        } else if (thursdayDate9.isEmpty()) {
            thursdayDate9 = time;
        } else {
            // Handle if more than 9 appointments on Thursday
        }
    }

    private void setFridayData(String formattedDateTime) {
        String time = getTimeFromDateTime(formattedDateTime);

        if (fridayDate1.isEmpty()) {
            fridayDate1 = time;
        } else if (fridayDate2.isEmpty()) {
            fridayDate2 = time;
        } else if (fridayDate3.isEmpty()) {
            fridayDate3 = time;
        } else if (fridayDate4.isEmpty()) {
            fridayDate4 = time;
        } else if (fridayDate5.isEmpty()) {
            fridayDate5 = time;
        } else if (fridayDate6.isEmpty()) {
            fridayDate6 = time;
        } else if (fridayDate7.isEmpty()) {
            fridayDate7 = time;
        } else if (fridayDate8.isEmpty()) {
            fridayDate8 = time;
        } else if (fridayDate9.isEmpty()) {
            fridayDate9 = time;
        } else {
            // Handle if more than 9 appointments on Friday
        }
    }

    private String getTimeFromDateTime(String formattedDateTime) {
        // Assuming the format is "dd/MM/yyyy HH:mm:ss"
        String[] dateTimeParts = formattedDateTime.split(" ");
        return dateTimeParts[1]; // Returns the time part
    }


}

