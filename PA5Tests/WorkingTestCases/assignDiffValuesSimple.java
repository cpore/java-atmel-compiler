import meggy.Meggy;

class assignDiffValuesSimple {
	public static void main(String [] args){
		new C().foo( (1<2) );
	}
}

class C {
    int i;
    C cc;
    
    public int foo( boolean p ) {

        B bb;
        
        // correct in terms of typing
        p = true;
        cc = this; 
        cc.foo2(p);
        bb = new B();
        bb.foo();
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
