package com.example.fantickets;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;


public class Match9janFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;
    private String TICKET_KEY = "Ticket";
    public String sectorChoose, tribuneChoose, rowChoose, placeChoose;

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
        View view = inflater.inflate(R.layout.fragment_match9jan, container, false);

        Spinner spinner1 = (Spinner) view.findViewById(R.id.tribune_spinner_9jan);
        ArrayAdapter<?> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.T_9j, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
                String[] choose = getResources().getStringArray(R.array.T_9j);
                sectorChoose = choose[selectedItemPosition];
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinner2 = (Spinner) view.findViewById(R.id.sector_spinner_9jan);
        ArrayAdapter<?> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.S_9j, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
                String[] choose = getResources().getStringArray(R.array.S_9j);
                tribuneChoose = choose[selectedItemPosition];
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinner3 = (Spinner) view.findViewById(R.id.row_spinner_9jan);
        ArrayAdapter<?> adapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.Row_9j, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
                String[] choose = getResources().getStringArray(R.array.Row_9j);
                rowChoose = choose[selectedItemPosition];
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinner4 = (Spinner) view.findViewById(R.id.place_spinner_9jan);
        ArrayAdapter<?> adapter4 = ArrayAdapter.createFromResource(getActivity(), R.array.Place_9j, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
                String[] choose = getResources().getStringArray(R.array.Place_9j);
                placeChoose = choose[selectedItemPosition];
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        Button button = view.findViewById(R.id.buy9jan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataBase = FirebaseDatabase.getInstance().getReference(TICKET_KEY);
                String id = mDataBase.getKey();
                int rand = new Random().nextInt(1000000);
                String number = "1000" + rand;
                String date = "9 января";
                String time = "14:00";
                String club1 = "Сочи";
                String club2 = "Урал";
                String stadium = "Стадион Фишт";
                String sector = sectorChoose;
                String tribuna = tribuneChoose;
                String rad = rowChoose;
                String mesto = placeChoose;
                String cost = "3500 рублей";
                String qr = "https://firebasestorage.googleapis.com/v0/b/fantickets-bfd4c.appspot.com/o/UsersImages%2FYQR.png?alt=media&token=b8ff2acc-cf49-4eea-852a-6540ee10bdd1";
                FirebaseUser cUser = mAuth.getCurrentUser();
                String idUser = cUser.getUid();
                Ticket newTicket = new Ticket(id, number, date, time, club1, club2, stadium, sector, tribuna, rad, mesto, cost, qr);
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                ref.child("Ticket").child(idUser).child("1").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            ref.child("Ticket").child(idUser).child("2").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                    if(snapshot2.exists()) {
                                        ref.child("Ticket").child(idUser).child("3").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot3) {
                                                if (snapshot3.exists()) {
                                                    Toast.makeText(getActivity(), "Превышен лимит билетов", Toast.LENGTH_SHORT).show();
                                                }
                                                else ref.child("Ticket").child(idUser).child("3").setValue(newTicket);
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {}
                                        });
                                    }
                                    else ref.child("Ticket").child(idUser).child("2").setValue(newTicket);
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {}
                            });
                        }
                        else ref.child("Ticket").child(idUser).child("1").setValue(newTicket);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
                Toast.makeText(getActivity(), "Успешно куплено", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}