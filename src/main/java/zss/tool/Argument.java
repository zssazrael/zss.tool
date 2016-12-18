package zss.tool;

import java.util.TreeMap;

public class Argument extends TreeMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    public static Argument map(final String name, final String value)
    {
        return new Argument().set(name, value);
    }

    public Argument set(final String name, final String value)
    {
        put(name, value);
        return this;
    }
}
