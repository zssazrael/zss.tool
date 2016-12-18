/**
 * Modified Time: 2011-11-06 12:34:35
 * Mender: ZssAzrael
 */
package zss.tool;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractEventTrigger<T>
{
    protected final List<T> listeners = new LinkedList<T>();

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
