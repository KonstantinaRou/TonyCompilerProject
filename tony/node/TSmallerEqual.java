/* This file was generated by SableCC (http://www.sablecc.org/). */

package tony.node;

import tony.analysis.*;

public final class TSmallerEqual extends Token
{
    public TSmallerEqual()
    {
        super.setText("<=");
    }

    public TSmallerEqual(int line, int pos)
    {
        super.setText("<=");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TSmallerEqual(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTSmallerEqual(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TSmallerEqual text.");
    }
}
