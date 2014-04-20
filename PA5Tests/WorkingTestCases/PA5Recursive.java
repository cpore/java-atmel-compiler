import meggy.Meggy;
 class PA5Recursive {
	 public static void main(String [] args){
		 new RecursiveClass().recursiveMethod(10);
		 new ButtonChecks().m();
	 }
}

 class RecursiveClass{
	 int i ;
	 public void recursiveMethod(int x){
		 i = 1;
		 if(x < 7){
			 i = x-1;
			 Meggy.setPixel((byte)x, (byte)i, Meggy.Color.BLUE);
		 }
		 else{
			 new TonePlayer().play(i);
			 this.recursiveMethod(x-1);
		 }
	 }
 }
 class TonePlayer{
	 public void play(int t){
		 Meggy.toneStart(Meggy.Tone.A3, t);
	 }
 }
 class ButtonChecks{
	 boolean b;
	public boolean check1(){
		b = this.check2();
		return b;
	}
	public boolean check2(){
		return Meggy.checkButton(Meggy.Button.A);
	}
	public void m(){
		ButtonChecks button;
		button = this;
		button.check1();
	}
 }