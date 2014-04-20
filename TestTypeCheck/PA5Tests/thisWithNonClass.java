
import meggy.Meggy;

class thisWithNonClass {
	public static void main(String [] args){
		new C().m();
	}
}
class C{
	int x;
	public void m(){
		//
		this.x = 1;

	}
}
