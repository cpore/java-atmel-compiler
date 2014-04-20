
import meggy.Meggy;

class errorObjectParam {
	public static void main(String [] args){
		// invalid argument class type
		new C().m(new B() );
	}
}
class C{
	public void m(D d){
	}
}
class B{
	
}

class D{
	
}
