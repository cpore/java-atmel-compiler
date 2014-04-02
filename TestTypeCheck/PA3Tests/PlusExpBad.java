/**
 * Dangling else
 * WB 2/8/12
 */

import meggy.Meggy;

class PlusExpBad {
    public static void main(String[] whatever){
	    if (1 == 1)
	    Meggy.setPixel( (byte)(6 + true), (byte)5, Meggy.Color.GREEN );
	  }
}
