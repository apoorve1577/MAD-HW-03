package com.example.hw_04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DrinksAdapter extends ArrayAdapter<Drink> {
    public DrinksAdapter(@NonNull Context context, int resource,  @NonNull ArrayList<Drink> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.drink_row_item, parent, false);

        }

        Drink currDrink = getItem(position);

        TextView textViewDrinksPercent = convertView.findViewById(R.id.textViewDrinkPercent);
        TextView textViewAlcoholSize = convertView.findViewById(R.id.textViewAlcoholSize);
        TextView textViewDate = convertView.findViewById(R.id.textViewDate);

        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm a");


        textViewDate.setText("Added " + dateFormat.format(currDrink.createDatetime));
        textViewAlcoholSize.setText( currDrink.size + " Oz");
        textViewDrinksPercent.setText(currDrink.percent + " % Alcohol");


        return super.getView(position, convertView, parent);
    }
}
