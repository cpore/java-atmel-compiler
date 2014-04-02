import meggy.Meggy;

class methodsWithSpecialTypes {
    public static void main(String[] whatever){
        new A().colorMethod(Meggy.Color.GREEN, 1);
        new A().toneMethod(Meggy.Tone.C3, 1);
    }
}

class A {
    public void colorMethod(Meggy.Color c, int x) {
        Meggy.setPixel((byte)1, (byte)1, c);
    }

    public void toneMethod(Meggy.Tone t, int x) {
        Meggy.toneStart(t, x);
    }
}

