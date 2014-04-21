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
	
	public Scope(String id, Scope enclosing){ 
		mDict = new HashMap<String, STE>();
		parentId = id;
		mEnclosing = enclosing;
	}

	public STE lookup(String Sym){
		STE ste = mDict.get(Sym);
		if(ste != null)
			return ste;

		//ste = mEnclosing.lookup(Sym);
		//STE ste = mDict.get(sym);
		//if(ste != null)
		//	return ste;

		//ste = mEnclosing.lookup(Sym);
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
	
	public STE lookupEnclosing(String Sym){
		STE ste = mDict.get(Sym);
		
		if(ste != null)
			return ste;

		return mEnclosing.lookupEnclosing(Sym);
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

	public Scope getEnclosing(){
		return mEnclosing;
	}
	
	public void printDict(){
		for(String s: mDict.keySet())
			System.out.println("\t" + s);
	}
	
	public HashMap<String,STE> getDict(){
		return mDict;
	}

	public STE search(String sym) {
		STE ste = mDict.get(sym);
		if(ste != null)
			return ste;

		//ste = mEnclosing.lookup(Sym);
		// look through named scopes if not in this scope
		for(STE s: mDict.values()){
			if(s instanceof NamedScopeSTE){
				ste = ((NamedScopeSTE) s).lookup(sym);
				if(ste != null)
					break;
			}
		}

		return ste;
	}
}
