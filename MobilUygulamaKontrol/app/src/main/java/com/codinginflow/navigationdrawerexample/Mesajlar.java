package com.codinginflow.navigationdrawerexample;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Mesajlar {

    private String mesajId;
    private String gonderilenMesaj;
    private int mesajSuresi;
    private String gonderilenTarih;
    private int durum;
    private int saat;
    private int dakika;
    private FirebaseUser user;
    private int cnt;




    public String getMesajId() {

        return mesajId;
    }

    public String getGonderilenMesaj() {
        return gonderilenMesaj;
    }

    public int getMesajSuresi() {
        return mesajSuresi;
    }

    public String getGonderilenTarih() {
        return gonderilenTarih;
    }

    public void setMesajId(String mesajId) {
        this.mesajId = mesajId;
    }

    public void setGonderilenMesaj(String gonderilenMesaj) {
        this.gonderilenMesaj = gonderilenMesaj;
    }

    public void setMesajSuresi(int mesajSuresi) {
        this.mesajSuresi = mesajSuresi;
    }

    public void setGonderilenTarih(String gonderilenTarih) {
        this.gonderilenTarih = gonderilenTarih;
    }

    public int getDurum() {
        return durum;
    }

    public void setDurum(int durum) {
        this.durum = durum;
    }

    public int getSaat() {
        return saat;
    }

    public void setSaat(int saat) {
        this.saat = saat;
    }

    public int getDakika() {
        return dakika;
    }

    public void setDakika(int dakika) {
        this.dakika = dakika;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public Mesajlar(){

    }

    public Mesajlar(String mesajId,String gonderilenMesaj,int mesajSuresi,String gonderilenTarih,int saat,int dakika,int durum,int cnt){

        durum = 1;
        cnt =0;

        this.mesajId=mesajId;
        this.gonderilenMesaj=gonderilenMesaj;
        this.mesajSuresi=mesajSuresi;
        this.gonderilenTarih=gonderilenTarih;
        this.saat =saat;
        this.dakika=dakika;
        this.durum = durum;
        this.cnt = cnt;
    }
}
