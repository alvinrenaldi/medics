package com.example.docapp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding); // Ganti dengan layout onboarding Anda

        Button btnSignIn = findViewById(R.id.btnSignIn); // Ganti dengan ID button "Masuk"
        Button btnSignUp = findViewById(R.id.btnSignUp); // Ganti dengan ID button "Daftar"

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigasi ke LoginActivity
                Intent intent = new Intent(OnboardingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigasi ke SignUpActivity
                Intent intent = new Intent(OnboardingActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        // Setelah onboarding selesai, simpan status di SharedPreferences
        SharedPreferences.Editor editor = getSharedPreferences("MyAppPrefs", MODE_PRIVATE).edit();
        editor.putBoolean("hasSeenOnboarding", true);
        editor.apply();
    }
}
