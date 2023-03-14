package com.ntukhpi.otp.momot.fourth_course_dma.lab6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ntukhpi.otp.momot.fourth_course_dma.lab1.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculateFragment extends Fragment {
    private ListView pointsList;
    private TextView ratingView;
    private SharedPreferences settings;
    private int additionalPoint;
    private List<Integer> points;
    private static final int MAX_RATING = 90;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lab6_fragment_calculate, container, false);

        settings = this.getActivity().getSharedPreferences("lab6_input", Context.MODE_PRIVATE);
        getData();

        pointsList = view.findViewById(R.id.lab6_points_list);
        TextView additionalPointText = view.findViewById(R.id.lab6_additional_point);
        additionalPointText.setText(getResources().getString(R.string.lab6_additional_point_text) +
                " " + additionalPoint);
        ratingView = view.findViewById(R.id.lab6_rating_input);

        setDataOnList();
        float rating = calculateRating(points, additionalPoint, MAX_RATING);
        showRating(rating);
        saveRating(rating);

        return view;
    }

    private void showRating(float rating) {
        ratingView.setText(getResources().getString(R.string.your_rating) + " " + String.format("%.01f", rating));
    }

    private void saveRating(float rating) {
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putFloat("lab6_rating", rating);
        prefEditor.apply();
    }

    private void getData() {
        if (!settings.contains("pointsNumber")) {
            points = Collections.emptyList();
        }

        int listSize = settings.getInt("pointsNumber", 0);
        points = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            points.add(i, settings.getInt("point_" + i, -1));
        }

        additionalPoint = settings.getInt("additional_point", 0);
    }

    private float calculateRating(List<Integer> points, int additionalPoint, int maxRating) {
        float averagePoint = additionalPoint;

        for (int i = 0; i < points.size(); i++) {
            averagePoint += points.get(i);
        }

        return averagePoint / (points.size() + 1) * (maxRating / 100f);
    }

    private void setDataOnList() {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_list_item_1, points);

        pointsList.setAdapter(adapter);
    }
}
