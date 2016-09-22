import java.awt.Point;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;


public class VariableKey
{
	String id,type;
	
	public VariableKey(String id) {
		this.id=id;
			
	}
	
	
	
	public VariableKey(String id, String type) {
		this.id=id;
		this.type= type;
	}



	public void setId(String id)
	{
		this.id=id;
	}
	
	
	@Override
	public boolean equals(Object arg0) {
		VariableKey vk;
		
		if (! (arg0 instanceof VariableKey)) 
			return false;
		else 
		{
			vk = (VariableKey) arg0;
			 return (this.id.equals(vk.id));
			
		}
		
	}
	
	@Override
	public int hashCode() {
		
		return id.hashCode();
		//return id.hashCode()+parametersType.hashCode();
	}
	
	
	
	
}