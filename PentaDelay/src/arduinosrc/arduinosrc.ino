#define GREEN_LED 11
#define RED_LED 12

void setup() {
  pinMode(GREEN_LED, OUTPUT);
  pinMode(RED_LED, OUTPUT);
  digitalWrite(RED_LED, HIGH);
  
  Serial.begin(9600);
  while(!Serial){;}
}



void loop() {
  
  if (Serial.available()){
    byte incomingByte = Serial.read();
    if (incomingByte == 1){
      digitalWrite(GREEN_LED, HIGH);
      digitalWrite(RED_LED, LOW);
    } else if (incomingByte == 0){
      digitalWrite(GREEN_LED, LOW);
      digitalWrite(RED_LED, HIGH);
    }
  }
}
