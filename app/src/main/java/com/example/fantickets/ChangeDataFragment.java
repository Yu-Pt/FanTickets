package com.example.fantickets;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class ChangeDataFragment extends Fragment {
    private FirebaseAuth mAuth;
    StorageReference storageProfilePictureRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        storageProfilePictureRef = FirebaseStorage.getInstance().getReference().child("UsersImages");
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
        View view = inflater.inflate(R.layout.fragment_change_data, container, false);
        FirebaseUser cUser = mAuth.getCurrentUser();
        RoundedImageView imageChange = (RoundedImageView) view.findViewById(R.id.imageChangeProfileMdata);
        imageChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserDataActivity.class);
                startActivity(intent);
            }
        });
        EditText FioText1 = (EditText) view.findViewById(R.id.changeFIO1);
        EditText FioText2 = (EditText) view.findViewById(R.id.changeFIO2);
        EditText BirthText = (EditText) view.findViewById(R.id.changedatabirth);
        EditText DocText = (EditText) view.findViewById(R.id.changedocument);
        EditText GenderText = (EditText) view.findViewById(R.id.changegender);
        String id = cUser.getUid();
        final DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference("User").child(id);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link = snapshot.child("image").getValue().toString();
                Picasso.get().load(link).into(imageChange);
                String fio = snapshot.child("Name").getValue().toString();
                String fio2 = snapshot.child("Surname").getValue().toString();
                FioText1.setText(fio);
                FioText2.setText(fio2);
                String birth = snapshot.child("Birth").getValue().toString();
                BirthText.setText(birth);
                String doc = snapshot.child("Document").getValue().toString();
                DocText.setText(doc);
                String gender = snapshot.child("Gender").getValue().toString();
                GenderText.setText(gender);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        Button bttSaveChanges = view.findViewById(R.id.button_save_changes);
        bttSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = FioText1.getText().toString();
                String usersurname = FioText2.getText().toString();
                String birth = BirthText.getText().toString();
                String doc = DocText.getText().toString();
                String gender = GenderText.getText().toString();
                String idtable = cUser.getUid();
                final DatabaseReference ref;
                ref = FirebaseDatabase.getInstance().getReference("User").child(idtable);
                ref.child("Name").setValue(username);
                ref.child("Surname").setValue(usersurname);
                ref.child("Birth").setValue(birth);
                ref.child("Document").setValue(doc);
                ref.child("Gender").setValue(gender);
                Toast.makeText(getActivity(), "Успешно сохранено", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}