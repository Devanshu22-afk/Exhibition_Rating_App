package com.example.madapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AllGroupsRatingActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private TextView ratingsTextView;
    private DatabaseReference ratingsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_groups_rating);

        scrollView = findViewById(R.id.scrollView);
        ratingsTextView = findViewById(R.id.ratingsTextView);
        ratingsRef = FirebaseDatabase.getInstance().getReference("Ratings");

        fetchAllGroupRatings();
    }

    private void fetchAllGroupRatings() {
        ratingsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StringBuilder builder = new StringBuilder();
                Map<String, Float> averageRatings = new HashMap<>();
                Map<String, Integer> countRatings = new HashMap<>();

                for (DataSnapshot groupSnapshot : snapshot.getChildren()) {
                    String groupName = groupSnapshot.getKey();
                    float sum = 0;
                    int count = 0;

                    // Collect ratings for each group
                    for (DataSnapshot ratingSnapshot : groupSnapshot.getChildren()) {
                        Float rating = ratingSnapshot.getValue(Float.class);
                        if (rating != null) {
                            sum += rating;
                            count++;
                        }
                    }

                    // Calculate average rating for each group
                    if (count > 0) {
                        float average = sum / count;
                        averageRatings.put(groupName, average);
                        builder.append("\n\n").append("Group: ").append(groupName)
                                .append("\nAverage Rating: ").append(String.format("%.2f", average))
                                .append("\nRatings:\n");

                        // Display all individual ratings for the group
                        for (DataSnapshot ratingSnapshot : groupSnapshot.getChildren()) {
                            String userEmail = ratingSnapshot.getKey();
                            Float ratingValue = ratingSnapshot.getValue(Float.class);
                            builder.append(userEmail).append(": ").append(ratingValue).append("\n");
                        }
                    }
                }

                // Find the group with the highest average rating
                String winnerGroup = null;
                float maxAverage = 0;

                for (Map.Entry<String, Float> entry : averageRatings.entrySet()) {
                    if (entry.getValue() > maxAverage) {
                        maxAverage = entry.getValue();
                        winnerGroup = entry.getKey();
                    }
                }

                // Display the winner group and its average rating
                if (winnerGroup != null) {
                    builder.append("\n\nWinner: ").append(winnerGroup)
                            .append(" with an average rating of ").append(String.format("%.2f", maxAverage));
                }

                // Set the formatted ratings text in the TextView
                ratingsTextView.setText(builder.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle any error that occurs during the data retrieval
            }
        });
    }
}
