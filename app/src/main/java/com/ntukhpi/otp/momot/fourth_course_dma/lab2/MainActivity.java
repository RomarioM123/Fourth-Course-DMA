package com.ntukhpi.otp.momot.fourth_course_dma.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ntukhpi.otp.momot.fourth_course_dma.InputFilterMinMax;
import com.ntukhpi.otp.momot.fourth_course_dma.Menu;
import com.ntukhpi.otp.momot.fourth_course_dma.lab1.R;

public class MainActivity extends Menu {
    private static final int MAX_RATING_VALUE = 90;
    private static final int MAX_GRADE_VALUE = 100;
    private EditText gradeInput;
    private float rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab2_activity_main);

        gradeInput = findViewById(R.id.input_grade);
        gradeInput.setFilters(new InputFilter[]{new InputFilterMinMax(0, MAX_GRADE_VALUE)});

        final TextView maxRatingText = findViewById(R.id.max_rating);
        maxRatingText.setText(new StringBuilder(maxRatingText.getText()).append(MAX_RATING_VALUE));

        if (checkExtras()) {
            setResultRating();
        }
    }

    private void setResultRating() {
        getRating();
        final TextView yourRating = findViewById(R.id.page1_rating);
        yourRating.setText(yourRating.getText().toString() + rating);
        yourRating.setVisibility(View.VISIBLE);
    }

    private void getRating() {
        Bundle bundle = getIntent().getExtras();
        rating = bundle.getFloat("rating");
    }

    public void getRatingButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, GradeMenu.class);
        intent.putExtra("grade", getGradeInput());
        intent.putExtra("max_rating", MAX_RATING_VALUE);
        startActivity(intent);
    }

    private int getGradeInput() {
        return Integer.parseInt(gradeInput.getText().toString());
    }

    private boolean checkExtras() {
        Intent intent = getIntent();
        return intent.hasExtra("rating");
    }
}