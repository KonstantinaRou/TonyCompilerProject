/* This file was generated by SableCC (http://www.sablecc.org/). */

package tony.node;

import java.util.*;
import tony.analysis.*;

public final class AElseStatement extends PStatement
{
    private final LinkedList _statement_ = new TypedLinkedList(new Statement_Cast());

    public AElseStatement()
    {
    }

    public AElseStatement(
        List _statement_)
    {
        {
            this._statement_.clear();
            this._statement_.addAll(_statement_);
        }

    }
    public Object clone()
    {
        return new AElseStatement(
            cloneList(_statement_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAElseStatement(this);
    }

    public LinkedList getStatement()
    {
        return _statement_;
    }

    public void setStatement(List list)
    {
        _statement_.clear();
        _statement_.addAll(list);
    }

    public String toString()
    {
        return ""
            + toString(_statement_);
    }

    void removeChild(Node child)
    {
        if(_statement_.remove(child))
        {
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        for(ListIterator i = _statement_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set(newChild);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

    }

    private class Statement_Cast implements Cast
    {
        public Object cast(Object o)
        {
            PStatement node = (PStatement) o;

            if((node.parent() != null) &&
                (node.parent() != AElseStatement.this))
            {
                node.parent().removeChild(node);
            }

            if((node.parent() == null) ||
                (node.parent() != AElseStatement.this))
            {
                node.parent(AElseStatement.this);
            }

            return node;
        }
    }
}