import tony.lexer.Lexer;
import tony.node.*;
public class MyLexer extends Lexer
{
	private int count;
	private TComment comment;
	private StringBuffer text;
	
	public MyLexer(java.io.PushbackReader in)
	{
		super(in);
	}
	protected void filter()
	{ 
			if(state.equals(State.COMMENT))
			{ 
				if(comment == null)
				{ 
					comment = (TComment) token;
					text = new StringBuffer(comment.getText());
					count = 1;
					token = null; 
				}
				else
				{ 
					text.append(token.getText()); 
					if(token instanceof TComment)
						count++;
					else if(token instanceof TCommentEnd)
						count--;
					if(count != 0)
						token = null; 
					else
					{ 
						comment.setText(text.toString());
						token = comment; 
						state = State.NORMAL; 
						comment = null; 
					}
				}
		}
	}
}