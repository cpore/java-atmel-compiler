package symtable;

public class ClassSTE extends NamedScopeSTE{
	private boolean mMain = false;
	private String mSuperClass = null;
	private int classSize = 0;
	
	public ClassSTE(String name, Scope enclosing) {
		super(name, enclosing);
		
	}
	
	public ClassSTE(String name) {
		super(name);
		
	}
	
	public void setClassSize(int n){
		classSize = n;
	}
	
	public int getClassSize(){
		return classSize;
	}

}
