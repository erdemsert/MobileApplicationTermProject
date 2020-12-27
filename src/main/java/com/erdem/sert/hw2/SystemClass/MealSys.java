package com.erdem.sert.hw2.SystemClass;

import com.erdem.sert.hw2.Food.Food;
import com.erdem.sert.hw2.Activities.Meal;
import com.erdem.sert.hw2.R;

import java.util.ArrayList;
import java.util.Collections;

public class MealSys {

    private static ArrayList<Meal> mealList;
    public static void prepareMealData(){
        mealList = new ArrayList<>();
        Collections.addAll(mealList,
                new Meal("Breakfast", R.drawable.breakfast,0),
                new Meal("Lunch",R.drawable.lunch,1),
                new Meal("Dinner",R.drawable.dinner,2)
        );

    }
    public static ArrayList<Meal> getMealList() {
        return mealList;
    }

}
