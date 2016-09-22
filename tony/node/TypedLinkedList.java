/* This file was generated by SableCC (http://www.sablecc.org/). */

package tony.node;

import java.util.*;

public class TypedLinkedList extends LinkedList
{
    Cast cast;

    public TypedLinkedList()
    {
        super();

        cast = NoCast.instance;
    }

    public TypedLinkedList(Collection c)
    {
        super(c);

        cast = NoCast.instance;
    }

    public TypedLinkedList(Cast cast)
    {
        super();

        this.cast = cast;
    }

    public TypedLinkedList(Collection c, Cast cast)
    {
        super(c);

        this.cast = cast;
    }

    public Cast getCast()
    {
        return cast;
    }

    public void add(int index, Object element)
    {
        super.add(index, cast.cast(element));
    }

    public boolean add(Object o)
    {
        return super.add(cast.cast(o));
    }

    public boolean addAll(Collection c)
    {
        Object[] elements = c.toArray();
        for(int i=0; i<elements.length; i++)
        {
            super.add(cast.cast(elements[i]));
        }
        return true;
    }

    public boolean addAll(int index, Collection c)
    {
        int pos = index;
        Object[] elements = c.toArray();
        for(int i=0; i<elements.length; i++)
        {
            super.add(pos++, cast.cast(elements[i]));
        }
        return true;
    }

    public void addFirst(Object o)
    {
        super.addFirst(cast.cast(o));
    }

    public void addLast(Object o)
    {
        super.addLast(cast.cast(o));
    }

    public ListIterator listIterator(int index)
    {
        return new TypedLinkedListIterator(super.listIterator(index));
    }

    private class TypedLinkedListIterator implements ListIterator
    {
        ListIterator iterator;

        TypedLinkedListIterator(ListIterator iterator)
        {
            this.iterator = iterator;
        }

        public boolean hasNext()
        {
            return iterator.hasNext();
        }

        public Object next()
        {
            return iterator.next();
        }

        public boolean hasPrevious()
        {
            return iterator.hasPrevious();
        }

        public Object previous()
        {
            return iterator.previous();
        }

        public int nextIndex()
        {
            return iterator.nextIndex();
        }

        public int previousIndex()
        {
            return iterator.previousIndex();
        }

        public void remove()
        {
            iterator.remove();
        }

        public void set(Object o)
        {
            iterator.set(cast.cast(o));
        }

        public void add(Object o)
        {
            iterator.add(cast.cast(o));
        }
    }
}
