package zss.tool;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Version("2018.09.21")
public class PropertiesTool
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesTool.class);

    public static final Properties newProperties(final String resourceName) {
        InputStream stream = ResourceTool.openStream(resourceName);
        try {
            return newProperties(stream);
        } finally {
            IOTool.close(stream);
        }
    }

    public static Properties newProperties(final Path path)
    {
        return newProperties(path.toFile());
    }

    public static final Properties newProperties(final File file) {
        InputStream stream = IOTool.newFileInputStream(file);
        try {
            return newProperties(stream);
        } finally {
            IOTool.close(stream);
        }
    }

    public static Properties newProperties(final InputStream stream)
    {
        final Properties properties = new Properties();
        try
        {
            properties.load(stream);
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException(PropertiesTool.class);
        }
        return properties;
    }

    private PropertiesTool() {
    }
}

