/* This file was generated by SableCC (http://www.sablecc.org/). */

package tony.node;

import tony.analysis.*;

public final class TBigger extends Token
{
    public TBigger()
    {
        super.setText(">");
    }

    public TBigger(int line, int pos)
    {
        super.setText(">");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TBigger(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTBigger(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TBigger text.");
    }
}
