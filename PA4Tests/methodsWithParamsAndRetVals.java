import meggy.Meggy;

class methodsWithParamsAndRetVals {
    public static void main(String[] whatever){
        Meggy.setPixel(new A().ident1(1),
                       (byte)(new A().ident2(2)),
                       new A().valGreen(Meggy.Tone.C3));
    }
}

class A {
    public byte ident1(int x) {
        return (byte)x;
    }

    public int ident2(int y) {
        return y;
    }

    public Meggy.Color valGreen(Meggy.Tone t) {
        return Meggy.Color.GREEN;
    }
}
