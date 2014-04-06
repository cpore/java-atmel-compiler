package symtable;

public class ClassSTE extends NamedScopeSTE{
	private boolean mMain = false;
	private String mSuperClass = null;
	
	public ClassSTE(String name) {
		super(name);
		
	}

}
