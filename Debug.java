
public class Debug {
	
	public static boolean on=false;
	
	public static void println(Object src)
	{
		if(on)
		System.out.println(src);
	}

}
