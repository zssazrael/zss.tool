package zss.tool;

import java.util.LinkedList;

@Version("2017.02.21")
public class ObjectList extends LinkedList<Object>
{
    private static final long serialVersionUID = 20170221173203L;

    public <T> T get(final int index, final Class<T> type)
    {
        final Object object = get(index);
        if (type.isInstance(object))
        {
            return type.cast(object);
        }
        return null;
    }

    public ObjectList getList(final int index)
    {
        return get(index, ObjectList.class);
    }

    public StringObjectMap getMap(final int index)
    {
        return get(index, StringObjectMap.class);
    }

    public StringObjectMap addMap()
    {
        final StringObjectMap map = new StringObjectMap();
        addLast(map);
        return map;
    }

    public <T> TypeIterator<T> iterator(final Class<T> type)
    {
        return new TypeIterator<>(iterator(), type);
    }
}
