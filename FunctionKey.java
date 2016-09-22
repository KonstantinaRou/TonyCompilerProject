import java.awt.Point;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;


public class FunctionKey
{
	String id;
	LinkedList<Type> parametersType;
	Type type;
	
	public FunctionKey(String id) {
		this.id=id;
		parametersType= new LinkedList<Type>();
		this.type=Type.VoidType();
			
	}
	
	public FunctionKey(String id , Type rType) {
		this(id);
		this.type=rType;
			
	}
	
	public FunctionKey(String id2, List<Type> par) {
		this.id=id;
		parametersType= new LinkedList<Type>(par);
		this.type=Type.VoidType();
	}

	public void setId(String id)
	{
		this.id=id;
	}
	
	public void setType(Type type)
	{
		this.type=type;
	}
	
	public void addParameterType(Type type)
	{
		parametersType.add(type);
	}
	
	@Override
	public boolean equals(Object arg0) {
		FunctionKey fk;
		
		if (! (arg0 instanceof FunctionKey)) 
			return false;
		else 
		{
			 fk = (FunctionKey) arg0;
			 return (this.id.equals(fk.id)&& parametersType.equals(fk.parametersType));
			
		}
		
	}
	
	@Override
	public int hashCode() {
		
		return id.hashCode();
		//return id.hashCode()+parametersType.hashCode();
	}
	
	public String getFormalsString()
	{
		String a="";
		for (Type type : parametersType) {
			if(type.isInt())
				a+="I";
		}
		
		return a;
	}
	
	public String getReturnTypeString()
	{
		if(type.isInt())
			return "I";
		else //(type.isVoid())
			return"V";
		
	}
	
	
	
}