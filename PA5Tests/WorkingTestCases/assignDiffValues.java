import meggy.Meggy;

class assignDiffValues {
	public static void main(String [] args){
		new C().foo( (1<2) );
	}
}

class C {
    int i;
    C c;
    
    public int foo( boolean p ) {

        B b;
        
        // correct in terms of typing
        p = true;
        c = this; 
        c.foo2(p);
        b = new B();
        b.foo();
        return 1;
    }
    public void foo2(boolean b){
    	if(b){
    		Meggy.setPixel((byte) 1, (byte)1 , Meggy.Color.BLUE );
    	}
    	else{
    		Meggy.setPixel((byte) 1, (byte)1 , Meggy.Color.RED );
    	}
    }
}

class B {
	public void foo(){
		Meggy.setPixel((byte) 1, (byte)1 , Meggy.Color.YELLOW );

	}
}
