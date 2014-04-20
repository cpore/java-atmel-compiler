
import meggy.Meggy;

class localVars {
	public static void main(String[] args){
		new C8().m1();
	}
}
class C8{
	public void m1(){
		
		byte n1;
		byte n2;
		byte n3;
		n1 = (byte) 3;
		n2 = (byte) 2;
		n3 = (byte) 1;
		
		Meggy.setPixel(n1, n2, Meggy.Color.RED);
		
		Meggy.setPixel(n2, n3, Meggy.Color.RED);
		
	}
}

