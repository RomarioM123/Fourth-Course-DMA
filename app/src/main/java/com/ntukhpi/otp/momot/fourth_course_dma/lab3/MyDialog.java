package com.ntukhpi.otp.momot.fourth_course_dma.lab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ntukhpi.otp.momot.fourth_course_dma.lab1.R;

public class MyDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Dialog window")
                .setMessage(R.string.name_group)
                .setPositiveButton("OK", null)
                .create();
    }
}
