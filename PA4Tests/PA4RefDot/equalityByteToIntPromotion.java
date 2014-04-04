import meggy.Meggy;

class equalityByteToIntPromotion {
    public static void main(String[] whatever){
        new A().countdown(3,5);
    }
}

class A {
    public void countdown(int n, int m) {
        if((byte)n == m) {
            Meggy.delay(n);
        } 

       if(n == (byte)m)  {
            Meggy.delay(m);
        } 
    }
}
