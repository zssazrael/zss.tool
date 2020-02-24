package zss.tool;

import java.util.Iterator;
import java.util.NoSuchElementException;

@Version("2020.02.23")
public class NullIterator<T> implements Iterator<T> {
    private static final NullIterator<Object> OBJECT_NULL_ITERATOR = new NullIterator<>();

    public static NullIterator<Object> getObjectNullIterator() {
        return OBJECT_NULL_ITERATOR;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
    }
}
