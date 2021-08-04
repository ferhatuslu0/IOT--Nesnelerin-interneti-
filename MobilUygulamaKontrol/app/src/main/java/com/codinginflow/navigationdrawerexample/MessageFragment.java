package com.codinginflow.navigationdrawerexample;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.lang.System.currentTimeMillis;

public class MessageFragment extends Fragment  {
  private Spinner spinner;
  private Button btnGonder;
  private TextView tarih;
  private EditText mesaj;
  int durum = 1;
  int cnt=0;



    Date th = new Date();


    private FirebaseAuth auth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();


    private  void writeNewMesaj(String mesajId,String gonderilenMesaj,int mesajSuresi,String gonderilenTarih,int saat,int dakika,int durum,int cnt){
        Mesajlar mesajArray=new Mesajlar(mesajId,gonderilenMesaj,mesajSuresi,gonderilenTarih,saat,dakika,durum,cnt);

        myRef.child("Mesajlar").child(mesajId).setValue(mesajArray);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_message, container, false);

        spinner=(Spinner) v.findViewById(R.id.spinnerSaat);
        tarih=(TextView) v.findViewById(R.id.etTarih);
        btnGonder=(Button) v.findViewById(R.id.btnMesajGonder);
        mesaj=(EditText) v.findViewById(R.id.mesajYaz);



        SimpleDateFormat bugun = new SimpleDateFormat("yyyy/MM/dd");


        final ArrayAdapter adapter =ArrayAdapter.createFromResource(this.getContext(),R.array.mesajSecim, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        tarih.setText(bugun.format(th));


        try {


                btnGonder.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Date saatDak = new Date();
                        int saatBilgi;
                        int dakikaBilgi;
                        SimpleDateFormat saat=new SimpleDateFormat("hh");
                        SimpleDateFormat dak=new SimpleDateFormat("ss");

                         saatBilgi = Integer.parseInt(saat.format(saatDak));
                        dakikaBilgi = Integer.parseInt(dak.format(saatDak));


                        if(spinner.getSelectedItemId() != 0 && mesaj.getText().length() > 0) {

                            writeNewMesaj(String.valueOf(Login.tutulacakId), mesaj.getText().toString(), Integer.parseInt(spinner.getSelectedItem().toString()), tarih.getText().toString(),saatBilgi,dakikaBilgi,durum,cnt);
                            Toast.makeText(view.getContext(), "Mesaj başarıyla Gönderildi.", Toast.LENGTH_SHORT).show();

                            mesaj.getText().clear();


                        }

                        else { Toast.makeText(getContext(), "Mesaj metni ve süresini düzenleyin!!", Toast.LENGTH_SHORT).show();}

                    }
                });



        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        return v;

    }


}

