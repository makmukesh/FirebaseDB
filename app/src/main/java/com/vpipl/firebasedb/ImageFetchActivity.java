package com.vpipl.firebasedb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ImageFetchActivity extends AppCompatActivity {

    private static final String TAG = "ImageFetchActivity";
    FirebaseDatabase database;

    // views for button
    private Button btnSelect, btnUpload;

    // view for image view
    private ImageView imageView;

    // Uri indicates, where the image will be picked from
    private Uri filePath;

    // request code
    private final int PICK_IMAGE_REQUEST = 22;

    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_fetch);

        FirebaseApp.initializeApp(ImageFetchActivity.this);

        // initialise views
        btnUpload = findViewById(R.id.btnUpload);
        imageView = findViewById(R.id.imgView);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        //StorageReference storageRef = storage.getReferenceFromUrl("gs://tutsplus-firebase.appspot.com").child("android.jpg");
        StorageReference storageRef = storage.getReferenceFromUrl("gs://fir-db-73e77.appspot.com").child("/images/cb71239c-1d7a-4b41-8db9-025826a230e6");

        try {
            final File localFile = File.createTempFile("images", "png");
            storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    imageView.setImageBitmap(bitmap);
                    Toast.makeText(ImageFetchActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(ImageFetchActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e ) {}
    }
}