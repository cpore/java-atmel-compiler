
import meggy.Meggy;

class toneStart {
	public static void main(String[] args){
		if( 2-1 < 2+1){
			Meggy.toneStart(Meggy.Tone.C3, 1);
                        Meggy.toneStart(Meggy.Tone.Ds3, 1);
		}

               
                new Tone2().m2(new Tone1().m1());
	}
}



class Tone1{
	public Meggy.Tone m1(){
		return Meggy.Tone.As3;
	}
}




class Tone2{
       public void m2( Meggy.Tone tone){
		Meggy.toneStart(tone, 2);
	}
}







