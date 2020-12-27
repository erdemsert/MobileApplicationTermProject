package com.erdem.sert.hw2.Food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.erdem.sert.hw2.Activities.Meal;
import com.erdem.sert.hw2.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {
    ImageView img;
    TextView mealTitle;
    RecyclerView recyclerViewFood;
    DatabaseHelper dbHelper;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hiding title bar using code
        getSupportActionBar().hide();
        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_food);

        //Copy database from the assets/ folder to the data/data/databases/ folder. İf ıt exist, there is no need to copy it
        try {
            String fileToDatabase = "/data/data/" + getPackageName() + "/databases/"+DatabaseHelper.DATABASE_NAME;
            File file = new File(fileToDatabase);
            File pathToDatabasesFolder = new File("/data/data/" + getPackageName() + "/databases/");
            if (!file.exists()) {
                pathToDatabasesFolder.mkdirs();
                Log.d("BURDA", "BURDA");
                CopyDB( getResources().getAssets().open(DatabaseHelper.DATABASE_NAME+".db"),
                        new FileOutputStream(fileToDatabase));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dbHelper=new DatabaseHelper(this);
        img=findViewById(R.id.imgMeal);
        mealTitle=findViewById(R.id.tvMeal);
        recyclerViewFood=findViewById(R.id.recyclerViewFood);

        intent = getIntent();
        Bundle b = intent.getExtras();
        Meal meal = b.getParcelable("selectedMeal");

        mealTitle.setText(meal.getName());
        img.setImageResource(meal.getImgIds());

        ArrayList<Food> selectedFoods = FoodDB.getFoodsByType(dbHelper,meal.getType());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FoodActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewFood.setLayoutManager(linearLayoutManager);

        MyRecyclerViewAdapter recyclerViewAdapter=new MyRecyclerViewAdapter(FoodActivity.this,selectedFoods);
        recyclerViewFood.setAdapter(recyclerViewAdapter);

    }
    public void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
        // Copy 1K bytes at a time
        byte[] buffer = new byte[1024];
        int length;
        Log.d("BURDA", "BURDA2");

        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
            Log.d("BURDA", "BURDA3");
        }
        inputStream.close();
        outputStream.close();
    }
    public void onClick(View view) {

    }
}