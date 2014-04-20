import meggy.Meggy;

class objectToObject {
    public static void main(String[] whatever){
        new A().m1();
        new A().m2();  
    }
}



class A {

    B ins;
    

       public void m1(){
		ins = new B();
                ins.setColor();
                Meggy.setPixel((byte)1, (byte)2, ins.getColor());
               
        }


	public void m2(){
		ins = new B();
                ins.setTone();
                Meggy.toneStart(ins.getTone(), 1);
               
        }
}




class B {
    Meggy.Color c;
    Meggy.Tone t;

    public void setColor() {
        c = Meggy.Color.RED;
    }
    
     public void setTone() {
         t = Meggy.Tone.C3;
     }  
      
     public Meggy.Color getColor() {
        return c;
    }
       

    public Meggy.Tone getTone() {
        return t;
    }    

    
}



 
    
