

import meggy.Meggy;

class thisUundefinedMethod {
	public static void main(String [] args){
		new C().m();
	}
}
class C{
	public void m(){
		// call undefined method
		this.m2();

	}
}
