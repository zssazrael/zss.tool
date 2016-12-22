package zss.tool;

import static org.apache.commons.io.IOUtils.closeQuietly;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Version("2016-03-17")
public final class IOTool
{
    private static final Logger LOGGER = LoggerFactory.getLogger(IOTool.class);

    public static final int KB = 1024;
    public static final int MB = KB * 1024;
    public static final int GB = MB * 1024;

    public static final Charset CHARSET_UTF_8 = Charset.forName("UTF-8");
    public static final Charset CHARSET_UTF_16BE = Charset.forName("UTF-16BE");
    public static final Charset CHARSET_GBK = Charset.forName("GBK");
    public static final Charset CHARSET_GB18030 = Charset.forName("GB18030");

    public static File getTempFolder()
    {
        return newFile(System.getProperty("java.io.tmpdir"));
    }

    private IOTool()
    {
    }

    public static void copy(final File input, final File output)
    {
        final InputStream inputStream = newFileInputStream(input);
        try
        {
            final OutputStream outputStream = newFileOutputStream(output);
            try
            {
                copy(inputStream, outputStream);
            }
            finally
            {
                closeQuietly(outputStream);
            }
        }
        finally
        {
            closeQuietly(inputStream);
        }
    }

    public static void copy(final InputStream input, final OutputStream output)
    {
        try
        {
            final byte[] data = new byte[KB * 4];
            while (true)
            {
                final int count = input.read(data);
                if (count == -1)
                {
                    break;
                }
                output.write(data, 0, count);
            }
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException(IOTool.class);
        }
    }

    public static LinkedList<File> listFiles(final File folder)
    {
        LinkedList<File> list = new LinkedList<>();
        if (folder.isDirectory())
        {
            File[] files = folder.listFiles();
            if ((files != null) && (files.length > 0))
            {
                for (File file : files)
                {
                    list.add(file);
                }
            }
        }
        return list;
    }

    public static File newFile(final String path)
    {
        return Paths.get(path).normalize().toFile();
    }

    public static File newFile(final File parent, final String child)
    {
        Path newPath = Paths.get(child);
        if (!newPath.isAbsolute())
        {
            newPath = Paths.get(parent.getAbsolutePath(), child);
        }
        return newPath.normalize().toFile();
    }

    public static FileInputStream newFileInputStream(final File file)
    {
        try
        {
            return new FileInputStream(file);
        }
        catch (FileNotFoundException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException(IOTool.class);
        }
    }

    public static FileOutputStream newFileOutputStream(final File file)
    {
        return newFileOutputStream(file, false);
    }

    public static FileOutputStream newFileOutputStream(final File file, final boolean append)
    {
        try
        {
            return new FileOutputStream(file, append);
        }
        catch (FileNotFoundException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException(IOTool.class);
        }
    }

    public static BufferedWriter newWriter(final File file, final Charset charset)
    {
        return newWriter(newFileOutputStream(file), charset);
    }

    public static BufferedWriter newWriter(final OutputStream stream, final Charset charset)
    {
        return new BufferedWriter(new OutputStreamWriter(stream, charset));
    }

    /**
     * 获取文件名的扩展名
     * 例子：输入 "123.abc" 放回 "abc"
     * 例子：输入 "123" 放回 ""
     * 
     * @param name
     * @return
     */
    public static String getExtension(final String name)
    {
        if (name == null)
        {
            return "";
        }
        int index = name.lastIndexOf('.');
        if (index < 0)
        {
            return "";
        }
        return name.substring(index + 1);
    }

    public static StringList readAllLine(final File file, final Charset charset)
    {
        return IOTool.readAllLineAndClose(newFileInputStream(file), charset);
    }

    public static StringList readAllLineAndClose(final InputStream stream, final Charset charset)
    {
        try
        {
            return readAllLine(stream, charset);
        }
        finally
        {
            closeQuietly(stream);
        }
    }

    public static StringList readAllLine(final InputStream stream, final Charset charset)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
        StringList list = new StringList();
        try
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                list.add(line);
            }
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException(IOTool.class);
        }
        return list;
    }

    public static StringBuilder readAllText(final File file, final Charset charset)
    {
        return readAllTextAndClose(newFileInputStream(file), charset);
    }

    public static StringBuilder readAllTextAndClose(final InputStream stream, final Charset charset)
    {
        try
        {
            return readAllText(stream, charset);
        }
        finally
        {
            closeQuietly(stream);
        }
    }

    public static StringBuilder readAllText(final InputStream stream, final Charset charset)
    {
        final StringBuilder builder = new StringBuilder();
        final Reader reader = new InputStreamReader(stream, charset);
        try
        {
            final char[] data = new char[1024];
            int count;
            while ((count = reader.read(data)) > -1)
            {
                builder.append(data, 0, count);
            }
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException(IOTool.class);
        }
        return builder;
    }

    public static byte[] readAllByte(final File file)
    {
        return readAllByteAndClose(newFileInputStream(file));
    }

    public static byte[] readAllByteAndClose(final InputStream stream)
    {
        try
        {
            return readAllByte(stream);
        }
        finally
        {
            closeQuietly(stream);
        }
    }

    public static byte[] readAllByte(final InputStream input)
    {
        final ByteArrayOutputStream output = new ByteArrayOutputStream(MB);
        copy(input, output);
        return output.toByteArray();
    }

    public static void flush(final Flushable flushable)
    {
        if (flushable != null)
        {
            try
            {
                flushable.flush();
            }
            catch (IOException e)
            {
                LOGGER.debug(e.getMessage(), e);
            }
        }
    }

    public static int read(final InputStream input, final byte[] buffer)
    {
        return read(input, buffer, 0, buffer.length);
    }

    public static int read(final InputStream input, final byte[] buffer, final int offset, final int length)
    {
        try
        {
            int remaining = length;
            while (remaining > 0)
            {
                final int count = input.read(buffer, offset + length - remaining, remaining);
                if (-1 == count)
                {
                    break;
                }
                remaining -= count;
            }
            return length - remaining;
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException(IOTool.class);
        }
    }

    public static void readFully(final InputStream input, final byte[] buffer, final int offset, final int length)
    {
        final int actual = read(input, buffer, offset, length);
        if (actual != length)
        {
            LOGGER.error(String.format("Length to read: %d actual: %d.", length, actual));
            throw new LoggedException(IOTool.class);
        }
    }

    public static void readFully(final InputStream input, final byte[] buffer)
    {
        readFully(input, buffer, 0, buffer.length);
    }

    public static void write(final File file, final String value, final Charset charset)
    {
        final BufferedWriter writer = newWriter(file, charset);
        try
        {
            writer.write(value);
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException();
        }
        finally
        {
            closeQuietly(writer);
        }
    }
}
