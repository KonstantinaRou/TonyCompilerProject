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
	LinkedList<String> parametersType;
	String type;
	
	public FunctionKey(String id) {
		this.id=id;
		parametersType= new LinkedList<String>();
		this.type="";
			
	}
	
	public FunctionKey() {
		this("");
			
	}
	public FunctionKey(String id , String rType) {
		this(id);
		this.type=rType;
			
	}
	
	public FunctionKey(String id2, List<String> par) {
		this.id=id;
		parametersType= new LinkedList<String>(par);
		this.type="";
	}

	public void setId(String id)
	{
		this.id=id;
	}
	
	public void setType(String type)
	{
		this.type=type;
	}
	
	public void addParameterType(String type)
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
	
	
	
	
}