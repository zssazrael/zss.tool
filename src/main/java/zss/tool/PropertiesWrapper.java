package zss.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Version("2013-02-23")
public class PropertiesWrapper
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesWrapper.class);

    private final Properties properties;

    public boolean getBoolean(final String name)
    {
        return "true".equals(properties.getProperty(name));
    }

    public int getInteger(final String name, final int defaultValue)
    {
        String text = properties.getProperty(name);
        if (text == null)
        {
            return defaultValue;
        }
        try
        {
            return Integer.parseInt(text);
        }
        catch (NumberFormatException e)
        {
            LOGGER.warn(e.getMessage(), e);
            return defaultValue;
        }
    }

    public int getInteger(final String name)
    {
        return Integer.parseInt(getString(name));
    }

    public <T> T getInstance(final String name, final Class<T> type)
    {
        String value = properties.getProperty(name);
        if (value == null)
        {
            return null;
        }
        try
        {
            return Class.forName(value).asSubclass(type).newInstance();
        }
        catch (InstantiationException e)
        {
            Throwable throwable = e.getCause();
            if (throwable == null)
            {
                LOGGER.warn(e.getMessage(), e);
            }
            else if (!(throwable instanceof LoggedException))
            {
                LOGGER.warn(throwable.getMessage(), throwable);
            }
            return null;
        }
        catch (IllegalAccessException e)
        {
            LOGGER.warn(e.getMessage(), e);
            return null;
        }
        catch (ClassNotFoundException e)
        {
            LOGGER.warn(e.getMessage(), e);
            return null;
        }
    }

    public String getString(final String name, final String defaultValue)
    {
        return properties.getProperty(name, defaultValue);
    }

    public String getString(final String name)
    {
        return properties.getProperty(name);
    }

    public Properties getProperties()
    {
        return properties;
    }

    public PropertiesWrapper(final Properties properties)
    {
        this.properties = properties;
    }

    public PropertiesWrapper()
    {
        properties = new Properties();
    }

    public PropertiesWrapper load(final String path)
    {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        if (stream == null)
        {
            return this;
        }
        try
        {
            properties.load(stream);
        }
        catch (IOException e)
        {
            LOGGER.warn(e.getMessage(), e);
        }
        finally
        {
            IOUtils.closeQuietly(stream);
        }
        return this;
    }

    public PropertiesWrapper load(final File file)
    {
        InputStream stream;
        try
        {
            stream = new FileInputStream(file);
        }
        catch (FileNotFoundException e)
        {
            LOGGER.warn(e.getMessage(), e);
            return this;
        }
        try
        {
            properties.load(stream);
        }
        catch (IOException e)
        {
            LOGGER.warn(e.getMessage(), e);
        }
        finally
        {
            IOUtils.closeQuietly(stream);
        }
        return this;
    }

    public PropertiesWrapper load(final InputStream stream)
    {
        try
        {
            properties.load(stream);
        }
        catch (IOException e)
        {
            LOGGER.warn(e.getMessage(), e);
        }
        return this;
    }
}
