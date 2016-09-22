import java.util.ArrayList;
import java.util.Hashtable;

public class MyST
{
	MyST parent;
	Hashtable symtable;
	
	public MyST(MyST parent) {
		this.parent=parent;
		symtable=new Hashtable();
	}
	
	public MyST getParent()
	{
		return parent;
	}
	
	public void instert(Object k, Object v)
	{
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