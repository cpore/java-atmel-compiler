import meggy.Meggy;

/*
* Test for various things when calling a fucntion
* Uncomment the code to test
*
*/

class call {
    public static void main(String[] whatever){
        // call class that doesn't exist
        //new Z().set(5);

        // call method that doesn't exist
        //new A().setZ(5);

        // call method with wrong number of arguments
        //new B().setB(Meggy.Color.BLUE, 3);

        //Test CallExp
        Meggy.setPixel(new A().getByte(5), (byte)2, new B().getBlue());
 
    }
}

class A {
   public void setA(Meggy.Color y) {
        Meggy.setPixel((byte)5, (byte)2, y);
    }

    public byte getByte(int i){
        return (byte)i;
    }

    
}
class B {
   public void setB(int y, int x, Meggy.Color z) {
        Meggy.setPixel((byte)5, (byte)2, z);
    }

    public Meggy.Color getBlue(){
        return Meggy.Color.BLUE;   
    }

}

class C {
   public void setC(Meggy.Color y) {
        // call class from another class
        new B().setB(3, 4, Meggy.Color.BLUE);
        Meggy.setPixel((byte)5, (byte)2, y);
    }

}

