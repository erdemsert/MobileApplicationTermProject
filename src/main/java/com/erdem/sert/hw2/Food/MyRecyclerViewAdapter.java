package com.erdem.sert.hw2.Food;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.erdem.sert.hw2.SystemClass.MealSys;
import com.erdem.sert.hw2.R;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewItemHolder> {
    private Context context;
    private ArrayList<Food> recyclerItemValues;
    DatabaseHelper dbHelper;
    Dialog dialog;
    public MyRecyclerViewAdapter(Context context, ArrayList<Food> values){
        this.context = context;
        this.recyclerItemValues = values;
        dbHelper= new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public MyRecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflator = LayoutInflater.from(viewGroup.getContext());

        View itemView = inflator.inflate(R.layout.recyclerview_food_item, viewGroup, false);

        MyRecyclerViewItemHolder mViewHolder = new MyRecyclerViewItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewItemHolder myRecyclerViewItemHolder, int i) {

        final Food food = recyclerItemValues.get(i);

        myRecyclerViewItemHolder.tvName.setText(food.getName());
        Bitmap bitmap = BitmapFactory.decodeByteArray(food.getImage() , 0, food.getImage().length);
        myRecyclerViewItemHolder.imgFood.setImageBitmap(bitmap);
        myRecyclerViewItemHolder.tvFoodPrice.setText(food.getPrice()+" TL");
        myRecyclerViewItemHolder.tvFoodCalorie.setText(food.getCalorie()+ "kcl");

        myRecyclerViewItemHolder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                displayDialog(food.getName(),bitmap,food.getType(),food.getPrice(),food.getCalorie(),i);
                return true;
            }
        });

    }
    public void refreshMyAdapterAfterDelete(int position){
        recyclerItemValues = FoodDB.getAllFoods(dbHelper);
        recyclerItemValues.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

     class MyRecyclerViewItemHolder extends  RecyclerView.ViewHolder{
        TextView tvName,tvFoodPrice,tvFoodCalorie;
        ImageView imgFood;
        ConstraintLayout parentLayout;
        public MyRecyclerViewItemHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvFoodName);
            tvFoodPrice = itemView.findViewById(R.id.tvFoodPrice);
            tvFoodCalorie = itemView.findViewById(R.id.tvFoodCalorie);

            imgFood = itemView.findViewById(R.id.imgFood);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
    public void displayDialog(final String name,Bitmap image,int type,double price,double calorie,int pos){
        final TextView itemName,itemPrice,itemType,itemCalorie;
        final ImageView itemImage;
        Button close;
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog);
        itemName=dialog.findViewById(R.id.itemName);
        itemPrice=dialog.findViewById(R.id.itemPrice);
        itemImage=dialog.findViewById(R.id.itemImage);
        itemType=dialog.findViewById(R.id.itemType);
        itemCalorie=dialog.findViewById(R.id.itemCalorie);

        close=dialog.findViewById(R.id.btnClose);


        itemName.setText(name);
        itemImage.setImageBitmap(image);
        if(type==0){
            itemType.setText("Breakfast");
        }
        else if(type==1){
            itemType.setText("Lunch");
        }
        else{
            itemType.setText("Dinner");
        }
        itemPrice.setText(price+" TL");
        itemCalorie.setText(calorie+" kcl");
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

      dialog.show();
    }

}
