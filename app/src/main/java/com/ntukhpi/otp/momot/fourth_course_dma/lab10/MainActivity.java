package com.ntukhpi.otp.momot.fourth_course_dma.lab10;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.ntukhpi.otp.momot.fourth_course_dma.Menu;
import com.ntukhpi.otp.momot.fourth_course_dma.lab1.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends Menu {
    private static final int NUMBER_OF_LINES = 6;
    private static final String WEB_PAGE = "https://kpi.kharkov.ua/ukr/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab10_activity_main);

        TextView contentView = findViewById(R.id.content);

        contentView.setText("Загрузка...");
        new Thread(() -> {
            try{
                String content = getContent(WEB_PAGE);
                contentView.post(() -> contentView.setText(getFirstNLines(content, NUMBER_OF_LINES, '\n')));
            }
            catch (IOException ex){
                contentView.post(() -> {
                    contentView.setText("Ошибка: " + ex.getMessage());
                    Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }

    private String getFirstNLines(String text, int count, char spliterator) {
        return Arrays
                .stream(text.split(String.valueOf(spliterator), count + 1))
                .limit(count)
                .collect(Collectors.joining(String.valueOf(spliterator)));
    }

    private String getContent(String path) throws IOException {
        HttpsURLConnection connection;
        URL url = new URL(path);

        connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setReadTimeout(10000);
        connection.connect();

        try (InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder buf = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                buf.append(line).append("\n");
            }
            return (buf.toString());
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}