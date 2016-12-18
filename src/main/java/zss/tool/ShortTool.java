/**
 * Modified Time: 2011-11-13 22:46:46
 * Mender: ZssAzrael
 */
package zss.tool;

public class ShortTool
{
    public static void write(final byte[] bytes, final short value)
    {
        bytes[1] = (byte) value;
        bytes[0] = (byte) (value >>> 8);
    }

    public static short read(final byte[] bytes)
    {
        return (short) ((bytes[0] << 8) + (bytes[1] & 0xFF));
    }

    public static short read(final byte byte0, final byte byte1)
    {
        return (short) ((byte0 << 8) + (byte1 & 0xFF));
    }
}
