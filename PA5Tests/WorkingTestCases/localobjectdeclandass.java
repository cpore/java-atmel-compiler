

import meggy.Meggy;

class localobjectdeclandass {
	public static void main(String[] args){
		new C6().m1();
	}
}
class C6{
	public void m1(){
		boolean b;
		byte x;
		int y;
		Meggy.Color color;
		Meggy.Tone tone;
		C7 c7;
		
		b = true;
		x= (byte)0;
		y = 0;
		color = Meggy.Color.GREEN;
		tone = Meggy.Tone.B3;
		c7 = new C7();
		
		while(b){
			Meggy.setPixel(x, (byte)(y) , color);
			Meggy.toneStart(tone, 1);
			if(Meggy.checkButton(Meggy.Button.Left)){
				Meggy.toneStart(tone, 1);
			}
			else{
				c7.m1();
			}
			if( x < (byte)7 ){
				x = (byte)(x+1);
				y = y+1;
			}
			else
				b = false;
		}
	}
}
class C7{
	public void m1(){
		Meggy.toneStart(Meggy.Tone.G3, 3);

	}
}

