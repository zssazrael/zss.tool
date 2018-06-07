package zss.tool;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TreeMap;

@Version("2018.07.12")
public class StringObjectMap extends TreeMap<String, Object> {
    private static final long serialVersionUID = 20150424223016L;

    public <T> T get(final String key, final Class<T> type) {
        final Object object = get(key);
        if (type.isInstance(object)) {
            return type.cast(object);
        }
        return null;
    }

    public Integer getInteger(final String key) {
        final Object value = get(key);
        if (value instanceof Integer) {
            return (Integer) value;
        }
        return null;
    }

    public Integer getInteger(final String key, final Integer defaultValue) {
        final Integer value = getInteger(key);
        if (value == null) {
            return setInteger(key, defaultValue);
        }
        return value;
    }

    public int getInteger(final String key, final int defaultValue) {
        final Integer value = getInteger(key);
        if (value == null) {
            return setInteger(key, defaultValue);
        }
        return value.intValue();
    }

    public int setInteger(String key, int value) {
        put(key, Integer.valueOf(value));
        return value;
    }

    public Integer setInteger(String key, Integer value) {
        put(key, value);
        return value;
    }

    public String getString(final String key) {
        final Object value = get(key);
        if (value instanceof String) {
            return (String) value;
        }
        return null;
    }

    public String getString(final String key, final String defaultValue) {
        final String value = getString(key);
        if (value == null) {
            return setString(key, defaultValue);
        }
        return value;
    }

    public String setString(String key, String value) {
        put(key, value);
        return value;
    }

    public ObjectList getList(final String key) {
        final Object value = get(key);
        if (value instanceof ObjectList) {
            return (ObjectList) value;
        }
        return null;
    }

    public ObjectList setList(final String key) {
        ObjectList list = getList(key);
        if (list == null) {
            list = new ObjectList();
            put(key, list);
        }
        return list;
    }

    public StringObjectMap setMap(final String key) {
        StringObjectMap map = getMap(key);
        if (map == null) {
            map = new StringObjectMap();
            put(key, map);
        }
        return map;
    }

    public StringObjectMap getMap(final String key) {
        return get(key, StringObjectMap.class);
    }

    public Boolean getBoolean(final String key) {
        return get(key, Boolean.class);
    }

    public Boolean getBoolean(final String key, final Boolean defaultValue) {
        final Boolean value = getBoolean(key);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    public boolean getBoolean(final String key, final boolean defaultValue) {
        final Boolean value = getBoolean(key);
        if (value == null) {
            return defaultValue;
        }
        return value.booleanValue();
    }

    public boolean setBoolean(final String key, final boolean value) {
        put(key, Boolean.valueOf(value));
        return value;
    }

    public Boolean setBoolean(final String key, final Boolean value) {
        put(key, value);
        return value;
    }

    public Long getLong(final String key) {
        final Object value = get(key);
        if (value instanceof Long) {
            return (Long) value;
        }
        return null;
    }

    public Long getLong(final String key, final Long defaultValue) {
        final Long value = getLong(key);
        if (value == null) {
            return setLong(key, defaultValue);
        }
        return value;
    }

    public long getLong(final String key, final long defaultValue) {
        final Long value = getLong(key);
        if (value == null) {
            return setLong(key, defaultValue);
        }
        return value;
    }

    public Long setLong(final String key, final Long value) {
        put(key, value);
        return value;
    }

    public long setLong(final String key, final long value) {
        put(key, Long.valueOf(value));
        return value;
    }

    public BigDecimal getBigDecimal(final String key) {
        final Object value = get(key);
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        return null;
    }

    public BigDecimal getBigDecimal(final String key, final BigDecimal defaultValue) {
        final BigDecimal value = getBigDecimal(key);
        if (value == null) {
            return setBigDecimal(key, defaultValue);
        }
        return value;
    }

    public BigDecimal setBigDecimal(final String key, final BigDecimal value) {
        put(key, value);
        return value;
    }

    public BigDecimal setBigDecimal(final String key, final double value) {
        return setBigDecimal(key, BigDecimal.valueOf(value));
    }

    public Number getNumber(final String key) {
        final Object value = get(key);
        if (value instanceof Number) {
            return (Number) value;
        }
        return null;
    }

    public Number getNumber(final String key, final Number defaultValue) {
        final Number value = getNumber(key);
        if (value == null) {
            return setNumber(key, defaultValue);
        }
        return value;
    }

    public Number setNumber(final String key, final Number value) {
        put(key, value);
        return value;
    }

    public Date getDate(final String key) {
        final Object value = get(key);
        if (value instanceof Date) {
            return (Date) value;
        }
        return null;
    }

    public Date getDate(final String key, final Date defaultValue) {
        final Date value = getDate(key);
        if (value == null) {
            return setDate(key, defaultValue);
        }
        return value;
    }

    public Date setDate(final String key, final Date value) {
        put(key, value);
        return value;
    }
}
