
import meggy.Meggy;

class invalidReceiver {
	public static void main(String [] args){
		new C().m();
	}
}
class C{
	public void m(){
		int x;
		// invalid receiver
		x.m();
	}
}
