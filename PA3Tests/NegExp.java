/**
 * Dangling else
 * WB 2/8/12
 */

import meggy.Meggy;

class NegExp {
    public static void main(String[] whatever){
	    Meggy.setPixel( (byte)((byte)2 - - -(byte)2), (byte)(3+1), Meggy.Color.GREEN );
	
    }
}
