/* This file was generated by SableCC (http://www.sablecc.org/). */

package tony.node;

import java.util.*;
import tony.analysis.*;

public final class AFunctionDeclFunction extends PFunction
{
    private PHeader _header_;

    public AFunctionDeclFunction()
    {
    }

    public AFunctionDeclFunction(
        PHeader _header_)
    {
        setHeader(_header_);

    }
    public Object clone()
    {
        return new AFunctionDeclFunction(
            (PHeader) cloneNode(_header_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFunctionDeclFunction(this);
    }

    public PHeader getHeader()
    {
        return _header_;
    }

    public void setHeader(PHeader node)
    {
        if(_header_ != null)
        {
            _header_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _header_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_header_);
    }

    void removeChild(Node child)
    {
        if(_header_ == child)
        {
            _header_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_header_ == oldChild)
        {
            setHeader((PHeader) newChild);
            return;
        }

    }
}
