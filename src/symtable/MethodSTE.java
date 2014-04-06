package symtable;

import java.util.HashMap;
import java.util.LinkedList;

import ast.node.BoolType;
import ast.node.ButtonType;
import ast.node.ByteType;
import ast.node.ColorType;
import ast.node.Formal;
import ast.node.IType;
import ast.node.IntType;
import ast.node.ToneType;
import ast.node.VoidType;

public class MethodSTE extends NamedScopeSTE{
	private HashMap<String, Type> mSignature;
	private LinkedList<Formal> formals;
	private Type returnType;
	private int frameSize = 0;

	public MethodSTE(String name) {
		super(name);
		mSignature = new HashMap<String, Type>();
	}

	public void addFormal(String name, IType type){
		mSignature.put(name, convertType(type));
	}

	public void addFormals(LinkedList<Formal> formals){
		this.formals = formals;
	}
	/*
	public void setReturnType(IType type){
		returnType = type;
	}*/

	public HashMap<String, Type> getSignature(){
		return mSignature;
	}

	public Type getReturnType(){
		return returnType;
	}

	public void setFrameSize(int size){

	}

	public int getFrameSize(){
		return frameSize;
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
