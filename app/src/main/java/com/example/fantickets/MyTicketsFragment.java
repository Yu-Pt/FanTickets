package com.example.fantickets;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class MyTicketsFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_my_tickets, container, false);
        TextView number1 = view.findViewById(R.id.number1ticket);
        TextView number2 = view.findViewById(R.id.number2ticket);
        TextView number3 = view.findViewById(R.id.number3ticket);
        TextView date1 = view.findViewById(R.id.date1ticket);
        TextView date2 = view.findViewById(R.id.date2ticket);
        TextView date3 = view.findViewById(R.id.date3ticket);
        TextView time1 = view.findViewById(R.id.time1ticket);
        TextView time2 = view.findViewById(R.id.time2ticket);
        TextView time3 = view.findViewById(R.id.time3ticket);
        TextView club11 = view.findViewById(R.id.club11ticket);
        TextView club21 = view.findViewById(R.id.club21ticket);
        TextView club12 = view.findViewById(R.id.club12ticket);
        TextView club22 = view.findViewById(R.id.club22ticket);
        TextView club13 = view.findViewById(R.id.club13ticket);
        TextView club23 = view.findViewById(R.id.club23ticket);
        TextView stadium1 = view.findViewById(R.id.stadium1ticket);
        TextView stadium2 = view.findViewById(R.id.stadium2ticket);
        TextView stadium3 = view.findViewById(R.id.stadium3ticket);
        TextView text1 = view.findViewById(R.id.text1ticket);
        TextView text2 = view.findViewById(R.id.text2ticket);
        TextView text3 = view.findViewById(R.id.text3ticket);
        ImageView ticket1 = view.findViewById(R.id.ticket1);
        ImageView ticket2 = view.findViewById(R.id.ticket2);
        ImageView ticket3 = view.findViewById(R.id.ticket3);
        TextView resh1 = view.findViewById(R.id.resh1);
        TextView resh2 = view.findViewById(R.id.resh2);
        TextView resh3 = view.findViewById(R.id.resh3);
        TextView tire1 = view.findViewById(R.id.tire);
        TextView tire2 = view.findViewById(R.id.tire2);
        TextView tire3 = view.findViewById(R.id.tire3);


        FirebaseUser cUser = mAuth.getCurrentUser();
        String idUser = cUser.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Ticket").child(idUser).child("1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String tic1 = snapshot.child("number").getValue().toString();
                    number1.setText(tic1);
                    String tic2 = snapshot.child("date").getValue().toString();
                    date1.setText(tic2);
                    String tic3 = snapshot.child("time").getValue().toString();
                    time1.setText(tic3);
                    String tic4 = snapshot.child("club1").getValue().toString();
                    club11.setText(tic4);
                    String tic5 = snapshot.child("club2").getValue().toString();
                    club21.setText(tic5);
                    String tic6 = snapshot.child("stadium").getValue().toString();
                    stadium1.setText(tic6);
                    resh1.setVisibility(View.VISIBLE);
                    tire1.setVisibility(View.VISIBLE);
                    text1.setText("Нажмите, чтобы открыть билет");
                    ticket1.setImageResource(R.drawable.fonticket);
                    ticket1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Navigation.findNavController(view).navigate(R.id.action_myTicketsFragment_to_ticket1Fragment);
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
        ref.child("Ticket").child(idUser).child("2").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String tic1 = snapshot.child("number").getValue().toString();
                    number2.setText(tic1);
                    String tic2 = snapshot.child("date").getValue().toString();
                    date2.setText(tic2);
                    String tic3 = snapshot.child("time").getValue().toString();
                    time2.setText(tic3);
                    String tic4 = snapshot.child("club1").getValue().toString();
                    club12.setText(tic4);
                    String tic5 = snapshot.child("club2").getValue().toString();
                    club22.setText(tic5);
                    String tic6 = snapshot.child("stadium").getValue().toString();
                    stadium2.setText(tic6);
                    resh2.setVisibility(View.VISIBLE);
                    tire2.setVisibility(View.VISIBLE);
                    text2.setText("Нажмите, чтобы открыть билет");
                    ticket2.setImageResource(R.drawable.fonticket);
                    ticket2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Navigation.findNavController(view).navigate(R.id.action_myTicketsFragment_to_ticket2Fragment);
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
        ref.child("Ticket").child(idUser).child("3").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String tic1 = snapshot.child("number").getValue().toString();
                    number3.setText(tic1);
                    String tic2 = snapshot.child("date").getValue().toString();
                    date3.setText(tic2);
                    String tic3 = snapshot.child("time").getValue().toString();
                    time3.setText(tic3);
                    String tic4 = snapshot.child("club1").getValue().toString();
                    club13.setText(tic4);
                    String tic5 = snapshot.child("club2").getValue().toString();
                    club23.setText(tic5);
                    String tic6 = snapshot.child("stadium").getValue().toString();
                    stadium3.setText(tic6);
                    resh3.setVisibility(View.VISIBLE);
                    tire3.setVisibility(View.VISIBLE);
                    text3.setText("Нажмите, чтобы открыть билет");
                    ticket3.setImageResource(R.drawable.fonticket);
                    ticket3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Navigation.findNavController(view).navigate(R.id.action_myTicketsFragment_to_ticket3Fragment);
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        return view;
    }

}