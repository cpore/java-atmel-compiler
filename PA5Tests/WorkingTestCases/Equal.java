import meggy.Meggy;

class Equal {
	public static void main(String [] args){
		new Start().notEqual();

        new Start().equal();
	}
}

class Start{
    A a1;
    A a2;
    //B b1;
   // B b2;

    //b1 = new B();
   // b2 = new B();

    public void notEqual(){
        a1 = new A();
        a2 = new A();

        if(a1 == a2){
            Meggy.setPixel((byte) 1, (byte)1 , Meggy.Color.GREEN);
        }else
            Meggy.setPixel((byte) 1, (byte)1 , Meggy.Color.RED);

    }

    public void equal(){
        a1 = new A();
        a2 = new A();

        a2 = a1;
        if(a1 == a2){
            Meggy.setPixel((byte) 1, (byte)1 , Meggy.Color.GREEN);
        }else
            Meggy.setPixel((byte) 1, (byte)1 , Meggy.Color.RED);

    }
    
}


class A{
    int x;
    int y;

    public void m1(){
       x = 0;
       y = 0;
    }
}

class B{
    byte x;
    byte y;

    public void m2(){
        x = (byte) 1;
        y = (byte) 1;
    }
}
