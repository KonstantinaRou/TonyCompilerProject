/* This file was generated by SableCC (http://www.sablecc.org/). */

package tony.node;

import java.util.*;
import tony.analysis.*;

public final class ASkipSimple extends PSimple
{

    public ASkipSimple()
    {
    }
    public Object clone()
    {
        return new ASkipSimple();
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASkipSimple(this);
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
