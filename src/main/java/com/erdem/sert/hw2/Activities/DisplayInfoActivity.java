package com.erdem.sert.hw2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.erdem.sert.hw2.R;
import com.erdem.sert.hw2.Activities.SecondActivity;

public class DisplayInfoActivity extends AppCompatActivity {
    TextView tvname,tvsurname,tvage,tvheight,tvweight,tvgender;
    Button logout,start;
    ImageView img;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME="mpref";
    private static final String KEY_NAME="name";
    private static final String KEY_SURNAME="surname";
    private static final String KEY_AGE="age";
    private static final String KEY_HEIGHT="height";
    private static final String KEY_WEIGHT="weight";
    private static final String KEY_GENDER="gender";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Hiding title bar using code
        getSupportActionBar().hide();
        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_display_info);
        tvname=findViewById(R.id.Name);
        tvsurname=findViewById(R.id.Surname);
        tvage=findViewById(R.id.Age);
        tvheight=findViewById(R.id.Height);
        tvweight=findViewById(R.id.Weight);
        tvgender=findViewById(R.id.Gender);
        logout=findViewById(R.id.logout);
        start=findViewById(R.id.start);
        img=findViewById(R.id.imgView);
        img.setImageResource(R.drawable.user);

        sharedPreferences=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name=sharedPreferences.getString(KEY_NAME,null);
        String surname=sharedPreferences.getString(KEY_SURNAME,null);
        String age=sharedPreferences.getString(KEY_AGE,null);
        String height=sharedPreferences.getString(KEY_HEIGHT,null);
        String weight=sharedPreferences.getString(KEY_WEIGHT,null);
        String gender=sharedPreferences.getString(KEY_GENDER,null);

        if(name!=null || surname !=null || age!=null || height!=null || weight!=null || gender!=null){
            tvname.setText("Name: "+name);
            tvsurname.setText("Surname: "+surname);
            tvage.setText("Age: "+age);
            tvheight.setText("Height: "+height);
            tvweight.setText("Weight: "+weight);
            tvgender.setText("Gender: "+gender);
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(DisplayInfoActivity.this, "Log Out is done", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DisplayInfoActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }
}