import meggy.Meggy;

class methodsWithRetVals {
    public static void main(String[] whatever){
        Meggy.setPixel(new A().val1(),
                       (byte)(new A().val2()),
                       new A().valGreen());
    }
}

class A {
    public byte val1() {
        return (byte)1;
    }

    public int val2() {
        return 2;
    }

    public Meggy.Color valGreen() {
        return Meggy.Color.GREEN;
    }
}
