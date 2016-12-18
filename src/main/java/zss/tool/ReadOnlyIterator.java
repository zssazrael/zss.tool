package zss.tool;

import java.util.Iterator;

@Version("2013-12-18")
public final class ReadOnlyIterator<T> implements Iterator<T>
{
    private final Iterator<T> iterator;

    public ReadOnlyIterator(final Iterator<T> iterator)
    {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext()
    {
        return iterator.hasNext();
    }

    @Override
    public T next()
    {
        return iterator.next();
    }

    @Override
    public void remove()
    {
    }
}
