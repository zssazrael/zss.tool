package zss.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Version("2017.09.06")
public final class ObjectTool
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectTool.class.getName());

    private ObjectTool()
    {
    }

    public static <T> T defaultIfNull(final T value, final T defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    public static Object deserialize(final String path)
    {
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        InputStream stream;
        try
        {
            stream = url.openStream();
        }
        catch (IOException e)
        {
            LOGGER.error("Invoke [deserialize] failed!", e);
            throw new LoggedException("Invoke [deserialize] failed!");
        }
        try
        {
            return deserialize(stream);
        }
        finally
        {
            IOUtils.closeQuietly(stream);
        }
    }

    public static Object deserialize(final File file)
    {
        FileInputStream stream;
        try
        {
            stream = new FileInputStream(file);
        }
        catch (FileNotFoundException e)
        {
            LOGGER.error("Invoke [deserialize] failed!", e);
            throw new LoggedException("Invoke [deserialize] failed!");
        }
        try
        {
            return deserialize(stream);
        }
        finally
        {
            IOUtils.closeQuietly(stream);
        }
    }

    public static Object deserialize(final InputStream stream)
    {
        try
        {
            return new ObjectInputStream(stream).readObject();
        }
        catch (IOException e)
        {
            LOGGER.error("Invoke [deserialize] failed!", e);
            throw new LoggedException("Invoke [deserialize] failed!");
        }
        catch (ClassNotFoundException e)
        {
            LOGGER.error("Invoke [deserialize] failed!", e);
            throw new LoggedException("Invoke [deserialize] failed!");
        }
    }
}
