package com.codinginflow.navigationdrawerexample;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Profil {
    private String email;
    private String sifre;
    private String unvan;
    private String adSoyad;
    private int idArttir;

    public Profil(){

    }
    public Profil(int idArttir ,String unvan,String adSoyad,String email, String sifre) {


        this.idArttir=idArttir;
        this.unvan=unvan;
        this.adSoyad=adSoyad;
        this.email = email;
        this.sifre = sifre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSifre() {
        return sifre;
    }
    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
    public String getUnvan() {
        return unvan;
    }
    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }
    public String getadSoyad() {
        return adSoyad;
    }
    public void setadSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public int getIdArttir() {
        return idArttir;
    }

    public void setIdArttir(int idArttir) {
        this.idArttir = idArttir;
    }
}