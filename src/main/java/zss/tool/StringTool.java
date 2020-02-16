package zss.tool;

import java.util.LinkedList;
import java.util.Locale;

@Version("2020.02.16")
public final class StringTool {
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

    public static String newString(final byte[] bs) {
        return new String(bs, IOTool.CHARSET_UTF_16BE);
    }

    public static byte[] getBytes(final String text) {
        if (text == null) {
            return null;
        }
        return text.getBytes(IOTool.CHARSET_UTF_16BE);
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
