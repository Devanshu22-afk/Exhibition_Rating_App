package com.example.madapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ratingpage extends AppCompatActivity {

    private String groupID;
    private String userEmail;
    private RatingBar ratingBar;
    private Button submitButton;
    private TextView groupTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratingpage);


        groupID = getIntent().getStringExtra("groupID");
        userEmail = getIntent().getStringExtra("username");

        ratingBar = findViewById(R.id.ratingBar);
        submitButton = findViewById(R.id.btnSubmit);
        groupTitleTextView = findViewById(R.id.tvGroupTitle);


        groupTitleTextView.setText(groupID != null ? groupID : "No Group Title");


        submitButton.setOnClickListener(v -> submitRating());
    }

    private void submitRating() {
        float ratingValue = ratingBar.getRating();

        if (groupID != null && userEmail != null) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Ratings")
                    .child(groupID)
                    .child(userEmail);
            databaseReference.setValue(ratingValue).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Rating submitted successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to submit rating", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(e -> {
                Log.e("RatingPage", "Error submitting rating: ", e);
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(this, "Group ID or User Email is missing", Toast.LENGTH_SHORT).show();
        }
    }
}
