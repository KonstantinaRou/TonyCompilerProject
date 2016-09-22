/* This file was generated by SableCC (http://www.sablecc.org/). */

package tony.node;

import tony.analysis.*;

public final class TMinus extends Token
{
    public TMinus()
    {
        super.setText("-");
    }

    public TMinus(int line, int pos)
    {
        super.setText("-");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TMinus(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTMinus(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TMinus text.");
    }
}
