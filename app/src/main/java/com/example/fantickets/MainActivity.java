package com.example.fantickets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        DrawerLayout drawerLayout = findViewById(R.id.drawableLayout);

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        NavController navController = Navigation.findNavController(this,R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView, navController);
        View headerView = navigationView.getHeaderView(0);
        TextView userNameHeader = headerView.findViewById(R.id.header_name);
        TextView userSurnameHeader = headerView.findViewById(R.id.header_surname);
        RoundedImageView profileImageView = headerView.findViewById(R.id.imageProfile);
        FirebaseUser cUser = mAuth.getCurrentUser();
        String id = cUser.getUid();
        final DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference("User").child(id);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String fio = snapshot.child("Name").getValue().toString();
                String fio2 = snapshot.child("Surname").getValue().toString();
                userNameHeader.setText(fio);
                userSurnameHeader.setText(fio2);
                String link = snapshot.child("image").getValue().toString();
                Picasso.get().load(link).into(profileImageView);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if(cUser == null  && cUser.isEmailVerified()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){

        }
    }
}
