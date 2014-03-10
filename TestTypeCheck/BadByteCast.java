/**
 * Dangling else
 * WB 2/8/12
 */

import meggy.Meggy;

class BadByteCast {
    public static void main(String[] whatever){
	    if (true && false)
	      Meggy.setPixel( (byte) true, (byte)5, Meggy.Color.GREEN );
	  }
}
