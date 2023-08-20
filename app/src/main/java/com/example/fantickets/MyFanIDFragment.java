package com.example.fantickets;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class MyFanIDFragment extends Fragment {

    private FirebaseAuth mAuth;


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
        View view = inflater.inflate(R.layout.fragment_my_fan_i_d, container, false);

        TextView name = view.findViewById(R.id.fanid_name);
        TextView code = view.findViewById(R.id.fanid_code);
        ImageView avatar = view.findViewById(R.id.fanid_avatar);
        ImageView imageCode = view.findViewById(R.id.fanid_image_code);

        FirebaseUser cUser = mAuth.getCurrentUser();
        String id = cUser.getUid();
        final DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference("User").child(id);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link = snapshot.child("image").getValue().toString();
                Picasso.get().load(link).into(avatar);
                String link2 = "https://firebasestorage.googleapis.com/v0/b/fantickets-bfd4c.appspot.com/o/UsersImages%2Fbarcode.png?alt=media&token=f95a93a3-ade3-4f15-8e55-e420b39716b2";
                Picasso.get().load(link2).into(imageCode);
                String fio = snapshot.child("Name").getValue().toString();
                String fio2 = snapshot.child("Surname").getValue().toString();
                name.setText(fio + " " + fio2);
                String codee = snapshot.child("Code").getValue().toString();
                code.setText(codee);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return view;
    }

}