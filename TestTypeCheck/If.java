/**
 * Dangling else
 * WB 2/8/12
 */

import meggy.Meggy;

class If {
    public static void main(String[] whatever){
	if ((byte)2  == ((byte)3+1)-2 == 3)
	    Meggy.setPixel( (byte)(1+6), (byte)(5 - -1), Meggy.Color.GREEN );
	else
	    Meggy.setPixel( (byte)((byte)(-1)* (byte)(- (byte)7 )), (byte)6, Meggy.Color.RED );
	    
    if (true)
        Meggy.setPixel( (byte)1, (byte)1, Meggy.Color.WHITE );
    }

    
}
