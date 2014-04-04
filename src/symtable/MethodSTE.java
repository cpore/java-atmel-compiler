package symtable;

import java.util.ArrayList;

public class MethodSTE extends NamedScopeSTE{
	private ArrayList<Type> mSignature;
	private Type returnType;
	private Scope mScope;
	
	public MethodSTE(String name) {
		super(name);
		
	}
	
	public ArrayList<Type> getSignature(){
		return mSignature;
	}

}
