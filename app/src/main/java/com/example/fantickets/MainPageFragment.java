package com.example.fantickets;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class MainPageFragment extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);

        TextView CloseDate = (TextView) view.findViewById(R.id.close_date);
        TextView CloseMonth = (TextView) view.findViewById(R.id.close_month);
        TextView CloseClub1 = (TextView) view.findViewById(R.id.close_club1);
        TextView CloseClub2 = (TextView) view.findViewById(R.id.close_club2);
        TextView CloseTime = (TextView) view.findViewById(R.id.close_time);
        TextView CloseStadium = (TextView) view.findViewById(R.id.close_stadium);
        ImageView CloseImage = (ImageView) view.findViewById(R.id.closermatch);
        ImageView CloseImageClub1 = (ImageView) view.findViewById(R.id.close_image1);
        ImageView CloseImageClub2 = (ImageView) view.findViewById(R.id.close_image2);
        final DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference("Match").child("Зенит");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String date = snapshot.child("date").getValue().toString();
                CloseDate.setText(date);
                String month = snapshot.child("month").getValue().toString();
                CloseMonth.setText(month);
                String club1 = snapshot.child("club1").getValue().toString();
                CloseClub1.setText(club1);
                String club2 = snapshot.child("club2").getValue().toString();
                CloseClub2.setText(club2);
                String time = snapshot.child("time").getValue().toString();
                CloseTime.setText(time);
                String stadium = snapshot.child("stadium").getValue().toString();
                CloseStadium.setText(stadium);
                String link = snapshot.child("image1").getValue().toString();
                Picasso.get().load(link).into(CloseImageClub1);
                String link2 = snapshot.child("image2").getValue().toString();
                Picasso.get().load(link2).into(CloseImageClub2);
                CloseImage.setImageResource(R.drawable.fonosnov);
                CloseImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Navigation.findNavController(view).navigate(R.id.action_mainPageFragment_to_match15decFragment);
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        TextView PopDate = (TextView) view.findViewById(R.id.pop_date);
        TextView PopMonth = (TextView) view.findViewById(R.id.pop_month);
        TextView PopClub1 = (TextView) view.findViewById(R.id.pop_club1);
        TextView PopClub2 = (TextView) view.findViewById(R.id.pop_club2);
        TextView PopTime = (TextView) view.findViewById(R.id.pop_time);
        TextView PopStadium = (TextView) view.findViewById(R.id.pop_stadium);
        ImageView PopImage = (ImageView) view.findViewById(R.id.popularmatch);
        ImageView PopImageClub1 = (ImageView) view.findViewById(R.id.pop_image1);
        ImageView PopImageClub2 = (ImageView) view.findViewById(R.id.pop_image2);
        final DatabaseReference ref1;
        ref1 = FirebaseDatabase.getInstance().getReference("Match").child("Динамо");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String date = snapshot.child("date").getValue().toString();
                PopDate.setText(date);
                String month = snapshot.child("month").getValue().toString();
                PopMonth.setText(month);
                String club1 = snapshot.child("club1").getValue().toString();
                PopClub1.setText(club1);
                String club2 = snapshot.child("club2").getValue().toString();
                PopClub2.setText(club2);
                String time = snapshot.child("time").getValue().toString();
                PopTime.setText(time);
                String stadium = snapshot.child("stadium").getValue().toString();
                PopStadium.setText(stadium);
                String link = snapshot.child("image1").getValue().toString();
                Picasso.get().load(link).into(PopImageClub1);
                String link2 = snapshot.child("image2").getValue().toString();
                Picasso.get().load(link2).into(PopImageClub2);
                PopImage.setImageResource(R.drawable.fonosnov);
                PopImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Navigation.findNavController(view).navigate(R.id.action_mainPageFragment_to_match18decFragment);
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        TextView Pop2Date = (TextView) view.findViewById(R.id.pop2_date);
        TextView Pop2Month = (TextView) view.findViewById(R.id.pop2_month);
        TextView Pop2Club1 = (TextView) view.findViewById(R.id.pop2_club1);
        TextView Pop2Club2 = (TextView) view.findViewById(R.id.pop2_club2);
        TextView Pop2Time = (TextView) view.findViewById(R.id.pop2_time);
        TextView Pop2Stadium = (TextView) view.findViewById(R.id.pop2_stadium);
        ImageView Pop2Image = (ImageView) view.findViewById(R.id.popularmatch2);
        ImageView Pop2ImageClub1 = (ImageView) view.findViewById(R.id.pop2_image1);
        ImageView Pop2ImageClub2 = (ImageView) view.findViewById(R.id.pop2_image2);
        final DatabaseReference ref2;
        ref2 = FirebaseDatabase.getInstance().getReference("Match").child("ЦСКА");
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String date = snapshot.child("date").getValue().toString();
                Pop2Date.setText(date);
                String month = snapshot.child("month").getValue().toString();
                Pop2Month.setText(month);
                String club1 = snapshot.child("club1").getValue().toString();
                Pop2Club1.setText(club1);
                String club2 = snapshot.child("club2").getValue().toString();
                Pop2Club2.setText(club2);
                String time = snapshot.child("time").getValue().toString();
                Pop2Time.setText(time);
                String stadium = snapshot.child("stadium").getValue().toString();
                Pop2Stadium.setText(stadium);
                String link = snapshot.child("image1").getValue().toString();
                Picasso.get().load(link).into(Pop2ImageClub1);
                String link2 = snapshot.child("image2").getValue().toString();
                Picasso.get().load(link2).into(Pop2ImageClub2);
                Pop2Image.setImageResource(R.drawable.fonosnov);
                Pop2Image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Navigation.findNavController(view).navigate(R.id.action_mainPageFragment_to_match20decFragment);
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return view;
    }

}