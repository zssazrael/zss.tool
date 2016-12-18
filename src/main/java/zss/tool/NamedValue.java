package zss.tool;

import java.util.Map;

@Version("2014-11-13")
public class NamedValue<T> implements Map.Entry<String, T>
{
    private final String name;
    private T value;

    public String getName()
    {
        return name;
    }

    @Override
    public String getKey()
    {
        return name;
    }

    @Override
    public T getValue()
    {
        return value;
    }

    @Override
    public T setValue(final T value)
    {
        final T oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    public NamedValue(final String name)
    {
        this.name = name;
    }

    public NamedValue(final String name, final T value)
    {
        this.name = name;
        this.value = value;
    }
}
