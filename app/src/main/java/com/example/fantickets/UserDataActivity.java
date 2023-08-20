package com.example.fantickets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.UUID;

public class UserDataActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        mAuth = FirebaseAuth.getInstance();
        Button bttChooseAvatar = findViewById(R.id.button_choose_avatar);
        Button bttUploadAvatar = findViewById(R.id.button_upload_avatar);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        bttChooseAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
        bttUploadAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });
        ImageView rImage = findViewById(R.id.imageViewAvatar);
        FirebaseUser cUser = mAuth.getCurrentUser();
        String id = cUser.getUid();
        final DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference("User").child(id);
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        String link = dataSnapshot.child("image").getValue().toString();
                        Picasso.get().load(link).into(rImage);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {
                    }
                });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageMdata = findViewById(R.id.imageViewAvatar);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageMdata.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {
        final StorageReference profileImageRef = FirebaseStorage.getInstance().getReference("profilepics/" + System.currentTimeMillis() + ".jpg");
        if(filePath != null)
        {
            profileImageRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            profileImageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String profileImageUrl=task.getResult().toString();
                                    FirebaseUser cUser = mAuth.getCurrentUser();
                                    String idtable = cUser.getUid();
                                    final DatabaseReference refdb;
                                    refdb = FirebaseDatabase.getInstance().getReference("User").child(idtable);
                                    refdb.child("image").setValue(profileImageUrl);
                                    }
                                });
                            }
                        });
                    }
    }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if (cUser == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }


}