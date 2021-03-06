/* This file was generated by SableCC (http://www.sablecc.org/). */

package tony.node;

import java.util.*;
import tony.analysis.*;

public final class AVarDefinitionFunction extends PFunction
{
    private PVarDefinition _varDefinition_;

    public AVarDefinitionFunction()
    {
    }

    public AVarDefinitionFunction(
        PVarDefinition _varDefinition_)
    {
        setVarDefinition(_varDefinition_);

    }
    public Object clone()
    {
        return new AVarDefinitionFunction(
            (PVarDefinition) cloneNode(_varDefinition_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAVarDefinitionFunction(this);
    }

    public PVarDefinition getVarDefinition()
    {
        return _varDefinition_;
    }

    public void setVarDefinition(PVarDefinition node)
    {
        if(_varDefinition_ != null)
        {
            _varDefinition_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _varDefinition_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_varDefinition_);
    }

    void removeChild(Node child)
    {
        if(_varDefinition_ == child)
        {
            _varDefinition_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_varDefinition_ == oldChild)
        {
            setVarDefinition((PVarDefinition) newChild);
            return;
        }

    }
}
