/**
 * This ast walker generates AVR assembly for the AST.  
 *
 * 3/4/14 Casey Pore
 */
package ast_visitors;

import java.io.PrintWriter;

import ast.visitor.DepthFirstVisitor;
import ast.node.*;

import java.util.*;

public class AVRgenVisitor extends DepthFirstVisitor {
	private PrintWriter out;


	/** Constructor takes a PrintWriter, and stores in instance var. */
	public AVRgenVisitor(PrintWriter out) {
		this.out = out;
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
		defaultOut(node);
	}

	@Override
	public void visitAndExp(AndExp node)
	{
		inAndExp(node);
		if(node.getLExp() != null)
		{
			node.getLExp().accept(this);
		}
		if(node.getRExp() != null)
		{
			node.getRExp().accept(this);
		}
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
		defaultOut(node);
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
		defaultOut(node);
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
		defaultOut(node);
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
		defaultOut(node);
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
		outEqualExp(node);
	}


	public void inFalseExp(FalseLiteral node)
	{
		defaultIn(node);
	}

	public void outFalseExp(FalseLiteral node)
	{
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
		defaultOut(node);
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
		defaultIn(node);
	}

	public void outIdLiteral(IdLiteral node)
	{
		defaultOut(node);
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
		inIfStatement(node);
		if(node.getExp() != null)
		{
			node.getExp().accept(this);
		}
		if(node.getThenStatement() != null)
		{
			node.getThenStatement().accept(this);
		}
		if(node.getElseStatement() != null)
		{
			node.getElseStatement().accept(this);
		}
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
		defaultOut(node);
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
		if(node.getLExp() != null)
		{
			node.getLExp().accept(this);
		}
		if(node.getRExp() != null)
		{
			node.getRExp().accept(this);
		}
		outLtExp(node);
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

	public void inMeggyCheckButton(MeggyCheckButton node)
	{
		defaultIn(node);
	}

	public void outMeggyCheckButton(MeggyCheckButton node)
	{
		defaultOut(node);
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
		defaultOut(node);
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
		defaultOut(node);
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
		defaultOut(node);
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
		defaultOut(node);
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
		defaultIn(node);
	}

	public void outMethodDecl(MethodDecl node)
	{
		defaultOut(node);
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

	public void inMinusExp(MinusExp node)
	{
		defaultIn(node);
	}

	public void outMinusExp(MinusExp node)
	{
		defaultOut(node);
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
		outMinusExp(node);
	}

	public void inMulExp(MulExp node)
	{
		defaultIn(node);
	}

	public void outMulExp(MulExp node)
	{
		defaultOut(node);
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
		outNegExp(node);
	}

	public void inNotExp(NotExp node)
	{
		defaultIn(node);
	}

	public void outNotExp(NotExp node)
	{
		defaultOut(node);
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
		outPlusExp(node);
	}

	public void inProgram(Program node)
	{
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
		defaultOut(node);
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
		defaultOut(node);
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
		defaultIn(node);
	}

	public void outTopClassDecl(TopClassDecl node)
	{
		defaultOut(node);
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
		inWhileStatement(node);
		if(node.getExp() != null)
		{
			node.getExp().accept(this);
		}
		if(node.getStatement() != null)
		{
			node.getStatement().accept(this);
		}
		outWhileStatement(node);
	}

}