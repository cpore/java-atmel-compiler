import meggy.Meggy;

class localMethodValue {
	public static void main(String [] args){
		new C().m();
	}
}
class C{
	public void m(){
		// assign invalid type to local variable
		int x; 
		x = true;
	}
}
