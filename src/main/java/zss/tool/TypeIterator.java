package zss.tool;

import java.util.Iterator;
import java.util.NoSuchElementException;

@Version("2020.02.23")
public class TypeIterator<T> implements Iterator<T>, Iterable<T> {
    private final Iterator<?> iterator;
    private final Class<T> type;
    private T value;

    public TypeIterator(final Iterator<?> iterator, final Class<T> type) {
        this.iterator = ObjectTool.defaultIfNull(iterator, NullIterator.getObjectNullIterator());
        this.type = type;
    }

    public TypeIterator(final Iterable<?> iterable, final Class<T> type) {
        if (iterable == null) {
            this.iterator = NullIterator.getObjectNullIterator();
        } else {
            this.iterator = ObjectTool.defaultIfNull(iterable.iterator(), NullIterator.getObjectNullIterator());
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
    public T next() {
        if (hasNext()) {
            final T value = this.value;
            this.value = null;
            return value;
        }
        throw new NoSuchElementException();
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
