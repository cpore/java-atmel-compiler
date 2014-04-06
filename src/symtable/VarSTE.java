package symtable;

import ast.node.*;

import ast.node.IType;

public class VarSTE extends STE{
	private Type mType;
	private String mBase;
	private int mOffset;
	
	private boolean isMember = false;
	private boolean isParam = false;
	private boolean isLocal = false;
	
	public VarSTE(String name) {
		super(name);
		//mType = type;
		mBase = "Y";
		mOffset = 0;
	}
	
	public void setLocation(String base, int offset){
		mBase = base;
		mOffset = offset;
	}
	
	/*public void setType(IType type){
		mType = type;
	}*/
	
	public String getbase(){
		return mBase;
		
	}

	public int getOffset(){
		return mOffset;
	}
	
	public Type getType(){
		return mType;
	}
	
	public boolean isMember(){
		return isMember;
	}
	
	public boolean isParam(){
		return isParam;
	}
	
	public void setIsParam(boolean isP){
		isParam = isP;
	}
	
	public boolean isLocal(){
		return isLocal;
	}
	
	// THIS MAY BE BAD
	public void setType(IType type){
		if(type instanceof BoolType)
			mType = Type.BOOL;
		else if(type instanceof ButtonType)
			mType = Type.BUTTON;
		else if(type instanceof ByteType)
			mType = Type.BYTE;
		else if(type instanceof ColorType)
			mType = Type.COLOR;
		else if(type instanceof IntType)
			mType = Type.INT;
		else if(type instanceof ToneType)
			mType = Type.TONE;
		else if(type instanceof VoidType)
			mType = Type.VOID;
		else
			mType = Type.CLASS;
	}
}
