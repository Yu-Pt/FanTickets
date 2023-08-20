package com.example.fantickets;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.HashMap;


public class MyDataFragment extends Fragment {
    private FirebaseAuth mAuth;
    Uri imageUri;
    StorageReference storageProfilePictureRef;
    StorageTask uploadTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        storageProfilePictureRef = FirebaseStorage.getInstance().getReference().child("UsersImages");
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if (cUser == null) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_data, container, false);
        FirebaseUser cUser = mAuth.getCurrentUser();
        TextView FioText1 = (TextView) view.findViewById(R.id.FIO1);
        TextView FioText2 = (TextView) view.findViewById(R.id.FIO2);
        TextView BirthText = (TextView) view.findViewById(R.id.databirth);
        TextView DocText = (TextView) view.findViewById(R.id.document);
        TextView GenderText = (TextView) view.findViewById(R.id.gender);
        RoundedImageView imageMdata = view.findViewById(R.id.imageProfileMdata);
        String id = cUser.getUid();
        final DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference("User").child(id);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link = snapshot.child("image").getValue().toString();
                Picasso.get().load(link).into(imageMdata);
                String fio = snapshot.child("Name").getValue().toString();
                String fio2 = snapshot.child("Surname").getValue().toString();
                FioText1.setText(fio);
                FioText2.setText(fio2);
                String birth = snapshot.child("Birth").getValue().toString();
                BirthText.setText(birth);
                String doc = snapshot.child("Document").getValue().toString();
                DocText.setText(doc);
                String gender = snapshot.child("Gender").getValue().toString();
                GenderText.setText(gender);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        Button bttSaveChanges = view.findViewById(R.id.button_save_changes);
        bttSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_myDataFragment_to_changeDataFragment);
            }
        });

        return view;
    }
}