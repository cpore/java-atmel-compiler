
import meggy.Meggy;

class NotExpBad {
    public static void main(String[] whatever){
	    if (!1)
	      Meggy.setPixel( (byte)(6), (byte)5, Meggy.Color.GREEN );
	  }
}
