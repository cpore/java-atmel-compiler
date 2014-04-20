import meggy.Meggy;

class instanceSpecials {
    public static void main(String[] whatever){
        new A().foo();
    }
}

class A {
    Meggy.Color c;
    Meggy.Tone t;

    public void foo() {
        c = Meggy.Color.RED;
        t = Meggy.Tone.C3;

        Meggy.setPixel((byte)1, (byte)2, c);
        Meggy.toneStart(t, 1);
    }
}

