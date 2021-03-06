/* This file was generated by SableCC (http://www.sablecc.org/). */

package tony.node;

import java.util.*;
import tony.analysis.*;

public final class ASingleParameters extends PParameters
{
    private PFormal _formal_;

    public ASingleParameters()
    {
    }

    public ASingleParameters(
        PFormal _formal_)
    {
        setFormal(_formal_);

    }
    public Object clone()
    {
        return new ASingleParameters(
            (PFormal) cloneNode(_formal_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASingleParameters(this);
    }

    public PFormal getFormal()
    {
        return _formal_;
    }

    public void setFormal(PFormal node)
    {
        if(_formal_ != null)
        {
            _formal_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _formal_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_formal_);
    }

    void removeChild(Node child)
    {
        if(_formal_ == child)
        {
            _formal_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_formal_ == oldChild)
        {
            setFormal((PFormal) newChild);
            return;
        }

    }
}
