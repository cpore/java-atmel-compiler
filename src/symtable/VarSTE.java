package symtable;

import ast.node.*;

import ast.node.IType;

public class VarSTE extends STE{
	private Type mType;
	private String mBase;
	private String className;
	private int mOffset;

	private boolean isMember = false;
	private boolean isParam = false;
	private boolean isLocal = false;

	public VarSTE(String name) {
		super(name);
		//mType = type;
		mBase = "Y";
		mOffset = 0;
		className = "";
	}
	
	public VarSTE(String name, IType type) {
		super(name);
		//mType = type;
		mBase = "Y";
		mOffset = 0;
		className = "";
		setType(type);
		className = mType.toString();
	}

	public void setLocation(String base, int offset){
		mBase = base;
		mOffset = offset;
	}

	public String getbase(){
		return mBase;

	}

	public int getOffset(){
		return mOffset;
	}
	
	public int getWidth(){
		return mType.getAVRTypeSize();
	}

	public String getBasePlusOffset(){
		return mBase + " + " + mOffset;
	}
	
	public void setClassType(Type type){
		mType = type;
	}

	public Type getType(){
		return mType;
	}

	public boolean isMember(){
		return isMember;
	}
	
	public void setIsMember(boolean isM){
		isMember = isM;
		mBase = "Z";
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
	
	public void setIsLocal(boolean isL){
		isLocal = isL;
	}
	
	public void setClassName(String type){
		className = type;
	}

	public String getClassName(){
		return className;
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
		else if(type instanceof ClassType){
			mType = Type.getClassType(((ClassType) type).getName());
		}
	}
}
