package com.example.fantickets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;

public class AddMatches extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText DateBDmatch, MonthBDmatch, Club1BDmatch, Club2BDmatch, TimeBDmatch, StadiumBDmatch, yyyBD;
    private DatabaseReference mDataBase;
    private TextView textViewKey;
    private String MATCH_KEY = "Match";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_matches);
        init();

    }

    public void init() {
        DateBDmatch = findViewById(R.id.dateaddmatch);
        MonthBDmatch = findViewById(R.id.monthaddmatch);
        Club1BDmatch = findViewById(R.id.club1addmatch);
        Club2BDmatch = findViewById(R.id.club2addmatch);
        TimeBDmatch = findViewById(R.id.timeaddmatch);
        StadiumBDmatch = findViewById(R.id.stadiumaddmatch);
        yyyBD = findViewById(R.id.yyyaddmatch);
        mDataBase = FirebaseDatabase.getInstance().getReference(MATCH_KEY);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();

        if (cUser==null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void onClickSaveBDmatch(View view) {

        String id = mDataBase.getKey();
        String date = DateBDmatch.getText().toString();
        String month = MonthBDmatch.getText().toString();
        String club1 = Club1BDmatch.getText().toString();
        String club2 = Club2BDmatch.getText().toString();
        String time = TimeBDmatch.getText().toString();
        String stadium = StadiumBDmatch.getText().toString();
        String yyy = yyyBD.getText().toString();
        String score1 = "?";
        String score2 = "?";
        String image1 = "https://firebasestorage.googleapis.com/v0/b/fantickets-bfd4c.appspot.com/o/UsersImages%2Favatar.png?alt=media&token=f5e98add-8813-495b-ab1f-88ed9d577f50";
        String image2 = "https://firebasestorage.googleapis.com/v0/b/fantickets-bfd4c.appspot.com/o/UsersImages%2Favatar.png?alt=media&token=f5e98add-8813-495b-ab1f-88ed9d577f50";
        if (!TextUtils.isEmpty(DateBDmatch.getText().toString()) && !TextUtils.isEmpty(MonthBDmatch.getText().toString()) && !TextUtils.isEmpty(Club2BDmatch.getText().toString()) && !TextUtils.isEmpty(Club1BDmatch.getText().toString())
                && !TextUtils.isEmpty(TimeBDmatch.getText().toString()) && !TextUtils.isEmpty(StadiumBDmatch.getText().toString())) {
            Match newMatch = new Match(id, date, month, club1, club2, time, stadium, score1, score2, image1, image2);
            mDataBase =  FirebaseDatabase.getInstance().getReference();
            mDataBase.child("Match").child(yyy).setValue(newMatch);
            Toast.makeText(this, "Успешно сохранено", Toast.LENGTH_SHORT).show();
        }
    }


}