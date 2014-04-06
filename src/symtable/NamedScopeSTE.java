package symtable;

public class NamedScopeSTE extends STE{
	private Scope mScope;
	
	public NamedScopeSTE(String name) {
		super(name);
		mScope = new Scope();
		
	}
	
	public STE lookup(String sym) {
		return mScope.lookup(sym);
    }
	
	public Scope getScope(){
		return mScope;
	}
	
	public Scope get(){
		return mScope;
	}

}
