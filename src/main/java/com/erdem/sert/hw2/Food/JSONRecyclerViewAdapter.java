package com.erdem.sert.hw2.Food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.erdem.sert.hw2.Activities.mealJson;
import com.erdem.sert.hw2.R;

import java.util.ArrayList;

public class JSONRecyclerViewAdapter extends RecyclerView.Adapter<JSONRecyclerViewAdapter.JSONRecyclerViewItemHolder> {
    private Context context;
    private ArrayList<mealJson> recyclerItemValues;


    public JSONRecyclerViewAdapter(Context context, ArrayList<mealJson> values){
        this.context = context;
        this.recyclerItemValues = values;

    }

    @NonNull
    @Override
    public JSONRecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflator = LayoutInflater.from(viewGroup.getContext());

        View itemView = inflator.inflate(R.layout.jsonrecyclerview, viewGroup, false);

        JSONRecyclerViewItemHolder mViewHolder = new JSONRecyclerViewItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JSONRecyclerViewItemHolder jsonRecyclerViewItemHolder, int i) {

        final mealJson food = recyclerItemValues.get(i);

        jsonRecyclerViewItemHolder.jsonName.setText(food.getName());
        jsonRecyclerViewItemHolder.jsonPrice.setText(food.getCalori());

    }
    public void refreshMyAdapterAfterDelete(int position){

        recyclerItemValues.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();

    }
    class JSONRecyclerViewItemHolder extends RecyclerView.ViewHolder {
        TextView jsonName,jsonPrice;
        ConstraintLayout jsonLayout;
        public JSONRecyclerViewItemHolder(@NonNull View itemView) {
            super(itemView);
            jsonName = itemView.findViewById(R.id.jsonName);
            jsonPrice = itemView.findViewById(R.id.jsonPrice);
            jsonLayout = itemView.findViewById(R.id.jsonLayout);

        }
    }
}
