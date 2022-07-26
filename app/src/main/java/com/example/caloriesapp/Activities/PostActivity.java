package com.example.caloriesapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.caloriesapp.Prevalent.Prevalent;
import com.example.caloriesapp.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class PostActivity extends AppCompatActivity {
    private ImageButton selectpostimage;
    private Button updatepostbutton;
    private EditText postdescription;
    private String Description;
    private StorageTask uploadTask;
    private ProgressDialog loadingbar;

    private StorageReference PostImagesReference;
    private DatabaseReference userRef, postRef;

    private String saveCurrentDate, saveCurrentTime, postRandomname;
    //downloadurl;

    private static final int Gallery_Pick= 1;
    private Uri imageUri;
    private String myUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        postRef = FirebaseDatabase.getInstance().getReference().child("Posts");

        PostImagesReference = FirebaseStorage.getInstance().getReference().child("Post Images");

        selectpostimage = (ImageButton) findViewById(R.id.select_post_image);
        updatepostbutton = (Button) findViewById(R.id.uploadpost_btn);
        postdescription = (EditText) findViewById(R.id.post_description);
        loadingbar = new ProgressDialog(this);


        selectpostimage.setOnClickListener(v -> OpenGallery());

        updatepostbutton.setOnClickListener(v -> ValidatepostInfo());

    }
    //open phone gallery to choose an image
    private void OpenGallery(){
        Intent galleryintent = new Intent();
        galleryintent.setAction(Intent.ACTION_GET_CONTENT);
        galleryintent.setType("image/*");
        startActivityForResult(galleryintent,  Gallery_Pick);
    }

    //check if description and image are not empty
    private void ValidatepostInfo(){
        Description = postdescription.getText().toString();
        if (imageUri == null){
            Toast.makeText(this, "Please select post image..", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Description)){
            Toast.makeText(this, "Please say something about image..", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingbar.setTitle("Add new post");
            loadingbar.setMessage("Please wait, while we are updating your new post");
            loadingbar.show();
            loadingbar.setCanceledOnTouchOutside(true);
            StoringImagetoFirebaseStorage();
        }

    }

    //save image to firestore and the url of image to save to current post
    private void StoringImagetoFirebaseStorage() {

        //get current date
        Calendar calFordDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calFordDate.getTime());

        //get current time
        Calendar calFordTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calFordTime.getTime());

        postRandomname = saveCurrentDate+saveCurrentTime;

        StorageReference filepath = PostImagesReference.child( postRandomname + ".jpg");
        uploadTask = filepath.putFile(imageUri);
        uploadTask.continueWithTask(new Continuation() {
            @Override
            public Object then(@NonNull Task task) throws Exception {
                if (!task.isSuccessful()){
                    throw task.getException();
                }
                return filepath.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()){
                    Uri downloadurl = task.getResult();
                    myUrl = downloadurl.toString();


                    Toast.makeText(PostActivity.this, "image uploaded successfully to storage", Toast.LENGTH_SHORT).show();
                    SavingPostInformation();}
                else
                {
                    String messaage= task.getException().getMessage();
                    Toast.makeText(PostActivity.this, "Error occured:" + messaage, Toast.LENGTH_SHORT).show();
                }
            }

        });


    }

    //save post's informations to firebase
    private void SavingPostInformation(){
        String phone = Prevalent.currentOnlineUser.getPhone();

        userRef.child(phone).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String name = Prevalent.currentOnlineUser.getName().toString();
                    String userprofileimage = snapshot.child("image").getValue().toString();

                    HashMap postMap = new HashMap();
                        postMap.put("phone", phone);
                        postMap.put("date", saveCurrentDate);
                        postMap.put("time", saveCurrentTime);
                        postMap.put("description", Description);
                        postMap.put("postimage",myUrl);
                        postMap.put("profileimage",userprofileimage);
                        postMap.put("fullname", name);

                    postRef.child(phone + postRandomname).updateChildren(postMap).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(PostActivity.this, HomeActivity.class);
                                startActivity(intent);
                                Toast.makeText(PostActivity.this, "New Post is updated successfully" , Toast.LENGTH_SHORT).show();
                                loadingbar.dismiss();

                            }
                            else{
                                String messaage= task.getException().getMessage();
                                Toast.makeText(PostActivity.this, "Error occured:" + messaage, Toast.LENGTH_SHORT).show();
                                loadingbar.dismiss();
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Gallery_Pick && resultCode== RESULT_OK && data!= null){
            //display image to user
            imageUri = data.getData();
            selectpostimage.setImageURI(imageUri);
        }
    }
}