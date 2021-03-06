package zss.tool;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

@Version("2018.09.21")
public class NumberTool {
    public static Integer defaultInteger(final Integer value, final int defaultValue) {
        if (value == null) {
            return Integer.valueOf(defaultValue);
        }
        return value;
    }

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

    public static Long defaultLong(final Long value, final long defaultValue) {
        if (value == null) {
            return Long.valueOf(defaultValue);
        }
        return value;
    }

    public static Long defaultLong(final Long value, final Long defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    public static Long defaultLong(final Long value) {
        if (value == null) {
            return Long.valueOf(0L);
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

    public static boolean greaterThan(BigDecimal value1, BigDecimal value2) {
        value1 = defaultBigDecimal(value1);
        value2 = defaultBigDecimal(value2);
        return value1.compareTo(value2) > 0;
    }

    public static boolean lessThan(BigDecimal value1, BigDecimal value2) {
        value1 = defaultBigDecimal(value1);
        value2 = defaultBigDecimal(value2);
        return value1.compareTo(value2) < 0;
    }

    public static BigDecimal toBigDecimal(final String value) {
        return toBigDecimal(value, BigDecimal.ZERO);
    }

    public static BigDecimal toBigDecimal(final String value, final BigDecimal defaultValue) {
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private NumberTool() {
    }
}
