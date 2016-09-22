/* This file was generated by SableCC (http://www.sablecc.org/). */

package tony.node;

import java.util.*;
import tony.analysis.*;

public final class AConstIntExpression extends PExpression
{
    private TConstInt _constInt_;

    public AConstIntExpression()
    {
    }

    public AConstIntExpression(
        TConstInt _constInt_)
    {
        setConstInt(_constInt_);

    }
    public Object clone()
    {
        return new AConstIntExpression(
            (TConstInt) cloneNode(_constInt_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAConstIntExpression(this);
    }

    public TConstInt getConstInt()
    {
        return _constInt_;
    }

    public void setConstInt(TConstInt node)
    {
        if(_constInt_ != null)
        {
            _constInt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _constInt_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_constInt_);
    }

    void removeChild(Node child)
    {
        if(_constInt_ == child)
        {
            _constInt_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_constInt_ == oldChild)
        {
            setConstInt((TConstInt) newChild);
            return;
        }

    }
}