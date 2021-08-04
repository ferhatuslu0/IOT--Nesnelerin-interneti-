#include <Firebase_Arduino_WiFi101.h>
#include <Firebase_Arduino_WiFi101_HTTPClient.h>

#include <Wire.h>
#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

#define FIREBASE_HOST "mesajsistemi-f4fbc.firebaseio.com"  //VeriTabani Baglanti Adresi
#define FIREBASE_AUTH "thOCJh4BwOp8rkVjr07ulawPerR41KQOlwmkv3l0"  //Tablo Baglanti Adresi
#define WIFI_SSID ""  //Baglanilan Wifi Adi
#define WIFI_PASSWORD "" //Wifi Parolasi
//
//
/*Kullnilan Degiskenler*/
byte durum=-1,ard_gelen_durum=-1,m_sure=0,dakika=0,saat,cnt;
String k_Adi,unvan,mesaj,tarih;

void setup()
{
 // I2C Baglanti Ayari 
 Wire.begin(D2,D1); 
 Serial.begin(9600);

 // Wifi 'ye Baglaniyor.
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Baglaniyor...");
  while (WiFi.status() != WL_CONNECTED) {Serial.print(".");delay(500);}
  Serial.println();
  Serial.print("Baglandi (IP): ");
  Serial.println(WiFi.localIP());
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH); 
 delay(8000);
}

void loop()
{    
  cnt=Firebase.getInt("Mesajlar/2/cnt");      
  
  if(cnt==0){
    /*Firebase' den Veri Cekme Islemi...*/ 
    unvan=Firebase.getString("Profil/2/unvan");
    k_Adi=Firebase.getString("Profil/2/adSoyad");
    tarih=Firebase.getString("Mesajlar/2/gonderilenTarih");
    mesaj=Firebase.getString("Mesajlar/2/gonderilenMesaj");
    m_sure=Firebase.getInt("Mesajlar/2/mesajSuresi");
    saat=Firebase.getInt("Mesajlar/2/saat");
    dakika=Firebase.getInt("Mesajlar/2/dakika");
    durum=Firebase.getInt("Mesajlar/2/durum");
    
    //Arduino Mega' ya Veri Gonderme...
    Wire.beginTransmission(4);// Transfer Baslatiliyor...      
    Wire.write(unvan.c_str());
    Wire.write(100);
    Wire.endTransmission();// Transfer Sonlandiriliyor...
      
    delay(10);
      
    Wire.beginTransmission(4);
    Wire.write(k_Adi.c_str());
    Wire.write(101);
    Wire.endTransmission();
      
    delay(10);
      
    Wire.beginTransmission(4);
    Wire.write(tarih.c_str());
    Wire.write(102);
    Wire.endTransmission();
      
    delay(10);

    Wire.beginTransmission(4);
    Wire.write(mesaj.c_str());
    Wire.write(103);
    Wire.endTransmission();
      
    delay(10);    

    Wire.beginTransmission(4);
    Wire.write(m_sure);
    Wire.endTransmission();
      
    delay(10);
      
    Wire.beginTransmission(4);
    Wire.write(120+saat);
    Wire.endTransmission();

    delay(10);

    Wire.beginTransmission(4);
    Wire.write(150+dakika);
    Wire.endTransmission();
    delay(10);

    Wire.beginTransmission(4);
    Wire.write(104+durum);
    Wire.endTransmission();
      
    delay(10);
    /*Firebase' e Veri Aktarimi*/
    Firebase.setInt("Mesajlar/2/cnt",1);
  }
  //Arduino Mega' ya Veri Alma...
  Wire.requestFrom(4, 1);
  ard_gelen_durum=Wire.read();
  if(ard_gelen_durum == 0){Firebase.setInt("Mesajlar/2/durum",0);}
  
  delay(1000);
}
