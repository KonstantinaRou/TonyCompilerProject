/* This file was generated by SableCC (http://www.sablecc.org/). */

package tony.node;

import java.util.*;
import tony.analysis.*;

public final class AScallSimple extends PSimple
{
    private PCall _call_;

    public AScallSimple()
    {
    }

    public AScallSimple(
        PCall _call_)
    {
        setCall(_call_);

    }
    public Object clone()
    {
        return new AScallSimple(
            (PCall) cloneNode(_call_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAScallSimple(this);
    }

    public PCall getCall()
    {
        return _call_;
    }

    public void setCall(PCall node)
    {
        if(_call_ != null)
        {
            _call_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _call_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_call_);
    }

    void removeChild(Node child)
    {
        if(_call_ == child)
        {
            _call_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_call_ == oldChild)
        {
            setCall((PCall) newChild);
            return;
        }

    }
}
