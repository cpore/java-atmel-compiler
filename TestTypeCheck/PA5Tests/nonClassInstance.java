
import meggy.Meggy;

class nonClassInstance {
	public static void main(String [] args){
		new C().m();
	}
}
class C{
	public void m(){
		// create instance using non-class type
		C c;
		int x;
		c = new x();
	}
}
