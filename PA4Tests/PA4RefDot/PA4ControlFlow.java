/**
 * PA5Cylon
 * WB 6/1/11
 */

import meggy.Meggy;

class PA4ControlFlow {
    public static void main(String[] whatever){
        {
            Meggy.setPixel( (byte)0, (byte)0, Meggy.Color.RED );

            // infinite loop that checks for button presses
            // while (Meggy.checkButton(Meggy.Button.B)==false) {
            while(true){
                if (Meggy.checkButton(Meggy.Button.Up)) {
                  if (Meggy.checkButton(Meggy.Button.Right)) {
		      //System.out.println("            *** Up&Right    ***"); 
                        Meggy.setPixel( (byte)0, (byte)0, Meggy.Color.DARK );
                        Meggy.setPixel( (byte)0, (byte)7, Meggy.Color.DARK );
                        new Row().cl((byte)4);
                 } else {
		      //System.out.println("            *** Up    ***");
		        Meggy.setPixel( (byte)0, (byte)0, Meggy.Color.DARK );
                        Meggy.setPixel( (byte)0, (byte)4, Meggy.Color.DARK );
                        new Row().cl((byte)7);
		  }
                } else {
                  if (Meggy.checkButton(Meggy.Button.Down)) {
		        //System.out.println("          *** Down    ***");
                        Meggy.setPixel( (byte)0, (byte)7, Meggy.Color.DARK );
                        Meggy.setPixel( (byte)0, (byte)4, Meggy.Color.DARK );
                        new Row().cl((byte)0);
                        
                  } else {
		      //System.out.println("           *** Not Up, not Down    ***");
		      if(Meggy.getPixel( (byte)0, (byte)0 ) ==  Meggy.Color.RED ){
                        Meggy.setPixel( (byte)0, (byte)7, Meggy.Color.DARK );
                        Meggy.setPixel( (byte)0, (byte)4, Meggy.Color.DARK );
                        new Row().cl((byte)0);
                      }
                      else {
	               if(Meggy.getPixel( (byte)0, (byte)4 ) ==  Meggy.Color.RED ){
                        Meggy.setPixel( (byte)0, (byte)0, Meggy.Color.DARK );
                        Meggy.setPixel( (byte)0, (byte)7, Meggy.Color.DARK );
                        new Row().cl((byte)4);
                       }
                       else {
		        Meggy.setPixel( (byte)0, (byte)4, Meggy.Color.DARK );
                        Meggy.setPixel( (byte)0, (byte)0, Meggy.Color.DARK );
                        new Row().cl((byte)7);
                       }
                      }
                  }
                }
                // delay 1 sec
                Meggy.delay(1000);
            }
        }
    }
}

class Row {
    // do a cylon motion once in row y
    public void cl(byte y){
	this.lr((byte)0,y); // left to right in row y
	this.rl((byte)7,y); // and back

    }

    public void lr(byte x, byte y) {
        // light up x,y if in bounds
        // and continue recursion
        if (this.inBounds(x, y)) {
            Meggy.setPixel(x, y, Meggy.Color.RED);
            if ( this.inBounds((byte)(x-(byte)1), y) ) {
		Meggy.setPixel((byte)(x-(byte)1), y, Meggy.Color.DARK);
            } else {}
            Meggy.delay(256);
            this.lr((byte)(x+(byte)1),y);
        } else {}
    }

    public void rl(byte x, byte y) {
        // light up x,y if in bounds
        // and continue recursion
        if (this.inBounds(x, y)) {
            Meggy.setPixel(x, y, Meggy.Color.RED);
            if ( this.inBounds((byte)(x+(byte)1), y) ) {
		Meggy.setPixel((byte)(x+(byte)1), y, Meggy.Color.DARK);
            } else {}
            Meggy.delay(256);
            this.rl((byte)(x-(byte)1),y);
        } else {}
    }
    
    public boolean inBounds(byte x, byte y) {
        return   ((byte)(0-1) < x) && 
	         (x < (byte)8) && 
                 ((byte)(0-1) < y) && 
                 (y < (byte)8);
    }

}


