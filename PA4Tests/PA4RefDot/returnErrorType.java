import meggy.Meggy;

class returnErrorType {
    public static void main(String[] whatever){
   
     Meggy.setPixel((byte)(new A().ident(5)),
                       (byte)(2),
                       Meggy.Color.GREEN);    
 
    }
}

class A {
   public Meggy.Color ident(int y) {
        return Meggy.Color.GREEN;
    }
}

