
import meggy.Meggy;

class thisMethodDifferentClass {
	public static void main(String [] args){
		new C().m();
	}
}
class C{
	public void m(){
		// use this to call method belonging to different class
		this.m2();
	}
}
class B{
	public void m2(){
		
	}
}
