package com.ntukhpi.otp.momot.fourth_course_dma.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ntukhpi.otp.momot.fourth_course_dma.lab1.R;

public class GradeMenu extends AppCompatActivity {
    private int maxRating;
    private float rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab2_activity_grade);

        final TextView myRatingText = findViewById(R.id.lab2_your_rating_grade);
        getMaxRating();
        getRating(getGrade());

        String formattedRating = String.format("%.01f", rating);
        myRatingText.setText(new StringBuilder(myRatingText.getText()).append(formattedRating));
    }

    private void getMaxRating() {
        Bundle bundle = getIntent().getExtras();
        maxRating = bundle.getInt("max_rating");
    }

    private int getGrade() {
        Bundle bundle = getIntent().getExtras();
        return bundle.getInt("grade");
    }

    private void getRating(int grade) {
        rating = grade * (maxRating / 100f);
    }

    public void returnToGradeInput(View view) {
        createIntent();
    }

    private void createIntent() {
        Intent intent = new Intent(GradeMenu.this, MainActivity.class);
        intent.putExtra("rating", rating);
        startActivity(intent);
    }
}