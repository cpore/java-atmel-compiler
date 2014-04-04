package symtable;

import java.util.HashMap;

import ast.node.Node;

public class Scope {
	private HashMap<String,STE> mDict;
	private Scope mEnclosing;
	
	public Scope(){ 
		
	}
	
	public STE lookupInnermost(String Sym){
		
		return null;
	}
	
	public STE lookup(String Sym){
		
		return mDict.get(Sym);
	}
	
	public void insert(STE ste){
		mDict.put(ste.getName(), ste);
	}
}
