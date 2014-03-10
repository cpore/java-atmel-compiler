
import meggy.Meggy;

class MeggySetPixelExpBad {
    public static void main(String[] whatever){
            
        if (Meggy.checkButton(Meggy.Button.Up)) {
            Meggy.setPixel( (byte)0, (byte)0,2 );
        }
    }
}
