package zss.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Version("2013-12-21")
public class LazyValue<T>
{
    private static final LocalProperties RESOURCE = LocalProperties.getLocalProperties(LazyValue.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(LazyValue.class);

    private final Object lock = new Object();

    private boolean initialized = false;
    private boolean destroyed = false;
    private T value = null;

    public final boolean isInitialized()
    {
        synchronized (lock)
        {
            return initialized;
        }
    }

    public final boolean isDestroyed()
    {
        synchronized (lock)
        {
            return destroyed;
        }
    }

    public final T get()
    {
        synchronized (lock)
        {
            if (destroyed)
            {
                String message = RESOURCE.getProperty("get.destroyed", "值 [%s@%s] 已销毁。");
                message = String.format(message, getClass().getName(), Integer.toHexString(hashCode()));
                LOGGER.error(message);
                throw new LoggedException(message);
            }
            if (initialized)
            {
                return value;
            }
            initialized = true;
            value = initialize();
            return value;
        }
    }

    public final void destroy()
    {
        synchronized (lock)
        {
            if (initialized && (destroyed == false))
            {
                destroyed = true;
                doDestroy(value);
            }
        }
    }

    protected T initialize()
    {
        return null;
    }

    protected void doDestroy(final T value)
    {
        this.value = null;
    }
}
