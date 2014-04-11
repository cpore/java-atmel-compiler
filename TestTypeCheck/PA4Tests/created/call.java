import meggy.Meggy;

/*
* Test for various things when calling a fucntion
*
*/

class call {
    public static void main(String[] whatever){
        // call class that doesn't exist
        //new Z().set(5);

        // call method that doesn't exist
        //new A().setZ(5);

        // call method with wrong number of arguments
        new B().setB(Meggy.Color.BLUE, 3, 4);
 
    }
}

class A {
   public void setA(int y) {
        Meggy.setPixel((byte)5, (byte)2, y);
    }

    
}
class B {
   public void setB(int y, int x, Meggy.Color z) {
        Meggy.setPixel((byte)5, (byte)2, y);
    }

}

class C {
   public void setC(int y) {
        Meggy.setPixel((byte)5, (byte)2, y);
    }

}

