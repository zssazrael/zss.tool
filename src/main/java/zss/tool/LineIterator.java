package zss.tool;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Version("2018.05.17")
public class LineIterator implements Iterator<String>, Closeable
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LineIterator.class);

    private static final String EXCEPTION_MESSAGE_HAS_NEXT = "Invoke [hasNext] failed!";
    private static final String EXCEPTION_MESSAGE_LINE_ITERATOR = "Invoke [LineIterator] failed!";
    private static final String EXCEPTION_MESSAGE_NEXT = "Invoke [next] failed!";

    private final BufferedReader reader;

    private String line;

    public LineIterator(final String resource, final Charset charset)
    {
        URL url = Thread.currentThread().getContextClassLoader().getResource(resource);
        try
        {
            reader = new BufferedReader(new InputStreamReader(url.openStream(), charset));
        }
        catch (IOException e)
        {
            LOGGER.error(EXCEPTION_MESSAGE_LINE_ITERATOR, e);
            throw new LoggedException(EXCEPTION_MESSAGE_LINE_ITERATOR);
        }
    }

    public LineIterator(final File file, final Charset charset)
    {
        try
        {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
        }
        catch (FileNotFoundException e)
        {
            LOGGER.error(EXCEPTION_MESSAGE_LINE_ITERATOR, e);
            throw new LoggedException(EXCEPTION_MESSAGE_LINE_ITERATOR);
        }
    }

    public LineIterator(final InputStream stream, final Charset charset)
    {
        reader = new BufferedReader(new InputStreamReader(stream, charset));
    }

    public LineIterator(final Reader reader)
    {
        this.reader = new BufferedReader(reader);
    }

    @Override
    public boolean hasNext()
    {
        if (line != null)
        {
            return true;
        }
        try
        {
            line = reader.readLine();
        }
        catch (IOException e)
        {
            LOGGER.error(EXCEPTION_MESSAGE_HAS_NEXT, e);
            throw new LoggedException(EXCEPTION_MESSAGE_HAS_NEXT);
        }
        return (line != null);
    }

    @Override
    public String next()
    {
        if (hasNext())
        {
            String line = this.line;
            this.line = null;
            return line;
        }
        LOGGER.error(EXCEPTION_MESSAGE_NEXT);
        throw new LoggedException(EXCEPTION_MESSAGE_NEXT);
    }

    @Override
    public void remove()
    {
    }

    @Override
    public void close() {
        IOTool.close(reader);
    }
}
