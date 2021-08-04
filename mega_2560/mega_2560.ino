#include <Wire.h>
#include <openGLCD.h>


/*Kullanilan Degiskenler*/
int bitis=0,m_sure=0,dk=0,saat=0,sn=0,bitis_say=0,durum=-1;
String k_Adi="--",unvan="--",mesaj="--",tarih="----/--/--";
void setup()
{
  GLCD.Init();  
  
  /*Universite Resmi*/
  GLCD.ClearScreen();
  delay(3000);  

  GLCD.ClearScreen();
  
  /*I2C HABERLESME (Mega vs NodeMCU)*/
  Wire.begin(4);                
  Wire.onReceive(veriAl);
  Wire.onRequest(veriGonder); 
  Serial.begin(9600);  
  Serial.println("Bekleniyor...");
  delay(2000);
}

void loop(){
  
    if(durum==1 || durum==0){
        if(sn>=0 && sn<59)sn++;
        else {sn=0;
              if(dk>=0 && dk<59)dk++;
              else{dk=0;
                  if(saat>=0 && saat<23)saat++;
                  else saat=0;
                  }
             }
        if(bitis!=bitis_say)bitis_say++;
        else {mesaj=" --";bitis_say=0;durum=0;}
      ciz();
    }
  delay(1000);
}

void veriAl(int veri)
{ 
 String kap;
 while(1<Wire.available())
 {
   char c = Wire.read();  
   kap+=c;
   delay(1000);        
 }
 int kod=Wire.read();
    if(kod<99){m_sure=kod; bitis=m_sure*60;bitis_say=0;}
    else if(kod==100) unvan=kap;
    else if(kod==101)k_Adi=kap;
    else if(kod==102)tarih=kap;
    else if(kod==103)mesaj=kap;
    else if(kod>=104 && kod<=105){durum=kod-104;if(durum==0){mesaj=" --";bitis_say=0;}}
    else if(kod>=120 && kod<=143){saat=kod-120;}
    else if(kod>=150 && kod<=209){dk=kod-150;}   

}

void veriGonder(){
    Wire.write(durum);
  }

void ciz(){  
    GLCD.ClearScreen();
    GLCD.CursorTo(0,0); GLCD.print(unvan+" "+k_Adi);    
    GLCD.CursorTo(0,1); GLCD.print("---------------------");  
    GLCD.CursorTo(0,2); GLCD.print("Mesaj: ");
    GLCD.CursorTo(6,2); GLCD.print(mesaj);
    GLCD.CursorTo(0,6); GLCD.print("---------------------"); 
    GLCD.CursorTo(0,7); GLCD.print(tarih); 
    GLCD.CursorTo(13,7);  
    if(saat<10) {GLCD.print("0");GLCD.CursorTo(14,7);GLCD.print(saat);}
    else GLCD.print(saat);
    GLCD.CursorTo(15,7); GLCD.print(":");
    GLCD.CursorTo(16,7); 
    if(dk<10) {GLCD.print("0");GLCD.CursorTo(17,7);GLCD.print(dk);}
    else GLCD.print(dk);
    GLCD.CursorTo(18,7); GLCD.print(":");
    GLCD.CursorTo(19,7); 
    if(sn<10) {GLCD.print("0");GLCD.CursorTo(20,7);GLCD.print(sn);}
    else GLCD.print(sn);
  }
 
