import meggy.Meggy;

class passingByteToIntPromotion {
    public static void main(String[] whatever){
        Meggy.setPixel(new A().fooA((byte)1),
                       (byte)(new B().fooB(2)),
                       Meggy.Color.GREEN);
    }
}

class A {
    public byte fooA(byte x) {
        return x;
    }
}


class B {    

   public int fooB(int y) {
        return y;
    }
   
}
