/* This file was generated by SableCC (http://www.sablecc.org/). */

package tony.node;

import java.util.*;
import tony.analysis.*;

public final class APlusExpression extends PExpression
{

    public APlusExpression()
    {
    }
    public Object clone()
    {
        return new APlusExpression();
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAPlusExpression(this);
    }

    public String toString()
    {
        return "";
    }

    void removeChild(Node child)
    {
    }

    void replaceChild(Node oldChild, Node newChild)
    {
    }
}
