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
import android.widget.FrameLayout;
import android.widget.ImageButton;
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

public class CalendarFragmentJanuary extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_calendar_january, container, false);

        TextView jan9 = (TextView) view.findViewById(R.id.calendar_to9jan);
        TextView jan14 = (TextView) view.findViewById(R.id.calendar_to14jan);
        TextView jan19 = (TextView) view.findViewById(R.id.calendar_to19jan);
        TextView jan28 = (TextView) view.findViewById(R.id.calendar_to28jan);
        FrameLayout frame = view.findViewById(R.id.calendar_frame_jan);
        TextView textChoose = view.findViewById(R.id.calendar_text_jan);
        ImageButton toDec = view.findViewById(R.id.toDec);
        TextView dateCalendar = (TextView) view.findViewById(R.id.calendar_date_jan);
        TextView monthCalendar = (TextView) view.findViewById(R.id.calendar_month_jan);
        TextView club1Calendar = (TextView) view.findViewById(R.id.calendar_club1_jan);
        TextView club2Calendar = (TextView) view.findViewById(R.id.calendar_club2_jan);
        TextView timeCalendar = (TextView) view.findViewById(R.id.calendar_time_jan);
        TextView stadiumCalendar = (TextView) view.findViewById(R.id.calendar_stadium_jan);
        ImageView imageClub1Calendar = (ImageView) view.findViewById(R.id.calendar_image1_jan);
        ImageView imageClub2Calendar = (ImageView) view.findViewById(R.id.calendar_image2_jan);
        Button button1 = (Button) view.findViewById(R.id.calendar_buy_jan);

        toDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_calendarFragmentJanuary_to_calendarFragment);
            }
        });

        jan9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame.setVisibility(frame.VISIBLE);
                textChoose.setVisibility(textChoose.INVISIBLE);
                final DatabaseReference ref;
                ref = FirebaseDatabase.getInstance().getReference("Match").child("Сочи");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String date = snapshot.child("date").getValue().toString();
                        dateCalendar.setText(date);
                        String month = snapshot.child("month").getValue().toString();
                        monthCalendar.setText(month);
                        String club1 = snapshot.child("club1").getValue().toString();
                        club1Calendar.setText(club1);
                        String club2 = snapshot.child("club2").getValue().toString();
                        club2Calendar.setText(club2);
                        String time = snapshot.child("time").getValue().toString();
                        timeCalendar.setText(time);
                        String stadium = snapshot.child("stadium").getValue().toString();
                        stadiumCalendar.setText(stadium);
                        String link = snapshot.child("image1").getValue().toString();
                        Picasso.get().load(link).into(imageClub1Calendar);
                        String link2 = snapshot.child("image2").getValue().toString();
                        Picasso.get().load(link2).into(imageClub2Calendar);
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Navigation.findNavController(view).navigate(R.id.action_calendarFragmentJanuary_to_match9janFragment);
                            }
                        });
                    }@Override public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
        });

        jan14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame.setVisibility(frame.VISIBLE);
                textChoose.setVisibility(textChoose.INVISIBLE);
                final DatabaseReference ref;
                ref = FirebaseDatabase.getInstance().getReference("Match").child("Ковровец");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String date = snapshot.child("date").getValue().toString();
                        dateCalendar.setText(date);
                        String month = snapshot.child("month").getValue().toString();
                        monthCalendar.setText(month);
                        String club1 = snapshot.child("club1").getValue().toString();
                        club1Calendar.setText(club1);
                        String club2 = snapshot.child("club2").getValue().toString();
                        club2Calendar.setText(club2);
                        String time = snapshot.child("time").getValue().toString();
                        timeCalendar.setText(time);
                        String stadium = snapshot.child("stadium").getValue().toString();
                        stadiumCalendar.setText(stadium);
                        String link = snapshot.child("image1").getValue().toString();
                        Picasso.get().load(link).into(imageClub1Calendar);
                        String link2 = snapshot.child("image2").getValue().toString();
                        Picasso.get().load(link2).into(imageClub2Calendar);
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Navigation.findNavController(view).navigate(R.id.action_calendarFragmentJanuary_to_match14janFragment);
                            }
                        });
                    }@Override public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
        });

        jan19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame.setVisibility(frame.VISIBLE);
                textChoose.setVisibility(textChoose.INVISIBLE);
                final DatabaseReference ref;
                ref = FirebaseDatabase.getInstance().getReference("Match").child("Факел");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String date = snapshot.child("date").getValue().toString();
                        dateCalendar.setText(date);
                        String month = snapshot.child("month").getValue().toString();
                        monthCalendar.setText(month);
                        String club1 = snapshot.child("club1").getValue().toString();
                        club1Calendar.setText(club1);
                        String club2 = snapshot.child("club2").getValue().toString();
                        club2Calendar.setText(club2);
                        String time = snapshot.child("time").getValue().toString();
                        timeCalendar.setText(time);
                        String stadium = snapshot.child("stadium").getValue().toString();
                        stadiumCalendar.setText(stadium);
                        String link = snapshot.child("image1").getValue().toString();
                        Picasso.get().load(link).into(imageClub1Calendar);
                        String link2 = snapshot.child("image2").getValue().toString();
                        Picasso.get().load(link2).into(imageClub2Calendar);
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Navigation.findNavController(view).navigate(R.id.action_calendarFragmentJanuary_to_match19janFragment);
                            }
                        });
                    }@Override public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
        });

        jan28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame.setVisibility(frame.VISIBLE);
                textChoose.setVisibility(textChoose.INVISIBLE);
                final DatabaseReference ref;
                ref = FirebaseDatabase.getInstance().getReference("Match").child("Спартак2");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String date = snapshot.child("date").getValue().toString();
                        dateCalendar.setText(date);
                        String month = snapshot.child("month").getValue().toString();
                        monthCalendar.setText(month);
                        String club1 = snapshot.child("club1").getValue().toString();
                        club1Calendar.setText(club1);
                        String club2 = snapshot.child("club2").getValue().toString();
                        club2Calendar.setText(club2);
                        String time = snapshot.child("time").getValue().toString();
                        timeCalendar.setText(time);
                        String stadium = snapshot.child("stadium").getValue().toString();
                        stadiumCalendar.setText(stadium);
                        String link = snapshot.child("image1").getValue().toString();
                        Picasso.get().load(link).into(imageClub1Calendar);
                        String link2 = snapshot.child("image2").getValue().toString();
                        Picasso.get().load(link2).into(imageClub2Calendar);
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Navigation.findNavController(view).navigate(R.id.action_calendarFragmentJanuary_to_match28janFragment);                         }
                        });
                    }@Override public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
        });

        return view;
    }

}