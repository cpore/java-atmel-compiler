/**
 * PA2bluedot
 * 
 * Lights up a single blue pixel.
 * 
 * MMS, 2/1/11
 */
import meggy.Meggy;

class MeggyCheckButton {
       public static void main(String[] whatever){
              if(Meggy.checkButton(Meggy.Button.Up)){
                  Meggy.setPixel( (byte)1, (byte)2, Meggy.Color.BLUE );
              }else{
                 if(Meggy.checkButton(Meggy.Button.Down)){
                    Meggy.setPixel( (byte)1, (byte)2, Meggy.Color.GREEN );
                 }else{
                     Meggy.setPixel( (byte)1, (byte)2, Meggy.Color.ORANGE );
                 }
              }
              
        }
}
