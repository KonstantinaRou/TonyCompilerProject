/* This file was generated by SableCC (http://www.sablecc.org/). */

package tony.node;

import java.util.*;
import tony.analysis.*;

public final class AListType extends PType
{
    private PType _type_;

    public AListType()
    {
    }

    public AListType(
        PType _type_)
    {
        setType(_type_);

    }
    public Object clone()
    {
        return new AListType(
            (PType) cloneNode(_type_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAListType(this);
    }

    public PType getType()
    {
        return _type_;
    }

    public void setType(PType node)
    {
        if(_type_ != null)
        {
            _type_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _type_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_type_);
    }

    void removeChild(Node child)
    {
        if(_type_ == child)
        {
            _type_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_type_ == oldChild)
        {
            setType((PType) newChild);
            return;
        }

    }
}
