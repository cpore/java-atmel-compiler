
import meggy.Meggy;

class errorMethodReceiver {
	public static void main(String [] args){
		new C().m();
	}
}

class C{
	public void m(){
		int x;
		// different class receiver 
		new D().m();
	}
}
class D{
	
}
