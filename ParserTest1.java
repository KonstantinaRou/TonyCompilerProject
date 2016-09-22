import java.io.*;
import tony.lexer.Lexer;
import tony.parser.Parser;
import tony.node.Start;

public class ParserTest1
{
  public static void main(String[] args)
  {
    try
    {
      Parser parser =
        new Parser(
        new MyLexer(
        new PushbackReader(
        new FileReader(args[0].toString()), 1024)));

      Start ast = parser.parse();
	  System.out.println(ast);
    }
    catch (Exception e)
    {
      System.err.println(e);
    }
  }
}

