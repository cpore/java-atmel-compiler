package symtable;

public class VarSTE extends STE{
	private Type mType;
	private int mBase;
	private int mOffset;
	
	public VarSTE(String name, Type type) {
		super(name);
		mType = type;
		mBase = 0;
		mOffset = 0;
	}
	
	public void setLocation(int base, int offset){
		mBase = base;
		mOffset = offset;
	}
	
	public int getbase(){
		return mBase;
		
	}

	public int getOffset(){
		return mOffset;
	}
	
	public boolean isMember(){
		return false;
	}
	
	public boolean isParam(){
		// TODO this
		return false;
	}
	
	public boolean isLocal(){
		return false;
	}
}
