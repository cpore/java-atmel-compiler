/**
 * PA6loc.java
 * 
 * call setP method
 * class C: instance variables
 * setP: locals, assignments
 * WB, 4/12
 */

import meggy.Meggy;

class PA6loc {

    public static void main(String[] whatever){
	new C().setP((byte)3,(byte)7,Meggy.Color.BLUE);
    }
}

class C {
    byte cx;    
    byte cy;
    Meggy.Color cc;
    public void setP(byte x, byte y, Meggy.Color c) {
	byte lx; byte ly; 
        lx = x; ly = y;
        cx = x; cy=y; cc=c;
        Meggy.setPixel(cx, ly, cc);    
    }
    
}
