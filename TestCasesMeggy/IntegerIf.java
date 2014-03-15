/**
 * Dangling else
 * WB 2/8/12
 */

import meggy.Meggy;

class IntegerIf {
    public static void main(String[] whatever){
	if (Meggy.Color.BLUE == Meggy.Color.BLUE){
	    Meggy.setPixel( (byte)(1+6), (byte)(5), Meggy.Color.GREEN );
    }
	}
}
