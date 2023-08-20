package com.example.fantickets;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText EmailBDent, PasswordBDent;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();
    }
    public void init(){
        EmailBDent = findViewById(R.id.edit_login);
        PasswordBDent = findViewById(R.id.edit_password);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if(cUser != null  && cUser.isEmailVerified()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else Toast.makeText(this, "Войдите или зарегистрируйтесь", Toast.LENGTH_SHORT).show();
    }

    public void onClickEnterBD(View view) {
        if(!TextUtils.isEmpty(EmailBDent.getText().toString()) && !TextUtils.isEmpty(PasswordBDent.getText().toString())) {
            mAuth.signInWithEmailAndPassword(EmailBDent.getText().toString(), PasswordBDent.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (task.isSuccessful() && user.isEmailVerified()) {
                        Toast.makeText(getApplicationContext(), "Вы успешно вошли", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else
                        Toast.makeText(getApplicationContext(), "Вход не произведен, подтвердите email и проверьте данные", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void onClickToReg(View view) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}