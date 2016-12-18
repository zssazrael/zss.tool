package zss.tool;

@Version("2013-03-24")
public class ArrayTool
{
    public static int sum(final int... values)
    {
        int value = 0;
        for (int i = 0; i < values.length; i++)
        {
            value += values[i];
        }
        return value;
    }
}
