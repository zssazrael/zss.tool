package zss.tool;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Version("2013-02-04")
public class HashTool
{
    private static final Logger LOGGER = LoggerFactory.getLogger(HashTool.class);

    public static String md5(final byte[] data)
    {
        MessageDigest digest = newMD5MessageDigest();
        return HexTool.transform(digest.digest(data));
    }

    public static String md5(final File file)
    {
        final InputStream stream = IOTool.newFileInputStream(file);
        try
        {
            return md5(stream);
        }
        finally
        {
            IOUtils.closeQuietly(stream);
        }
    }

    public static String md5(final InputStream stream)
    {
        MessageDigest digest = newMD5MessageDigest();
        try
        {
            byte[] buffer = new byte[IOTool.MB];
            int count;
            while ((count = stream.read(buffer)) > -1)
            {
                digest.update(buffer, 0, count);
            }
            return HexTool.transform(digest.digest());
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException();
        }
    }

    public static MessageDigest newMD5MessageDigest()
    {
        try
        {
            return MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new LoggedException();
        }
    }
}
