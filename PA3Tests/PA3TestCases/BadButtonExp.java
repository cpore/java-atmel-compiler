/**
 * PA3buttondot
 * 
 * If no button is pressed, then lights up a single ORANGE pixel.
 * If button A is pressed, then lights up a single VIOLET pixel.
 * If button B is pressed, then lights up a single GREEN pixel.
 * 
 * MMS, 2/1/11
 */

import meggy.Meggy;

class BadButtonExp {
    public static void main(String[] whatever){
            
        if (Meggy.checkButton(2)) {
            Meggy.setPixel( (byte)0, (byte)0, Meggy.Color.VIOLET );
            Meggy.setPixel( (byte)7, (byte)0, Meggy.Color.DARK );
            Meggy.setPixel( (byte)7, (byte)7, Meggy.Color.DARK );
        }
    }
}
