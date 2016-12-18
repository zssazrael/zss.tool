package zss.tool;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.LinkedList;
import java.util.Locale;

@Version("2015-01-07")
public final class StringTool
{
    public static String toLowerCase(final String text)
    {
        if (text == null)
        {
            return null;
        }
        return text.toLowerCase(Locale.ENGLISH);
    }

    public static String toUpperCase(final String text)
    {
        if (text == null)
        {
            return null;
        }
        return text.toUpperCase(Locale.ENGLISH);
    }

    public static String concat(final Object... values)
    {
        StringBuilder builder = new StringBuilder();
        for (Object object : values)
        {
            builder.append(object);
        }
        return builder.toString();
    }

    public static String newString(final byte[] bs)
    {
        final CharBuffer buffer = ByteBuffer.wrap(bs).asCharBuffer();
        return buffer.toString();
    }

    public static byte[] getBytes(final String text)
    {
        final byte[] bs = new byte[text.length() * 2];
        final CharBuffer buffer = ByteBuffer.wrap(bs).asCharBuffer();
        buffer.append(text);
        return bs;
    }

    public static LinkedList<String> split(final String text, final char separator)
    {
        char[] chars = text.toCharArray();
        int index = 0;
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < chars.length; i++)
        {
            if (chars[i] == separator)
            {
                if (index < i)
                {
                    list.add(text.substring(index, i));
                }
                index = i + 1;
            }
        }
        if (index < chars.length)
        {
            list.add(text.substring(index));
        }
        return list;
    }
}
