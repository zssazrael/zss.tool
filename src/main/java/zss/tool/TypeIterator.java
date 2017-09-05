package zss.tool;

import java.util.Iterator;

@Version("2017.09.06")
public class TypeIterator<T> implements Iterator<T>, Iterable<T> {
    private static final NullIterator NULL_ITERATOR = new NullIterator();

    private final Iterator<?> iterator;
    private final Class<T> type;
    private T value;

    public TypeIterator(final Iterator<?> iterator, final Class<T> type) {
        this.iterator = ObjectTool.defaultIfNull(iterator, NULL_ITERATOR);
        this.type = type;
    }

    public TypeIterator(final Iterable<?> iterable, final Class<T> type) {
        if (iterable == null) {
            this.iterator = NULL_ITERATOR;
        } else {
            this.iterator = ObjectTool.defaultIfNull(iterable.iterator(), NULL_ITERATOR);
        }
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

    private static class NullIterator implements Iterator<Object> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }

        @Override
        public void remove() {
        }
    }
}
