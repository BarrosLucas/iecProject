#include <Servo.h>

#define SERVO1 A0
#define SERVO2 A1
#define SERVO3 A2
#define SERVO4 A3
#define SERVO5 A4
#define SERVO6 A5

#define NEXTBUTTON 3
#define BACKBUTTON 2
#define CONFIRMBUTTON 4

#define CELL1 5
#define CELL2 6
#define CELL3 7
#define CELL4 8
#define CELL5 9
#define CELL6 10

Servo servo1,servo2,servo3,servo4,servo5,servo6;


void setup(){
  Serial.begin(9600);

  servo1.attach(SERVO1);
  servo2.attach(SERVO2);
  servo3.attach(SERVO3);
  servo4.attach(SERVO4);
  servo5.attach(SERVO5);
  servo6.attach(SERVO6);  
  
  servo1.write(0);  
  servo2.write(0);  
  servo3.write(0);  
  servo4.write(0); 
  servo5.write(0);  
  servo6.write(0);  
  
}

void loop (){
  String message = "";
  while(Serial.available()){
    message.concat(Serial.read());
  }
  for(int i = 0; i < message.length(); i++){
   
    if(message.charAt(i) == 'A'){
      Serial.println("A");
    }
    
    if(message.charAt(i) == 'B'){
      Serial.println("B");
    }
    
    if(message.charAt(i) == 'C'){
      Serial.println("C");
    }
    
    if(message.charAt(i) == 'D'){
      Serial.println("D");
    }
    
    if(message.charAt(i) == 'E'){
      Serial.println("E");
    }
    
    if(message.charAt(i) == 'F'){
      Serial.println("F");
    }
    
    if(message.charAt(i) == 'G'){
      Serial.println("G");
    }
    
    if(message.charAt(i) == 'H'){
      Serial.println("H");
    }
    
    if(message.charAt(i) == 'I'){
      Serial.println("I");
    }
    
    if(message.charAt(i) == 'J'){
      Serial.println("J");
    }
    
    if(message.charAt(i) == 'K'){
      Serial.println("K");
    }
    
    if(message.charAt(i) == 'L'){
      Serial.println("L");
    }
    
    if(message.charAt(i) == 'M'){
      Serial.println("M");
    }
    
    if(message.charAt(i) == 'N'){
      Serial.println("N");
    }
    
    if(message.charAt(i) == 'O'){
      Serial.println("O");
    }
    
    if(message.charAt(i) == 'P'){
      Serial.println("P");
    }
    
    if(message.charAt(i) == 'Q'){
      Serial.println("Q");
    }
    
    if(message.charAt(i) == 'R'){
      Serial.println("R");
    }
    
    if(message.charAt(i) == 'S'){
      Serial.println("S");
    }
    
    if(message.charAt(i) == 'T'){
      Serial.println("T");
    }
    
    if(message.charAt(i) == 'U'){
      Serial.println("U");
    }
    
    if(message.charAt(i) == 'V'){
      Serial.println("V");
    }
    
    if(message.charAt(i) == 'W'){
      Serial.println("W");
    }
    
    if(message.charAt(i) == 'X'){
      Serial.println("X");
    }
    
    if(message.charAt(i) == 'Y'){
      Serial.println("Y");
    }
      
    if(message.charAt(i) == 'Z'){
      Serial.println("Z");
    }
    
    if(message.charAt(i) == '.'){
      Serial.println(".");
    }
    
    if(message.charAt(i) == ','){
      Serial.println(",");
    }
    
    if(message.charAt(i) == '!'){
      Serial.println("!");
    }
    
    if(message.charAt(i) == '?'){
      Serial.println("?");    
    }
    
    }
  Serial.println(message);
  
  
}
