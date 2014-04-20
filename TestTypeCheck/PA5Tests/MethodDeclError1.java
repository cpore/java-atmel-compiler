
import meggy.Meggy;

class MethodDeclError1 {
	public static void main(String [] args){
		new C().m();
	}
}
class C{
	// invalid return value for class type
	public C m(){
		return new D();
	}
}
class D{
	
}