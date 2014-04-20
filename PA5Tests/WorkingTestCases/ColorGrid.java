import meggy.Meggy;

class ColorGrid {
	public static void main(String [] args){
		new Start().Start();
	}
}

class Start{
	boolean match;
	byte x;
	byte y;
	public void Start(){
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
	public byte increment(byte y) {
		return  (byte)(y+1);
	}
	public void colorCell(int x, int y){
		if( x == y ){
			new TonePlayer().m(Meggy.Tone.A3);
			Meggy.setPixel((byte)x, (byte)y, Meggy.Color.YELLOW);
		}
		else {
			if( x < y){
				if(new ButtonChecker().checkButton()){
					Meggy.setPixel((byte)x, (byte)y, Meggy.Color.RED);
				}
			}
			else{
				Meggy.setPixel((byte)x, (byte)y, Meggy.Color.GREEN);
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