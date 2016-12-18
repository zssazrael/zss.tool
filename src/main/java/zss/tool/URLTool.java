package zss.tool;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Version("2016-03-10")
public class URLTool
{
    private static final Logger LOGGER = LoggerFactory.getLogger(URLTool.class);

    public static InputStream openStream(final String url)
    {
        return openStream(newURL(url));
    }

    public static InputStream openStream(final URL url)
    {
        try
        {
            return url.openStream();
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException();
        }
    }

    public static URL newURL(final URL context, final String url)
    {
        try
        {
            return new URL(context, url);
        }
        catch (MalformedURLException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException();
        }
    }

    public static URL newURL(final String url)
    {
        try
        {
            return new URL(url);
        }
        catch (MalformedURLException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException();
        }
    }

    public static String decode(final String text)
    {
        if (text == null)
        {
            return "";
        }
        try
        {
            return URLDecoder.decode(text, IOTool.CHARSET_UTF_8.name());
        }
        catch (UnsupportedEncodingException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException();
        }
    }

    public static String encode(final String text)
    {
        if (text == null)
        {
            return "";
        }
        try
        {
            return URLEncoder.encode(text, IOTool.CHARSET_UTF_8.name());
        }
        catch (UnsupportedEncodingException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException();
        }
    }
}
