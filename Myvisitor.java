import tony.analysis.*;
import tony.node.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;



public class Myvisitor extends DepthFirstAdapter 
{
	private MyST symtable;	
	private PrintWriter writer;
	static int scopeCounter=-1;
	private MyST tempParent;
	ArrayList<StringBuffer> block = new ArrayList<StringBuffer>();
	//ArrayList<StringBuffer> forSimple = new ArrayList<StringBuffer>();
	int index=-1; 
	int labelCounter=0, compareLabelCounter=0, forLabelCounter=0, ifNestedCounter=0;
	Stack<Integer> ifLableStack= new Stack<Integer>();
	Stack<Integer> elseLableStack= new Stack<Integer>();
	Stack<Integer> forSimple= new Stack<Integer>();

	

	Myvisitor(MyST symtable) 
	{
		Debug.on=true;
		
		this.symtable = symtable;
		symtable.setName("");
		//System.out.println("type"+AIntType.class.getName().toString());
		FunctionKey fk = new FunctionKey("puti",Type.VoidType());
		fk.addParameterType(Type.IntType());
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("putb",Type.VoidType());
		fk.addParameterType(Type.BoolType());
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("putc",Type.VoidType());
		fk.addParameterType(Type.CharType());
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("puts",Type.VoidType());
		fk.addParameterType(new Type(Type.ARRAY,Type.CharType()));
		symtable.instert(fk,fk);
		
		
		fk = new FunctionKey("geti",Type.IntType());
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("getb",Type.BoolType());
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("getc",Type.CharType());
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("gets",Type.VoidType());
		fk.addParameterType(Type.IntType());
		fk.addParameterType(new Type(Type.ARRAY,Type.CharType()));
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("abs",Type.IntType());
		fk.addParameterType(Type.IntType());
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("ord",Type.IntType());
		fk.addParameterType(Type.CharType());
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("chr",Type.CharType());
		fk.addParameterType(Type.IntType());
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("strlen",Type.IntType());
		fk.addParameterType(new Type(Type.ARRAY,Type.CharType()));
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("strcmp",Type.IntType());
		fk.addParameterType(new Type(Type.ARRAY,Type.CharType()));
		fk.addParameterType(new Type(Type.ARRAY,Type.CharType()));
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("strcpy",Type.VoidType());
		fk.addParameterType(new Type(Type.ARRAY,Type.CharType()));
		fk.addParameterType(new Type(Type.ARRAY,Type.CharType()));
		
		fk = new FunctionKey("strcat",Type.VoidType());
		fk.addParameterType(new Type(Type.ARRAY,Type.CharType()));
		fk.addParameterType(new Type(Type.ARRAY,Type.CharType()));
		symtable.instert(fk,fk);

		try {
			writer = new PrintWriter(ParserTest.PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	//-----------------------------------------paragwgh kwdika
	@Override
	public void inAProgram(AProgram node) {
		writer.println(".class public "+ParserTest.CLASSNAME);
		writer.println(".super java/lang/Object");
		
		//write methods
		writer.println("\n;---puti---");
		writer.println(".method public static puti(I)V");
		writer.println(".limit stack 3");
		writer.println(".limit locals 2");
		writer.println("getstatic      java/lang/System/out Ljava/io/PrintStream;");
		writer.println("iload 0");
		writer.println("invokevirtual  java/io/PrintStream/println(I)V");
		writer.println("return");
		writer.println(".end method");
		
		//end methods
				
		
		
	}
	
	@Override
	public void outAProgram(AProgram node) {
		writer.flush();
	}
	
	@Override
	public void outAConstIntExpression(AConstIntExpression node) {
		block.get(index).append("\n"+("ldc "+node.getConstInt().getText()));
	}


	
	@Override
	public void inATrueExpression(ATrueExpression node) {
		createPreElseIf(node);
	}
	
	@Override
	public void outATrueExpression(ATrueExpression node) {
		block.get(index).append("\n"+("ldc 1"));
		createIfBlock(node);
	}
	
	@Override
	public void inAFalseExpression(AFalseExpression node) {
		createPreElseIf(node);
	}
	
	@Override
	public void outAFalseExpression(AFalseExpression node) {
		block.get(index).append("\n"+("ldc 0"));
		createIfBlock(node);
	}
	
	@Override
	public void inAIfStatement(AIfStatement node) {
		
		
		//block.get(index).append("\n"+("ifeq Label"+labelCounter));
	}
	
	@Override
	public void inAPrExpression(APrExpression node) {
		createPreElseIf(node);
	}
	
	@Override
	public void outAPrExpression(APrExpression node) {
		createIfBlock(node);
	}
	
	
	@Override
	public void outAElseStatement(AElseStatement node) {
		Debug.println("else out");
		//block.get(index).append("\n"+("Label"+ifLableStack.pop() +":"));
		// TODO
	}
	
	@Override
	public void outASingleSimpleSimpleList(ASingleSimpleSimpleList node) {
		if(node.parent() instanceof AForStatement
				&& ((AForStatement)node.parent()).getR().equals(node)
			)
		{
			block.get(index).append("\n"+("goto LabelF"+((forSimple.size()-1)*2)));
			block.get(index).append("\n"+("LabelS"+((forSimple.size()-1)*2))+":");
		}
	}
	
	@Override
	public void outAMultiSimpleSimpleList(AMultiSimpleSimpleList node) {
		if(node.parent() instanceof AForStatement
				&& ((AForStatement)node.parent()).getR().equals(node)
			)
		{
			block.get(index).append("\n"+("goto LabelF"+((forSimple.size()-1)*2)));
			block.get(index).append("\n"+("LabelS"+((forSimple.size()-1)*2))+":");
		}
	}
	
	
	private void createIfBlock(Node node) {
		// TODO Auto-generated method stub
		
		if(node.parent()instanceof AForStatement)
		{
			block.get(index).append("\n"+("ifeq LabelF"+((forSimple.size()-1)*2+1)));
			block.get(index).append("\n"+("goto LabelS"+((forSimple.size()-1)*2)));
			block.get(index).append("\n"+("LabelS"+(((forSimple.size()-1)*2)+1))+":");
			
		}
		
		
		if(node.parent() instanceof AIfStatement )
		{
			labelCounter+=ifNestedCounter;
			ifNestedCounter++;
			Debug.println("inIfSLBL:"+labelCounter);
			ifLableStack.push(labelCounter);
		}
		
		//----------------------------
		
		
		if(node.parent() instanceof AIfStatement 
				&& ((AIfStatement)node.parent()).getElseif().size()==0 
				&& ((AIfStatement)node.parent()).getElse()==null 
			)
			{
			Debug.println("Sketo if");//sketo
				int l =ifLableStack.peek();
				block.get(index).append("\n"+("ifeq Label"+l));
				
			}
			
		else if(node.parent() instanceof AIfStatement 
				||node.parent() instanceof AElseifStatement
				||node.parent() instanceof AElseStatement
				)
		{
			Debug.println("OXI Sketo if");
			if(node.parent() instanceof AIfStatement)
			{					
				Debug.println("if");
				
				
				labelCounter++;
				
				
				block.get(index).append("\n"+("ifeq Label"+labelCounter));
				elseLableStack.push(labelCounter);
				//labelCounter++;
				Debug.println("if end");
			}
			
			else if(node.parent() instanceof AElseifStatement)
			{
				Debug.println("elseif");
				//goto print thn sigrish
				if(node.parent().parent() instanceof AIfStatement
						&& ((AIfStatement)node.parent().parent()).getElseif().get(((AIfStatement)node.parent().parent()).getElseif().size()-1).equals(node.parent())
						)
				{
					Debug.println("elseif edw eimaste");
					block.get(index).append("\n"+("ifeq Label"+ifLableStack.peek()));////////////
				}
				else
				{
					elseLableStack.push(++labelCounter);
					block.get(index).append("\n"+("ifeq Label"+elseLableStack.peek()));
				}
			}
			else if(node.parent() instanceof AElseStatement)
			{
//				Debug.println("else");
//				block.get(index).append("\n"+"goto Label"+(ifLableStack.peek()));
//				block.get(index).append("\n"+("Label"+elseLableStack.pop() +":"));
			}
			
			
		}
			
		
	}
	
	private void createPreElseIf(Node node) {
		if(node.parent() instanceof AElseifStatement)
		{
			block.get(index).append("\n"+"goto Label"+(ifLableStack.peek()));//}
			block.get(index).append("\n"+("Label"+elseLableStack.pop() +":"));
		}
		if(node.parent() instanceof AForStatement)
		{
			//block.get(index).append("\n"+"goto Label"+(ifLableStack.peek()));//}
			block.get(index).append("\n"+("LabelF"+(forLabelCounter*2) +":"));
			forSimple.push(forLabelCounter);
			forLabelCounter++;
		}
		
	}
	
	//-----------------------------------------end paragwgh kwdika

	@Override
	public void inAFunctionDefFunction(AFunctionDefFunction node) 
	{
		index++;
		block.add(index, new StringBuffer(""));
		scopeCounter++;
		//-------------------------------------------------------------------
		//FunctionKey fk = new FunctionKey();
		AHeader header = (AHeader) node.getHeader();
		String fName = header.getId().getText();
		Type type=Type.VoidType();
		if(!header.getType().isEmpty()){
			
			type=getType(header.getType().getFirst());
		}
		FunctionKey fk = new FunctionKey(fName);
		//fk.setId(fName);
		//System.out.println("f:("+fName+")type:("+type+")");
		fk.setType(type);
		
		
		if(!header.getParameters().isEmpty())
		{
			Object objP = header.getParameters().getFirst();
			while(objP instanceof AMultiParameters)
			{
				AMultiParameters par=(AMultiParameters)objP;
				AFormalFormal formal=(AFormalFormal)par.getFormal();
				
				Type ftype=getType(formal.getType());
				
				Object objIdList = formal.getIdentifierList();
				
				while(objIdList instanceof AMultiIdListIdentifierList){
					AMultiIdListIdentifierList id = (AMultiIdListIdentifierList) objIdList;
					
					objIdList= id.getIdentifierList();
					fk.addParameterType(ftype);
					
				
				}
				ASingleIdListIdentifierList id =(ASingleIdListIdentifierList) objIdList;
				
				fk.addParameterType(ftype);
				///
				objP= par.getParameters();
			}
			
			ASingleParameters par=(ASingleParameters)objP;
			AFormalFormal formal=(AFormalFormal)par.getFormal();
			
			Type ftype=getType(formal.getType());
			
			Object objIdList = formal.getIdentifierList();
			
			while(objIdList instanceof AMultiIdListIdentifierList){
				AMultiIdListIdentifierList id = (AMultiIdListIdentifierList) objIdList;
				objIdList= id.getIdentifierList();
				fk.addParameterType(ftype);
				//System.out.println(ftype);
			
			}
			ASingleIdListIdentifierList id =(ASingleIdListIdentifierList) objIdList;
			fk.addParameterType(ftype);
			//System.out.println(ftype);
			
			
			
		}
		
		//paragwgh
		if (symtable.parent==null)
		{
			writer.println("\n;---main---");
			writer.println(".method public static main([Ljava/lang/String;)V");
			writer.println(".limit stack 5");
			writer.println(".limit locals 5");
			//invokestatic test/g(II)V
			writer.println("invokestatic "+ParserTest.CLASSNAME+"/"+symtable.getName()+fk.id+"("+fk.getFormalsString()+")"+fk.getReturnTypeString());
			writer.println("return");
			writer.println(".end method");
		}
		
		
		//paragwgh end
		
		int line = ((TId) header.getId()).getLine();
		
		if(symtable.lookUp(fk))
		{	
			System.out.println("Line " + line + ": " +" Function " + fName +" is already defined");
		}
		else
		{
			symtable.instert(fk, fk);
			
		}
		
		
		
		symtable = new MyST(symtable);
		symtable.setName(symtable.parent.getName()+fk.id);
		
		{
			
			block.get(index).append("\n"+("\n;---"+fk.id+"---"));
			block.get(index).append("\n"+(".method public static "+ symtable.parent.getName()+fk.id+"("+fk.getFormalsString()+")"+fk.getReturnTypeString()));
			block.get(index).append("\n"+(".limit stack 5"));
			block.get(index).append("\n"+(".limit locals 5"));
			//System.out.println(block.get(index));
//			block.get(index).concat("\n"+);
//			writer.println("\n;---"+fk.id+"---");
//			writer.println(".method public static "+ symtable.parent.getName()+fk.id+"("+fk.getFormalsString()+")"+fk.getReturnTypeString());
//			writer.println(".limit stack 5");
//			writer.println(".limit locals 5");
		}
		
	}
	
	public void outAFunctionDefFunction(AFunctionDefFunction node) 
	{
		//------------------------------------
		symtable=symtable.parent;
		scopeCounter--;
		//System.out.println(block.get(index));
		writer.println(block.get(index));
		writer.println("return");
		writer.println(".end method");
		index--;
		
		//writer.println(symtable.code);
	}
	
	
	@Override
	public void inAFormalFormal(AFormalFormal node) {
		Type type =getType(node.getType());
		Object objIdList = node.getIdentifierList();

		VariableKey vk;
		
		while(objIdList instanceof AMultiIdListIdentifierList){
			AMultiIdListIdentifierList id = (AMultiIdListIdentifierList) objIdList;
			
			int line=id.getId().getLine();
			String idName=id.getId().getText();
			vk = new VariableKey(idName,type);
			
			if(symtable.lookUp(vk))
			{	
				System.out.println("Line " + line + ": " +" Variable " + idName +" is already defined");
			}
			else
			{
				symtable.instert(vk, vk);
				
			}
			
			objIdList=id.getIdentifierList();
		}
		ASingleIdListIdentifierList id =(ASingleIdListIdentifierList) objIdList;
		int line=id.getId().getLine();
		String idName=id.getId().getText();
		vk = new VariableKey(idName,type);
		
		if(symtable.lookUp(vk))
		{	
			System.out.println("Line " + line + ": " +" Function " + idName +" is already defined");
		}
		else
		{
			symtable.instert(vk, vk);
			
		}

	}
	
	
	public void inAVarDefinition(AVarDefinition node)
	{
		
		Type type=getType(node.getType());
		Object objIdList = node.getIdentifierList();

		VariableKey vk;
		
		while(objIdList instanceof AMultiIdListIdentifierList){
			AMultiIdListIdentifierList id = (AMultiIdListIdentifierList) objIdList;
			
			int line=id.getId().getLine();
			String idName=id.getId().getText();
			vk = new VariableKey(idName,type);
			
			if(symtable.lookUp(vk))
			{	
				System.out.println("Line " + line + ": " +" Variable " + idName +" is already defined");
			}
			else
			{
				symtable.instert(vk, vk);
				
			}
			
			objIdList=id.getIdentifierList();
		}
		ASingleIdListIdentifierList id =(ASingleIdListIdentifierList) objIdList;
		int line=id.getId().getLine();
		String idName=id.getId().getText();
		vk = new VariableKey(idName,type);
		
		if(symtable.lookUp(vk))
		{	
			System.out.println("Line " + line + ": " +" Function " + idName +" is already defined");
		}
		else
		{
			symtable.instert(vk, vk);
			
			
		}
	}

	@Override
	public void outACallCall(ACallCall node) {
		String fName=node.getId().getText();
		FunctionKey fk = new FunctionKey(fName);
		int line = node.getId().getLine();
		Object objP = node.getRealParList();
		
		if(!node.getRealParList().isEmpty())
		{
			//System.out.println("kai edw");
			objP=node.getRealParList().getFirst();
			while(objP instanceof AMultiRealParRealParList)
			{
				AMultiRealParRealParList par=(AMultiRealParRealParList)objP;
				Type type=getExpType(par.getExpression());
				fk.addParameterType(type);
				
				objP=par.getRealParList();
			}
			
			ASigleRealParRealParList par=(ASigleRealParRealParList)objP;
			Type type=getExpType(par.getExpression());
			fk.addParameterType(type);
		}
		
		boolean defined=false;
		
		if(findFunc(fk)!=null)
			defined=true;
		
		if(!defined)
		{
			String parTyp =fk.parametersType.toString();
			parTyp= parTyp.substring(1, parTyp.length()-1);
			System.out.println("Line " + line + ": " +" function " + fName+"("+parTyp+")" +" is not defined");
		}
		//paragwgh
		//writer.println("invokestatic test/g(II)V");
		block.get(index).append("\n"+("invokestatic "+ParserTest.CLASSNAME+"/"+tempParent.getName()+fk.id+"("+fk.getFormalsString()+")"+fk.getReturnTypeString()));
		
	}
	
	@Override
	public void inAAtomAtom(AAtomAtom node) {//-----------oxi
	
		String varname=node.getId().getText();
		int line=node.getId().getLine();
		VariableKey vk = new VariableKey(varname);
		MyST temp=symtable;
		boolean alreadyExist=false;
		while (temp!=null)
		{
			if(alreadyExist=temp.lookUp(vk))
				break;
			
			temp=temp.parent;
		}
		
		if (!alreadyExist)
		{	
			System.out.println("Line " + line + ": " +" Variable " + varname +" is not  defined");
			System.exit(-1);
		}
		//paragwgh
		int ldnum =symtable.vars.indexOf(vk);
		Debug.println(ldnum);
		vk=findVar(varname);
		
		Debug.println(node.parent());
		if(node.parent() instanceof AAtomExpression)
		{
			//Debug.println("hi");
			createPreElseIf(node.parent());
			if(symtable.vars.get(ldnum).type.isInt()||symtable.vars.get(ldnum).type.isBool())
				block.get(index).append("\n"+("iload "+ldnum));
			createIfBlock(node.parent());
			

				
		}
		//else if(node.parent() instanceof AAtomassSimple)
			
		// end paragwgh
	}
	
	@Override
	public void outAAtomassSimple(AAtomassSimple node) {
		//System.out.println("cmp:"+getAtomType(node.getAtom())+"="+getExpType(node.getExpression()));
		if(!getAtomType(node.getAtom()).equals(getExpType(node.getExpression())))
			System.out.println("assigning variable type:"+getAtomType(node.getAtom())+" incompatible type:"+getExpType(node.getExpression()));
		if(node.getAtom() instanceof AAtomAtom)
		{
			VariableKey vk = new VariableKey(((AAtomAtom)node.getAtom()).getId().getText());
			
			int ldnum =symtable.vars.indexOf(vk);
			block.get(index).append("\n"+("istore "+ldnum));
		}	
	}
	
	@Override
	public void outAIfStatement(AIfStatement node) {
		if(!getExpType(node.getExpression()).isBool())
		{
			System.out.println("if expression is not bool");
			System.exit(-1);
		}
		//paragwgh
		Debug.println("outAIfStatement");
		int l =ifLableStack.pop();
		//block.get(index).append("\n"+("Label"+labelCounter++ +":"));
		
		block.get(index).append("\n"+("Label"+l +":"));
		ifNestedCounter--;
	}

	@Override
	public void outAElseifStatement(AElseifStatement node) {
		if(!getExpType(node.getExpression()).isBool())
		{
			System.out.println("else if expression is not bool");
			System.exit(-1);
		}
	}
	@Override
	public void outAForStatement(AForStatement node) {
		if(!getExpType(node.getExpression()).isBool())
		{
			System.out.println("for expression is not bool");
			System.exit(-1);
		}
		
		block.get(index).append("\n"+("goto LabelS"+((forSimple.size()-1)*2+1)));
		block.get(index).append("\n"+("LabelF"+((forSimple.size()-1)*2+1))+":");
	}	
	@Override
	public void outAAdditionExpression(AAdditionExpression node) {
		if(!(getExpType(node.getL()).isInt()&& getExpType(node.getR()).isInt()))
		{
			System.out.println("addition expression is not int");
			System.exit(-1);
		}
		block.get(index).append("\n"+("iadd"));
	}
	@Override
	public void outASubtractionExpression(ASubtractionExpression node) {
		if(!(getExpType(node.getL()).isInt()&& getExpType(node.getR()).isInt()))
		{
			System.out.println("subtraction expression is not int");
			System.exit(-1);
		}
		block.get(index).append("\n"+("isub"));
	}
	
	@Override
	public void outAMultiplicationExpression(AMultiplicationExpression node) {
		if(!(getExpType(node.getL()).isInt()&& getExpType(node.getR()).isInt()))
		{
			System.out.println("multiplication expression is not int");
			System.exit(-1);
		}
		block.get(index).append("\n"+("imul"));
	}

	
	@Override
	public void outADivisionExpression(ADivisionExpression node) {
		if(!(getExpType(node.getL()).isInt()&& getExpType(node.getR()).isInt()))
		{
			System.out.println("division expression is not int");
			System.exit(-1);
		}
		block.get(index).append("\n"+("idiv"));
	}
	@Override
	public void outAModuloExpression(AModuloExpression node) {
		if(!(getExpType(node.getL()).isInt()&& getExpType(node.getR()).isInt()))
		{
			System.out.println("modulo expression is not int");
			System.exit(-1);
		}
	}
	
	@Override
	public void outASignnumberExpression(ASignnumberExpression node) {
		if(!(getExpType(node.getL()).isInt()&& getExpType(node.getR()).isInt()))
		{
			System.out.println("sign number expression is not int");
			System.exit(-1);
		}
	}

	@Override
	public void inAEqExpression(AEqExpression node) {
		createPreElseIf(node);
	}

	@Override
	public void outAEqExpression (AEqExpression node) {
		if(!(isBasicType(getExpType(node.getL())) && isBasicType(getExpType(node.getR())))){
			System.out.println("comparing is not defind between basic types");
			System.exit(-1);
		}
		if(!getExpType(node.getL()).equals(getExpType(node.getR())))
		{
			System.out.println("comparing is not defind between different types");
			System.exit(-1);
		}
		//paragwgh
		
		block.get(index).append("\n"+("if_icmpeq LabelC"+compareLabelCounter));
		block.get(index).append("\n"+("ldc 0"));
		block.get(index).append("\n"+"goto LabelC"+(compareLabelCounter+1));
		block.get(index).append("\n"+"LabelC"+(compareLabelCounter)+":");
		block.get(index).append("\n"+("ldc 1"));
		block.get(index).append("\n"+"LabelC"+(compareLabelCounter+1)+":");
		compareLabelCounter+=2;
		
		createIfBlock(node);
	}
	
	@Override
	public void inANotEqualExpression(ANotEqualExpression node) {
		createPreElseIf(node);
	}
	
	@Override
	public void outANotEqualExpression(ANotEqualExpression node) {
		if(!(isBasicType(getExpType(node.getL())) && isBasicType(getExpType(node.getR())))){
			System.out.println("comparing is not defind between basic types");
			System.exit(-1);
		}
		if(!getExpType(node.getL()).equals(getExpType(node.getR())))
		{
			System.out.println("comparing is not defind between different types");
			System.exit(-1);
		}
		
		//paragwgh
		block.get(index).append("\n"+("if_icmpne LabelC"+compareLabelCounter));
		block.get(index).append("\n"+("ldc 0"));
		block.get(index).append("\n"+"goto LabelC"+(compareLabelCounter+1));
		block.get(index).append("\n"+"LabelC"+(compareLabelCounter)+":");
		block.get(index).append("\n"+("ldc 1"));
		block.get(index).append("\n"+"LabelC"+(compareLabelCounter+1)+":");
		compareLabelCounter+=2;
		
		createIfBlock(node);
	}
	@Override
	public void inASmallerEqExpression(ASmallerEqExpression node) {
		createPreElseIf(node);
	}
	
	@Override
	public void outASmallerEqExpression(ASmallerEqExpression node) {
		if(!(isBasicType(getExpType(node.getL())) && isBasicType(getExpType(node.getR())))){
			System.out.println("comparing is not defind between basic types");
			System.exit(-1);
		}
		if(!getExpType(node.getL()).equals(getExpType(node.getR())))
		{
			System.out.println("comparing is not defind between different types");
			System.exit(-1);
		}
		
		//paragwgh
				block.get(index).append("\n"+("if_icmple LabelC"+compareLabelCounter));
				block.get(index).append("\n"+("ldc 0"));
				block.get(index).append("\n"+"goto LabelC"+(compareLabelCounter+1));
				block.get(index).append("\n"+"LabelC"+(compareLabelCounter)+":");
				block.get(index).append("\n"+("ldc 1"));
				block.get(index).append("\n"+"LabelC"+(compareLabelCounter+1)+":");
				compareLabelCounter+=2;
		
		createIfBlock(node);
	}
	
	@Override
	public void inASmallerExpression(ASmallerExpression node) {
		createPreElseIf(node);
	}
	
	@Override
	public void outASmallerExpression(ASmallerExpression node) {
		if(!(isBasicType(getExpType(node.getL())) && isBasicType(getExpType(node.getR())))){
			System.out.println("comparing is not defind between basic types");
			System.exit(-1);
		}
		if(!getExpType(node.getL()).equals(getExpType(node.getR())))
		{
			System.out.println("comparing is not defind between different types");
			System.exit(-1);
		}
		
		//paragwgh
				block.get(index).append("\n"+("if_icmplt LabelC"+compareLabelCounter));
				block.get(index).append("\n"+("ldc 0"));
				block.get(index).append("\n"+"goto LabelC"+(compareLabelCounter+1));
				block.get(index).append("\n"+"LabelC"+(compareLabelCounter)+":");
				block.get(index).append("\n"+("ldc 1"));
				block.get(index).append("\n"+"LabelC"+(compareLabelCounter+1)+":");
				compareLabelCounter+=2;
				
		
		createIfBlock(node);
	}
		
	@Override
	public void inABiggerEqExpression(ABiggerEqExpression node) {
		createPreElseIf(node);
	}
	@Override
	public void outABiggerEqExpression(ABiggerEqExpression node) {
		if(!(isBasicType(getExpType(node.getL())) && isBasicType(getExpType(node.getR())))){
			System.out.println("comparing is not defind between basic types");
			System.exit(-1);
		}
		if(!getExpType(node.getL()).equals(getExpType(node.getR())))
		{
			System.out.println("comparing is not defind between different types");
			System.exit(-1);
		}
		
		//paragwgh
		block.get(index).append("\n"+("if_icmpge LabelC"+compareLabelCounter));
		block.get(index).append("\n"+("ldc 0"));
		block.get(index).append("\n"+"goto LabelC"+(compareLabelCounter+1));
		block.get(index).append("\n"+"LabelC"+(compareLabelCounter)+":");
		block.get(index).append("\n"+("ldc 1"));
		block.get(index).append("\n"+"LabelC"+(compareLabelCounter+1)+":");
		compareLabelCounter+=2;
		
		createIfBlock(node);
	}
	@Override
	public void inABiggerExpression(ABiggerExpression node) {
		
		createPreElseIf(node);
		
	}
	
	
	@Override
	public void outABiggerExpression(ABiggerExpression node) {
		
		
		if(!(isBasicType(getExpType(node.getL())) && isBasicType(getExpType(node.getR())))){
			System.out.println("comparing is not defind between basic types");
			System.exit(-1);
		}
		if(!getExpType(node.getL()).equals(getExpType(node.getR())))
		{
			System.out.println("comparing is not defind between different types");
			System.exit(-1);
		}
		
				block.get(index).append("\n"+("if_icmpgt LabelC"+compareLabelCounter));
				block.get(index).append("\n"+("ldc 0"));
				block.get(index).append("\n"+"goto LabelC"+(compareLabelCounter+1));
				block.get(index).append("\n"+"LabelC"+(compareLabelCounter)+":");
				block.get(index).append("\n"+("ldc 1"));
				block.get(index).append("\n"+"LabelC"+(compareLabelCounter+1)+":");
				compareLabelCounter+=2;
				
		

		createIfBlock(node);
			
			
				
	}
	
	
	
	@Override
	public void inAElseStatement(AElseStatement node) {
		Debug.println("else");
		block.get(index).append("\n"+"goto Label"+(ifLableStack.peek()));
		block.get(index).append("\n"+("Label"+elseLableStack.pop() +":"));
	}
	
	@Override
	public void outANilqExpression(ANilqExpression node) {
		//to do
		//paragwgh 
		if(node.parent() instanceof AIfStatement)
		{
			block.get(index).append("\n"+("ifeq Label"+labelCounter));
			
		}
	}
	@Override
	public void inAOrExpression(AOrExpression node) {
		createPreElseIf(node);
	}
	
	@Override
	public void outAOrExpression(AOrExpression node) {
		if(!(getExpType(node.getL()).isBool()&&(getExpType(node.getR()).isBool())))
		{
			System.out.println("or is not defind between different types");
			System.exit(-1);
		}
		
		block.get(index).append("\n"+("ior"));
		
		createIfBlock(node);
	}
	
	@Override
	public void inAAndExpression(AAndExpression node) {
		createPreElseIf(node);
	}
	
	@Override
	public void outAAndExpression(AAndExpression node) {
		if(!(getExpType(node.getL()).isBool()&&(getExpType(node.getR()).isBool())))
		{
			System.out.println("and is not defind between different types");
			System.exit(-1);
		}
		block.get(index).append("\n"+("iand"));
		createIfBlock(node);
	}
	@Override
	public void inANotExpression(ANotExpression node) {
		createPreElseIf(node);
	}
	
	@Override
	public void outANotExpression(ANotExpression node) {
		if(!getExpType(node.getExpression()).isBool())
		{
			System.out.println("not is not defind between different types");
			System.exit(-1);
		}
		//paragwgh
		block.get(index).append("\n"+("ifeq LabelC"+compareLabelCounter));
		block.get(index).append("\n"+("ldc 0"));
		block.get(index).append("\n"+"goto LabelC"+(compareLabelCounter+1));
		block.get(index).append("\n"+"LabelC"+(compareLabelCounter)+":");
		block.get(index).append("\n"+("ldc 1"));
		block.get(index).append("\n"+"LabelC"+(compareLabelCounter+1)+":");
		compareLabelCounter+=2;
		createIfBlock(node);
	}

//	@Override
//	public void outAAtomExpression(AAtomExpression node) {
//		// TODO Auto-generated method stub
//		super.outAAtomExpression(node);
//	}
//	
//	@Override
//	public void inAAtomExpression(AAtomExpression node) {
//		// TODO Auto-generated method stub
//		super.inAAtomExpression(node);
//	}
	
	@Override
	public void outANewExpression(ANewExpression node) {
		if(!getExpType(node.getExpression()).isInt())
		{
			System.out.println("new is not defind between different types");
			System.exit(-1);
		}
	}
	public boolean isBasicType(Type type)
	{
		return	type.isBasicType();//type.equals("int")|| type.equals("char")|| type.equals("bool");
	}
	
	public Type getType(Object src)
	{
		Type type = Type.VoidType();
		if (src.getClass().getName().equals(AIntType.class.getName().toString()))
			type=Type.IntType();
		else if(src.getClass().getName().equals(ABoolType.class.getName().toString()))
			type=Type.BoolType();
		else if(src.getClass().getName().equals(ACharType.class.getName().toString()))
			type=Type.CharType();
		else if(src.getClass().getName().equals(AArrayTypeType.class.getName().toString()))
		{
			PType array =((AArrayTypeType)src).getType();
			type=new Type(Type.ARRAY,getType(array));
					//getType(array)+"[]";

		}
		else if(src.getClass().getName().equals(AListType.class.getName().toString()))
		{
			PType list =((AListType)src).getType();
			type=new Type(Type.LIST,getType(list));
			//type="list["+getType(list)+"]";
			
		}
		return type;
	}
	
	public Type getExpType(Object src)
	{
		Type type = null;
		if (src.getClass().getName().equals(AOrExpression.class.getName().toString())||
				src.getClass().getName().equals(AAndExpression.class.getName().toString())||
				src.getClass().getName().equals(ANotExpression.class.getName().toString())||
				src.getClass().getName().equals(AEqExpression.class.getName().toString())||
				src.getClass().getName().equals(ANotEqualExpression.class.getName().toString())||
				src.getClass().getName().equals(ASmallerExpression.class.getName().toString())||
				src.getClass().getName().equals(ABiggerExpression.class.getName().toString())||
				src.getClass().getName().equals(ASmallerEqExpression.class.getName().toString())||
				src.getClass().getName().equals(ABiggerEqExpression.class.getName().toString())||
				src.getClass().getName().equals(ANilqExpression.class.getName().toString())||
				src.getClass().getName().equals(ATrueExpression.class.getName().toString())||
				src.getClass().getName().equals(AFalseExpression.class.getName().toString())
			)
		{
			type=Type.BoolType();
		}
		
		else if(src.getClass().getName().equals(AAdditionExpression.class.getName().toString())||
				src.getClass().getName().equals(ASubtractionExpression.class.getName().toString())||
				src.getClass().getName().equals(AMultiplicationExpression.class.getName().toString())||
				src.getClass().getName().equals(ADivisionExpression.class.getName().toString())||
				src.getClass().getName().equals(AModuloExpression.class.getName().toString())||
				src.getClass().getName().equals(ASignnumberExpression.class.getName().toString())||
				src.getClass().getName().equals(AConstIntExpression.class.getName().toString())

				)
		{
			type=Type.IntType();
		}
		else if(src.getClass().getName().equals(ACharConstExpression.class.getName().toString()))
		{
			type=Type.CharType();
		}
		
		else if(src.getClass().getName().equals(APrExpression.class.getName().toString()))
		{
			APrExpression exp=(APrExpression) src;
			type=getExpType(exp.getExpression());
		}
		else if(src.getClass().getName().equals(AAtomExpression.class.getName().toString()))
		{
			AAtomExpression atom=(AAtomExpression) src;
			type=getAtomType(atom.getAtom());
					
		}
		
		else if(src.getClass().getName().equals(AListExpression.class.getName().toString()))
		{
			AListExpression listexp=(AListExpression) src;
			type=getExpType(listexp.getR());
					
		}
		
		else if(src.getClass().getName().equals(ANewExpression.class.getName().toString()))
		{
			ANewExpression newexp=(ANewExpression) src;//-----------------------------------------------------
			//System.out.println("new0:"+getType(newexp.getType()));
			type= new Type(Type.ARRAY,getType(newexp.getType()));
			//type=(getType(newexp.getType()))+"[]";
			//System.out.println("new1:"+type);
					
		}
		else if(src.getClass().getName().equals(ANilExpression.class.getName().toString()))
		{
			//type="list[]";
			type=Type.NilType();
		}
		else if(src.getClass().getName().equals(AHeadExpression.class.getName().toString()))
		{
			//type="list[]";
			AHeadExpression headexp=(AHeadExpression) src;
			Type tmptype=getExpType(headexp.getExpression());//Type tmptype=getExpType(getType(headexp.getExpression()));/////////////////////////bla 
			if(tmptype.isList())
			{
				//System.out.println("Start with list");
				//if(tmptype.lastIndexOf(']')+1-tmptype.indexOf('[')>0)
					//type=tmptype.substring(tmptype.indexOf('[')+1, tmptype.lastIndexOf(']'));
					type=tmptype.getInnerType();
				
			}
			else
				System.out.println("can not resolve type... head with out list");
		}
		else if(src.getClass().getName().equals(ATailExpression.class.getName().toString()))
		{
			//type="list[]";
			ATailExpression tailexp =(ATailExpression)src;
			
			if(getExpType(tailexp.getExpression()).isList())//if(getExpType(getType(tailexp.getExpression())).isList())
			{
				type=getExpType(tailexp.getExpression());	//type=getExpType(getType(tailexp.getExpression()));				
			}
			else
				System.out.println("can not resolve type... tail with out list");
			
			
		}
		
		Debug.println(src.getClass().getName()+"type:"+type);
		
		if(type==null)
			System.out.println("canot resolve expresion type"+type);
		return type;
	}
	
	public Type getAtomType(Object src)
	{
		Type type=null;
		if(src.getClass().getName().equals(AAtomAtom.class.getName().toString()))
		{
			AAtomAtom atom=(AAtomAtom) src;
			VariableKey vk =findVar(atom.getId().getText());
			if(vk==null)
				System.out.println("variable:" +vk.id+ "does not exist in this scope");
			else{
				type=vk.type;
			
			}
		}
		else if(src.getClass().getName().equals(AStringLiteralAtom.class.getName().toString()))
		{
			type= new Type(Type.ARRAY,Type.CharType());//"char[]";
		}
		else if(src.getClass().getName().equals(AIndAccessAtom.class.getName().toString()))
		{
			AIndAccessAtom atom=(AIndAccessAtom) src;
			
			type=getAtomType(atom.getAtom());
			System.out.println(type);
			if(type.isArray())
				type=type.getInnerType();//type=type.substring(0, type.lastIndexOf('['));
			else
				System.out.println("can not resolve type... array accest with  out an array");
		}
		else if(src.getClass().getName().equals(AAcallAtom.class.getName().toString()))
		{
			//---------------------------------
			AAcallAtom scall=(AAcallAtom) src;
			ACallCall node =(ACallCall)scall.getCall();
			String fName=node.getId().getText();
			FunctionKey fk = new FunctionKey(fName);
			int line = node.getId().getLine();
			Object objP = node.getRealParList();
			if(!node.getRealParList().isEmpty())
			{
				//System.out.println("kai edw");
				objP=node.getRealParList().getFirst();
					//System.out.println("edw");
				while(objP instanceof AMultiRealParRealParList)
				{
					AMultiRealParRealParList par=(AMultiRealParRealParList)objP;
					Type exptype=getExpType(par.getExpression());
					fk.addParameterType(exptype);
					objP=par.getRealParList();
					
					
				}
				ASigleRealParRealParList par=(ASigleRealParRealParList)objP;
				Type exptype=getExpType(par.getExpression());
				fk.addParameterType(exptype);
			}
			
			boolean defined=false;
			FunctionKey definedfk = findFunc(fk);
			if(definedfk!=null)
			{
				defined=true;
				type=definedfk.type;
				//System.out.println(definedfk.id+":"+definedfk.type);
			}
			
			if(!defined)
			{
				System.out.println("Line " + line + ": " +" Variable " + fName +" is not defined");
			}
			//------------------------------------------
		}
		
		//System.out.println("at:"+type);
		if(type==null)
		System.out.println("canot resolve atom type");
		return type;
	}
	
	VariableKey findVar(String id)
	{
		MyST temp=symtable;
		VariableKey vk = new VariableKey(id);
		
		while (temp!=null)
		{
			if(temp.lookUp(vk))
			{
				//System.out.println("vk:"+((VariableKey)temp.getBykey(vk)).type);
				return (VariableKey)temp.getBykey(vk);
			}
				
			
			temp=temp.parent;
		}
		return null;
	}
	
	FunctionKey findFunc(FunctionKey fk)
	{
		MyST temp=symtable;
		tempParent=temp;
		while (temp!=null)
		{
			if(temp.lookUp(fk))
			{
				//System.out.println("fk:"+((FunctionKey)temp.getBykey(fk)).id+((FunctionKey)temp.getBykey(fk)).type);
				return (FunctionKey)temp.getBykey(fk);
			}
				
			//tempParent=temp;
			temp=temp.parent;
			tempParent=temp;
		}
		
		return null;
	}
	
}
