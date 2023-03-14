package com.ntukhpi.otp.momot.fourth_course_dma.lab6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import static android.widget.LinearLayout.LayoutParams.MATCH_PARENT;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ntukhpi.otp.momot.fourth_course_dma.InputFilterMinMax;
import com.ntukhpi.otp.momot.fourth_course_dma.lab1.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InputFragment extends Fragment {
    private static final int MAX_NUMBER_OF_POINTS = 5;
    private static final int ADDITIONAL_POINT = 70;
    private EditText currentEditText;
    private EditText hiddenEditText;
    private TextView alert;
    private TextView ratingResult;
    private SharedPreferences settings;
    private InputFilter[] filters;
    private View view;
    private int index;
    private int count = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lab6_fragment_value_input, container, false);
        filters = new InputFilter[]{new InputFilterMinMax(0, 100)};

        index = R.id.lab6_grade_input1 + 1;

        currentEditText = view.findViewById(R.id.lab6_grade_input1);
        ((EditText) view.findViewById(R.id.lab6_grade_input1)).setFilters(filters);

        TextView additionalPoint = view.findViewById(R.id.lab6_additional_point);
        additionalPoint.setText(getResources().getString(R.string.lab6_additional_point_text) + " " + ADDITIONAL_POINT);

        TextView maxNumberOfPoints = view.findViewById(R.id.lab6_max_number_of_points);
        maxNumberOfPoints.setText(getResources().getString(R.string.lab6_max_number_of_values) + " " + MAX_NUMBER_OF_POINTS);

        ratingResult = view.findViewById(R.id.lab6_rating_input);

        alert = view.findViewById(R.id.lab6_max_number_alert);

        setFocusListener();

        settings = this.getActivity().getSharedPreferences("lab6_input", Context.MODE_PRIVATE);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        saveAllPoints();
        currentEditText.setOnFocusChangeListener(null);
    }

    private List<Integer> getPoints() {
        SharedPreferences.Editor prefEditor = settings.edit();
        if (!settings.contains("pointsNumber")) {
            return Collections.emptyList();
        }

        int listSize = settings.getInt("pointsNumber", 0);
        List<Integer> points = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            points.add(i, settings.getInt("point_", -1));
        }
        return points;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!checkIfRatingExists()) {
            return;
        }

        float rating = getRating();
        showRating(rating);
    }

    private boolean checkIfRatingExists() {
        return settings.contains("lab6_rating");
    }

    private void showRating(float rating) {
        ratingResult.setText(getResources().getString(R.string.your_rating) +
                " " + String.format("%.01f", rating));
        ratingResult.setVisibility(View.VISIBLE);

        Log.i("Lab 6 rating result", String.valueOf(rating));
    }

    private float getRating() {
        return settings.getFloat("lab6_rating", 0);
    }

    private void saveAllPoints() {
        List<Integer> points = getAllInputs();
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putInt("pointsNumber", points.size());

        for (int i = 0; i < points.size(); i++) {
            prefEditor.putInt("point_" + i, points.get(i));
        }

        prefEditor.putInt("additional_point", ADDITIONAL_POINT);

        prefEditor.apply();
    }

    private void setFocusListener() {
        currentEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                if (count < MAX_NUMBER_OF_POINTS - 1) {
                    createHiddenEditText();
                    showEditText();
                } else {
                    showAlert();
                }
            } else {
                if (checkIfInputHasValue()) {
                    currentEditText.setOnFocusChangeListener(null);
                    currentEditText = hiddenEditText;
                    setFocusListener();
                    hiddenEditText = null;
                } else {
                    deleteEditText();
                }
            }
        });
    }

    private void showAlert() {
        alert.setVisibility(View.VISIBLE);
    }

    private void hideAlert() {
        alert.setVisibility(View.GONE);
    }

    private void showEditText() {
        hiddenEditText.setVisibility(View.VISIBLE);
    }

    private void deleteEditText() {
        hiddenEditText = null;
        index--;
        count--;
        hideAlert();
    }

    private boolean checkIfInputHasValue() {
        return currentEditText.getText().length() != 0;
    }

    private void createHiddenEditText() {
        hiddenEditText = new EditText(getContext());
        hiddenEditText.setId(index++);

        LayoutParams layoutParams = new LayoutParams(MATCH_PARENT, MATCH_PARENT);
        float factor = this.getResources().getDisplayMetrics().density;
        layoutParams.setMarginStart(80 * (int) factor);
        layoutParams.setMarginEnd(80 * (int) factor);
        hiddenEditText.setLayoutParams(layoutParams);

        String hint = getResources()
                .getString(R.string.input_value_new) + " " + (2 + count++);
        hiddenEditText.setHint(hint);
        hiddenEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        hiddenEditText.setFilters(filters);
        hiddenEditText.setSelectAllOnFocus(true);
        hiddenEditText.setVisibility(View.GONE);

        LinearLayout relativeLayout = view.findViewById(R.id.lab6_edittext_input_layout);
        relativeLayout.addView(hiddenEditText);
    }

    private List<Integer> getAllInputs() {
        LinearLayout linearLayout = view.findViewById(R.id.lab6_edittext_input_layout);
        final int childCount = linearLayout.getChildCount();
        List<String> strings = new ArrayList<>(childCount);

        for (int i = 0; i < childCount; i++) {
            EditText v = (EditText) linearLayout.getChildAt(i);
            if (v.getText().toString().length() != 0) {
                strings.add(i, v.getText().toString());
            }
        }

        return strings.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
