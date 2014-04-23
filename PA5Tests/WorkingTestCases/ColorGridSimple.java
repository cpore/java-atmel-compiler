import meggy.Meggy;

class ColorGridSimple {
	public static void main(String [] args){
		new Start().doStart();
	}
}

class Start{
	boolean match;
	byte x;
	byte y;
	public void doStart(){
		x = (byte)0;
		y = (byte)0;
		
		while( x < 8 ){
			while( y < 8){
				this.colorCell(x, y);
				y = this.increment(y);
			}
			x = this.increment(x);

		}
	}
	public byte increment(byte b) {
		return  (byte)(b+1);
	}
	public void colorCell(int c, int d){
		if( c == d ){
			new TonePlayer().m(Meggy.Tone.A3);
			Meggy.setPixel((byte)c, (byte)d, Meggy.Color.YELLOW);
		}
		else {
			if( c < y){
				if(new ButtonChecker().checkButton()){
					Meggy.setPixel((byte)c, (byte)d, Meggy.Color.RED);
				}
			}
			else{
				Meggy.setPixel((byte)c, (byte)d, Meggy.Color.GREEN);
			}
		}
	}
}
class TonePlayer{
	public void m(Meggy.Tone tone){
		Meggy.toneStart(tone, 1);
	}
}
class ButtonChecker{
	public boolean checkButton(){
		return Meggy.checkButton(Meggy.Button.B);
	}
}
