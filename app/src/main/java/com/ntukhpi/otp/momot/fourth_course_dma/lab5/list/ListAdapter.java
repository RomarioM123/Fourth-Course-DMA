package com.ntukhpi.otp.momot.fourth_course_dma.lab5.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntukhpi.otp.momot.fourth_course_dma.lab1.R;

import java.util.List;

public class ListAdapter extends ArrayAdapter<ListElement> {
    private final LayoutInflater inflater;
    private final int layout;
    private final List<ListElement> elements;

    public ListAdapter(Context context, int resource, List<ListElement> elements) {
        super(context, resource, elements);
        this.elements = elements;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public List<ListElement> getAll() {
        return elements;
    }

    public ListElement remove(int index) {
        return elements.remove(index);
    }

    public int indexOf(ListElement element) {
        return elements.indexOf(element);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(this.layout, parent, false);

        ImageView imageView = view.findViewById(R.id.lab5_image);
        TextView text1View = view.findViewById(R.id.lab5_text1);
        TextView text2View = view.findViewById(R.id.lab5_text2);

        ListElement element = elements.get(position);

        imageView.setImageResource(element.getImageResourceId());
        text1View.setText(element.getText1());
        text2View.setText(element.getText2());

        return view;
    }
}
