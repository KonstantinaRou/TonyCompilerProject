/* This file was generated by SableCC (http://www.sablecc.org/). */

package tony.node;

import java.util.*;
import tony.analysis.*;

public final class ACharConstExpression extends PExpression
{
    private TConstChar _constChar_;

    public ACharConstExpression()
    {
    }

    public ACharConstExpression(
        TConstChar _constChar_)
    {
        setConstChar(_constChar_);

    }
    public Object clone()
    {
        return new ACharConstExpression(
            (TConstChar) cloneNode(_constChar_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACharConstExpression(this);
    }

    public TConstChar getConstChar()
    {
        return _constChar_;
    }

    public void setConstChar(TConstChar node)
    {
        if(_constChar_ != null)
        {
            _constChar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _constChar_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_constChar_);
    }

    void removeChild(Node child)
    {
        if(_constChar_ == child)
        {
            _constChar_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_constChar_ == oldChild)
        {
            setConstChar((TConstChar) newChild);
            return;
        }

    }
}
