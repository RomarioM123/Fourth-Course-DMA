package com.ntukhpi.otp.momot.fourth_course_dma.lab9;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import static android.provider.ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ntukhpi.otp.momot.fourth_course_dma.Menu;
import com.ntukhpi.otp.momot.fourth_course_dma.lab1.R;

import java.util.ArrayList;

public class MainActivity extends Menu {
    private final ArrayList<String> contacts = new ArrayList<>();
    private boolean isReadingContactsAllowed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab9_activity_main);

        int hasReadContactPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (hasReadContactPermission == PackageManager.PERMISSION_GRANTED) {
            isReadingContactsAllowed = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        }

        if (isReadingContactsAllowed) {
            getContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            isReadingContactsAllowed = true;
        }
        if (isReadingContactsAllowed) {
            getContacts();
        } else {
            Toast.makeText(this, "Требуется установить разрешения", Toast.LENGTH_LONG).show();
        }
    }

    private void getContacts() {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range")
                String contact = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME_PRIMARY));
                contacts.add(contact);
            }
            cursor.close();
        }

        outputDataToList();
    }

    private void outputDataToList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, contacts);

        ListView contactList = findViewById(R.id.lab9_contact_list);
        contactList.setAdapter(adapter);
    }
}