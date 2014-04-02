
import meggy.Meggy;

class MeggyGetPixelBad {
    public static void main(String[] whatever){
        // tenth second delay, 100 milliseconds
        if(Meggy.getPixel(1,(byte)1) == Meggy.Color.GREEN){
            Meggy.setPixel( (byte)6, (byte)5, Meggy.Color.GREEN );
        }
    }
}

