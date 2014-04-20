/* PA5Test3
 * 
 * assign a byte type expression to an integer variable. 
 * 
 */

import meggy.Meggy;

class bytecasting {
	public static void main(String[] args){
		new C8().m1();
	}
}
class C8{
	public void m1(){
		int x;
		byte n1;
		byte n2;
		byte n3;
		n1 = (byte) 3;
		n2 = (byte) 2;
		n3 = (byte) 1;
		x = n1;
		Meggy.setPixel((byte)x, (byte)x, Meggy.Color.RED);
		x = n1+n2;
		Meggy.setPixel((byte)x, (byte)x, Meggy.Color.RED);
		x = n1*n2-n3+(byte)1;
		Meggy.setPixel((byte)x, (byte)x, Meggy.Color.RED);

	}
}

