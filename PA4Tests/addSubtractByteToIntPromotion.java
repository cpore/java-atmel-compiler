import meggy.Meggy;

class addSubtractByteToIntPromotion {
    public static void main(String[] whatever){
        new A().fooAdd(1, 2);
        new A().fooSub(3, 2);
    }
}

class A {
    
    public void fooAdd(int x, int y) {
        Meggy.setPixel( (byte)(x + y), (byte)y, Meggy.Color.BLUE);
        }

    public void fooSub(int a, int b) {
        Meggy.setPixel( (byte)(a - b), (byte)b, Meggy.Color.RED);
    }
}
