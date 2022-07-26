package com.example.caloriesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.caloriesapp.R;

public class Gymplan1Activity extends AppCompatActivity {
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymplan1);
        back = (ImageButton) findViewById(R.id.back_btn1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Gymplan1Activity.this, HomeActivity.class);
                startActivity(i);
            }
        });




    }
}