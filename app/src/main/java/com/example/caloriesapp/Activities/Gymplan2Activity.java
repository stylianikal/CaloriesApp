package com.example.caloriesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.caloriesapp.R;

public class Gymplan2Activity extends AppCompatActivity {
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymplan2);

        back = (ImageButton) findViewById(R.id.back_btn2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Gymplan2Activity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }
}