package com.codinginflow.navigationdrawerexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private FirebaseAuth auth;
    private Button btnGiris,btnKayit;
    private TextView textEmail,textPassword;
    ArrayList<Profil> uyeler;
    Profil user;
    static public String tutulacakeMail;
    static public int tutulacakId;
    int sayi=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uyeler = new ArrayList<>();

        auth = FirebaseAuth.getInstance();

        textEmail =(EditText) findViewById(R.id.girisEmail);
        textPassword =(EditText) findViewById(R.id.girisSifre);
        btnGiris =(Button) findViewById(R.id.btnGirisYap);
        btnKayit =(Button) findViewById(R.id.btnKayitOl);

                Login.tutulacakeMail = textEmail.getText().toString();

                btnGiris.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference();

                        myRef.child("Profil").addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    user = snapshot.getValue(Profil.class);
                                    uyeler.add(
                                            new Profil(
                                                    user.getIdArttir(),
                                                    user.getUnvan(),
                                                    user.getadSoyad(),
                                                    user.getEmail(),
                                                    user.getSifre()

                                            )

                                    );

                                    //Toast.makeText(Login.this,"" + user.getEmail() + "   " + user.getSifre() + "  " + " "+ textEmail.getText() +" "+ textPassword.getText(),Toast.LENGTH_LONG).show();
                                    if(user.getSifre().equals(textPassword.getText().toString()) && user.getEmail().equals(textEmail.getText().toString()))
                                    {
                                        Toast.makeText(Login.this,"Giriş Başarılı",Toast.LENGTH_LONG).show();

                                            sayi = user.getIdArttir();
                                            gonder();


                                    }


                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                            }
                        });

                    }

                    private void gonder() {
                        Login.tutulacakId =sayi;

                        startActivity(new Intent(Login.this,MainActivity.class));

                    }
                });




        btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,KayitOl.class));

            }
        });

    }
}
