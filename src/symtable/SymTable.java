package symtable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

import ast.node.Node;

/** 
 * SymTable
 * ....
 * The symbol table also keeps a mapping of expression nodes to
 * types because that information is needed elsewhere especially
 * when looking up method call information.
 * 
 * @author mstrout
 * WB: Simplified to only expression types
 */
public class SymTable {
    private final HashMap<Node,Type> mExpType = new HashMap<Node,Type>();
	private Scope mGlobalScope = null;
	private Scope mScope;
    private Deque<Scope> mScopeStack;

    public SymTable() {
    	mScopeStack = new ArrayDeque<Scope>();
    	mScope = new Scope();
    	mScopeStack.push(mScope);
    }
    
    /**
     * For first time a named scope is created
     * like a MethodSTE, or ClassSTE. 
     */
    public void insertAndPushScope(NamedScopeSTE ste){
    	insert(ste);
    	mScopeStack.push(ste.getScope());
    	printStack();
    }
    
    /** Lookup a symbol in this symbol table.
     * Starts looking in innermost scope and then
     * look in enclosing scopes.
     * Returns null if the symbol is not found.
     */
    public STE lookup(String sym) {
        return mScope.lookup(sym);
    }

    /** Lookup a symbol in innermost scope only.
     * return null if the symbol is not found
     */
    public STE lookupInnermost(String sym) {
    		Scope currentScope = mScopeStack.peek();
    		return currentScope.lookupInnermost(sym);
    }

    /** When inserting an STE will just insert
     * it into the scope at the top of the scope stack.
     */
    public void insert(STE ste) {
    	mScopeStack.peek().insert(ste);
    }
    
    /** 
     * Lookup the given method scope and make it the innermost
     * scope.  IOW make it the top of the scope stack.
     */
    public void pushScope(String id) {
    	STE ste = lookup(id);
    	if(ste instanceof NamedScopeSTE)
    		mScopeStack.push(((NamedScopeSTE) ste).getScope());
    }
    
    public void popScope() {
        mScopeStack.pop();
    }
    
    public void setExpType(Node exp, Type t)
    {
    	this.mExpType.put(exp, t);
    }
    
    public Type getExpType(Node exp)
    {
    	return this.mExpType.get(exp);
    }
    
    public Scope getGlobalScope(){
    	return mGlobalScope;
    }
    
    public void printStack(){
    	System.out.println("####### Scope Stack #######");
    	for(Scope s: mScopeStack)
    		s.printDict();
    }
   
/*
 */

}
