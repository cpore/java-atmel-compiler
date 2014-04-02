/**
 * Dangling else
 * WB 2/8/12
 */

import meggy.Meggy;

class MeggyGetPixel {
    public static void main(String[] whatever){
	
    Meggy.setPixel( (byte)3, (byte)3, Meggy.Color.WHITE );
    Meggy.setPixel( (byte)4, (byte)4, Meggy.Color.BLUE );
    Meggy.setPixel( (byte)4, (byte)4, Meggy.getPixel((byte)3, (byte) 3) );
	    
    }
}
