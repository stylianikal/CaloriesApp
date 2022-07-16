package com.example.caloriesapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.caloriesapp.Prevalent.Prevalent;
import com.example.caloriesapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class BmiActivity extends AppCompatActivity {

    private EditText height;
    private EditText weight;
    private EditText age;
    private CheckBox checkwoman;
    private TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        age = (EditText) findViewById(R.id.age);
        checkwoman = (CheckBox) findViewById(R.id.checkWoman);
        result = (TextView) findViewById(R.id.result);
    }

    public void calculateBMI(View v) {
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();
        String ageStr = age.getText().toString();


        if (heightStr != null && !"".equals(heightStr)
                && weightStr != null && !"".equals(weightStr) && ageStr != null && !"".equals(ageStr)) {
            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);
            float ageValue = Float.parseFloat(ageStr);
            if (checkwoman.isChecked()) {
                double bmi = 655 + (9.6 * weightValue) + (1.8 * heightValue) - (4.7 * ageValue);
                displayBMI(bmi);
                String phone = Prevalent.currentOnlineUser.getPhone();
                addBmi(bmi, phone);
            } else {
                double bmi = 66 + (13.7 * weightValue) + (5 * heightValue) - (6.8 * ageValue);
                displayBMI(bmi);
                String phone = Prevalent.currentOnlineUser.getPhone();
                addBmi(bmi, phone);
            }

            //float bmi = weightValue / (heightValue * heightValue);

        }
    }
    private void addBmi ( Double bmi,  String phone){
        final DatabaseReference dref;
        dref = FirebaseDatabase.getInstance().getReference();

        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ((snapshot.child("Users").child(phone).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("bmi", bmi);

                    dref.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(BmiActivity.this, "Congratulations, your bmi add to firebase.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(BmiActivity.this, "No save, your bmi add to firebase.", Toast.LENGTH_SHORT).show();

            }
        });
    }


            private void displayBMI(double bmi) {
        String bmiLabel = "";

        if (Double.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
        } else if (Double.compare(bmi, 15f) > 0  &&  Double.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
        } else if (Double.compare(bmi, 16f) > 0  &&  Double.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Double.compare(bmi, 18.5f) > 0  &&  Double.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Double.compare(bmi, 25f) > 0  &&  Double.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Double.compare(bmi, 30f) > 0  &&  Double.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
        } else if (Double.compare(bmi, 35f) > 0  &&  Double.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
        }


        bmiLabel = bmi + "\n\n" + bmiLabel ;
        result.setText(bmiLabel);

    }
}