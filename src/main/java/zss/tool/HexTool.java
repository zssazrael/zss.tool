/**
 * Modified Time: 2011-10-22 01:25:58
 * Mender: ZssAzrael
 */
package zss.tool;

public final class HexTool
{
    private static final char[] NUMBERS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    private HexTool()
    {
    }

    public static byte transform(final char char1, final char char2)
    {
        return (byte) ((hex(char1) << 4) + hex(char2));
    }

    public static char[] transform(final byte data)
    {
        char[] chars = new char[2];
        int number = data & 255;
        chars[0] = NUMBERS[number >> 4];
        chars[1] = NUMBERS[number & 15];
        return chars;
    }

    public static byte[] transform(final String hexText)
    {
        char[] chars = hexText.toCharArray();
        byte[] bytes = new byte[chars.length >> 1];
        int index;
        for (int i = 0; i < bytes.length; i++)
        {
            index = i << 1;
            bytes[i] = (byte) ((hex(chars[index]) << 4) + hex(chars[index + 1]));
        }
        return bytes;
    }

    private static int hex(final char c)
    {
        int number = c & 255;
        if (number > 96)
        {
            return number - 87;
        }
        if (number > 64)
        {
            return number - 55;
        }
        return number - 48;
    }

    public static String transform(final byte[] bytes)
    {
        char[] chars = new char[bytes.length << 1];
        int index;
        int number;
        for (int i = 0; i < bytes.length; i++)
        {
            number = bytes[i] & 255;
            index = i << 1;
            chars[index] = NUMBERS[number >> 4];
            chars[index + 1] = NUMBERS[number & 15];
        }
        return new String(chars);
    }
}
