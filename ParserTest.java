import java.io.*;

import tony.lexer.Lexer;
import tony.parser.Parser;
import tony.node.*;

import java.util.*;

public class ParserTest
{
	public static String CLASSNAME,PATH;
	
  public static void main(String[] args)
  {
	  int end = args[0].indexOf('.');
	  PATH=args[0].substring(0, end).concat(".j");

	  System.out.println(PATH);
	  int start = args[0].lastIndexOf('\\');
	  start++;
	  CLASSNAME=args[0].substring(start, end);
	  System.out.println(CLASSNAME);
    try
    {
      Parser parser =
        new Parser(
        new Lexer(
        new PushbackReader(
        new FileReader(args[0].toString()), 1024)));

     //Hashtable symtable =  new Hashtable();
     Start ast = parser.parse();
     ast.apply(new Myvisitor(new MyST(null)));
     //ast.apply(new Myvisitor(null));
     /* Gia ton deutero visitor grapste thn entolh
      * ast.apply(new mysecondvisitor(symtable));
      */
    }
    catch (Exception e)
    {
      System.err.println(e);
    }
    
  
    
    
  }
  
  
}

