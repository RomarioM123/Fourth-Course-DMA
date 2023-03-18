package com.ntukhpi.otp.momot.fourth_course_dma.lab7;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ntukhpi.otp.momot.fourth_course_dma.Menu;
import com.ntukhpi.otp.momot.fourth_course_dma.lab1.R;
import com.ntukhpi.otp.momot.fourth_course_dma.lab7.util.CarNumberGenerator;
import com.ntukhpi.otp.momot.fourth_course_dma.lab7.util.YearGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Menu {
    private SQLiteDatabase database;
    Map<String, String> carsData;
    private static final int NUMBER_OF_NEW_LINES = 6;
    private static final String TABLE_NAME = "cars";
    private static final String CAR_NUMBER = "car_number";
    private static final String CAR_PRODUCTION_YEAR = "production_year";
    private static final String GET_DATA_FROM_TABLE_COMMAND = "SELECT * FROM cars;";
    private static final String GET_TABLE_SIZE_COMMAND = "SELECT COUNT(*) FROM cars;";
    private static final String CLEAR_TABLE = "DELETE FROM cars;";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab7_activity_main);

        database = getBaseContext().openOrCreateDatabase("cars.db", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS cars (car_number TEXT NOT NULL UNIQUE , production_year TEXT NOT NULL);");

        if (getTableSize() < 5) {
            fillTable();
        } else {
            clearTable();
        }

        carsData = new HashMap<>();

        getDataFromTable();
        outputToView();
        database.close();
    }

    private void clearTable() {
        database.execSQL(CLEAR_TABLE);
    }

    private int getTableSize() {
        Cursor query = database.rawQuery(GET_TABLE_SIZE_COMMAND, null);
        int number = 0;

        while (query.moveToNext()) {
            number = query.getInt(0);
        }

        query.close();
        return number;
    }

    private void fillTable() {
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < NUMBER_OF_NEW_LINES; i++) {
            contentValues.put(CAR_NUMBER, CarNumberGenerator.generateCarNumber());
            contentValues.put(CAR_PRODUCTION_YEAR, YearGenerator.getRandomYear());
            database.insert(TABLE_NAME, null, contentValues);
        }
    }

    private void outputToView() {
        ListView carListView = findViewById(R.id.lab7_cars_list);

        List<String> temp = new ArrayList<>();
        for (Map.Entry<String, String> entry : carsData.entrySet()) {
            temp.add(String.format("Car %s year %s", entry.getKey(), entry.getValue()));
        }
        Collections.sort(temp);

        ArrayAdapter<?> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, temp);

        carListView.setAdapter(adapter);
    }

    private void getDataFromTable() {
        Cursor query = database.rawQuery(GET_DATA_FROM_TABLE_COMMAND, null);

        while (query.moveToNext()) {
            String number = query.getString(0);
            String year = query.getString(1);
            carsData.put(number, year);
        }
        query.close();
    }
}