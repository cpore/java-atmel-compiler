/**
 * WB 2/8/12
 */

import meggy.Meggy;

class AndExpBad {
    public static void main(String[] whatever){
	    if (true && 1){
	      Meggy.setPixel( (byte)6, (byte)5, Meggy.Color.GREEN );
      }
	  }
}
