import java.util.ArrayList;
import java.util.Hashtable;

public class MyST
{
	MyST parent;
	Hashtable symtable;
	int maxStack=0,maxlockal=0;
	String name;
	ArrayList<VariableKey> vars = new ArrayList<VariableKey>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MyST(MyST parent) {
		this.parent=parent;
		symtable=new Hashtable();
		if(parent != null)
		{
			maxlockal+=parent.maxlockal;
		}
	}
	
	public MyST getParent()
	{
		return parent;
	}
	
	public void instert(Object k, Object v)
	{
		if(k instanceof VariableKey)
		{
			maxlockal++;
			vars.add((VariableKey)k);
		}
		
		if(k instanceof FunctionKey)
		{
			//if ()
			if(((FunctionKey)k).parametersType.size()>maxStack)
			{
				maxStack=((FunctionKey)k).parametersType.size();
			}
		}
		symtable.put(k, v);
		//symtable.add(k);
	}
	
	public boolean lookUp(Object k)
	{
		//return symtable.contains(k);
		return symtable.containsKey(k);
	}
	
	public  Object  getBykey(Object k)
	{
		//return symtable.contains(k);
		return symtable.get(k);
	}
	
	void a(String a){}
	void b(Integer a){}
	

	
	
	
	
	
}