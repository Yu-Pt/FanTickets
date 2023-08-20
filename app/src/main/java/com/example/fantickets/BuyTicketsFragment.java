package com.example.fantickets;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class BuyTicketsFragment extends Fragment {

    private FirebaseAuth mAuth;
    EditText searcher;
    TextView find1, find2, find3, find4, find5, find6, findvs;
    TextView find21, find22, find23, find24, find25, find26, find2vs;
    ImageView image1, image2, find1image1, find1image2, find2image1, find2image2;
    Button buttonSearch;

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
        View view = inflater.inflate(R.layout.fragment_buy_tickets, container, false);
        searcher = view.findViewById(R.id.findClub);
        find1 = view.findViewById(R.id.find1_date);
        find2 = view.findViewById(R.id.find1_month);
        find3 = view.findViewById(R.id.find1_club1);
        find4 = view.findViewById(R.id.find1_club2);
        find5 = view.findViewById(R.id.find1_time);
        find6 = view.findViewById(R.id.find1_stadium);
        findvs = view.findViewById(R.id.vs1);
        find21 = view.findViewById(R.id.find2_date);
        find22 = view.findViewById(R.id.find2_month);
        find23 = view.findViewById(R.id.find2_club1);
        find24 = view.findViewById(R.id.find2_club2);
        find25 = view.findViewById(R.id.find2_time);
        find26 = view.findViewById(R.id.find2_stadium);
        find2vs = view.findViewById(R.id.vs2);
        image1 = view.findViewById(R.id.find1match);
        image2 = view.findViewById(R.id.find2match);
        find1image1 = view.findViewById(R.id.find1_image1);
        find1image2 = view.findViewById(R.id.find1_image2);
        find2image1 = view.findViewById(R.id.find2_image1);
        find2image2 = view.findViewById(R.id.find2_image2);
        buttonSearch = view.findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = searcher.getText().toString();
                final DatabaseReference ref;
                ref = FirebaseDatabase.getInstance().getReference("Match");
                if(search.equals("ЦСКА") || search.equals("цска") || search.equals("ЦСКА ")){
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String finddate = snapshot.child("ЦСКА").child("date").getValue().toString();
                            find1.setText(finddate);
                            String month = snapshot.child("ЦСКА").child("month").getValue().toString();
                            find2.setText(month);
                            String club1 = snapshot.child("ЦСКА").child("club1").getValue().toString();
                            find3.setText(club1);
                            String club2 = snapshot.child("ЦСКА").child("club2").getValue().toString();
                            find4.setText(club2);
                            String time = snapshot.child("ЦСКА").child("time").getValue().toString();
                            find5.setText(time);
                            String stadium = snapshot.child("ЦСКА").child("stadium").getValue().toString();
                            find6.setText(stadium);
                            findvs.setText("VS");
                            String link = snapshot.child("ЦСКА").child("image1").getValue().toString();
                            Picasso.get().load(link).into(find1image1);
                            String link2 = snapshot.child("ЦСКА").child("image2").getValue().toString();
                            Picasso.get().load(link2).into(find1image2);
                            image1.setImageResource(R.drawable.fonosnov);
                            image1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Navigation.findNavController(view).navigate(R.id.action_buyTicketsFragment_to_match20decFragment);
                                }
                            });

                            String finddate2 = snapshot.child("ЦСКА2").child("date").getValue().toString();
                            find21.setText(finddate2);
                            String month2 = snapshot.child("ЦСКА2").child("month").getValue().toString();
                            find22.setText(month2);
                            String club12 = snapshot.child("ЦСКА2").child("club1").getValue().toString();
                            find23.setText(club12);
                            String club22 = snapshot.child("ЦСКА2").child("club2").getValue().toString();
                            find24.setText(club22);
                            String time2 = snapshot.child("ЦСКА2").child("time").getValue().toString();
                            find25.setText(time2);
                            String stadium2 = snapshot.child("ЦСКА2").child("stadium").getValue().toString();
                            find26.setText(stadium2);
                            find2vs.setText("VS");
                            String link3 = snapshot.child("ЦСКА2").child("image1").getValue().toString();
                            Picasso.get().load(link3).into(find2image1);
                            String link4 = snapshot.child("ЦСКА2").child("image2").getValue().toString();
                            Picasso.get().load(link4).into(find2image2);
                            image2.setImageResource(R.drawable.fonosnov);
                            image2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Navigation.findNavController(view).navigate(R.id.action_buyTicketsFragment_to_match28janFragment);
                                }
                            });
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}});}

                if(search.equals("Спартак") || search.equals("спартак")){
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String finddate = snapshot.child("Спартак").child("date").getValue().toString();
                            find1.setText(finddate);
                            String month = snapshot.child("Спартак").child("month").getValue().toString();
                            find2.setText(month);
                            String club1 = snapshot.child("Спартак").child("club1").getValue().toString();
                            find3.setText(club1);
                            String club2 = snapshot.child("Спартак").child("club2").getValue().toString();
                            find4.setText(club2);
                            String time = snapshot.child("Спартак").child("time").getValue().toString();
                            find5.setText(time);
                            String stadium = snapshot.child("Спартак").child("stadium").getValue().toString();
                            find6.setText(stadium);
                            findvs.setText("VS");
                            String link = snapshot.child("Спартак").child("image1").getValue().toString();
                            Picasso.get().load(link).into(find1image1);
                            String link2 = snapshot.child("Спартак").child("image2").getValue().toString();
                            Picasso.get().load(link2).into(find1image2);
                            image1.setImageResource(R.drawable.fonosnov);
                            image1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Navigation.findNavController(view).navigate(R.id.action_buyTicketsFragment_to_match15decFragment);
                                }
                            });

                            String finddate2 = snapshot.child("Спартак2").child("date").getValue().toString();
                            find21.setText(finddate2);
                            String month2 = snapshot.child("Спартак2").child("month").getValue().toString();
                            find22.setText(month2);
                            String club12 = snapshot.child("Спартак2").child("club1").getValue().toString();
                            find23.setText(club12);
                            String club22 = snapshot.child("Спартак2").child("club2").getValue().toString();
                            find24.setText(club22);
                            String time2 = snapshot.child("Спартак2").child("time").getValue().toString();
                            find25.setText(time2);
                            String stadium2 = snapshot.child("Спартак2").child("stadium").getValue().toString();
                            find26.setText(stadium2);
                            find2vs.setText("VS");
                            String link3 = snapshot.child("Спартак2").child("image1").getValue().toString();
                            Picasso.get().load(link3).into(find2image1);
                            String link4 = snapshot.child("Спартак2").child("image2").getValue().toString();
                            Picasso.get().load(link4).into(find2image2);
                            image2.setImageResource(R.drawable.fonosnov);
                            image2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Navigation.findNavController(view).navigate(R.id.action_buyTicketsFragment_to_match28janFragment);
                                }
                            });
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}});}

                if(search.equals("Ахмат") || search.equals("Торпедо") || search.equals("торпедо") || search.equals("ахмат")){
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String finddate = snapshot.child("Ахмат").child("date").getValue().toString();
                            find1.setText(finddate);
                            String month = snapshot.child("Ахмат").child("month").getValue().toString();
                            find2.setText(month);
                            String club1 = snapshot.child("Ахмат").child("club1").getValue().toString();
                            find3.setText(club1);
                            String club2 = snapshot.child("Ахмат").child("club2").getValue().toString();
                            find4.setText(club2);
                            String time = snapshot.child("Ахмат").child("time").getValue().toString();
                            find5.setText(time);
                            String stadium = snapshot.child("Ахмат").child("stadium").getValue().toString();
                            find6.setText(stadium);
                            findvs.setText("VS");
                            String link = snapshot.child("Ахмат").child("image1").getValue().toString();
                            Picasso.get().load(link).into(find1image1);
                            String link2 = snapshot.child("Ахмат").child("image2").getValue().toString();
                            Picasso.get().load(link2).into(find1image2);
                            image1.setImageResource(R.drawable.fonosnov);
                            image1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Navigation.findNavController(view).navigate(R.id.action_buyTicketsFragment_to_match24decFragment);
                                }
                            });
                            find21.setText(" ");
                            find22.setText(" ");
                            find23.setText(" ");
                            find24.setText(" ");
                            find25.setText(" ");
                            find26.setText(" ");
                            find2vs.setText(" ");
                            find2image1.setImageResource(R.drawable.invisfon);
                            find2image2.setImageResource(R.drawable.invisfon);
                            image2.setImageResource(R.drawable.invisfon);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}});}

                if(search.equals("Ростов") || search.equals("Динамо") || search.equals("динамо") || search.equals("ростов")){
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String finddate = snapshot.child("Ростов").child("date").getValue().toString();
                            find1.setText(finddate);
                            String month = snapshot.child("Ростов").child("month").getValue().toString();
                            find2.setText(month);
                            String club1 = snapshot.child("Ростов").child("club1").getValue().toString();
                            find3.setText(club1);
                            String club2 = snapshot.child("Ростов").child("club2").getValue().toString();
                            find4.setText(club2);
                            String time = snapshot.child("Ростов").child("time").getValue().toString();
                            find5.setText(time);
                            String stadium = snapshot.child("Ростов").child("stadium").getValue().toString();
                            find6.setText(stadium);
                            findvs.setText("VS");
                            String link = snapshot.child("Ростов").child("image1").getValue().toString();
                            Picasso.get().load(link).into(find1image1);
                            String link2 = snapshot.child("Ростов").child("image2").getValue().toString();
                            Picasso.get().load(link2).into(find1image2);
                            image1.setImageResource(R.drawable.fonosnov);
                            image1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Navigation.findNavController(view).navigate(R.id.action_buyTicketsFragment_to_match18decFragment);
                                }
                            });
                            find21.setText(" ");
                            find22.setText(" ");
                            find23.setText(" ");
                            find24.setText(" ");
                            find25.setText(" ");
                            find26.setText(" ");
                            find2vs.setText(" ");
                            find2image1.setImageResource(R.drawable.invisfon);
                            find2image2.setImageResource(R.drawable.invisfon);
                            image2.setImageResource(R.drawable.invisfon);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}});}

                if(search.equals("Рубин") || search.equals("Оренбург") || search.equals("оренбург") || search.equals("рубин")){
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String finddate = snapshot.child("Рубин").child("date").getValue().toString();
                            find1.setText(finddate);
                            String month = snapshot.child("Рубин").child("month").getValue().toString();
                            find2.setText(month);
                            String club1 = snapshot.child("Рубин").child("club1").getValue().toString();
                            find3.setText(club1);
                            String club2 = snapshot.child("Рубин").child("club2").getValue().toString();
                            find4.setText(club2);
                            String time = snapshot.child("Рубин").child("time").getValue().toString();
                            find5.setText(time);
                            String stadium = snapshot.child("Рубин").child("stadium").getValue().toString();
                            find6.setText(stadium);
                            findvs.setText("VS");
                            String link = snapshot.child("Рубин").child("image1").getValue().toString();
                            Picasso.get().load(link).into(find1image1);
                            String link2 = snapshot.child("Рубин").child("image2").getValue().toString();
                            Picasso.get().load(link2).into(find1image2);
                            image1.setImageResource(R.drawable.fonosnov);
                            image1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Navigation.findNavController(view).navigate(R.id.action_buyTicketsFragment_to_match28decFragment);
                                }
                            });
                            find21.setText(" ");
                            find22.setText(" ");
                            find23.setText(" ");
                            find24.setText(" ");
                            find25.setText(" ");
                            find26.setText(" ");
                            find2vs.setText(" ");
                            find2image1.setImageResource(R.drawable.invisfon);
                            find2image2.setImageResource(R.drawable.invisfon);
                            image2.setImageResource(R.drawable.invisfon);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}});}

                if(search.equals("Сочи") || search.equals("Урал") || search.equals("урал") || search.equals("сочи")){
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String finddate = snapshot.child("Сочи").child("date").getValue().toString();
                            find1.setText(finddate);
                            String month = snapshot.child("Сочи").child("month").getValue().toString();
                            find2.setText(month);
                            String club1 = snapshot.child("Сочи").child("club1").getValue().toString();
                            find3.setText(club1);
                            String club2 = snapshot.child("Сочи").child("club2").getValue().toString();
                            find4.setText(club2);
                            String time = snapshot.child("Сочи").child("time").getValue().toString();
                            find5.setText(time);
                            String stadium = snapshot.child("Сочи").child("stadium").getValue().toString();
                            find6.setText(stadium);
                            findvs.setText("VS");
                            String link = snapshot.child("Сочи").child("image1").getValue().toString();
                            Picasso.get().load(link).into(find1image1);
                            String link2 = snapshot.child("Сочи").child("image2").getValue().toString();
                            Picasso.get().load(link2).into(find1image2);
                            image1.setImageResource(R.drawable.fonosnov);
                            image1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Navigation.findNavController(view).navigate(R.id.action_buyTicketsFragment_to_match9janFragment);
                                }
                            });
                            find21.setText(" ");
                            find22.setText(" ");
                            find23.setText(" ");
                            find24.setText(" ");
                            find25.setText(" ");
                            find26.setText(" ");
                            find2vs.setText(" ");
                            find2image1.setImageResource(R.drawable.invisfon);
                            find2image2.setImageResource(R.drawable.invisfon);
                            image2.setImageResource(R.drawable.invisfon);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}});}

                if(search.equals("Ковровец") || search.equals("Крылья Советов") || search.equals("Крылья") || search.equals("Крылья советов") || search.equals("крылья советов") || search.equals("крылья") || search.equals("ковровец")){
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String finddate = snapshot.child("Ковровец").child("date").getValue().toString();
                            find1.setText(finddate);
                            String month = snapshot.child("Ковровец").child("month").getValue().toString();
                            find2.setText(month);
                            String club1 = snapshot.child("Ковровец").child("club1").getValue().toString();
                            find3.setText(club1);
                            String club2 = snapshot.child("Ковровец").child("club2").getValue().toString();
                            find4.setText(club2);
                            String time = snapshot.child("Ковровец").child("time").getValue().toString();
                            find5.setText(time);
                            String stadium = snapshot.child("Ковровец").child("stadium").getValue().toString();
                            find6.setText(stadium);
                            findvs.setText("VS");
                            String link = snapshot.child("Ковровец").child("image1").getValue().toString();
                            Picasso.get().load(link).into(find1image1);
                            String link2 = snapshot.child("Ковровец").child("image2").getValue().toString();
                            Picasso.get().load(link2).into(find1image2);
                            image1.setImageResource(R.drawable.fonosnov);
                            image1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Navigation.findNavController(view).navigate(R.id.action_buyTicketsFragment_to_match14janFragment);
                                }
                            });
                            find21.setText(" ");
                            find22.setText(" ");
                            find23.setText(" ");
                            find24.setText(" ");
                            find25.setText(" ");
                            find26.setText(" ");
                            find2vs.setText(" ");
                            find2image1.setImageResource(R.drawable.invisfon);
                            find2image2.setImageResource(R.drawable.invisfon);
                            image2.setImageResource(R.drawable.invisfon);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}});}

                if(search.equals("Факел") || search.equals("Краснодар") || search.equals("факел") || search.equals("краснодар")){
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String finddate = snapshot.child("Факел").child("date").getValue().toString();
                            find1.setText(finddate);
                            String month = snapshot.child("Факел").child("month").getValue().toString();
                            find2.setText(month);
                            String club1 = snapshot.child("Факел").child("club1").getValue().toString();
                            find3.setText(club1);
                            String club2 = snapshot.child("Факел").child("club2").getValue().toString();
                            find4.setText(club2);
                            String time = snapshot.child("Факел").child("time").getValue().toString();
                            find5.setText(time);
                            String stadium = snapshot.child("Факел").child("stadium").getValue().toString();
                            find6.setText(stadium);
                            findvs.setText("VS");
                            String link = snapshot.child("Факел").child("image1").getValue().toString();
                            Picasso.get().load(link).into(find1image1);
                            String link2 = snapshot.child("Факел").child("image2").getValue().toString();
                            Picasso.get().load(link2).into(find1image2);
                            image1.setImageResource(R.drawable.fonosnov);
                            image1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Navigation.findNavController(view).navigate(R.id.action_buyTicketsFragment_to_match19janFragment);
                                }
                            });
                            find21.setText(" ");
                            find22.setText(" ");
                            find23.setText(" ");
                            find24.setText(" ");
                            find25.setText(" ");
                            find26.setText(" ");
                            find2vs.setText(" ");
                            find2image1.setImageResource(R.drawable.invisfon);
                            find2image2.setImageResource(R.drawable.invisfon);
                            image2.setImageResource(R.drawable.invisfon);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}});}

                if(search.equals("Зенит") || search.equals("зенит")){
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String finddate = snapshot.child("Зенит").child("date").getValue().toString();
                            find1.setText(finddate);
                            String month = snapshot.child("Зенит").child("month").getValue().toString();
                            find2.setText(month);
                            String club1 = snapshot.child("Зенит").child("club1").getValue().toString();
                            find3.setText(club1);
                            String club2 = snapshot.child("Зенит").child("club2").getValue().toString();
                            find4.setText(club2);
                            String time = snapshot.child("Зенит").child("time").getValue().toString();
                            find5.setText(time);
                            String stadium = snapshot.child("Зенит").child("stadium").getValue().toString();
                            find6.setText(stadium);
                            findvs.setText("VS");
                            String link = snapshot.child("Зенит").child("image1").getValue().toString();
                            Picasso.get().load(link).into(find1image1);
                            String link2 = snapshot.child("Зенит").child("image2").getValue().toString();
                            Picasso.get().load(link2).into(find1image2);
                            image1.setImageResource(R.drawable.fonosnov);
                            image1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Navigation.findNavController(view).navigate(R.id.action_buyTicketsFragment_to_match15decFragment);
                                }
                            });
                            find21.setText(" ");
                            find22.setText(" ");
                            find23.setText(" ");
                            find24.setText(" ");
                            find25.setText(" ");
                            find26.setText(" ");
                            find2vs.setText(" ");
                            find2image1.setImageResource(R.drawable.invisfon);
                            find2image2.setImageResource(R.drawable.invisfon);
                            image2.setImageResource(R.drawable.invisfon);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}});}

                if(search.equals("Локомотив") || search.equals("локомотив")){
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String finddate = snapshot.child("Локомотив").child("date").getValue().toString();
                            find1.setText(finddate);
                            String month = snapshot.child("Локомотив").child("month").getValue().toString();
                            find2.setText(month);
                            String club1 = snapshot.child("Локомотив").child("club1").getValue().toString();
                            find3.setText(club1);
                            String club2 = snapshot.child("Локомотив").child("club2").getValue().toString();
                            find4.setText(club2);
                            String time = snapshot.child("Локомотив").child("time").getValue().toString();
                            find5.setText(time);
                            String stadium = snapshot.child("Локомотив").child("stadium").getValue().toString();
                            find6.setText(stadium);
                            findvs.setText("VS");
                            String link = snapshot.child("Локомотив").child("image1").getValue().toString();
                            Picasso.get().load(link).into(find1image1);
                            String link2 = snapshot.child("Локомотив").child("image2").getValue().toString();
                            Picasso.get().load(link2).into(find1image2);
                            image1.setImageResource(R.drawable.fonosnov);
                            image1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Navigation.findNavController(view).navigate(R.id.action_buyTicketsFragment_to_match20decFragment);
                                }
                            });
                            find21.setText(" ");
                            find22.setText(" ");
                            find23.setText(" ");
                            find24.setText(" ");
                            find25.setText(" ");
                            find26.setText(" ");
                            find2vs.setText(" ");
                            find2image1.setImageResource(R.drawable.invisfon);
                            find2image2.setImageResource(R.drawable.invisfon);
                            image2.setImageResource(R.drawable.invisfon);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}});}
            }
        });
        return view;
    }

}