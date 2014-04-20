
import meggy.Meggy;

class instancOfUndefinedClass {
	public static void main(String [] args){
		new C().m();
	}
}


class C{
	public void m(){
		// create instance using undefined class
		C c;
		c = new B();
	}
}
