package symtable;

public class ClassSTE extends NamedScopeSTE{
	private boolean mMain;
	private String mSuperClass;
	private Scope mScope;
	
	public ClassSTE(String name) {
		super(name);
		
	}

}
