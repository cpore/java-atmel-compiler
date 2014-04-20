import meggy.Meggy;

class instanceOfDifferentClass {
	public static void main(String [] args){
		new C().m();
	}
}
class C{
	public void m(){
		// create instance using different class type
		C c;
		c = new B();
	}
}
class B{
	
}
