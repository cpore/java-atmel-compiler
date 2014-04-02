import meggy.Meggy;

class resultByteToIntPromotion {
    public static void main(String[] whatever){
        new A().fooAddResult(1, 2);
        new A().fooSubResult(3, 4);
    }
}

class A {
    public void fooAddResult(int x, int y) {
        Meggy.setPixel((byte)(x + y), (byte)(y + x), Meggy.Color.BLUE);
    }

    public void fooSubResult(int a, int b) {
        Meggy.setPixel((byte)(b - a), (byte)(a), Meggy.Color.RED);
    }
}
