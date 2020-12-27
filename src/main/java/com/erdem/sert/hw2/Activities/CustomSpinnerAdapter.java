package com.erdem.sert.hw2.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.erdem.sert.hw2.R;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends ArrayAdapter<Meal> {

    Context context;
    ArrayList<Meal> spinnerItemValues;

    public CustomSpinnerAdapter(@NonNull Context context, @NonNull ArrayList<Meal> spinnerItems) {
        super(context, R.layout.spinner_meal_item, spinnerItems);
        this.context = context;
        spinnerItemValues = spinnerItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position,convertView,parent);
    }
    public View getCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.spinner_meal_item, parent, false);

        ConstraintLayout constraintLayout = view.findViewById(R.id.consSpinItemLayout);
        ImageView imgItem = view.findViewById(R.id.mealImage);
        TextView tvCategory = view.findViewById(R.id.mealType);

        Meal selectedCategory = spinnerItemValues.get(position);

        tvCategory.setText(selectedCategory.getName());
        imgItem.setImageResource(selectedCategory.getImgIds());

        return view;
    }
}
