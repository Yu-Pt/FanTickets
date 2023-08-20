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

public class Ticket2Fragment extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_ticket2, container, false);

        TextView numberText = view.findViewById(R.id.number_ticket2);
        TextView dateText = view.findViewById(R.id.date_ticket2);
        TextView timeText = view.findViewById(R.id.time_ticket2);
        TextView clubsText = view.findViewById(R.id.clubs_ticket2);
        TextView stadiumText = view.findViewById(R.id.stadium_ticket2);
        TextView sectorText = view.findViewById(R.id.sector_ticket2);
        TextView tribuneText = view.findViewById(R.id.tribune_ticket2);
        TextView rowText = view.findViewById(R.id.row_ticket2);
        TextView placeText = view.findViewById(R.id.place_ticket2);
        TextView costText = view.findViewById(R.id.cost_ticket2);
        ImageView qrImage = view.findViewById(R.id.qr_ticket2);

        FirebaseUser cUser = mAuth.getCurrentUser();
        String idUser = cUser.getUid();
        final DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference("Ticket").child(idUser).child("2");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String number = snapshot.child("number").getValue().toString();
                numberText.setText(number);
                String date = snapshot.child("date").getValue().toString();
                dateText.setText(date);
                String time = snapshot.child("time").getValue().toString();
                timeText.setText(time);
                String club1 = snapshot.child("club1").getValue().toString();
                String club2 = snapshot.child("club2").getValue().toString();
                clubsText.setText(club1 + " - " + club2);
                String stadium = snapshot.child("stadium").getValue().toString();
                stadiumText.setText(stadium);
                String sector = snapshot.child("sector").getValue().toString();
                sectorText.setText(sector);
                String tribuna = snapshot.child("tribuna").getValue().toString();
                tribuneText.setText(tribuna);
                String rad = snapshot.child("rad").getValue().toString();
                rowText.setText(rad);
                String mesto = snapshot.child("mesto").getValue().toString();
                placeText.setText(mesto);
                String cost = snapshot.child("cost").getValue().toString();
                costText.setText(cost);
                String link = snapshot.child("qr").getValue().toString();
                Picasso.get().load(link).into(qrImage);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return view;
    }

}