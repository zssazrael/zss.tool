package zss.tool;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Version("2018.09.18")
public class LineIterator implements Iterator<String>, Iterable<String>, Closeable {
    private static final Logger LOGGER = LoggerFactory.getLogger(LineIterator.class);

    private final BufferedReader reader;

    private String line;

    public LineIterator(final String resource, final Charset charset) {
        this(ResourceTool.openStream(resource), charset);
    }

    public LineIterator(final File file, final Charset charset) {
        reader = IOTool.newReader(file, charset);
    }

    public LineIterator(final InputStream stream, final Charset charset) {
        reader = IOTool.newReader(stream, charset);
    }

    public LineIterator(final Reader reader) {
        if (reader instanceof BufferedReader) {
            this.reader = (BufferedReader) reader;
        } else {
            this.reader = new BufferedReader(reader);
        }
    }

    @Override
    public boolean hasNext() {
        if (line != null) {
            return true;
        }
        try {
            line = reader.readLine();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException();
        }
        return (line != null);
    }

    @Override
    public String next() {
        if (hasNext()) {
            String line = this.line;
            this.line = null;
            return line;
        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove()
    {
    }

    @Override
    public void close() {
        IOTool.close(reader);
    }

    @Override
    public Iterator<String> iterator() {
        return this;
    }
}
