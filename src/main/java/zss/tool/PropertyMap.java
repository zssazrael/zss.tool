package zss.tool;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Version("2014-06-01")
public class PropertyMap extends LinkedHashMap<String, String>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyMap.class);
    private static final long serialVersionUID = 1387368384026L;

    public PropertyMap putAll(final Properties properties)
    {
        Enumeration<?> names = properties.propertyNames();
        while (names.hasMoreElements())
        {
            String name = (String) names.nextElement();
            put(name, properties.getProperty(name));
        }
        return this;
    }

    public Properties toProperties()
    {
        Properties properties = new Properties();
        for (Map.Entry<String, String> entry : entrySet())
        {
            String key = entry.getKey();
            String value = entry.getValue();
            if ((key != null) && (value != null))
            {
                properties.setProperty(key, value);
            }
        }
        return properties;
    }

    public String getString(final String name, final String defaultValue)
    {
        String value = get(name);
        if (value == null)
        {
            return defaultValue;
        }
        return value;
    }

    public int getInteger(final String name, final int defaultValue)
    {
        String text = get(name);
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

    public String getString(final String name)
    {
        String value = get(name);
        if (value == null)
        {
            LOGGER.error("The key [{}] could not be found.", name);
            throw new LoggedException(PropertyMap.class);
        }
        return value;
    }
}
