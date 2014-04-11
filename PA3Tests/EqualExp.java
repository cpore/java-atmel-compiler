/**
 * Dangling else
 * WB 2/8/12
 */

import meggy.Meggy;

class EqualExp {
    public static void main(String[] whatever){
	    if (512  == 1024)
	    		Meggy.setPixel( (byte)0, (byte)1, Meggy.Color.GREEN );
	    	else
	    		Meggy.setPixel( (byte)0, (byte)1, Meggy.Color.RED );
    }
}
