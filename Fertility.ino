#include <Wire.h>
#include <LiquidCrystal_I2C.h>

#define Buzzer 11
 
//------------ LCD ---------------------------
LiquidCrystal_I2C lcd(0x3F,20,4);// PCF8574A =  0x3F


//-----------------------------------------------------
void setup(void)
{   
    lcd.begin();
    lcd.setCursor(0,0);lcd.print("    Soil - Meter    ");  
    lcd.setCursor(0,1);lcd.print("                    "); 
    lcd.setCursor(0,2);lcd.print("Fertility =        %"); 
    lcd.setCursor(0,3);lcd.print("PH        =         ");
    pinMode(Buzzer,OUTPUT); digitalWrite(Buzzer,HIGH); 
    Beep();         
    
}
//-----------------------------------------------------
void loop(void)
{ int  Fertility,PH;
  float PH_F;
  
  Fertility = Read_Fertility();
  PH_F      = Read_PH();
  lcd.setCursor(15,2);ShowNum(Fertility,3);
  PH  =  PH_F * 10;
  lcd.setCursor(15,3);ShowNum(PH/10,1);lcd.print(".");ShowNum(PH%10,1);
   
  
}
//----------------------------------------------------------------------------
int Read_Fertility()
{int i,Fertility;
   Fertility = 0; 
   for(i=0;i<10;i++){Fertility = Fertility + analogRead(0);delay(1);}
   Fertility = Fertility/10;
   if(Fertility >= 480){Fertility = ((Fertility - 480)/10) + 93;}else
   if(Fertility >= 360){Fertility = ((Fertility - 360)/7.5) + 77;}else
   if(Fertility >= 275){Fertility = ((Fertility - 275)/5) + 59;}else
   if(Fertility >= 200){Fertility = ((Fertility - 200)/6.25) + 47;}else
   if(Fertility >= 125){Fertility = ((Fertility - 125)/5.3) + 31;}else
   if(Fertility >= 65){Fertility = ((Fertility - 65)/4) + 16;}else
   if(Fertility >=  0){Fertility = ((Fertility - 0)/3.75) + 0;}
   return(Fertility);
}
//----------------------------------------------------------------------------
float Read_PH()
{int i;
 float PH;
   PH = 0;
   for(i=0;i<10;i++){PH  = PH + analogRead(0);delay(10); } 
   PH       = PH/10;
   if(PH >= 480){PH = (10-(PH - 480)/14);}else
   if(PH >= 360){PH = (20-(PH - 360)/12);}else
   if(PH >= 275){PH = (30-(PH - 275)/8.5);}else
   if(PH >= 200){PH = (40-(PH - 200)/7.5);}else
   if(PH >= 125){PH = (50-(PH - 125)/8.5);}else
   if(PH >= 65 ){PH = (60-(PH - 60)/6.5);}else
   if(PH >=  0 ){PH = (70-(PH - 0)/6.0);}
   return(PH/10);
}
//---------------------------------------------------------------------------
void NumToChar(unsigned long Num, char  *Buffer,unsigned char Digit)
{char i;
   for(i=(Digit-1);i>= 0;i--)
    { Buffer[i] =  (Num % 10) + '0';  // 234 , 23 , 2
      Num = Num / 10;
    }  
   for(i=0;i<(Digit-1);i++)
    { if(Buffer[i] == '0'){Buffer[i] =  ' ';}else{i =100;}
                         
    }   
}
/*-------------------------------------------------------------*
*  Show Number On LCD                                           *
*-------------------------------------------------------------*/
void ShowNum(unsigned long Num,unsigned char Count)
{ char Buf[10],i;
  NumToChar(Num,Buf,Count);  
  for(i=0;i<Count;i++){lcd.print(Buf[i]);}
  
}
/*-------------------------------------------------------------*
*  Beep  Sound                                                 *
*-------------------------------------------------------------*/
void Beep()
{
  digitalWrite(Buzzer,LOW);delay(50);
  digitalWrite(Buzzer,HIGH);
}

