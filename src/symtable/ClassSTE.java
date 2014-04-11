package symtable;

public class ClassSTE extends NamedScopeSTE{
	private boolean mMain = false;
	private String mSuperClass = null;
	
	public ClassSTE(String name, Scope enclosing) {
		super(name, enclosing);
		
	}
	
	public ClassSTE(String name) {
		super(name);
		
	}

}
