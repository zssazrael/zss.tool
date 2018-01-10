package zss.tool;

import java.math.BigDecimal;

@Version("2018.01.10")
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

    public static BigDecimal defaultBigDecimal(final BigDecimal value, final BigDecimal defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    public static BigDecimal defaultBigDecimal(final BigDecimal value) {
        return defaultBigDecimal(value, BigDecimal.ZERO);
    }
}
