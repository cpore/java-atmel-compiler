import meggy.Meggy;

class localSpecials {
    public static void main(String[] whatever){
        new A().foo();
    }
}

class A {
    public void foo() {
        Meggy.Color c;
        Meggy.Tone t;

        c = Meggy.Color.RED;
        t = Meggy.Tone.C3;

        Meggy.setPixel((byte)1, (byte)2, c);
        Meggy.toneStart(t, 1);
    }
}

