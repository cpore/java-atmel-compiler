import meggy.Meggy;
class AssignLocalValue {
	public static void main(String [] args){
		new C().m();
	}
}
class C{
	int x;
	public void m(){
		// assign invalid type to member variable
		x = true;
	}
}
