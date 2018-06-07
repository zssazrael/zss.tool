package zss.tool;

import java.io.InputStream;

@Version("2018.09.21")
public class IntegerTool
{
    public static void write(final byte[] bytes, final int value)
    {
        bytes[3] = (byte) (value);
        bytes[2] = (byte) (value >>> 8);
        bytes[1] = (byte) (value >>> 16);
        bytes[0] = (byte) (value >>> 24);
    }

    public static int read(final byte[] bytes)
    {
        return (bytes[0] << 24) + ((bytes[1] & 0xFF) << 16) + ((bytes[2] & 0xFF) << 8) + ((bytes[3] & 0xFF));
    }

    public static int read(final byte byte0, final byte byte1, final byte byte2, final byte byte3)
    {
        return (byte0 << 24) + ((byte1 & 0xFF) << 16) + ((byte2 & 0xFF) << 8) + ((byte3 & 0xFF));
    }

    public static int read(final InputStream stream) {
        final byte[] data = new byte[4];
        IOTool.readFully(stream, data);
        return read(data);
    }

    private IntegerTool() {
    }
}
