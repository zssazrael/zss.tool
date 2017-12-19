package zss.tool;

@Version("2017.12.19")
public class NumberTool {
    public static Integer defaultInteger(final Integer value, final Integer defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    public static Integer defaultInteger(final Integer value) {
        if (value == null) {
            return Integer.valueOf(0);
        }
        return value;
    }
}
