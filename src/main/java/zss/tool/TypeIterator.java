package zss.tool;

import java.util.Iterator;

@Version("2017.02.21")
public class TypeIterator<T> implements Iterator<T>, Iterable<T>
{
    private final Iterator<?> iterator;
    private final Class<T> type;
    private T value;

    public TypeIterator(final Iterator<?> iterator, final Class<T> type)
    {
        this.iterator = iterator;
        this.type = type;
    }

    @Override
    public boolean hasNext()
    {
        if (value != null)
        {
            return true;
        }
        while (iterator.hasNext())
        {
            Object value = iterator.next();
            if (type.isInstance(value))
            {
                this.value = type.cast(value);
                return true;
            }
        }
        return false;
    }

    @Override
    public T next()
    {
        if (hasNext())
        {
            final T value = this.value;
            this.value = null;
            return value;
        }
        return null;
    }

    @Override
    public void remove()
    {
        iterator.remove();
    }

    @Override
    public Iterator<T> iterator()
    {
        return this;
    }
}
