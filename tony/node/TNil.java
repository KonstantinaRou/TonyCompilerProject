/* This file was generated by SableCC (http://www.sablecc.org/). */

package tony.node;

import tony.analysis.*;

public final class TNil extends Token
{
    public TNil()
    {
        super.setText("nil");
    }

    public TNil(int line, int pos)
    {
        super.setText("nil");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TNil(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTNil(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TNil text.");
    }
}