package com.erdem.sert.hw2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.erdem.sert.hw2.Activities.DisplayInfoActivity;
import com.erdem.sert.hw2.R;

public class MainActivity extends AppCompatActivity {
    EditText etName,etSurname,etAge,etHeight,etWeight;
    RadioGroup rgGender;
    RadioButton rbGender;
    Button button_save;
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
        setContentView(R.layout.activity_main);


        etName=findViewById(R.id.etName);
        etSurname=findViewById(R.id.etSurname);
        etAge=findViewById(R.id.etAge);
        etHeight=findViewById(R.id.etHeight);
        etWeight=findViewById(R.id.etWeight);

        button_save=findViewById(R.id.button_save);

        rgGender=findViewById(R.id.rdGroup);

        sharedPreferences=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name=sharedPreferences.getString(KEY_NAME,null);

        if(name!=null){
            Intent intent=new Intent(MainActivity.this, DisplayInfoActivity.class);
            startActivity(intent);
        }


        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(KEY_NAME,etName.getText().toString());
                editor.putString(KEY_SURNAME,etSurname.getText().toString());
                editor.putString(KEY_AGE,etAge.getText().toString());
                editor.putString(KEY_HEIGHT,etHeight.getText().toString());
                editor.putString(KEY_WEIGHT,etWeight.getText().toString());
                editor.putString(KEY_GENDER,rbGender.getText().toString());
                editor.apply();
                Intent intent=new Intent(MainActivity.this,DisplayInfoActivity.class);
                Bundle b=new Bundle();
                String age=etAge.getText().toString();
                b.putString("age",age);
                intent.putExtras(b);

                startActivity(intent);

                Toast.makeText(MainActivity.this,"Login is done",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void onRadioButtonClicked(View view) {
        int radiobuttonId=rgGender.getCheckedRadioButtonId();
        rbGender=findViewById(radiobuttonId);
        Toast.makeText(this, "The gender is "+rbGender.getText().toString(), Toast.LENGTH_SHORT).show();

    }
}