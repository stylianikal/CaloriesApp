package com.example.caloriesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriesapp.Model.Products;
import com.example.caloriesapp.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class CategoriesActivity extends AppCompatActivity {

    private ImageView meats, fish, dairy, vegies, fruits, bread, sweets;

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference unverifiedProductsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        meats = (ImageView) findViewById(R.id.meats_category);
        fish = (ImageView) findViewById(R.id.fish_category);
        dairy = (ImageView) findViewById(R.id.dairy_category);
        vegies = (ImageView) findViewById(R.id.vegies_category);
        fruits = (ImageView) findViewById(R.id.fruits_category);
        bread = (ImageView) findViewById(R.id.bread_category);
        sweets = (ImageView) findViewById(R.id.sweets_category);


        unverifiedProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");

        recyclerView = findViewById(R.id.category_products_checklist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        meats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FirebaseRecyclerOptions<Products> options =
                        new FirebaseRecyclerOptions.Builder<Products>()
                                .setQuery(unverifiedProductsRef.orderByChild("category").equalTo("meat"), Products.class).build();

                FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                        new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                            @Override
                            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Products model)
                            {
                                holder.txtProductName.setText(model.getPname());
                                holder.txtProductDescription.setText(model.getDescription());
                                holder.txtProductCalories.setText("Calories = " + model.getCalories() + "Cal");
                                Picasso.get().load(model.getImage()).into(holder.imageView);

                                holder.itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(CategoriesActivity.this, ProductDetailsActivity.class);
                                        intent.putExtra("pid", model.getPid());
                                        startActivity(intent);
                                    }
                                });
                            }

                            @NonNull
                            @Override
                            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                            {
                                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
                                ProductViewHolder holder = new ProductViewHolder(view);
                                return holder;
                            }
                        };

                recyclerView.setAdapter(adapter);
                adapter.startListening();
            }

        });

        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FirebaseRecyclerOptions<Products> options =
                        new FirebaseRecyclerOptions.Builder<Products>()
                                .setQuery(unverifiedProductsRef.orderByChild("category").equalTo("fish"), Products.class).build();

                FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                        new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                            @Override
                            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Products model)
                            {
                                holder.txtProductName.setText(model.getPname());
                                holder.txtProductDescription.setText(model.getDescription());
                                holder.txtProductCalories.setText("Calories = " + model.getCalories() + "Cal");
                                Picasso.get().load(model.getImage()).into(holder.imageView);

                            }

                            @NonNull
                            @Override
                            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                            {
                                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
                                ProductViewHolder holder = new ProductViewHolder(view);
                                return holder;
                            }
                        };

                recyclerView.setAdapter(adapter);
                adapter.startListening();
            }

        });

        dairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FirebaseRecyclerOptions<Products> options =
                        new FirebaseRecyclerOptions.Builder<Products>()
                                .setQuery(unverifiedProductsRef.orderByChild("category").equalTo("dairy"), Products.class).build();

                FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                        new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                            @Override
                            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Products model)
                            {
                                holder.txtProductName.setText(model.getPname());
                                holder.txtProductDescription.setText(model.getDescription());
                                holder.txtProductCalories.setText("Calories = " + model.getCalories() + "Cal");
                                Picasso.get().load(model.getImage()).into(holder.imageView);

                            }

                            @NonNull
                            @Override
                            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                            {
                                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
                                ProductViewHolder holder = new ProductViewHolder(view);
                                return holder;
                            }
                        };

                recyclerView.setAdapter(adapter);
                adapter.startListening();
            }

        });

        vegies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FirebaseRecyclerOptions<Products> options =
                        new FirebaseRecyclerOptions.Builder<Products>()
                                .setQuery(unverifiedProductsRef.orderByChild("category").equalTo("vegies"), Products.class).build();

                FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                        new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                            @Override
                            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Products model)
                            {
                                holder.txtProductName.setText(model.getPname());
                                holder.txtProductDescription.setText(model.getDescription());
                                holder.txtProductCalories.setText("Calories = " + model.getCalories() + "Cal");
                                Picasso.get().load(model.getImage()).into(holder.imageView);

                            }

                            @NonNull
                            @Override
                            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                            {
                                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
                                ProductViewHolder holder = new ProductViewHolder(view);
                                return holder;
                            }
                        };

                recyclerView.setAdapter(adapter);
                adapter.startListening();
            }

        });

        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FirebaseRecyclerOptions<Products> options =
                        new FirebaseRecyclerOptions.Builder<Products>()
                                .setQuery(unverifiedProductsRef.orderByChild("category").equalTo("fruits"), Products.class).build();

                FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                        new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                            @Override
                            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Products model)
                            {
                                holder.txtProductName.setText(model.getPname());
                                holder.txtProductDescription.setText(model.getDescription());
                                holder.txtProductCalories.setText("Calories = " + model.getCalories() + "Cal");
                                Picasso.get().load(model.getImage()).into(holder.imageView);

                            }

                            @NonNull
                            @Override
                            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                            {
                                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
                                ProductViewHolder holder = new ProductViewHolder(view);
                                return holder;
                            }
                        };

                recyclerView.setAdapter(adapter);
                adapter.startListening();
            }

        });

        bread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FirebaseRecyclerOptions<Products> options =
                        new FirebaseRecyclerOptions.Builder<Products>()
                                .setQuery(unverifiedProductsRef.orderByChild("category").equalTo("bread"), Products.class).build();

                FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                        new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                            @Override
                            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Products model)
                            {
                                holder.txtProductName.setText(model.getPname());
                                holder.txtProductDescription.setText(model.getDescription());
                                holder.txtProductCalories.setText("Calories = " + model.getCalories() + "Cal");
                                Picasso.get().load(model.getImage()).into(holder.imageView);

                            }

                            @NonNull
                            @Override
                            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                            {
                                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
                                ProductViewHolder holder = new ProductViewHolder(view);
                                return holder;
                            }
                        };

                recyclerView.setAdapter(adapter);
                adapter.startListening();
            }

        });


        sweets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FirebaseRecyclerOptions<Products> options =
                        new FirebaseRecyclerOptions.Builder<Products>()
                                .setQuery(unverifiedProductsRef.orderByChild("category").equalTo("sweets"), Products.class).build();

                FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                        new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                            @Override
                            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Products model)
                            {
                                holder.txtProductName.setText(model.getPname());
                                holder.txtProductDescription.setText(model.getDescription());
                                holder.txtProductCalories.setText("Calories = " + model.getCalories() + "Cal");
                                Picasso.get().load(model.getImage()).into(holder.imageView);

                                holder.itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(CategoriesActivity.this, ProductDetailsActivity.class);
                                        intent.putExtra("pid", model.getPid());
                                        startActivity(intent);
                                    }
                                });
                            }

                            @NonNull
                            @Override
                            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                            {
                                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
                                ProductViewHolder holder = new ProductViewHolder(view);
                                return holder;
                            }
                        };

                recyclerView.setAdapter(adapter);
                adapter.startListening();
            }

        });

    }
}