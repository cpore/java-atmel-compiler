/**
 * Dangling else
 * WB 2/8/12
 */

import meggy.Meggy;

class MeggyGetPixel {
    public static void main(String[] whatever){
	
    Meggy.setPixel( (byte)4, (byte)4, Meggy.Color.BLUE );

    if (Meggy.getPixel((byte)4, (byte) 4)  == Meggy.Color.BLUE){
	   Meggy.setPixel( (byte)4, (byte)4, Meggy.Color.WHITE );
	}
	    
	    
    }
}
