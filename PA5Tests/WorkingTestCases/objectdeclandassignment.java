

import meggy.Meggy;

class objectdeclandassignment {
	public static void main(String[] args){
		new C4().m1();	
	}
}
class C4{
	boolean b;
	byte x;
	int y;
	Meggy.Color color;
	Meggy.Tone tone;
	C5 c5;
	
	public void m1(){
		b = true;
		x = (byte) 0;
		y = 0;
		color = Meggy.Color.GREEN;
		tone = Meggy.Tone.A3;
		c5 = new C5();
		this.m2();
	}
	public void m2(){
		Meggy.setPixel(x, (byte)y, color);
		Meggy.toneStart(tone, 5);
		if(Meggy.checkButton(Meggy.Button.A))
			c5.m3();
	}
}
class C5{
	public void m3(){
		Meggy.setPixel((byte)3, (byte)2, Meggy.Color.DARK);
	}
}
