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


                    for (DataSnapshot ratingSnapshot : groupSnapshot.getChildren()) {
                        Float rating = ratingSnapshot.getValue(Float.class);
                        if (rating != null) {
                            sum += rating;
                            count++;
                        }
                    }

                    if (count > 0) {
                        float average = sum / count;
                        averageRatings.put(groupName, average);
                        builder.append("Group: ").append(groupName)
                                .append("\nAverage Rating: ").append(average)
                                .append("\nRatings: ");


                        for (DataSnapshot ratingSnapshot : groupSnapshot.getChildren()) {
                            builder.append(ratingSnapshot.getKey()).append(": ").append(ratingSnapshot.getValue()).append("\n");
                        }

                        builder.append("\n");
                    }
                }

                String winnerGroup = null;
                float maxAverage = 0;

                for (Map.Entry<String, Float> entry : averageRatings.entrySet()) {
                    if (entry.getValue() > maxAverage) {
                        maxAverage = entry.getValue();
                        winnerGroup = entry.getKey();
                    }
                }

                if (winnerGroup != null) {
                    builder.append("Winner: ").append(winnerGroup).append(" with an average rating of ").append(maxAverage);
                }

                ratingsTextView.setText(builder.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
