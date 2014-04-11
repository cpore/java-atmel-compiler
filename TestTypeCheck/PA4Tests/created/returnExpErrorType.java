import meggy.Meggy;

class returnExpErrorType {
    public static void main(String[] whatever){
   
     Meggy.setPixel((byte)5, (byte)(2), new A().ident(5));    
 
    }
}

class A {
   public Meggy.Color ident(int y) {
        return 4;
    }
}

