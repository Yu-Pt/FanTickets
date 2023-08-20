package com.example.fantickets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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

import java.text.DateFormat;
import java.util.HashMap;

public class Registration extends AppCompatActivity {
    private EditText NameBDreg, SurnameBDreg, EmailBDreg, PasswordBDreg, PasswordRepeat;
    public CheckBox checkBox;
    private DatabaseReference mDataBase;
    private String USER_KEY = "User";
    private String idtable;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        init();
    }
    public void init(){
        NameBDreg = findViewById(R.id.edit_login);
        SurnameBDreg = findViewById(R.id.edit_surname);
        EmailBDreg = findViewById(R.id.edit_email);
        PasswordBDreg = findViewById(R.id.edit_password);
        checkBox = findViewById(R.id.checkBox);
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onClickSaveBD(View view) {
        String id = mDataBase.getKey();
        String username = NameBDreg.getText().toString();
        String usersurname = SurnameBDreg.getText().toString();
        String email = EmailBDreg.getText().toString();
        String password = PasswordBDreg.getText().toString();
        String birth = "Пока не заполнено";
        String doc = "Пока не заполнено";
        String gender = "Пока не заполнено";
        String image = "https://firebasestorage.googleapis.com/v0/b/fantickets-bfd4c.appspot.com/o/UsersImages%2Favatar.png?alt=media&token=f5e98add-8813-495b-ab1f-88ed9d577f50";
        if(!TextUtils.isEmpty(EmailBDreg.getText().toString()) && !TextUtils.isEmpty(PasswordBDreg.getText().toString()) && checkBox.isChecked()) {
            mAuth.createUserWithEmailAndPassword(EmailBDreg.getText().toString(), PasswordBDreg.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        sendEmailVer();
                        FirebaseUser cUser = mAuth.getCurrentUser();
                        idtable = cUser.getUid();
                        saveBD(id, idtable, username, usersurname, email, password, birth, doc, gender, image);
                    } else
                        Toast.makeText(getApplicationContext(), "Регистрация не удалась, проверьте данные и попробуйте еще раз", Toast.LENGTH_SHORT).show();
                }
            });

        }
        else Toast.makeText(this, "Заполните пустые поля", Toast.LENGTH_SHORT).show();
    }

    private void saveBD(String id, String idtable, String username, String usersurname, String email, String password, String birth, String doc, String gender, String image){
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("User").child(idtable).exists())){
                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("id", id);
                    userDataMap.put("idtable", idtable);
                    userDataMap.put("Name", username);
                    userDataMap.put("Surname", usersurname);
                    userDataMap.put("Email", email);
                    userDataMap.put("Password", password);
                    userDataMap.put("Birth", birth);
                    userDataMap.put("Document", doc);
                    userDataMap.put("Gender", gender);
                    userDataMap.put("image", image);

                    RootRef.child("User").child(idtable).updateChildren(userDataMap);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Registration.this, "E-mail" + email +"зарегистрирован", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendEmailVer(){
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Проверьте ваш почтовый ящик, подтвердите email и выполните вход", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getApplicationContext(), "Отправка сообщения провалилась, проверьте данные", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickToEnter(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
