import tony.analysis.*;
import tony.node.*;

import java.util.*;

public class Myvisitor extends DepthFirstAdapter 
{
	private MyST symtable;	
	
	static int scopeCounter=-1;
	

	Myvisitor(MyST symtable) 
	{
		this.symtable = symtable;
		//System.out.println("type"+AIntType.class.getName().toString());
		FunctionKey fk = new FunctionKey("puti","");
		fk.addParameterType("int");
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("putb","");
		fk.addParameterType("bool");
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("putc","");
		fk.addParameterType("char");
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("puts","");
		fk.addParameterType("char[]");
		symtable.instert(fk,fk);
		
		
		fk = new FunctionKey("geti","int");
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("getb","bool");
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("getc","char");
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("gets","");
		fk.addParameterType("int");
		fk.addParameterType("char[]");
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("abs","int");
		fk.addParameterType("int");
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("ord","int");
		fk.addParameterType("char");
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("chr","char");
		fk.addParameterType("int");
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("strlen","int");
		fk.addParameterType("char[]");
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("strcmp","int");
		fk.addParameterType("char[]");
		fk.addParameterType("char[]");
		symtable.instert(fk,fk);
		
		fk = new FunctionKey("strcpy","");
		fk.addParameterType("char[]");
		fk.addParameterType("char[]");
		
		fk = new FunctionKey("strcat","");
		fk.addParameterType("char[]");
		fk.addParameterType("char[]");
		symtable.instert(fk,fk);

	}

	@Override
	public void inAFunctionDefFunction(AFunctionDefFunction node) 
	{
		
		scopeCounter++;
		//-------------------------------------------------------------------
		FunctionKey fk = new FunctionKey();
		AHeader header = (AHeader) node.getHeader();
		String fName = header.getId().getText();
		String type="void";
		if(!header.getType().isEmpty()){
			
			type=getType(header.getType().getFirst());
		}
		
		fk.setId(fName);
		//System.out.println("f:("+fName+")type:("+type+")");
		fk.setType(type);
		
		
		if(!header.getParameters().isEmpty())
		{
			Object objP = header.getParameters().getFirst();
			while(objP instanceof AMultiParameters)
			{
				AMultiParameters par=(AMultiParameters)objP;
				AFormalFormal formal=(AFormalFormal)par.getFormal();
				
				String ftype=getType(formal.getType());
				
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
			
			String ftype=getType(formal.getType());
			
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
		
	}
	
	public void outAFunctionDefFunction(AFunctionDefFunction node) 
	{
		symtable=symtable.parent;
		scopeCounter--;
	}
	
	
	@Override
	public void inAFormalFormal(AFormalFormal node) {
		String type =getType(node.getType());
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
		
		String type=getType(node.getType());
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
				String type=getExpType(par.getExpression());
				fk.addParameterType(type);
				
				objP=par.getRealParList();
			}
			
			ASigleRealParRealParList par=(ASigleRealParRealParList)objP;
			String type=getExpType(par.getExpression());
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
		
	}
	
	@Override
	public void outAAtomassSimple(AAtomassSimple node) {
		//System.out.println("cmp:"+getAtomType(node.getAtom())+"="+getExpType(node.getExpression()));
		if(!getAtomType(node.getAtom()).equals(getExpType(node.getExpression())))
			System.out.println("assigning variable type:"+getAtomType(node.getAtom())+" incompatible type:"+getExpType(node.getExpression()));
	}
	
	@Override
	public void outAIfStatement(AIfStatement node) {
		if(!getExpType(node.getExpression()).equals("bool"))
		{
			System.out.println("if expression is not bool");
			System.exit(-1);
		}
	}

	@Override
	public void outAElseifStatement(AElseifStatement node) {
		if(!getExpType(node.getExpression()).equals("bool"))
		{
			System.out.println("else if expression is not bool");
			System.exit(-1);
		}
	}
	@Override
	public void outAForStatement(AForStatement node) {
		if(!getExpType(node.getExpression()).equals("bool"))
		{
			System.out.println("for expression is not bool");
			System.exit(-1);
		}
	}	
	@Override
	public void outAAdditionExpression(AAdditionExpression node) {
		if(!(getExpType(node.getL()).equals("int")&& getExpType(node.getR()).equals("int")))
		{
			System.out.println("addition expression is not int");
			System.exit(-1);
		}
	}
	@Override
	public void outASubtractionExpression(ASubtractionExpression node) {
		if(!(getExpType(node.getL()).equals("int")&& getExpType(node.getR()).equals("int")))
		{
			System.out.println("subtraction expression is not int");
			System.exit(-1);
		}
	}
	
	@Override
	public void outAMultiplicationExpression(AMultiplicationExpression node) {
		if(!(getExpType(node.getL()).equals("int")&& getExpType(node.getR()).equals("int")))
		{
			System.out.println("multiplication expression is not int");
			System.exit(-1);
		}
	}

	
	@Override
	public void outADivisionExpression(ADivisionExpression node) {
		if(!(getExpType(node.getL()).equals("int")&& getExpType(node.getR()).equals("int")))
		{
			System.out.println("division expression is not int");
			System.exit(-1);
		}
	}
	@Override
	public void outAModuloExpression(AModuloExpression node) {
		if(!(getExpType(node.getL()).equals("int")&& getExpType(node.getR()).equals("int")))
		{
			System.out.println("modulo expression is not int");
			System.exit(-1);
		}
	}
	
	@Override
	public void outASignnumberExpression(ASignnumberExpression node) {
		if(!(getExpType(node.getL()).equals("int")&& getExpType(node.getR()).equals("int")))
		{
			System.out.println("sign number expression is not int");
			System.exit(-1);
		}
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
	}
	
	@Override
	public void outAOrExpression(AOrExpression node) {
		if(!(getExpType(node.getL()).equals("bool")&&(getExpType(node.getR()).equals("bool"))))
		{
			System.out.println("or is not defind between different types");
			System.exit(-1);
		}
	}
	@Override
	public void outAAndExpression(AAndExpression node) {
		if(!(getExpType(node.getL()).equals("bool")&&(getExpType(node.getR()).equals("bool"))))
		{
			System.out.println("and is not defind between different types");
			System.exit(-1);
		}
	}
	@Override
	public void outANotExpression(ANotExpression node) {
		if(!getExpType(node.getExpression()).equals("bool"))
		{
			System.out.println("not is not defind between different types");
			System.exit(-1);
		}
	}

	@Override
	public void outANewExpression(ANewExpression node) {
		if(!getExpType(node.getExpression()).equals("int"))
		{
			System.out.println("new is not defind between different types");
			System.exit(-1);
		}
	}
	public boolean isBasicType(String type)
	{
		return	type.equals("int")|| type.equals("char")|| type.equals("bool");
	}
	
	public String getType(Object src)
	{
		String type = null;
		if (src.getClass().getName().equals(AIntType.class.getName().toString()))
			type="int";
		else if(src.getClass().getName().equals(ABoolType.class.getName().toString()))
			type="bool";
		else if(src.getClass().getName().equals(ACharType.class.getName().toString()))
			type="char";
		else if(src.getClass().getName().equals(AArrayTypeType.class.getName().toString()))
		{
			PType array =((AArrayTypeType)src).getType();
			type=getType(array)+"[]";

		}
		else if(src.getClass().getName().equals(AListType.class.getName().toString()))
		{
			PType list =((AListType)src).getType();
			type="list["+getType(list)+"]";
			
		}
		return type;
	}
	
	public String getExpType(Object src)
	{
		String type = null;
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
			type="bool";
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
			type="int";
		}
		else if(src.getClass().getName().equals(ACharConstExpression.class.getName().toString()))
		{
			type="char";
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
			type=(getType(newexp.getType()))+"[]";
			//System.out.println("new1:"+type);
					
		}
		else if(src.getClass().getName().equals(ANilExpression.class.getName().toString()))
		{
			type="list[]";
		}
		else if(src.getClass().getName().equals(AHeadExpression.class.getName().toString()))
		{
			//type="list[]";
			AHeadExpression headexp=(AHeadExpression) src;
			String tmptype=getExpType(getType(headexp.getExpression()));
			if(tmptype.startsWith("list"))
			{
				System.out.println("Start with list");
				if(tmptype.lastIndexOf(']')+1-tmptype.indexOf('[')>0)
					type=tmptype.substring(tmptype.indexOf('[')+1, tmptype.lastIndexOf(']'));
				
			}
			else
				System.out.println("can not resolve type... head with out list");
		}
		else if(src.getClass().getName().equals(ATailExpression.class.getName().toString()))
		{
			//type="list[]";
			ATailExpression tailexp =(ATailExpression)src;
			
			if(getExpType(getType(tailexp.getExpression())).startsWith("list"))
			{
				type=getExpType(getType(tailexp.getExpression()));				
			}
			else
				System.out.println("can not resolve type... tail with out list");
		}
		if(type==null)
			System.out.println("canot resolve expresion type"+type);
		return type;
	}
	
	public String getAtomType(Object src)
	{
		String type=null;
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
			type="char[]";
		}
		else if(src.getClass().getName().equals(AIndAccessAtom.class.getName().toString()))
		{
			AIndAccessAtom atom=(AIndAccessAtom) src;
			
			type=getAtomType(atom.getAtom());
			System.out.println(type);
			if(type.endsWith("[]"))
				type=type.substring(0, type.lastIndexOf('['));
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
					String exptype=getExpType(par.getExpression());
					fk.addParameterType(exptype);
					objP=par.getRealParList();
					
					
				}
				ASigleRealParRealParList par=(ASigleRealParRealParList)objP;
				String exptype=getExpType(par.getExpression());
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
		
		while (temp!=null)
		{
			if(temp.lookUp(fk))
			{
				//System.out.println("fk:"+((FunctionKey)temp.getBykey(fk)).id+((FunctionKey)temp.getBykey(fk)).type);
				return (FunctionKey)temp.getBykey(fk);
			}
				
			
			temp=temp.parent;
		}
		
		return null;
	}
	
}
