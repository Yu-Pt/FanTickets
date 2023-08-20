package com.example.fantickets;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;


public class PersonalAccountFragment extends Fragment {

    private FirebaseAuth mAuth;
    StorageReference stRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
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
        View view = inflater.inflate(R.layout.fragment_personal_account, container, false);
        FirebaseUser cUser = mAuth.getCurrentUser();
        TextView FioText = (TextView) view.findViewById(R.id.personal_fio);
        TextView EmailText = (TextView) view.findViewById(R.id.datauser_email);
        RoundedImageView rImage = view.findViewById(R.id.imageProfilePA);
        EmailText.setText(cUser.getEmail());
        String id = cUser.getUid();
        final DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference("User").child(id);
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        String fio = dataSnapshot.child("Name").getValue().toString();
                        String fio2 = dataSnapshot.child("Surname").getValue().toString();
                        FioText.setText(fio + " " + fio2);
                        String link = dataSnapshot.child("image").getValue().toString();
                        Picasso.get().load(link).into(rImage);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {
                    }
                });
        Button button = (Button) view.findViewById(R.id.sign_out);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        ImageButton button1 = (ImageButton) view.findViewById(R.id.toMyTickets);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_personalAccountFragment_to_myTicketsFragment);
            }
        });
        ImageButton button2 = (ImageButton) view.findViewById(R.id.toMyFanID);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_personalAccountFragment_to_myFanIDFragment);
            }
        });
        ImageButton button3 = (ImageButton) view.findViewById(R.id.toMyMatches);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_personalAccountFragment_to_myMatchesFragment);
            }
        });
        ImageButton button4 = (ImageButton) view.findViewById(R.id.toMyData);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_personalAccountFragment_to_myDataFragment);
            }
        });
        return view;

    }

}