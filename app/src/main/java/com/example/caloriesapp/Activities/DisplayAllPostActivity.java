package com.example.caloriesapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.caloriesapp.Model.Posts;
import com.example.caloriesapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DisplayAllPostActivity extends AppCompatActivity {
    private RecyclerView postList;
    private Button openadd;
    private ImageView imageview;
    private Toolbar mtoolbar;

    private DatabaseReference userRef, postRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_post);
        imageview = (ImageView) findViewById(R.id.post_image);
        postRef = FirebaseDatabase.getInstance().getReference().child("Posts");
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        mtoolbar = (Toolbar) findViewById(R.id.toolbar_post);
        openadd = (Button) findViewById(R.id.Open_add_new_post);


        postList = (RecyclerView) findViewById(R.id.all_users_post_list);
        postList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        postList.setLayoutManager(linearLayoutManager);

        //oepn PostActivity to add new post
        openadd.setOnClickListener(v -> {
            Intent intent = new Intent(DisplayAllPostActivity.this, PostActivity.class);
            startActivity(intent);
            finish();
        });
        DisplayAllUsersPost();

    }

    //method to display all post in a recycler view
    private void DisplayAllUsersPost() {
        FirebaseRecyclerOptions<Posts> options = new FirebaseRecyclerOptions.Builder<Posts> ()
                .setQuery(postRef, Posts.class)
                        .build();
        FirebaseRecyclerAdapter<Posts,PostsViewHolder> adapter = new FirebaseRecyclerAdapter<Posts, PostsViewHolder>(options) {
            @NonNull
            @Override
            public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_posts_layout, parent, false);
                PostsViewHolder holder = new PostsViewHolder(view);
                return holder;
            }

            @Override
            protected void onBindViewHolder(@NonNull PostsViewHolder postsViewHolder, int i, @NonNull Posts posts) {
                postsViewHolder.setFullname(posts.getFullname());
                postsViewHolder.setTime(posts.getTime());
                postsViewHolder.setDate(posts.getDate());
                postsViewHolder.setDescription(posts.getDescription());
                Picasso.get().load(posts.getPostimage()).into(postsViewHolder.setPostImage(posts.getPostimage()));
                Picasso.get().load(posts.getProfileimage()).into(postsViewHolder.setProfileImage(posts.getProfileimage()));
                postsViewHolder.setPostImage(posts.getPostimage());
                postsViewHolder.setProfileImage(posts.getProfileimage());
            }
        };
        postList.setAdapter(adapter);
        adapter.startListening();
    }
    //set the information to show for each post (fullname, date, time , picture and description
    public static class PostsViewHolder extends RecyclerView.ViewHolder{
        View mview;
        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            mview = itemView;
        }
        public void setFullname(String fullname){
            TextView username = (TextView) mview.findViewById(R.id.post_username);
            username.setText(fullname);
        }
        public void setTime(String time){
            TextView post_time = (TextView) mview.findViewById(R.id.post_time);
            post_time.setText("   " +time);
        }
        public void setDate(String date){
            TextView post_date = (TextView) mview.findViewById(R.id.post_date);
            post_date.setText("   " + date);
        }
        public void setDescription(String description){
            TextView post_description = (TextView) mview.findViewById(R.id.post_description);
            post_description.setText(description);
        }
        public ImageView setPostImage(String postpostimage){
            ImageView postpostImage = (ImageView) mview.findViewById(R.id.post_image);
            Picasso.get().load(postpostimage).into(postpostImage);
            return postpostImage;


        }
        public ImageView setProfileImage(String postprofileimage){
            ImageView profileImage = (ImageView) mview.findViewById(R.id.post_profile_image);
            Picasso.get().load(postprofileimage).into(profileImage);
            return profileImage;

        }

    }

}