package symtable;

import java.util.HashMap;

public class Scope {

	private HashMap<String,STE> mDict;
	private Scope mEnclosing;
	private String parentId;

	public Scope(String id){ 
		mDict = new HashMap<String, STE>();
		parentId = id;
		//mEnclosing = enclosing;
	}

	public STE lookup(String Sym){
		STE ste = mDict.get(Sym);
		if(ste != null)
			return ste;

		// look through named scopes if not in this scope
		for(STE s: mDict.values()){
			if(s instanceof NamedScopeSTE){
				ste = ((NamedScopeSTE) s).lookup(Sym);
				if(ste != null)
					break;
			}
		}

		return ste;
	}
	
	public String getParentId(){
		return parentId;
	}
	
	public STE lookupInnermost(String sym) {
		return mDict.get(sym);
}

	public void insert(STE ste){
		mDict.put(ste.getName(), ste);
	}

	public void setEnclosing(Scope enclosing){
		mEnclosing = enclosing;
	}

	public Scope getEnclosing(){
		return mEnclosing;
	}
	
	public void printDict(){
		for(String s: mDict.keySet())
			System.out.println("\t" + s);
	}
}
