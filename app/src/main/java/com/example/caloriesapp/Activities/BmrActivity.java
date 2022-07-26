package com.example.caloriesapp.Activities;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.caloriesapp.Prevalent.Prevalent;
import com.example.caloriesapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class BmrActivity extends AppCompatActivity {

    private EditText height;
    private EditText weight;
    private EditText age;
    private ImageButton bmralert;
    private CheckBox checkwoman;
    private TextView result;
    private Button gym;
    private Double bmig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr);

        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        age = (EditText) findViewById(R.id.age);
        checkwoman = (CheckBox) findViewById(R.id.checkWoman);
        result = (TextView) findViewById(R.id.result);
        bmralert = (ImageButton) findViewById(R.id.imagebtn_bmr);
        gym = (Button) findViewById(R.id.gymplan_btn);

        //alert dialog to see what is bmr
        bmralert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(BmrActivity.this).create();
                alertDialog.setMessage("The basal metabolic rate (BMR) is the amount of energy needed while resting in a temperate environment when the digestive system is inactive. It is the equivalent of figuring out how much gas an idle car consumes while parked");
                alertDialog.setTitle("BMR");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();

            }
        });

        gym.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (bmig == null ){
                    Toast.makeText(BmrActivity.this, "You must first enter your informations to see your gym plan.", Toast.LENGTH_SHORT).show();
                }
                else {
                if (Double.compare(bmig, 15f) <= 0) {
                    Intent i = new Intent(BmrActivity.this, Gymplan1Activity.class);
                    startActivity(i);
                } else if (Double.compare(bmig, 15f) > 0  &&  Double.compare(bmig, 16f) <= 0) {
                    Intent i = new Intent(BmrActivity.this, Gymplan1Activity.class);
                    startActivity(i);

                } else if (Double.compare(bmig, 16f) > 0  &&  Double.compare(bmig, 18.5f) <= 0) {
                    Intent i = new Intent(BmrActivity.this, Gymplan1Activity.class);
                    startActivity(i);

                } else if (Double.compare(bmig, 18.5f) > 0  &&  Double.compare(bmig, 25f) <= 0) {
                    Intent i = new Intent(BmrActivity.this, Gymplan2Activity.class);
                    startActivity(i);

                } else if (Double.compare(bmig, 25f) > 0  &&  Double.compare(bmig, 30f) <= 0) {
                    Intent i = new Intent(BmrActivity.this, Gymplan2Activity.class);
                    startActivity(i);

                } else if (Double.compare(bmig, 30f) > 0  &&  Double.compare(bmig, 35f) <= 0) {
                    Intent i = new Intent(BmrActivity.this, Gymplan2Activity.class);
                    startActivity(i);

                } else if (Double.compare(bmig, 35f) > 0  &&  Double.compare(bmig, 40f) <= 0) {
                    Intent i = new Intent(BmrActivity.this, Gymplan3Activity.class);
                    startActivity(i);

                } else {
                    Intent i = new Intent(BmrActivity.this, Gymplan3Activity.class);
                    startActivity(i);
          ;
                }

            }}

        });


    }

    public void calculateBMR(View v) {

        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();
        String ageStr = age.getText().toString();

        //chect the if editboxs is not empty
        if (heightStr != null && !"".equals(heightStr)
                && weightStr != null && !"".equals(weightStr) && ageStr != null && !"".equals(ageStr)) {
            float heightValue = Float.parseFloat(heightStr) ;
            float weightValue = Float.parseFloat(weightStr);
            float ageValue = Float.parseFloat(ageStr);

            //calculate bmr for woman
            if (checkwoman.isChecked()) {
                double bmr =  (10 * weightValue) + (6.25 * heightValue) - (5 * ageValue) - 161;
                double bmi = ( (weightValue) / (heightValue/100)* (heightValue/100));
                displayBMR(bmr);
                opengymplan(bmi);
                //get user phone for save to current user into database
                String phone = Prevalent.currentOnlineUser.getPhone();
                addBmr(bmr, phone);
                bmig = bmi;
            //calculate bmr for man
            } else {
                double bmr = 5 + (10 * weightValue) + (6.25 * heightValue) - (5 * ageValue);
                double bmi = ((weightValue) / (heightValue/100)* (heightValue/100));
                displayBMR(bmr);
                opengymplan(bmi);
                bmig = bmi;
                String phone = Prevalent.currentOnlineUser.getPhone();
                addBmr(bmr, phone);
            }

        }
    }



    //save bmr in firebase
    private void addBmr ( Double bmr,  String phone){
        final DatabaseReference dref;
        dref = FirebaseDatabase.getInstance().getReference();
        //add bmr to user database
        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ((snapshot.child("Users").child(phone).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("bmr", bmr);

                    dref.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    //Toast.makeText(BmrActivity.this, "Congratulations, your bmi has saved successfully.", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(BmrActivity.this, "Error in saving.", Toast.LENGTH_SHORT).show();

            }
        });
    }

            //method to display bmr
            private void displayBMR(double bmr) {
        String bmrLabel = "";

        bmrLabel = "You must eat " + Double.toString(bmr) + " calories per day";
        result.setText(bmrLabel);

    }

    private void opengymplan(double bmi) {
        String bmiLabel = "";



//        bmrLabel = Double.toString(bmr) ;
//        result.setText(bmrLabel);
    }

}