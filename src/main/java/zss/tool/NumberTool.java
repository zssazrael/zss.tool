package zss.tool;

import java.math.BigDecimal;

@Version("2018.02.03")
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

    public static BigDecimal max(BigDecimal value1, BigDecimal value2) {
        value1 = defaultBigDecimal(value1);
        value2 = defaultBigDecimal(value2);
        return value1.compareTo(value2) > 0 ? value1 : value2;
    }

    public static BigDecimal min(BigDecimal value1, BigDecimal value2) {
        value1 = defaultBigDecimal(value1);
        value2 = defaultBigDecimal(value2);
        return value1.compareTo(value2) < 0 ? value1 : value2;
    }
}
