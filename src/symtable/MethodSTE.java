package symtable;

import java.util.ArrayList;

import ast.node.BoolType;
import ast.node.ButtonType;
import ast.node.ByteType;
import ast.node.ColorType;
import ast.node.IType;
import ast.node.IntType;
import ast.node.ToneType;
import ast.node.VoidType;

public class MethodSTE extends NamedScopeSTE{
	private ArrayList<Type> mSignature;
	private Type returnType;
	private int frameSize = 0;
	private String className;

	public MethodSTE(String name) {
		super(name);
		mSignature = new ArrayList<Type>();
	}

	public void addFormal(IType type){
		mSignature.add(convertType(type));
	}

	public ArrayList<Type> getSignature(){
		return mSignature;
	}

	public Type getReturnType(){
		return returnType;
	}

	public void setFrameSize(int size){
		frameSize = size;
	}

	public int getFrameSize(){
		return frameSize;
	}
	
	public void setClassName(String id){
		className = id;
	}
	
	public String getClassName(){
		return className;
	}
	
	public String getAVRLabel(){
		return className+getName();
	}

	// THIS MAY BE BAD
	public void setReturnType(IType type){
		returnType = convertType(type);
	}
	
	private Type convertType(IType type){
		if(type instanceof BoolType)
			return Type.BOOL;
		else if(type instanceof ButtonType)
			return Type.BUTTON;
		else if(type instanceof ByteType)
			return Type.BYTE;
		else if(type instanceof ColorType)
			return Type.COLOR;
		else if(type instanceof IntType)
			return Type.INT;
		else if(type instanceof ToneType)
			return Type.TONE;
		else if(type instanceof VoidType)
			return Type.VOID;
		else
			return Type.CLASS;
	}
	
}
