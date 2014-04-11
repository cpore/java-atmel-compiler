import meggy.Meggy;

/*
* Tests Symbol table to make sure that it reports
* all symbol redefinitions
*
*/

class multiDef {
    public static void main(String[] whatever){
   
        new A().set(5);    
 
    }
}

class A {
   public void setA(int y) {
        Meggy.setPixel((byte)5, (byte)2, x);
    }

    public void setA(int y) {
        Meggy.setPixel((byte)5, (byte)2, x);
    }

    public void setB(int y) {
        Meggy.setPixel((byte)5, (byte)2, x);
    }


    public void setB(int y) {
        Meggy.setPixel((byte)5, (byte)2, x);
    }

    public void setC(int y) {
        Meggy.setPixel((byte)5, (byte)2, x);
    }
    public void setC(int y) {
        Meggy.setPixel((byte)5, (byte)2, x);
    }
}
class A {
   public void setD(int y, int y) {
        Meggy.setPixel((byte)5, (byte)2, x);
    }

}

class A {
   public void setD(int y) {
        Meggy.setPixel((byte)5, (byte)2, x);
    }

}

