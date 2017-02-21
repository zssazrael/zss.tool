package zss.tool;

import java.util.TreeMap;

@Version("2017.02.21")
public class StringObjectMap extends TreeMap<String, Object>
{
    private static final long serialVersionUID = 20150424223016L;

    public <T> T get(final String key, final Class<T> type)
    {
        final Object object = get(key);
        if (type.isInstance(object))
        {
            return type.cast(object);
        }
        return null;
    }

    public int getInteger(final String key, final int defaultValue)
    {
        final Integer value = get(key, Integer.class);
        if (value == null)
        {
            return defaultValue;
        }
        return value.intValue();
    }

    public Integer getInteger(final String key)
    {
        return get(key, Integer.class);
    }

    public String getString(final String key, final String defaultValue)
    {
        final String value = get(key, String.class);
        if (value == null)
        {
            return defaultValue;
        }
        return value;
    }

    public String getString(final String key)
    {
        return get(key, String.class);
    }

    public String setString(String key, String value)
    {
        put(key, value);
        return value;
    }

    public int setInteger(String key, int value)
    {
        put(key, Integer.valueOf(value));
        return value;
    }

    public Integer setInteger(String key, Integer value)
    {
        put(key, value);
        return value;
    }

    public ObjectList getList(final String key)
    {
        return get(key, ObjectList.class);
    }

    public ObjectList setList(final String key)
    {
        ObjectList list = getList(key);
        if (list == null)
        {
            list = new ObjectList();
            put(key, list);
        }
        return list;
    }

    public StringObjectMap getMap(final String key)
    {
        return get(key, StringObjectMap.class);
    }
}
