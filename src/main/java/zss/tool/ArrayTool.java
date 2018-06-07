package zss.tool;

@Version("2018.09.18")
public class ArrayTool {
    private ArrayTool() {
    }

    public static int sum(final int... values)
    {
        int value = 0;
        for (int i = 0; i < values.length; i++)
        {
            value += values[i];
        }
        return value;
    }

    public static int minimum(final int... values) {
        int value = values[0];
        for (int i = 1; i < values.length; i++) {
            value = Math.min(value, values[i]);
        }
        return value;
    }
}
