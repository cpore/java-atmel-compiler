/**
 * Dangling else
 * WB 2/8/12
 */

import meggy.Meggy;

class IntegerIf {
    public static void main(String[] whatever){
	if ((5-1) == 4){
	    Meggy.setPixel( (byte)(1 + 5), (byte)(5 - -1), Meggy.Color.RED );
    }

    if ((5+1) == 6){
	    Meggy.setPixel( (byte)(1+5), (byte)(5), Meggy.Color.GREEN );
    }
	}
}
