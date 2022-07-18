package com.example.caloriesapp.Admin;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriesapp.Model.AdminPlans;
import com.example.caloriesapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminNewPlans extends AppCompatActivity
{
    private RecyclerView plansList;
    private DatabaseReference plansRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_plans);

        plansRef = FirebaseDatabase.getInstance().getReference().child("Plans");

        plansList = findViewById(R.id.plans_list);
        plansList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<AdminPlans> options =
                new FirebaseRecyclerOptions.Builder<AdminPlans>()
                .setQuery(plansRef, AdminPlans.class)
                .build();

        FirebaseRecyclerAdapter<AdminPlans, AdminOrderViewHolder> adapter =
                new FirebaseRecyclerAdapter<AdminPlans, AdminOrderViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AdminOrderViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull AdminPlans model)
                    {
                        holder.userName.setText("Name: " + model.getName());
                        holder.userPhoneNumber.setText("Phone: " + model.getPhone());
                        holder.userTotalCalories.setText("Total Calories= Cal" + model.getTotalAmount());
                        holder.userDataTime.setText("Plan at: " + model.getDate() + "  " + model.getTime());
                        holder.userEmailAddress.setText("Email: " + model.getEmail() );


                        holder.ShowBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
                                String uID = getRef(position).getKey();

                                Intent intent = new Intent(AdminNewPlans.this, AdminUserProductsActivity.class);
                                intent.putExtra("uid", uID);
                                startActivity(intent);
                            }
                        });

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
                                CharSequence options[] = new CharSequence[]
                                        {
                                                "Yes",
                                                "No"
                                        };

                                AlertDialog.Builder builder = new AlertDialog.Builder(AdminNewPlans.this);
                                builder.setTitle("Have you seen this daily calculation?");

                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {
                                        if (i == 0)
                                        {
                                            String uID = getRef(position).getKey();

                                            RemoverOrder(uID);
                                        }
                                        else
                                        {
                                            finish();
                                        }
                                    }
                                });
                                builder.show();
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public AdminOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout, parent, false);
                        return new AdminOrderViewHolder(view);
                    }
                };
        plansList.setAdapter(adapter);
        adapter.startListening();
    }


    public static class AdminOrderViewHolder extends RecyclerView.ViewHolder
    {
        public TextView userName, userPhoneNumber, userTotalCalories, userDataTime, userEmailAddress;
        public Button ShowBtn;

        public AdminOrderViewHolder(@NonNull View itemView)
        {
            super(itemView);

            userName = itemView.findViewById(R.id.plan_user_name);
            userPhoneNumber = itemView.findViewById(R.id.plan_phone_number);
            userTotalCalories = itemView.findViewById(R.id.plan_total_calories);
            userDataTime = itemView.findViewById(R.id.plan_date_time);
            userEmailAddress = itemView.findViewById(R.id.plan_email);
            ShowBtn = itemView.findViewById(R.id.show_all_products_btn);

        }
    }

    private void RemoverOrder(String uID)
    {
        plansRef.child(uID).removeValue();
    }
}