/**
 * PA3Expressions.java
 * MMS 2/16/11, updated WB 7/15/11
 */

import meggy.Meggy;

class PA3ByteByteExpr {
	public static void main(String[] whatever){
	    {
	        // Byte addition
	        Meggy.setPixel( (byte)((byte)((byte)3+(byte)2)+(byte)1), 
	                        (byte)((byte)((byte)0+(byte)0)+(byte)7),
                                 Meggy.Color.RED);

	        // Byte subtraction
	        Meggy.setPixel( (byte)((byte)((byte)3-(byte)2)-(byte)1), 
	                        (byte)((byte)((byte)0-(byte)7)+(byte)7),
	                        Meggy.Color.YELLOW);
	        // Byte casts Byte
	        Meggy.setPixel( (byte)((byte)3),
	                        (byte)((byte)((byte)2+(byte)2)),
	                        Meggy.Color.ORANGE);

	    }
    }
}
