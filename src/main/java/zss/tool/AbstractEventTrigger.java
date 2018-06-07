package zss.tool;

import java.util.LinkedList;
import java.util.List;

@Version("2018.09.18")
public abstract class AbstractEventTrigger<T>
{
    protected final List<T> listeners = new LinkedList<>();

    public List<T> getListeners()
    {
        return listeners;
    }

    public void bind(final T listener)
    {
        listeners.add(listener);
    }

    public void unbind(final T listener)
    {
        listeners.remove(listener);
    }
}
