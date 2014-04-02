/**
 * WB 2/8/12
 */

import meggy.Meggy;

class LtExpBad {
    public static void main(String[] whatever){
	    if((byte)2 < 1)
	      Meggy.setPixel( (byte)6, (byte)5, Meggy.Color.GREEN );

      if((byte)2 < true)
	      Meggy.setPixel( (byte)6, (byte)5, Meggy.Color.GREEN );
    }
}
