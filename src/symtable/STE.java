package symtable;

public abstract class STE {
	private String mName;
	
	public STE(String name){
		mName = name;
	}
	
	public String getName(){
		return mName;
	}
}
