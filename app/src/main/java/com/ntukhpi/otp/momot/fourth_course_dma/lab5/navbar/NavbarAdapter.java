package com.ntukhpi.otp.momot.fourth_course_dma.lab5.navbar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntukhpi.otp.momot.fourth_course_dma.lab1.R;

public class NavbarAdapter extends ArrayAdapter<NavbarModel> {
    private final Context context;
    private final int resourceId;
    private final NavbarModel[] data;

    public NavbarAdapter(Context context, int resourceId, NavbarModel[] data) {
        super(context, resourceId, data);
        this.resourceId = resourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View listItem = inflater.inflate(resourceId, parent, false);

        ImageView imageViewIcon = listItem.findViewById(R.id.imageViewIcon);
        TextView textViewName = listItem.findViewById(R.id.textViewName);

        NavbarModel folder = data[position];

        imageViewIcon.setImageResource(folder.getIcon());
        textViewName.setText(folder.getName());

        return listItem;
    }
}
