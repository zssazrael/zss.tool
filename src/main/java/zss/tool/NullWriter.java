/**
 * Modified Time: 2011-09-02 23:37:57
 * Mender: ZssAzrael
 */
package zss.tool;

import java.io.Writer;

public class NullWriter extends Writer
{
    private static final NullWriter INSTANCE = new NullWriter();

    public static NullWriter getInstance()
    {
        return INSTANCE;
    }

    private NullWriter()
    {
    }

    @Override
    public void write(final int c)
    {
    }

    @Override
    public void write(final char[] cbuf)
    {
    }

    @Override
    public void write(final String str)
    {
    }

    @Override
    public void write(final String str, final int off, final int len)
    {
    }

    @Override
    public Writer append(final CharSequence csq)
    {
        return this;
    }

    @Override
    public Writer append(final CharSequence csq, final int start, final int end)
    {
        return this;
    }

    @Override
    public Writer append(final char c)
    {
        return this;
    }

    @Override
    public void write(final char[] cbuf, final int off, final int len)
    {
    }

    @Override
    public void flush()
    {
    }

    @Override
    public void close()
    {
    }
}
