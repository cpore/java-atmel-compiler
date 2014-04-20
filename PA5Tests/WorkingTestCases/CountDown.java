import meggy.Meggy;

class CountDown {
    public static void main(String[] a){
        Meggy.setPixel((byte)(new Recurse().printCount(3)),
                       (byte)7,
                       Meggy.Color.WHITE);
    }
}

class Recurse {
    public int printCount(int i) {
        int x;
        x = i + 3;
        Meggy.setPixel( (byte)i, (byte)i, Meggy.Color.BLUE );
        if ( i < 1 ) {
        } else {
            x = this.printCount(i-1);
        }
        return x;
    }
}