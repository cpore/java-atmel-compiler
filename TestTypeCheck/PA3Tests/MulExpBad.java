/**
 * Dangling else
 * WB 2/8/12
 */

import meggy.Meggy;

class MulExpBad {
    public static void main(String[] whatever){
	    if (1 == 1)
	      Meggy.setPixel( (byte)((byte)6 * 6), (byte)5, Meggy.Color.GREEN );
	  }
}
