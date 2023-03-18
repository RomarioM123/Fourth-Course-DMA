package com.ntukhpi.otp.momot.fourth_course_dma.lab9;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import static android.provider.ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;

import android.provider.Settings;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.ntukhpi.otp.momot.fourth_course_dma.Menu;
import com.ntukhpi.otp.momot.fourth_course_dma.lab1.R;

import java.util.ArrayList;

public class MainActivity extends Menu {
    private final ArrayList<Contact> contacts = new ArrayList<>();
    private boolean isReadingContactsAllowed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab9_activity_main);

        createInternetConnectionListener();
        readContactsTask();
    }

    public void openNetworkActivity() {
        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        startActivity(intent);
    }

    private void createInternetConnectionListener() {
        ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                Snackbar.make(getWindow().getDecorView().getRootView(), "Network aviable", Snackbar.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onLost(Network network) {
                Snackbar.make(getWindow().getDecorView(), "No connectivity", Snackbar.LENGTH_INDEFINITE)
                        .setAction("SETTINGS", v -> openNetworkActivity())
                        .show();
            }
        };

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        connectivityManager.registerDefaultNetworkCallback(networkCallback);
    }

    private void readContactsTask() {
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
            Toast.makeText(this, "You need to allow reading contacts", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("Range")
    private void getContacts() {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if(cursor != null) {
            while(cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                String hasPhone =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                if (hasPhone.equalsIgnoreCase("1")) {
                    Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                    phones.moveToFirst();
                    String contactNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String contactName = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME_PRIMARY));
                    Contact contact = new Contact(contactName, contactNumber);
                    contacts.add(contact);
                }
            }
        }

        outputDataToList();
    }

    private void outputDataToList() {
        ArrayAdapter<Contact> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, contacts);

        ListView contactList = findViewById(R.id.lab9_contact_list);
        contactList.setAdapter(adapter);
        contactList.setOnItemClickListener((parent, view, position, id) -> {
            openPhoneDial(contacts.get(position).getPhone());
            Snackbar.make(getWindow().getDecorView().getRootView(), contacts.get(position).getPhone(), Snackbar.LENGTH_LONG)
                    .show();
        });
    }

    private void openPhoneDial(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }
}