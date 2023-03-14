package com.ntukhpi.otp.momot.fourth_course_dma.lab6;

import android.os.Bundle;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ntukhpi.otp.momot.fourth_course_dma.Menu;
import com.ntukhpi.otp.momot.fourth_course_dma.lab1.R;

public class MainActivity extends Menu {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab6_activity_main);

        Button showFragment1Button = findViewById(R.id.lab6_button_fragment1);
        Button showFragment2Button = findViewById(R.id.lab6_button_fragment2);

        Fragment inputFragment = new InputFragment();
        Fragment calculateFragment = new CalculateFragment();

        showFragment1Button.setOnClickListener(v -> replaceFragment(inputFragment));
        showFragment2Button.setOnClickListener(v -> replaceFragment(calculateFragment));

        replaceFragment(inputFragment);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lab6_frame_layout, fragment);
        fragmentTransaction.commit();
    }
}