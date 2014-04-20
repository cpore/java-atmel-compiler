import meggy.Meggy;

class methodParmValue {
	public static void main(String [] args){
		new C().m(1);
	}
}
class C{
	public void m(int x){
		// assign invalid type to formal variable
		x = true;
	}
}
