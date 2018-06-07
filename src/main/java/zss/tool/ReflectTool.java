package zss.tool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Version("2018.09.21")
public class ReflectTool
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectTool.class);

    public static Object invoke(final Method method, final Object obj, final Object... args)
    {
        try
        {
            return method.invoke(obj, args);
        } catch (IllegalAccessException | IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException();
        }
        catch (InvocationTargetException e)
        {
            final Throwable t = e.getCause();
            if (t instanceof LoggedException)
            {
                throw (LoggedException) t;
            }
            else
            {
                LOGGER.error(t.getMessage(), t);
                throw new LoggedException(ReflectTool.class);
            }
        }
    }

    public static Method getMethod(final Class<?> type, final String name, final Class<?>... parameterTypes)
    {
        try
        {
            return type.getMethod(name, parameterTypes);
        } catch (NoSuchMethodException | SecurityException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException();
        }
    }

    public static Class<?> forName(final String className)
    {
        try
        {
            return Class.forName(className);
        }
        catch (ClassNotFoundException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException(ReflectTool.class);
        }
    }

    public static <T> Class<? extends T> forName(final String className, final Class<T> type)
    {
        try
        {
            return forName(className).asSubclass(type);
        }
        catch (ClassCastException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException(ReflectTool.class);
        }
    }

    public static <T> T newInstance(final Class<T> type)
    {
        try
        {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException();
        }
    }

    public static <T> T getInstance(final Class<T> type)
    {
        final Method method = getMethod(type, "getInstance");
        if (!(Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers())))
        {
            LOGGER.error("Invoke [getInstance] failed!");
            throw new LoggedException("Invoke [getInstance] failed!");
        }
        if (!method.getReturnType().isAssignableFrom(type))
        {
            LOGGER.error("Invoke [getInstance] failed!");
            throw new LoggedException("Invoke [getInstance] failed!");
        }
        return type.cast(invoke(method, type));
    }

    public static <T> T cast(final Object value, final Class<T> type)
    {
        if (type.isInstance(value))
        {
            return type.cast(value);
        }
        return null;
    }

    private ReflectTool() {
    }
}
