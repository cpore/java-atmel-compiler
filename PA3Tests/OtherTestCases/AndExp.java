/**
 * Dangling else
 * WB 2/8/12
 */

import meggy.Meggy;

class AndExp {
    public static void main(String[] whatever){
	if (false  && true){
	    Meggy.setPixel( (byte)((byte)(-1)* (byte)(- (byte)7 )), (byte)6, Meggy.Color.RED );
	}
    }
}
