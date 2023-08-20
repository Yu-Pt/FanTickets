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

import org.w3c.dom.Text;


public class CalendarFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        TextView dec15 = (TextView) view.findViewById(R.id.calendar_to15dec);
        TextView dec18 = (TextView) view.findViewById(R.id.calendar_to18dec);
        TextView dec20 = (TextView) view.findViewById(R.id.calendar_to20dec);
        TextView dec24 = (TextView) view.findViewById(R.id.calendar_to24dec);
        TextView dec28 = (TextView) view.findViewById(R.id.calendar_to28dec);
        FrameLayout frame = view.findViewById(R.id.calendar_frame);
        TextView textChoose = view.findViewById(R.id.calendar_text_choose);
        ImageButton toJan = view.findViewById(R.id.toJan);
        TextView dateCalendar = (TextView) view.findViewById(R.id.calendar_date);
        TextView monthCalendar = (TextView) view.findViewById(R.id.calendar_month);
        TextView club1Calendar = (TextView) view.findViewById(R.id.calendar_club1);
        TextView club2Calendar = (TextView) view.findViewById(R.id.calendar_club2);
        TextView timeCalendar = (TextView) view.findViewById(R.id.calendar_time);
        TextView stadiumCalendar = (TextView) view.findViewById(R.id.calendar_stadium);
        ImageView imageClub1Calendar = (ImageView) view.findViewById(R.id.calendar_image1);
        ImageView imageClub2Calendar = (ImageView) view.findViewById(R.id.calendar_image2);
        Button button1 = (Button) view.findViewById(R.id.calendar_buy);

        toJan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_calendarFragment_to_calendarFragmentJanuary);
            }
        });

        dec15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame.setVisibility(frame.VISIBLE);
                textChoose.setVisibility(textChoose.INVISIBLE);
                final DatabaseReference ref;
                ref = FirebaseDatabase.getInstance().getReference("Match").child("Зенит");
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
                                Navigation.findNavController(view).navigate(R.id.action_calendarFragment_to_match15decFragment);
                            }
                        });
                    }@Override public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
        });

        dec18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame.setVisibility(frame.VISIBLE);
                textChoose.setVisibility(textChoose.INVISIBLE);
                final DatabaseReference ref;
                ref = FirebaseDatabase.getInstance().getReference("Match").child("Ростов");
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
                                Navigation.findNavController(view).navigate(R.id.action_calendarFragment_to_match18decFragment);
                            }
                        });
                    }@Override public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
        });

        dec20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame.setVisibility(frame.VISIBLE);
                textChoose.setVisibility(textChoose.INVISIBLE);
                final DatabaseReference ref;
                ref = FirebaseDatabase.getInstance().getReference("Match").child("Локомотив");
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
                                Navigation.findNavController(view).navigate(R.id.action_calendarFragment_to_match20decFragment);
                            }
                        });
                    }@Override public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
        });

        dec24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame.setVisibility(frame.VISIBLE);
                textChoose.setVisibility(textChoose.INVISIBLE);
                final DatabaseReference ref;
                ref = FirebaseDatabase.getInstance().getReference("Match").child("Ахмат");
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
                                Navigation.findNavController(view).navigate(R.id.action_calendarFragment_to_match24decFragment);
                            }
                        });
                    }@Override public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
        });

        dec28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame.setVisibility(frame.VISIBLE);
                textChoose.setVisibility(textChoose.INVISIBLE);
                final DatabaseReference ref;
                ref = FirebaseDatabase.getInstance().getReference("Match").child("Рубин");
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
                                Navigation.findNavController(view).navigate(R.id.action_calendarFragment_to_match28decFragment);
                            }
                        });
                    }@Override public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
        });

        return view;
    }

}