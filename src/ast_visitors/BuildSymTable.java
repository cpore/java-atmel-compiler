package ast_visitors;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import exceptions.SemanticException;
import symtable.ClassSTE;
import symtable.MethodSTE;
import symtable.SymTable;
import symtable.Type;
import symtable.VarSTE;
import ast.node.AssignStatement;
import ast.node.CallExp;
import ast.node.CallStatement;
import ast.node.ChildClassDecl;
import ast.node.ClassType;
import ast.node.Formal;
import ast.node.IClassDecl;
import ast.node.IExp;
import ast.node.IStatement;
import ast.node.MainClass;
import ast.node.MethodDecl;
import ast.node.NewExp;
import ast.node.Node;
import ast.node.Program;
import ast.node.ThisLiteral;
import ast.node.TopClassDecl;
import ast.node.VarDecl;
import ast.visitor.DepthFirstVisitor;

public class BuildSymTable extends DepthFirstVisitor{
	
	private SymTable mCurrentST;
	private LinkedList<SemanticException> doubleDef;
	
	public BuildSymTable(){
		mCurrentST = new symtable.SymTable();
		doubleDef = new LinkedList<SemanticException>();
	}
	
	public SymTable getSymTable(){
		return mCurrentST;
	}
	
	public void defaultIn(Node node)
    {
        // Do nothing
    }

    public void defaultOut(Node node)
    {
        // Do nothing
    }

    public void inAssignStatement(AssignStatement node)
    {
        defaultIn(node);
    }

    public void outAssignStatement(AssignStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void visitAssignStatement(AssignStatement node)
    {
        inAssignStatement(node);
        if(node.getExp() != null)
        {
            node.getExp().accept(this);
        }
        outAssignStatement(node);
    }

    public void inCallExp(CallExp node)
    {
        defaultIn(node);
    }

    public void outCallExp(CallExp node)
    {
        defaultOut(node);
    }

    @Override
    public void visitCallExp(CallExp node)
    {
        inCallExp(node);
        if(node.getExp() != null)
        {
            node.getExp().accept(this);
        }
        {
            List<IExp> copy = new ArrayList<IExp>(node.getArgs());
            for(IExp e : copy)
            {
                e.accept(this);
            }
        }
        outCallExp(node);
    }

    public void inCallStatement(CallStatement node)
    {
        defaultIn(node);
    }

    public void outCallStatement(CallStatement node)
    {
        defaultOut(node);
    }

    @Override
    public void visitCallStatement(CallStatement node)
    {
        inCallStatement(node);
        if(node.getExp() != null)
        {
            node.getExp().accept(this);
        }
        {
            List<IExp> copy = new ArrayList<IExp>(node.getArgs());
            for(IExp e : copy)
            {
                e.accept(this);
            }
        }
        outCallStatement(node);
    }

    public void inChildClassDecl(ChildClassDecl node)
    {
        defaultIn(node);
    }

    public void outChildClassDecl(ChildClassDecl node)
    {
        defaultOut(node);
    }

    @Override
    public void visitChildClassDecl(ChildClassDecl node)
    {
        inChildClassDecl(node);
        {
            List<VarDecl> copy = new ArrayList<VarDecl>(node.getVarDecls());
            for(VarDecl e : copy)
            {
                e.accept(this);
            }
        }
        {
            List<MethodDecl> copy = new ArrayList<MethodDecl>(node.getMethodDecls());
            for(MethodDecl e : copy)
            {
                e.accept(this);
            }
        }
        outChildClassDecl(node);
    }

    public void inClassType(ClassType node)
    {
        defaultIn(node);
    }

    public void outClassType(ClassType node)
    {
    	// add class type to type map
        Type.setClassType(node.getName());
    }

    @Override
    public void visitClassType(ClassType node)
    {
        inClassType(node);
        outClassType(node);
    }

    public void inFormal(Formal node)
    {
        defaultIn(node);
    }

    public void outFormal(Formal node)
    {
    	
    	VarSTE vste = new VarSTE(node.getName());
    	if(mCurrentST.lookupInnermost(vste.getName()) != null){
    		doubleDef.add(new SemanticException(
					"Redefined symbol " + vste.getName(),
					node.getLine(),
					node.getPos()));
    		
    		/*throw new SemanticException(
					"Redefined symbol " + vste.getName(),
					node.getLine(),
					node.getPos());*/
        }
        vste.setType(node.getType());
        System.out.println("FORMAL NAME: " + vste.getName());
        System.out.println("FORMAL TYPE: " + vste.getType());
        vste.setIsParam(true);
        //Type.setClassType(node.getName());
    	mCurrentST.insert(vste);
    }

    @Override
    public void visitFormal(Formal node)
    {
        inFormal(node);
        if(node.getType() != null)
        {
            node.getType().accept(this);
        }
        outFormal(node);
    }

    public void inMainClass(MainClass node)
    {
        defaultIn(node);
    }

    public void outMainClass(MainClass node)
    {
        defaultOut(node);
    }

    @Override
    public void visitMainClass(MainClass node)
    {
        inMainClass(node);
        if(node.getStatement() != null)
        {
            node.getStatement().accept(this);
        }
        outMainClass(node);
    }

    
    public void inMethodDecl(MethodDecl node)
    {
        MethodSTE mste = new MethodSTE(node.getName(), mCurrentST.peek());
        // create a list of formal types
        LinkedList<Formal> list = node.getFormals();
        for(int i=0; i<list.size(); i++){
        	mste.addFormal(list.get(i).getType());
        	//System.out.println(list.get(i).getName() +":" + mste.getSignature().get(i));
        }
        
        // TODO create a signature: formatTypes -> return type
        
        // create a methodSTE with class&method name, node, signature
        mste.setClassName(mCurrentST.innermostId());
        mste.setReturnType(node.getType());
        //System.out.println("RETURN TYPE = " + mste.getReturnType());
        
        if(mCurrentST.lookupInnermost(mste.getName()) != null){
        	doubleDef.add(new SemanticException(
					"Redefined symbol " + mste.getName(),
					node.getLine(),
					node.getPos()));
        	/*throw new SemanticException(
					"Redefined symbol " + mste.getName(),
					node.getLine(),
					node.getPos());*/
        }
        //insert it and pushScope
        mCurrentST.insertAndPushScope(mste);
        //create and entry for "this"
        VarSTE thisRef = new VarSTE("this");
        thisRef.setIsLocal(true);
        mCurrentST.insert(thisRef);
    }

    public void outMethodDecl(MethodDecl node)
    {
        mCurrentST.popScope();
    }

    @Override
    public void visitMethodDecl(MethodDecl node)
    {
        inMethodDecl(node);
        if(node.getType() != null)
        {
            node.getType().accept(this);
        }
        {
            List<Formal> copy = new ArrayList<Formal>(node.getFormals());
            for(Formal e : copy)
            {
                e.accept(this);
            }
        }
        {
            List<VarDecl> copy = new ArrayList<VarDecl>(node.getVarDecls());
            for(VarDecl e : copy)
            {
                e.accept(this);
            }
        }
        {
            List<IStatement> copy = new ArrayList<IStatement>(node.getStatements());
            for(IStatement e : copy)
            {
                e.accept(this);
            }
        }
        if(node.getExp() != null)
        {
            node.getExp().accept(this);
        }
        outMethodDecl(node);
    }

    public void inNewExp(NewExp node)
    {
        defaultIn(node);
    }

    public void outNewExp(NewExp node)
    {
        defaultOut(node);
    }

    @Override
    public void visitNewExp(NewExp node)
    {
        inNewExp(node);
        outNewExp(node);
    }

    public void inProgram(Program node)
    {
        defaultIn(node);
    }

    public void outProgram(Program node)
    {
        if(doubleDef.size() >0){
        	for(SemanticException se : doubleDef){
        		System.out.println(se.getMessage());
        	}
        	throw new SemanticException("Errors found while building symbol table");
        }
    	defaultOut(node);
    }

    @Override
    public void visitProgram(Program node)
    {
        inProgram(node);
        if(node.getMainClass() != null)
        {
            node.getMainClass().accept(this);
        }
        {
            List<IClassDecl> copy = new ArrayList<IClassDecl>(node.getClassDecls());
            for(IClassDecl e : copy)
            {
                e.accept(this);
            }
        }
        outProgram(node);
    }

    public void inThisExp(ThisLiteral node)
    {
        defaultIn(node);
    }

    public void outThisExp(ThisLiteral node)
    {
        defaultOut(node);
    }

    @Override
    public void visitThisLiteral(ThisLiteral node)
    {
        inThisExp(node);
        outThisExp(node);
    }

    public void inTopClassDecl(TopClassDecl node)
    {
        ClassSTE cste = new ClassSTE(node.getName(), mCurrentST.peek());
    	
        if(mCurrentST.lookupInnermost(cste.getName()) != null){
        	doubleDef.add(new SemanticException(
					"Redefined symbol " + cste.getName(),
					node.getLine(),
					node.getPos()));
        	
        	/*throw new SemanticException(
					"Redefined symbol " + cste.getName(),
					node.getLine(),
					node.getPos());*/
        }
        mCurrentST.insertAndPushScope(cste);
    }

    public void outTopClassDecl(TopClassDecl node)
    {
        mCurrentST.popScope();
    }

    @Override
    public void visitTopClassDecl(TopClassDecl node)
    {
        inTopClassDecl(node);
        {
            List<VarDecl> copy = new ArrayList<VarDecl>(node.getVarDecls());
            for(VarDecl e : copy)
            {
                e.accept(this);
            }
        }
        {
            List<MethodDecl> copy = new ArrayList<MethodDecl>(node.getMethodDecls());
            for(MethodDecl e : copy)
            {
                e.accept(this);
            }
        }
        outTopClassDecl(node);
    }

    public void inVarDecl(VarDecl node)
    {
    	defaultIn(node);
    }

    public void outVarDecl(VarDecl node)
    {
    	VarSTE vste = new VarSTE(node.getName(), node.getType());
    	System.out.println("VSTE NAME=" + vste.getName());
    	System.out.println("VSTE TYPE=" + vste.getType());
    	if(node.parent() instanceof TopClassDecl)
    		vste.setIsMember(true);
    	if(node.parent() instanceof MethodDecl)
    		vste.setIsLocal(true);
    	mCurrentST.insert(vste);
    }

    @Override
    public void visitVarDecl(VarDecl node)
    {
        inVarDecl(node);
        if(node.getType() != null)
        {
            node.getType().accept(this);
        }
        outVarDecl(node);
    }

}
