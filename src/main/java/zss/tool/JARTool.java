package zss.tool;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Version("2015-01-05")
public class JARTool
{
    private static final Logger LOGGER = LoggerFactory.getLogger(JARTool.class);

    public static LinkedList<JarEntry> entries(final File file)
    {
        final InputStream stream = IOTool.newFileInputStream(file);
        try
        {
            return entries(stream);
        }
        finally
        {
            IOUtils.closeQuietly(stream);
        }
    }

    public static LinkedList<JarEntry> entries(final InputStream stream)
    {
        if (stream instanceof JarInputStream)
        {
            return entries((JarInputStream) stream);
        }
        try
        {
            return entries(new JarInputStream(stream, false));
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException(JARTool.class);
        }
    }

    public static LinkedList<JarEntry> entries(final JarInputStream stream)
    {
        final LinkedList<JarEntry> list = new LinkedList<>();
        while (true)
        {
            final JarEntry entry;
            try
            {
                entry = stream.getNextJarEntry();
            }
            catch (IOException e)
            {
                LOGGER.error(e.getMessage(), e);
                throw new LoggedException(JARTool.class);
            }
            if (entry == null)
            {
                break;
            }
            list.add(entry);
        }
        return list;
    }

    public static LinkedList<String> classNames(final File file)
    {
        return classNames(entries(file));
    }

    public static LinkedList<String> classNames(final LinkedList<JarEntry> entries)
    {
        final LinkedList<String> names = new LinkedList<>();
        for (JarEntry entry : entries)
        {
            final String entrytName = entry.getName();
            if (!entrytName.endsWith(".class"))
            {
                continue;
            }
            final int slashIndex = entrytName.lastIndexOf('/');
            if (entrytName.indexOf('$', slashIndex + 1) > -1)
            {
                continue;
            }
            final String className = entrytName.substring(0, entrytName.length() - 6).replace('/', '.');
            names.add(className);
        }
        return names;
    }
}
