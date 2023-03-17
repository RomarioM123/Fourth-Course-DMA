package com.ntukhpi.otp.momot.fourth_course_dma;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.ntukhpi.otp.momot.fourth_course_dma.lab1.R;

public abstract class Menu extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.lab_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Class<? extends Menu> thisClass = this.getClass();
        Class<? extends Menu> tempClass;

        Intent intent;
        switch (id) {
            case R.id.lab1:
                tempClass = com.ntukhpi.otp.momot.fourth_course_dma.lab1.MainActivity.class;
                if (thisClass != tempClass) {
                    intent = new Intent(this, tempClass);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    return true;
                }
                break;

            case R.id.lab2:
                tempClass = com.ntukhpi.otp.momot.fourth_course_dma.lab2.MainActivity.class;
                if (thisClass != tempClass) {
                    intent = new Intent(this, tempClass);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    return true;
                }
                break;

            case R.id.lab3:
                tempClass = com.ntukhpi.otp.momot.fourth_course_dma.lab3.MainActivity.class;
                if (thisClass != tempClass) {
                    intent = new Intent(this, tempClass);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    return true;
                }
                break;

            case R.id.lab4:
                tempClass = com.ntukhpi.otp.momot.fourth_course_dma.lab4.MainActivity.class;
                if (thisClass != tempClass) {
                    intent = new Intent(this, tempClass);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    return true;
                }
                break;

            case R.id.lab5:
                tempClass = com.ntukhpi.otp.momot.fourth_course_dma.lab5.MainActivity.class;
                if (thisClass != tempClass) {
                    intent = new Intent(this, tempClass);
                    startActivity(intent);
                    return true;
                }
                break;

            case R.id.lab6:
                tempClass = com.ntukhpi.otp.momot.fourth_course_dma.lab6.MainActivity.class;
                if (thisClass != tempClass) {
                    intent = new Intent(this, tempClass);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    return true;
                }
                break;

            case R.id.lab7:
                tempClass = com.ntukhpi.otp.momot.fourth_course_dma.lab7.MainActivity.class;
                if (thisClass != tempClass) {
                    intent = new Intent(this, tempClass);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    return true;
                }
                break;

            case R.id.lab8:
                tempClass = com.ntukhpi.otp.momot.fourth_course_dma.lab8.MainActivity.class;
                if (thisClass != tempClass) {
                    intent = new Intent(this, tempClass);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    return true;
                }
                break;

            case R.id.lab9:
                tempClass = com.ntukhpi.otp.momot.fourth_course_dma.lab9.MainActivity.class;
                if (thisClass != tempClass) {
                    intent = new Intent(this, tempClass);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    return true;
                }
                break;

            case R.id.lab10:
                tempClass = com.ntukhpi.otp.momot.fourth_course_dma.lab10.MainActivity.class;
                if (thisClass != tempClass) {
                    intent = new Intent(this, tempClass);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    return true;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
