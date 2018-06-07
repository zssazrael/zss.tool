package zss.tool;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Version("2018.09.18")
public class LocalProperties
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalProperties.class);
    private static final LocalPropertiesMap LOCAL_PROPERTIES_MAP = new LocalPropertiesMap();
    private static final LocaleLocal LOCALE = new LocaleLocal();

    public static LocalProperties getLocalProperties(final Class<?> type)
    {
        return getLocalProperties(type.getName().replace('.', '/'));
    }

    public static LocalProperties getLocalProperties(final String basePath)
    {
        synchronized (LOCAL_PROPERTIES_MAP)
        {
            LocalProperties properties = LOCAL_PROPERTIES_MAP.get(basePath);
            if (properties == null)
            {
                properties = new LocalProperties(basePath);
                LOCAL_PROPERTIES_MAP.put(basePath, properties);
            }
            return properties;
        }
    }

    public static String getLocale()
    {
        return LOCALE.get();
    }

    public static void setLocale(final Locale locale)
    {
        setLocale(locale.toString());
    }

    public static void setLocale(final String locale)
    {
        if (StringUtils.isEmpty(locale))
        {
            LOCALE.remove();
        }
        else
        {
            LOCALE.set(locale);
        }
    }

    @Version("2013-11-30")
    private static class LocaleLocal extends ThreadLocal<String>
    {
        @Override
        protected String initialValue()
        {
            return Locale.getDefault().toString();
        }
    }

    @Version("2013-11-30")
    private static class PropertiesMap extends HashMap<String, Properties>
    {
        private static final long serialVersionUID = 1385811984099L;
    }

    @Version("2013-11-30")
    private static class LocalPropertiesMap extends HashMap<String, LocalProperties>
    {
        private static final long serialVersionUID = 1385813178195L;
    }

    private final Properties defaultProperties = new Properties();
    private final PropertiesMap propertiesMap = new PropertiesMap();
    private final String resource;

    private Properties getProperties(final String locale)
    {
        Properties properties = propertiesMap.get(locale);
        if (properties != null)
        {
            return properties;
        }
        synchronized (propertiesMap)
        {
            properties = propertiesMap.get(locale);
            if (properties != null)
            {
                return properties;
            }
            Iterator<String> iterator = StringTool.split(locale, '_').iterator();
            String baseLocale = iterator.next();
            properties = propertiesMap.get(baseLocale);
            if (properties == null)
            {
                properties = new Properties(defaultProperties);
                loadProperties(properties, resource + '_' + baseLocale + ".properties");
                propertiesMap.put(baseLocale, properties);
            }
            while (iterator.hasNext())
            {
                baseLocale += '_' + iterator.next();
                Properties baseProperties = propertiesMap.get(baseLocale);
                if (baseProperties == null)
                {
                    baseProperties = new Properties(properties);
                    loadProperties(baseProperties, resource + '_' + baseLocale + ".properties");
                    propertiesMap.put(baseLocale, baseProperties);
                }
                properties = baseProperties;
            }
            return properties;
        }
    }

    public String getProperty(final String key, final String defaultValue)
    {
        return getProperties(LOCALE.get()).getProperty(key, defaultValue);
    }

    private LocalProperties(final String resource)
    {
        this.resource = resource;
        loadProperties(defaultProperties, resource + ".properties");
    }

    private void loadProperties(final Properties properties, final String resource) {
        Enumeration<URL> urls;
        try {
            urls = Thread.currentThread().getContextClassLoader().getResources(resource);
        } catch (IOException e) {
            LOGGER.warn(e.getMessage(), e);
            return;
        }
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            InputStream stream;
            try {
                stream = url.openStream();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage(), e);
                continue;
            }
            try {
                properties.load(stream);
            } catch (IOException e) {
                LOGGER.warn(e.getMessage(), e);
                continue;
            } finally {
                IOTool.close(stream);
            }
        }
    }
}
