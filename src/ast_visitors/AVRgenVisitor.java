/**
 * This ast walker generates AVR assembly for the AST.  
 *
 * 3/4/14 Casey Pore
 */
package ast_visitors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import ast.visitor.DepthFirstVisitor;
import ast.node.*;

import java.util.*;

import symtable.ClassSTE;
import symtable.MethodSTE;
import symtable.SymTable;
import symtable.Type;
import symtable.VarSTE;
import label.Label;

public class AVRgenVisitor extends DepthFirstVisitor {
	private PrintWriter out;
	private SymTable mCurrentST;
	private int currReg = 25;
	private boolean debug = false;

	/** Constructor takes a PrintWriter, and stores in instance var. */
	public AVRgenVisitor(PrintWriter out, SymTable st) {
		this.out = out;
		mCurrentST = st;
		if(debug){
			System.out.println("----------------Starting AVRgenVistor--------------------");
			mCurrentST.printScopes();
		}
	}

	public void defaultIn(Node node)
	{
		// Do nothing
	}

	public void defaultOut(Node node)
	{
		// Do nothing
	}

	public void inAndExp(AndExp node)
	{
		defaultIn(node);
	}

	public void outAndExp(AndExp node)
	{

	}

	@Override
	public void visitAndExp(AndExp node)
	{
		inAndExp(node);

		out.println("    #### short-circuited && operation");
	    out.println("    # &&: left operand");
	 	out.println();
	 	out.flush();
		// get test expression
		if(node.getLExp() != null)
		{
			node.getLExp().accept(this);
		}
		
		//Get necessary labels
		String goodLeft = new Label().toString();
		String badLeft = new Label().toString();
		out.println("    # &&: if left operand is false do not eval right");
		out.println("    # load a one byte expression off stack");
		out.println("    pop    r24");
		out.println("    # push one byte expression onto stack");
		out.println("    push   r24");

		out.println("    #load zero into reg");
		out.println("    ldi    r25, 0");
		out.println();

		out.println("    #use cp to set SREG");
		out.println("    cp     r24, r25");
		out.println("    #WANT breq " + goodLeft);
		out.println("    brne   " + badLeft);
		out.println("    jmp    " + goodLeft);
		out.println();

		out.println(badLeft + ":");
		out.println("    # right operand");
		out.println("    # load a one byte expression off stack");
		out.println("    pop    r24");
		out.println();
		
		out.flush();
		
		if(node.getRExp() != null)
		{
			node.getRExp().accept(this);
		}

		out.println("    # load a one byte expression off stack");
		out.println("    pop    r24");
		out.println("    # push one byte expression onto stack");
		out.println("    push   r24");
		
		out.println(goodLeft + ":");
		out.println();
		out.flush();
		outAndExp(node);
	}

	public void inArrayAssignStatement(ArrayAssignStatement node)
	{
		defaultIn(node);
	}

	public void outArrayAssignStatement(ArrayAssignStatement node)
	{
		defaultOut(node);
	}

	@Override
	public void visitArrayAssignStatement(ArrayAssignStatement node)
	{
		inArrayAssignStatement(node);
		if(node.getIdLit() != null)
		{
			node.getIdLit().accept(this);
		}
		if(node.getIndex() != null)
		{
			node.getIndex().accept(this);
		}
		if(node.getExp() != null)
		{
			node.getExp().accept(this);
		}
		outArrayAssignStatement(node);
	}

	public void inArrayExp(ArrayExp node)
	{
		defaultIn(node);
	}

	public void outArrayExp(ArrayExp node)
	{
		defaultOut(node);
	}

	@Override
	public void visitArrayExp(ArrayExp node)
	{
		inArrayExp(node);
		if(node.getExp() != null)
		{
			node.getExp().accept(this);
		}
		if(node.getIndex() != null)
		{
			node.getIndex().accept(this);
		}
		outArrayExp(node);
	}

	public void inAssignStatement(AssignStatement node)
	{
		defaultIn(node);
	}

	public void outAssignStatement(AssignStatement node)
	{
		VarSTE vste = (VarSTE) mCurrentST.lookupEnclosing(node.getId());
		
		out.println("    ### AssignStatement");
		out.println("    # load rhs exp");
		Type expType = mCurrentST.getExpType(node.getExp());
		if(expType.getAVRTypeSize() == 2){
			loadLInt();
		}else if(expType.getAVRTypeSize() == 1){
			loadLByte();
		}
		
		if(vste.isMember()){
			out.println();
			out.println("    # loading the implicit \"this\"");
			out.println();
			out.println("    # load a two byte variable from base+offset");
			out.println("    ldd    r31, Y + 2");
			out.println("    ldd    r30, Y + 1");
		}
		
		// TODO VSTE WIDTHS ARE NOT SET CORRECTLY!!!!
		out.println("    # store rhs into var " + vste.getName());
		if(vste.getWidth() == 2){
			out.println("    std    " + vste.getbase() + " + " + (vste.getOffset()+1) + ", r25");
			out.println("    std    "  + vste.getbase() + " + " + (vste.getOffset()) + ", r24");
			
		}else if(vste.getWidth() == 1){
			out.println("    std    "  + vste.getbase() + " + " + (vste.getOffset()) + ", r24");
		}

		if(debug){
			System.out.print("ASSIGN TYPE: " + expType + " To Variable: " + vste.getName() + " of TYPE: " + vste.getType());
			if(vste.isMember())
				System.out.println(" IS MEMBER");
			else
				System.out.println();
		}

		out.println();
		out.flush();
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

	public void inBlockStatement(BlockStatement node)
	{
		defaultIn(node);
	}

	public void outBlockStatement(BlockStatement node)
	{
		defaultOut(node);
	}

	@Override
	public void visitBlockStatement(BlockStatement node)
	{
		inBlockStatement(node);
		{
			List<IStatement> copy = new ArrayList<IStatement>(node.getStatements());
			for(IStatement e : copy)
			{
				e.accept(this);
			}
		}
		outBlockStatement(node);
	}

	public void inBoolType(BoolType node)
	{
		defaultIn(node);
	}

	public void outBoolType(BoolType node)
	{
		defaultOut(node);
	}

	@Override
	public void visitBoolType(BoolType node)
	{
		inBoolType(node);
		outBoolType(node);
	}

	public void inButtonExp(ButtonLiteral node)
	{
		defaultIn(node);
	}

	public void outButtonExp(ButtonLiteral node)
	{
		// I think buttons are the only thing that don't go
		// on the stack.
		/*
		// This code assumes that node.getIntValue() will fit into
		// A byte. Do we need to check for this in the type checker?
		out.println("    # Load Button expression from Symbol");
		out.println("    ldi    r24, " + node.getIntValue());
		out.println("    # push one byte expression onto stack");
		out.println("    push   r24");
		out.println();
		out.flush();*/
	}

	@Override
	public void visitButtonLiteral(ButtonLiteral node)
	{
		inButtonExp(node);
		outButtonExp(node);
	}

	public void inButtonType(ButtonType node)
	{
		defaultIn(node);
	}

	public void outButtonType(ButtonType node)
	{
		defaultOut(node);
	}

	@Override
	public void visitButtonType(ButtonType node)
	{
		inButtonType(node);
		outButtonType(node);
	}

	public void inByteCast(ByteCast node)
	{
		defaultIn(node);
	}

	public void outByteCast(ByteCast node)
	{
		if(mCurrentST.getExpType(node.getExp()) == Type.BYTE){
			// Do nothing
			return;
		}
		out.println("    # Casting int to byte by popping");
		out.println("    # 2 bytes off stack and only pushing low order bits");
		out.println("    # back on.  Low order bits are on top of stack.");
		out.println("    pop    r24");
		out.println("    pop    r25");
		out.println("    push   r24");
		out.println();
		out.flush();
	}

	@Override
	public void visitByteCast(ByteCast node)
	{
		inByteCast(node);
		if(node.getExp() != null)
		{
			node.getExp().accept(this);
		}
		outByteCast(node);
	}

	public void inByteType(ByteType node)
	{
		defaultIn(node);
	}

	public void outByteType(ByteType node)
	{
		defaultOut(node);
	}

	@Override
	public void visitByteType(ByteType node)
	{
		inByteType(node);
		outByteType(node);
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
		//get scope for exp
		//find scope of class called
		Type expType = mCurrentST.getExpType(node.getExp());
		if(debug)
			System.out.println("CALL TYPE " + expType);
		ClassSTE classSTE = (ClassSTE) mCurrentST.getClassSTE(expType);
		
		MethodSTE mste = (MethodSTE) classSTE.lookupEnclosing(node.getId());
		
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
		
		out.println("    #### function call");
		out.println("    # put parameter values into appropriate registers");
		
		int regNum = 24;
		for(Type type: mste.getSignature()){
			regNum -= 2;
		}
		
		LinkedList<IExp> args = node.getArgs();
		Collections.reverse(args);
		for(IExp exp: args){
			Type type = mCurrentST.getExpType(exp);
			if(type.getAVRTypeSize() == 1){
				out.println("    # load a one byte expression off stack");
				out.println("    pop    r" + regNum);
			}else if(type.getAVRTypeSize() == 2){
				out.println("    # load a two byte expression off stack");
				out.println("    pop    r" + (regNum));
				out.println("    pop    r" + (regNum+1));
			}
			regNum += 2;
		}
		/*ArrayList<Type> sig = mste.getSignature();
		Collections.reverse(sig);
		for(Type type: sig){
			if(type.getAVRTypeSize() == 1){
				out.println("    # load a one byte expression off stack");
				out.println("    pop    r" + regNum);
			}else if(type.getAVRTypeSize() == 2){
				out.println("    # load a two byte expression off stack");
				out.println("    pop    r" + (regNum));
				out.println("    pop    r" + (regNum+1));
			}
			regNum += 2;
		}*/

		// handling "this"
		out.println("    # receiver will be passed as first param");
		out.println("    # load a two byte expression off stack");
		out.println("    pop    r24");
		out.println("    pop    r25");
		out.println();

		out.println("    call    " + mste.getAVRLabel());
		out.println();
		// TODO handle return value here
		if(mste.getReturnType().getAVRTypeSize() == 2){
			out.println("    # handle return value");
			out.println("    # push two byte expression onto stack");
			out.println("    push   r25");
			out.println("    push   r24");
		}else if(mste.getReturnType().getAVRTypeSize() == 0){
			out.println("    # no return value");
		}else{
			out.println("    # handle return value");
			out.println("    # push one byte expression onto stack");
			out.println("    push   r24");
		}
		out.println();
		out.flush();
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

		//get scope for exp
		//find scope of class called
		Type expType = mCurrentST.getExpType(node.getExp());
		if(debug )
			System.out.println("CALL TYPE " + expType);
		ClassSTE classSTE = (ClassSTE) mCurrentST.getClassSTE(expType);
		
		MethodSTE mste = (MethodSTE) classSTE.lookupEnclosing(node.getId());
		
		if(mste == null)
			System.out.println("Can't find " + node.getId() + " in Symbol table");
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
		out.println("    #### function call");
		out.println("    # put parameter values into appropriate registers");

		int regNum = 24;
		for(Type type: mste.getSignature()){
			regNum -= 2;
		}
		
		LinkedList<IExp> args = node.getArgs();
		Collections.reverse(args);
		for(IExp exp: args){
			Type type = mCurrentST.getExpType(exp);
			if(type.getAVRTypeSize() == 1){
				out.println("    # load a one byte expression off stack");
				out.println("    pop    r" + regNum);
			}else if(type.getAVRTypeSize() == 2){
				out.println("    # load a two byte expression off stack");
				out.println("    pop    r" + (regNum));
				out.println("    pop    r" + (regNum+1));
			}
			regNum += 2;
		}
		/*ArrayList<Type> sig = mste.getSignature();
		Collections.reverse(sig);
		for(Type type: sig){
			if(type.getAVRTypeSize() == 1){
				out.println("    # load a one byte expression off stack");
				out.println("    pop    r" + regNum);
			}else if(type.getAVRTypeSize() == 2){
				out.println("    # load a two byte expression off stack");
				out.println("    pop    r" + (regNum));
				out.println("    pop    r" + (regNum+1));
			}
			regNum += 2;
		}*/

		// handling "this"
		out.println("    # receiver will be passed as first param");
		out.println("    # load a two byte expression off stack");
		out.println("    pop    r24");
		out.println("    pop    r25");
		out.println();

		out.println("    call    " + mste.getAVRLabel());
		out.println();
		// TODO handle return value here
		/*if(mste.getReturnType().getAVRTypeSize() == 2){
			out.println("    # handle return value");
			out.println("    # push two byte expression onto stack");
			out.println("    push   r25");
			out.println("    push   r24");
		}else if(mste.getReturnType().getAVRTypeSize() == 0){
			out.println("    # no return value");
		}else{
			out.println("    # handle return value");
			out.println("    # push one byte expression onto stack");
			out.println("    push   r24");
		}*/
		out.println();
		out.flush();
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
		defaultOut(node);
	}

	@Override
	public void visitClassType(ClassType node)
	{
		inClassType(node);
		outClassType(node);
	}

	public void inColorExp(ColorLiteral node)
	{
		defaultIn(node);
	}

	public void outColorExp(ColorLiteral node)
	{
		// This code assumes that node.getIntValue() will fit into
		// A byte. Do we need to check for this in the type checker?
		out.println("    # Color expression " + node.getLexeme());
		out.println("    ldi    r22," + node.getIntValue());
		out.println("    # push one byte expression onto stack");
		out.println("    push   r22");
		out.println();
		out.flush();
	}

	@Override
	public void visitColorLiteral(ColorLiteral node)
	{

		inColorExp(node);

		outColorExp(node);
	}

	public void inColorArrayType(ColorArrayType node)
	{
		defaultIn(node);
	}

	public void outColorArrayType(ColorArrayType node)
	{
		defaultOut(node);
	}

	public void visitColorArrayType(ColorArrayType node)
	{
		inColorArrayType(node);
		outColorArrayType(node);
	}

	public void inColorType(ColorType node)
	{
		defaultIn(node);
	}

	public void outColorType(ColorType node)
	{
		defaultOut(node);
	}

	public void visitColorType(ColorType node)
	{
		inColorType(node);
		outColorType(node);
	}

	public void inEqualExp(EqualExp node)
	{
		defaultIn(node);
	}

	public void outEqualExp(EqualExp node)
	{

	}

	@Override
	public void visitEqualExp(EqualExp node)
	{
		inEqualExp(node);
		if(node.getLExp() != null)
		{
			node.getLExp().accept(this);
		}


		if(node.getRExp() != null)
		{
			node.getRExp().accept(this);
		}

		Type rType = mCurrentST.getExpType(node.getRExp());
		if(rType == Type.BYTE ||
				rType == Type.BOOL ||
				rType == Type.COLOR){
			loadRByte();
			promoteRByte();
		}
		else
			loadRInt();

		Type lType = mCurrentST.getExpType(node.getLExp());
		if(lType == Type.BYTE ||
				lType == Type.BOOL ||
				lType == Type.COLOR){
			loadLByte();
			promoteLByte();
		}
		else
			loadLInt();

		//Get necessary labels
		String equalLbl = new Label().toString();
		String notEqualLbl = new Label().toString();
		out.println("    cp     r24, r18");//Compare
		//out.println("    brne " + notEqualLbl);
		out.println("    cpc    r25, r19");//Compare with carry
		out.println("    #Branch if not equals");//Aka Z = 0;
		out.println("    brne " + notEqualLbl);
		out.println("    ldi    r24, 1");
		out.println("    push   r24");//push True
		out.println("    jmp    " + equalLbl);
		out.println(notEqualLbl + ":");
		out.println("    ldi    r24, 0");
		out.println("    push   r24");//push False
		out.println(equalLbl + ":");
		out.println();//Done
		//The next command will pop to read the value.

		out.flush();
		outEqualExp(node);
	}


	public void inFalseExp(FalseLiteral node)
	{
		defaultIn(node);
	}

	public void outFalseExp(FalseLiteral node)
	{
		out.println("    # False/0 expression");
		out.println("    ldi    r22, " + node.getIntValue());
		out.println("    # push one byte expression onto stack");
		out.println("    push   r22");
		out.println();
		out.flush();
		defaultOut(node);
	}

	@Override
	public void visitFalseLiteral(FalseLiteral node)
	{
		inFalseExp(node);
		outFalseExp(node);
	}

	public void inFormal(Formal node)
	{
		defaultIn(node);
	}

	public void outFormal(Formal node)
	{
		// offset for formals
		VarSTE vste = (VarSTE) mCurrentST.lookupInnermost(node.getName());
		
		if(vste.getWidth() == 2){
			int offsetplusone = vste.getOffset()+1;
			out.println("    std    " + vste.getbase() + " + " + offsetplusone + ", r" + currReg);
			out.println("    std    " + vste.getbase() + " + " + (vste.getOffset()) + ", r" + (currReg-1));
		}else{
			out.println("    std    " + vste.getbase() + " + " + (vste.getOffset()) + ", r" + (currReg-1));
		}
		currReg -= 2;
		out.flush();
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

	public void inIdLiteral(IdLiteral node)
	{
		
		out.println("    # IdExp");
		out.println("    # load value for variable " + node.getLexeme());
		VarSTE vste = (VarSTE) mCurrentST.lookupEnclosing(node.getLexeme());
		
		if(vste.isMember()){
			out.println();
			out.println("    # loading the implicit \"this\"");
			out.println();
			out.println("    # load a two byte variable from base+offset");
			out.println("    ldd    r31, Y + 2");
			out.println("    ldd    r30, Y + 1");
			out.println("    # variable is a member variable");
			out.println();
			
			if(vste.getWidth() == 2){
				out.println("    # load a two byte variable from base+offset");
				out.println("    ldd    r25, " + vste.getbase() + " + " + (vste.getOffset()+1));
				out.println("    ldd    r24, " + vste.getbase() + " + " + (vste.getOffset()));
				out.println("    # push two byte expression onto stack");
				out.println("    push   r25");
				out.println("    push   r24");
			}else if(vste.getWidth() == 1){
				out.println("    # load a one byte variable from base+offset");
				out.println("    ldd    r24, " + vste.getbase() + " + " + (vste.getOffset()));
				out.println("    # push one byte expression onto stack");
				out.println("    push   r24");
			}
		}else{

			out.println("    # variable is a local or param variable");
			out.println();

			if(vste.getWidth() == 2){
				out.println("    # load a two byte variable from base+offset");
				out.println("    ldd    r25, " + vste.getbase() + " + " + (vste.getOffset()+1));
				out.println("    ldd    r24, " + vste.getbase() + " + " + (vste.getOffset()));
				out.println("    # push two byte expression onto stack");
				out.println("    push   r25");
				out.println("    push   r24");
			}else if(vste.getWidth() == 1){
				out.println("    # load a one byte variable from base+offset");
				out.println("    ldd    r24, " + vste.getbase() + " + " + (vste.getOffset()));
				out.println("    # push one byte expression onto stack");
				out.println("    push   r24");
			}
		}
		currReg -= 2;
		out.println();
		out.flush();
	}

	public void outIdLiteral(IdLiteral node)
	{
	}

	@Override
	public void visitIdLiteral(IdLiteral node)
	{
		inIdLiteral(node);
		outIdLiteral(node);
	}

	public void inIfStatement(IfStatement node)
	{
		defaultIn(node);
	}

	public void outIfStatement(IfStatement node)
	{
		defaultOut(node);
	}

	@Override
	public void visitIfStatement(IfStatement node)
	{
		//Get necessary labels
		String elseLbl = new Label().toString();
		String thenLbl = new Label().toString();
		String doneLbl = new Label().toString();

		inIfStatement(node);

		out.println("    #### if statement");
		out.println();
		out.println();
		// get test expression
		if(node.getExp() != null)
		{
			node.getExp().accept(this);
		}
		out.println("    # load condition and branch if false");
		out.println("    # load a one byte expression off stack");
		out.println("    pop    r24");
		out.println("    #load zero into reg");
		// notice that we are comparing r24 to FALSE
		out.println("    ldi    r25, 0");
		out.println();

		out.println("    #use cp to set SREG");
		out.println("    cp     r24, r25");
		out.println("    #WANT breq " + elseLbl);
		out.println("    brne   " + thenLbl);
		out.println("    jmp    " + elseLbl);
		out.println();

		out.println("    # then label for if");
		out.println(thenLbl + ":");
		out.println();
		out.flush();
		//write then code
		if(node.getThenStatement() != null)
		{
			node.getThenStatement().accept(this);
		}
		out.println("    jmp    " + doneLbl);
		out.println();
		out.println("    # else label for if");
		out.println(elseLbl + ":");
		out.println();
		out.flush();
		//write else code
		if(node.getElseStatement() != null)
		{
			node.getElseStatement().accept(this);
		}

		out.println("    # done label for if");
		out.println(doneLbl + ":");
		out.println();
		out.flush();
		outIfStatement(node);

	}

	public void inIntArrayType(IntArrayType node)
	{
		defaultIn(node);
	}

	public void outIntArrayType(IntArrayType node)
	{
		defaultOut(node);
	}

	@Override
	public void visitIntArrayType(IntArrayType node)
	{
		inIntArrayType(node);
		outIntArrayType(node);
	}

	public void inIntegerExp(IntLiteral node)
	{
		defaultIn(node);
	}

	public void outIntegerExp(IntLiteral node)
	{
		out.println("    # Load constant int " + node.getIntValue());
		out.println("    ldi    r24,lo8("+node.getIntValue()+")");
		out.println("    ldi    r25,hi8("+node.getIntValue()+")");
		out.println("    # push two byte expression onto stack");
		out.println("    push   r25");
		out.println("    push   r24");
		out.println();
		out.flush();
	}

	@Override
	public void visitIntLiteral(IntLiteral node)
	{
		inIntegerExp(node);
		outIntegerExp(node);
	}

	public void inIntType(IntType node)
	{
		defaultIn(node);
	}

	public void outIntType(IntType node)
	{
		defaultOut(node);
	}

	@Override
	public void visitIntType(IntType node)
	{
		inIntType(node);
		outIntType(node);
	}

	public void inLengthExp(LengthExp node)
	{
		defaultIn(node);
	}

	public void outLengthExp(LengthExp node)
	{
		defaultOut(node);
	}

	@Override
	public void visitLengthExp(LengthExp node)
	{
		inLengthExp(node);
		if(node.getExp() != null)
		{
			node.getExp().accept(this);
		}
		outLengthExp(node);
	}

	public void inLtExp(LtExp node)
	{
		defaultIn(node);
	}

	public void outLtExp(LtExp node)
	{
		defaultOut(node);
	}

	@Override
	public void visitLtExp(LtExp node)
	{
		inLtExp(node);
		String ltLbl = new Label().toString();
		String resultLbl = new Label().toString();
		if(node.getLExp() != null)
		{
			node.getLExp().accept(this);
		}
		if(node.getRExp() != null)
		{
			node.getRExp().accept(this);
		}

		if(mCurrentST.getExpType(node.getRExp()) == Type.BYTE){
			loadRByte();
			//promoteRByte();
		}
		else
			loadRInt();

		if(mCurrentST.getExpType(node.getLExp()) == Type.BYTE){
			loadLByte();
			//promoteLByte();
		}
		else
			loadLInt();

		//Get necessary labels
		
		out.println("    cp    r24, r18");//Compare
		//out.println("    cpc   r25, r19");//Compare with carry
		out.println("    #Branch if less than");//Aka Z = 0;
		out.println("    brlt " + ltLbl);
		out.println();
		out.println("    # load false");
		out.println("    ldi    r24, 0");
		out.println("    jmp    " + resultLbl);
		out.println();
		out.println("    # load true ");
		out.println(ltLbl + ":");
		out.println("    ldi    r24, 1");
		out.println("    # push result of less than");
		out.println(resultLbl + ":");
		out.println("    # push one byte expression onto stack");
		out.println("    push   r24");
		out.println();//Done
		//The next command will pop to read the value.

		out.flush();
		outLtExp(node);
	}

	public void inMainClass(MainClass node)
	{
		defaultIn(node);
	}

	public void outMainClass(MainClass node)
	{
		// This adds the epilog
		//System.out.println("Generate epilog using avrF.rtl.s");
		InputStream mainEpilogue=null;
		BufferedReader reader=null;
		try {
			mainEpilogue = this.getClass().getClassLoader().getResourceAsStream("avrF.rtl.s");
			reader = new BufferedReader(new InputStreamReader(mainEpilogue));

			String line = null;
			while ((line = reader.readLine()) != null) {
				out.println(line);
			}
		} catch ( Exception e2) {
			e2.printStackTrace();
		}
		finally{
			try{
				if(mainEpilogue!=null) mainEpilogue.close();
				if(reader!=null) reader.close();
				out.flush();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
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

	public void inMeggyCheckButton(MeggyCheckButton node)
	{
		defaultIn(node);
	}

	public void outMeggyCheckButton(MeggyCheckButton node)
	{
		//Setup our labels
		String ifZeroLbl = new Label().toString();
		String ifOneLbl = new Label().toString();
		String jmpPastZeroLbl = new Label().toString();
		// Check button is special case where we need
		// to examine the child nodes, do not use the stack

		String buttonValue = node.getExp().toString();
		if(buttonValue.equals("Meggy.Button.Up"))
			buttonValue = "Button_Up";
		else if(buttonValue.equals("Meggy.Button.Down"))
			buttonValue = "Button_Down";
		else if(buttonValue.equals("Meggy.Button.Left"))
			buttonValue = "Button_Left";
		else if(buttonValue.equals("Meggy.Button.Right"))
			buttonValue = "Button_Right";
		else if(buttonValue.equals("Meggy.Button.A"))
			buttonValue = "Button_A";
		else if(buttonValue.equals("Meggy.Button.B"))
			buttonValue = "Button_B";

		out.println("    ### MeggyCheckButton");
		out.println("    call     _Z16CheckButtonsDownv");
		out.println("    # pop button value off stack");
		out.println("    lds    r24, " + buttonValue);
		out.println("    # if button value is zero, push 0 else push 1");
		out.println("    tst    r24");
		out.println("    breq   " + ifZeroLbl);
		out.println(ifOneLbl + ":");
		out.println("    ldi    r24, 1");
		out.println("    jmp    " + jmpPastZeroLbl);
		out.println(ifZeroLbl + ":");
		out.println(jmpPastZeroLbl + ":");
		out.println("    # push one byte expression onto stack");
		out.println("    push   r24");
		out.flush();
	}

	public void visitMeggyCheckButton(MeggyCheckButton node)
	{
		inMeggyCheckButton(node);
		if(node.getExp() != null)
		{
			node.getExp().accept(this);
		}
		outMeggyCheckButton(node);
	}

	public void inMeggyDelay(MeggyDelay node)
	{
		defaultIn(node);
	}

	public void outMeggyDelay(MeggyDelay node)
	{
		out.println("    ### Meggy.delay() call");
		out.println("    # load delay parameter");
		out.println("    # load a two byte expression off stack");
		out.println("    pop    r24");
		out.println("    pop    r25");
		out.println("    call   _Z8delay_msj");
		out.println();
		out.flush();
	}

	public void visitMeggyDelay(MeggyDelay node)
	{
		inMeggyDelay(node);
		if(node.getExp() != null)
		{
			node.getExp().accept(this);
		}
		outMeggyDelay(node);
	}

	public void inMeggyGetPixel(MeggyGetPixel node)
	{
		defaultIn(node);
	}

	public void outMeggyGetPixel(MeggyGetPixel node)
	{
		out.println("    ### Meggy.getPixel(x,y) call");
		out.println("    # load a one byte expression off stack");
		out.println("    pop    r22");
		out.println("    # load a one byte expression off stack");
		out.println("    pop    r24");
		out.println("    call   _Z6ReadPxhh");
		out.println("    # push one byte color expression onto stack");
		out.println("    push   r24");
		out.println();
		out.flush();
	}

	public void visitMeggyGetPixel(MeggyGetPixel node)
	{
		inMeggyGetPixel(node);
		if(node.getXExp() != null)
		{
			node.getXExp().accept(this);
		}

		if(node.getYExp() != null)
		{
			node.getYExp().accept(this);
		}

		outMeggyGetPixel(node);
	}

	public void inMeggySetAuxLEDs(MeggySetAuxLEDs node)
	{
		defaultIn(node);
	}

	public void outMeggySetAuxLEDs(MeggySetAuxLEDs node)
	{
		defaultOut(node);
	}

	public void visitMeggySetAuxLEDs(MeggySetAuxLEDs node)
	{
		inMeggySetAuxLEDs(node);
		if(node.getExp() != null)
		{
			node.getExp().accept(this);
		}
		outMeggySetAuxLEDs(node);
	}

	public void inMeggySetPixel(MeggySetPixel node)
	{
		defaultIn(node);
	}

	public void outMeggySetPixel(MeggySetPixel node)
	{
		out.println("    ### Meggy.setPixel(x,y,color) call");
		out.println("    # load a one byte expression off stack");
		out.println("    pop    r20");
		out.println("    # load a one byte expression off stack");
		out.println("    pop    r22");
		out.println("    # load a one byte expression off stack");
		out.println("    pop    r24");
		out.println("    call   _Z6DrawPxhhh");
		out.println("    call   _Z12DisplaySlatev");
		out.println();
		out.flush();
	}

	public void visitMeggySetPixel(MeggySetPixel node)
	{
		inMeggySetPixel(node);
		if(node.getXExp() != null)
		{
			node.getXExp().accept(this);
		}

		if(node.getYExp() != null)
		{
			node.getYExp().accept(this);
		}

		if(node.getColor() != null)
		{
			node.getColor().accept(this);
		}
		outMeggySetPixel(node);
	}

	public void inMeggyToneStart(MeggyToneStart node)
	{
		defaultIn(node);
	}

	public void outMeggyToneStart(MeggyToneStart node)
	{
		out.println("    ### Meggy.toneStart(tone, time_ms) call");
		out.println("    # load a two byte expression off stack");
		out.println("    pop    r22");
		out.println("    pop    r23");
		out.println("    # load a two byte expression off stack");
		out.println("    pop    r24");
		out.println("    pop    r25");
		out.println("    call   _Z10Tone_Startjj");
		out.println();
		out.flush();
	}

	public void visitMeggyToneStart(MeggyToneStart node)
	{
		inMeggyToneStart(node);
		if(node.getToneExp() != null)
		{
			node.getToneExp().accept(this);
		}

		if(node.getDurationExp() != null)
		{
			node.getDurationExp().accept(this);
		}
		outMeggyToneStart(node);
	}

	public void inMethodDecl(MethodDecl node)
	{
		mCurrentST.pushScope(node.getName());
		currReg = 25;
	}

	public void outMethodDecl(MethodDecl node)
	{
		mCurrentST.popScope();
		currReg = 25;
	}

	@Override
	public void visitMethodDecl(MethodDecl node)
	{
		inMethodDecl(node);
		MethodSTE mste = (MethodSTE) mCurrentST.lookupEnclosing(node.getName());
		String name = mste.getAVRLabel();

		out.println();
		out.println();
		out.println("    .text");
		out.println(".global " + name);
		out.println("    .type  " + name +", @function");
		out.println(name + ":");
		// push old FP
		out.println("    push   r29");
		out.println("    push   r28");
		out.flush();

		// make space for frame (push 0s for each formal)
		out.println("    # make space for locals and params");
		out.println("    ldi    r30, 0"); 

		for(int i=1; i<mste.getFrameSize(); i++)
			out.println("    push   r30");
		out.flush();

		out.println();

		out.println("    # Copy stack pointer to frame pointer");
		out.println("    in     r28,__SP_L__");
		out.println("    in     r29,__SP_H__");
		out.println();

		out.println("    # save off parameters");

		// offset for "this"
		VarSTE vste = (VarSTE) mste.getScope().lookupInnermost("this");
		//if(vste == null)
		//	System.out.println("COULD NOT FIND \"this\" IN AVRGEN");
		out.println("    std    " + vste.getbase() + " + " + (vste.getOffset()+1) + ", r" + currReg);
		out.println("    std    " + vste.getbase() + " + " + (vste.getOffset()) + ", r" + (currReg-1));
		currReg -= 2;
		out.flush();

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
		// TODO for PA5 handle variable declarations;
		{
			List<VarDecl> copy = new ArrayList<VarDecl>(node.getVarDecls());
			for(VarDecl e : copy)
			{
				e.accept(this);
			}
		}

		out.println("/* done with function " + name + " prologue */");
		out.println();
		out.println();

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

		out.flush();

		out.println("/* epilogue start for " + name + " */");
		// TODO handle return value here
		if(mste.getReturnType().getAVRTypeSize() == 2){
			out.println("    # handle return value");
			loadLInt();
		}else if(mste.getReturnType().getAVRTypeSize() == 0){
			out.println("    # no return value");
		}else{
			out.println("    # handle return value");
			loadLByte();
		}

		out.println("    # pop space off stack for parameters and locals");
		for(int i=1; i<mste.getFrameSize(); i++)
			out.println("    pop    r30");

		out.println("    # restoring the frame pointer");
		out.println("    pop    r28");
		out.println("    pop    r29");
		out.println("    ret");
		out.println("    .size " + name + ", .-" + name);
		out.println();
		out.flush();
		outMethodDecl(node);
	}

	public void inMinusExp(MinusExp node)
	{
		defaultIn(node);
	}

	public void outMinusExp(MinusExp node)
	{

	}

	@Override
	public void visitMinusExp(MinusExp node)
	{
		inMinusExp(node);
		if(node.getLExp() != null)
		{
			node.getLExp().accept(this);
		}


		if(node.getRExp() != null)
		{
			node.getRExp().accept(this);
		}

		if(mCurrentST.getExpType(node.getRExp()) == Type.BYTE){
			//System.out.println("Found RByte");
			loadRByte();
			promoteRByte();
		}
		else
			loadRInt();


		if(mCurrentST.getExpType(node.getLExp()) == Type.BYTE){
			//System.out.println("Found LByte");
			loadLByte();
			promoteLByte();
		}
		else
			loadLInt();


		out.println("    # Do INT sub operation");
		out.println("    sub    r24, r18");
		out.println("    sbc    r25, r19");
		out.println("    # push hi order byte first");
		out.println("    # push two byte expression onto stack");
		out.println("    push   r25");
		out.println("    push   r24");
		out.println();
		out.flush();
		outMinusExp(node);
	}

	public void inMulExp(MulExp node)
	{
		defaultIn(node);
	}

	public void outMulExp(MulExp node)
	{
		out.println("    # Do multiplication of bytes");
		out.println("    pop    r24");
		out.println("    pop    r26");
		out.println("    muls   r26, r24");
		out.println("    push   r1");
		out.println("    push   r0");
		out.println();
		out.flush();
	}

	@Override
	public void visitMulExp(MulExp node)
	{
		inMulExp(node);
		if(node.getLExp() != null)
		{
			node.getLExp().accept(this);
		}
		if(node.getRExp() != null)
		{
			node.getRExp().accept(this);
		}
		outMulExp(node);
	}

	public void inNewArrayExp(NewArrayExp node)
	{
		defaultIn(node);
	}

	public void outNewArrayExp(NewArrayExp node)
	{
		defaultOut(node);
	}

	@Override
	public void visitNewArrayExp(NewArrayExp node)
	{
		inNewArrayExp(node);
		if(node.getType() != null)
		{
			node.getType().accept(this);
		}
		if(node.getExp() != null)
		{
			node.getExp().accept(this);
		}
		outNewArrayExp(node);
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
		ClassSTE cste = (ClassSTE) mCurrentST.lookup(node.getId());
		
		int size = cste.getClassSize();

		out.println("    # NewExp");
		out.println("    ldi    r24, lo8("+size+")");
		out.println("    ldi    r25, hi8("+size+")");
		out.println("    # allocating object of size "+size+" on heap");
		out.println("    call    malloc");
		out.println("    # push object address");
		out.println("    # push two byte expression onto stack");
		out.println("    push   r25");
		out.println("    push   r24");
		out.println();
		out.flush();
		outNewExp(node);
	}

	public void inNegExp(NegExp node)
	{
		defaultIn(node);
	}

	public void outNegExp(NegExp node)
	{
		defaultOut(node);
	}

	@Override
	public void visitNegExp(NegExp node)
	{
		inNegExp(node);
		if(node.getExp() != null)
		{
			node.getExp().accept(this);
		}

		if(mCurrentST.getExpType(node.getExp()) == Type.BYTE){
			loadLByte();
			promoteLByte();
		}
		else
			loadLInt();

		out.println("    # Do INT sub operation twice to negate");
		out.println("    #Copy Registers");
		out.println("    mov r18, r24");
		out.println("    mov r19, r25");

		out.println("    sub r24, r18");
		out.println("    sbc r25, r19");
		out.println("    sub r24, r18");
		out.println("    sbc r25, r19");
		out.println("    # push hi order byte first");
		out.println("    # push two byte expression onto stack");
		out.println("    push r25");
		out.println("    push r24");
		out.println();
		out.flush();

		outNegExp(node);
	}

	public void inNotExp(NotExp node)
	{
		defaultIn(node);
	}

	public void outNotExp(NotExp node)
	{
		out.println("    pop R24");
		out.println("    ldi R22,1");
		out.println("    eor R24,R22");
		out.println("    push R24");
		out.println();
		out.flush();
	}

	@Override
	public void visitNotExp(NotExp node)
	{
		inNotExp(node);
		if(node.getExp() != null)
		{
			node.getExp().accept(this);
		}
		outNotExp(node);
	}

	public void inPlusExp(PlusExp node)
	{
		defaultIn(node);
	}

	public void outPlusExp(PlusExp node)
	{
		defaultOut(node);
	}

	@Override
	public void visitPlusExp(PlusExp node)
	{
		inPlusExp(node);
		if(node.getLExp() != null)
		{
			node.getLExp().accept(this);
		}
		if(node.getRExp() != null)
		{
			node.getRExp().accept(this);
		}


		if(mCurrentST.getExpType(node.getRExp()) == Type.BYTE){
			//System.out.println("Found RByte");
			loadRByte();
			promoteRByte();
		}
		else
			loadRInt();


		if(mCurrentST.getExpType(node.getLExp()) == Type.BYTE){
			//System.out.println("Found LByte");
			loadLByte();
			promoteLByte();
		}
		else
			loadLInt();


		out.println("    # Do add operation");
		out.println("    add    r24, r18");
		out.println("    adc    r25, r19");
		out.println("    # push two byte expression onto stack");
		out.println("    push   r25");
		out.println("    push   r24");
		out.println();
		out.flush();
		outPlusExp(node);
	}

	private void loadRInt(){
		out.println("    # load an int expression off stack");
		out.println("    pop    r18");
		out.println("    pop    r19");
		out.println();
		out.flush();
	}

	private void loadLInt(){
		out.println("    # load an int expression off stack");
		out.println("    pop    r24");
		out.println("    pop    r25");
		out.println();
		out.flush();
	}

	private void loadRByte(){
		out.println("    # load a one byte expression off stack");
		out.println("    pop    r18");
		out.flush();
	}

	private void loadLByte(){
		out.println("    # load a one byte expression off stack");
		out.println("    pop    r24");
		out.flush();
	}

	private void promoteRByte(){
		//Get necessary labels
		String signExtendLbl = new Label().toString();
		String noSignExtendLbl = new Label().toString();
		out.println("    # promoting a byte to an int");
		out.println("    tst     r18");
		out.println("    brlt     " + signExtendLbl);
		out.println("    ldi    r19, 0");
		out.println("    jmp    " + noSignExtendLbl);
		out.println(signExtendLbl + ":");
		out.println("    ldi    r19, hi8(-1)");
		out.println(noSignExtendLbl + ":");
		out.println();
		out.flush();
	}

	private void promoteLByte(){
		//Get necessary labels
		String signExtendLbl = new Label().toString();
		String noSignExtendLbl = new Label().toString();
		out.println("    # promoting a byte to an int");
		out.println("    tst     r24");
		out.println("    brlt     " + signExtendLbl);
		out.println("    ldi    r25, 0");
		out.println("    jmp    " + noSignExtendLbl);
		out.println(signExtendLbl + ":");
		out.println("    ldi    r25, hi8(-1)");
		out.println(noSignExtendLbl + ":");
		out.println();
		out.flush();
	}

	public void inProgram(Program node)
	{
		// This adds the prolog
		//System.out.println("Generate prolog using avrH.rtl.s");
		InputStream mainPrologue=null;
		BufferedReader reader=null;
		try {
			// The syntax for loading a text resource file 
			// from a jar file here:
			// http://www.rgagnon.com/javadetails/java-0077.html
			mainPrologue = this.getClass().getClassLoader().getResourceAsStream("avrH.rtl.s");
			reader = new BufferedReader(new InputStreamReader(mainPrologue));

			String line = null;
			while ((line = reader.readLine()) != null) {
				//System.out.println(line);
				out.println(line);
			}
			out.println();
			out.println();
		} catch ( Exception e2) {
			e2.printStackTrace();
		}
		finally{
			try{
				if(mainPrologue!=null)
					mainPrologue.close();
				if(reader!=null)
					reader.close();
				out.flush();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		defaultIn(node);
	}

	public void outProgram(Program node)
	{
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
		
		// This code is just a place holder until
		// We can actually get a heap pointer to "this"
		out.println("    # Place holder Call to \"this\"");
		out.println("    ldd    r31, Y + 2");
		out.println("    ldd    r30, Y + 1");
		// In PA5 this pushes the address of "this" onto the stack
		out.println("    # push two byte expression onto stack");
		out.println("    push   r31");
		out.println("    push   r30");
	}

	@Override
	public void visitThisLiteral(ThisLiteral node)
	{
		inThisExp(node);
		outThisExp(node);
	}

	public void inToneExp(ToneLiteral node)
	{
		defaultIn(node);
	}

	public void outToneExp(ToneLiteral node)
	{
		out.println("    # Push " + node.getLexeme() + " onto the stack.");
		out.println("    ldi    r24,lo8("+node.getIntValue()+")");
		out.println("    ldi    r25,hi8("+node.getIntValue()+")");
		out.println("    # push two byte expression onto stack");
		out.println("    push   r25");
		out.println("    push   r24");
		out.println();
		out.flush();
	}

	@Override
	public void visitToneLiteral(ToneLiteral node)
	{
		inToneExp(node);
		outToneExp(node);
	}


	public void inToneType(ToneType node)
	{
		defaultIn(node);
	}

	public void outToneType(ToneType node)
	{
		defaultOut(node);
	}

	@Override
	public void visitToneType(ToneType node)
	{
		inToneType(node);
		outToneType(node);
	}

	public void inTopClassDecl(TopClassDecl node)
	{
		mCurrentST.pushScope(node.getName());
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

	public void inTrueExp(TrueLiteral node)
	{
		defaultIn(node);
	}

	public void outTrueExp(TrueLiteral node)
	{
		out.println("    # True/1 expression");
		out.println("    ldi    r22, " + node.getIntValue());
		out.println("    # push one byte expression onto stack");
		out.println("    push   r22");
		out.println();
		out.flush();

		defaultOut(node);
	}

	@Override
	public void visitTrueLiteral(TrueLiteral node)
	{
		inTrueExp(node);
		outTrueExp(node);
	}

	public void inVarDecl(VarDecl node)
	{
		defaultIn(node);
	}

	public void outVarDecl(VarDecl node)
	{
		defaultOut(node);
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

	public void inVoidType(VoidType node)
	{
		defaultIn(node);
	}

	public void outVoidType(VoidType node)
	{
		defaultOut(node);
	}

	@Override
	public void visitVoidType(VoidType node)
	{
		inVoidType(node);
		outVoidType(node);
	}

	public void inWhileStatement(WhileStatement node)
	{
		defaultIn(node);
	}

	public void outWhileStatement(WhileStatement node)
	{
		defaultOut(node);
	}

	@Override
	public void visitWhileStatement(WhileStatement node)
	{
		//Get necessary labels
		String whileTestLbl = new Label().toString();
		String whileBodyLbl = new Label().toString();
		String whileExitLbl = new Label().toString();
		inWhileStatement(node);
		out.println("    #### while statement");
		out.println(whileTestLbl + ":");
		out.println();
		out.flush();
		if(node.getExp() != null)
		{
			node.getExp().accept(this);
		}
		//After we get the expression, there will be a bool
		//on the stack that we can test
		
		out.println("    # if not(condition)");
		out.println("    # load a one byte expression off stack");
		out.println("    pop    r24");
		out.println("    ldi    r25,0");
		out.println("    cp     r24, r25");
		out.println("    # WANT breq " + whileExitLbl);
		out.println("    brne   " + whileBodyLbl);
		out.println("    jmp    " + whileExitLbl);
		out.println();

		out.println("    # while loop body");
		out.println(whileBodyLbl + ":");
		out.println();
		out.flush();
		// This will add AVR code for all the statements in the while block
		if(node.getStatement() != null)
		{
			node.getStatement().accept(this);
		}

		out.println("    # jump to while test");
		out.println("    jmp    " + whileTestLbl);
		out.println();

		out.println("    # end of while");
		out.println(whileExitLbl + ":");
		out.flush();
		outWhileStatement(node);
	}

}
