package zss.tool;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Version("2014-05-28")
public class ResourceTool
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceTool.class);

    public static Path getResourcePath(final String resourceName)
    {
        Enumeration<URL> eu;
        try
        {
            eu = Thread.currentThread().getContextClassLoader().getResources(resourceName);
        }
        catch (IOException e)
        {
            LOGGER.debug(e.getMessage(), e);
            return null;
        }
        while (eu.hasMoreElements())
        {
            URL url = eu.nextElement();
            if ("file".equalsIgnoreCase(url.getProtocol()))
            {
                return Paths.get(url.getPath());
            }
        }
        return null;
    }

    public static InputStream openStream(final String resourceName)
    {
        URL url = Thread.currentThread().getContextClassLoader().getResource(resourceName);
        if (url == null)
        {
            LOGGER.error("The resource [{}] could not be found.", resourceName);
            throw new LoggedException(ResourceTool.class);
        }
        try
        {
            return url.openStream();
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException(ResourceTool.class);
        }
    }
}
