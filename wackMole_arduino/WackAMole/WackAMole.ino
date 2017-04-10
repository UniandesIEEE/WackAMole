#include <Thread.h>
#include <ThreadController.h>

ThreadController controll = ThreadController();

Thread led1 = Thread();
Thread led2 = Thread();
Thread led3 = Thread();
Thread led4 = Thread();

int estado = 0;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);

  pinMode(6, OUTPUT);
  pinMode(7, INPUT);
  pinMode(8, OUTPUT);
  pinMode(9, INPUT);
  pinMode(10, OUTPUT);
  pinMode(11, INPUT);
  pinMode(12, OUTPUT);
  pinMode(13, INPUT);

  led1.onRun(ranLed1);
  led1.setInterval(700);
    
  led2.onRun(ranLed2);
  led2.setInterval(700);
    
  led3.onRun(ranLed3);
  led3.setInterval(700);
  
  led4.onRun(ranLed4);
  led4.setInterval(700);
 
}

void loop() {
  // put your main code here, to run repeatedly:
  int p = Serial.read();

//Verifica si se encuentra en estado HIGH y lo pasa a estado LOW
  if(p==0&&estado==1){
    estado = 0;
    digitalWrite(12,LOW);
    digitalWrite(10,LOW);
    digitalWrite(8,LOW);
    digitalWrite(6,LOW);
  }
  //Verifica si está en estado LOW y lo pasa a HIGH
  else if(p==1&&estado==0){
    estado = 1;
  }
  
  if(estado==1)
  {
    if(led1.shouldRun())
    {
      led1.run();
    }
    if(led2.shouldRun())
    {
      led2.run();
    }
    if(led3.shouldRun())
    {
      led3.run();
    }
    if(led4.shouldRun())
    {
      led4.run();
    }
    revisar();
  }
}

void ranLed1()
{
  long rans = random(0,10);
  if(rans>6)
  {
    digitalWrite(12,HIGH);
  }
}

void ranLed2()
{
  long rans = random(0,10);
  if(rans>6)
  {
    digitalWrite(10,HIGH);
  }
}

void ranLed3()
{
  long rans = random(0,10);
  if(rans>6)
  {
    digitalWrite(8,HIGH);
  }
}

void ranLed4()
{
  long rans = random(0,10);
  if(rans>6)
  {
    digitalWrite(6,HIGH);
  }
}

void revisar(){
  if(digitalRead(13)==1)
  {
    Serial.println(2);
    digitalWrite(12,LOW);
  }
  if(digitalRead(11)==1)
  {
    Serial.println(2);
    digitalWrite(10,LOW);
  }
  if(digitalRead(9)==1)
  {
    Serial.println(2);
    digitalWrite(8,LOW);
  }
  if(digitalRead(7)==1)
  {
    Serial.println(2);
    digitalWrite(6,LOW);
  }
}

