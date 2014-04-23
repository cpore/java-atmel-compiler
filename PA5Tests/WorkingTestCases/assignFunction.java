import meggy.Meggy;

class assignFunction {
	public static void main(String [] args){
		new C().lightUp();
	}
}

class C {
    int i;

    public void lightUp(){
        i = new A().getInt();
        if(i ==1)
            Meggy.setPixel((byte)i, (byte)i, Meggy.Color.GREEN);
        else
            Meggy.setPixel((byte)1, (byte)1, Meggy.Color.RED);
    }
    
    

}

class A{
    public int getInt() {
        return 1;
    }
}
