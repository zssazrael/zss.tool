package zss.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

@Version("2018.05.03")
public class DateTool {
    public static final String ISO_DATE_FORMAT = "yyyy-MM-dd";
    public static final String ISO_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String ISO_DATETIME_NO_T_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date parse(final String pattern, final String source, final Date defaultValue) {
        final Date value = parse(pattern, source);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    public static Calendar parse(final String pattern, final String source, final Calendar defaultValue) {
        final Date value = parse(pattern, source);
        if (value == null) {
            return defaultValue;
        }
        return newCalendar(value);
    }

    public static Calendar newCalendar(final Date date) {
        if (date == null) {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date parse(final String pattern, final String source) {
        if (StringUtils.isEmpty(pattern)) {
            return null;
        }
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        try {
            return new SimpleDateFormat(pattern).parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Calendar truncateSecond(final Calendar calendar)
    {
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.getTimeInMillis();
        return calendar;
    }

    public static Calendar truncateSecond()
    {
        return truncateSecond(Calendar.getInstance());
    }

    public static Calendar truncateMinute(final Calendar calendar)
    {
        truncateSecond(calendar);
        calendar.set(Calendar.SECOND, 0);
        calendar.getTimeInMillis();
        return calendar;
    }

    public static Calendar truncateMinute()
    {
        return truncateMinute(Calendar.getInstance());
    }

    public static Calendar truncateHour(final Calendar calendar)
    {
        truncateMinute(calendar);
        calendar.set(Calendar.MINUTE, 0);
        calendar.getTimeInMillis();
        return calendar;
    }

    public static Calendar truncateHour()
    {
        return truncateHour(Calendar.getInstance());
    }

    public static Calendar truncateDay(final Calendar calendar)
    {
        truncateHour(calendar);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.getTimeInMillis();
        return calendar;
    }

    public static Calendar truncateDay()
    {
        return truncateDay(Calendar.getInstance());
    }

    public static Calendar truncateMonth(final Calendar calendar)
    {
        truncateDay(calendar);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.getTimeInMillis();
        return calendar;
    }

    public static Calendar truncateMonth()
    {
        return truncateMonth(Calendar.getInstance());
    }

    public static Calendar truncateYear(final Calendar calendar)
    {
        truncateMonth(calendar);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.getTimeInMillis();
        return calendar;
    }

    public static Calendar truncateYear()
    {
        return truncateYear(Calendar.getInstance());
    }

    public static Calendar setDayFirst(final Calendar calendar) {
        return truncateDay(calendar);
    }

    public static Calendar setDayLast(final Calendar calendar) {
        truncateDay(calendar);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.getTimeInMillis();
        calendar.add(Calendar.MILLISECOND, -1);
        calendar.getTimeInMillis();
        return calendar;
    }

    public static Calendar setMonthFirst(final Calendar calendar) {
        return truncateMonth(calendar);
    }

    public static Calendar setMonthLast(final Calendar calendar) {
        truncateMonth(calendar);
        calendar.add(Calendar.MONTH, 1);
        calendar.getTimeInMillis();
        calendar.add(Calendar.MILLISECOND, -1);
        calendar.getTimeInMillis();
        return calendar;
    }

    public static Calendar setYearFirst(final Calendar calendar) {
        return truncateYear(calendar);
    }

    public static Calendar setYearLast(final Calendar calendar) {
        truncateYear(calendar);
        calendar.add(Calendar.YEAR, 1);
        calendar.getTimeInMillis();
        calendar.add(Calendar.MILLISECOND, -1);
        calendar.getTimeInMillis();
        return calendar;
    }

    public static Calendar setWeekFirst(final Calendar calendar)
    {
        truncateDay(calendar);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.getTimeInMillis();
        return calendar;
    }

    public static Calendar setWeekLast(final Calendar calendar)
    {
        truncateDay(calendar);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.getTimeInMillis();
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        calendar.getTimeInMillis();
        calendar.add(Calendar.MILLISECOND, -1);
        calendar.getTimeInMillis();
        return calendar;
    }

    public static String toISO8601(final Calendar calendar) {
        if (calendar == null) {
            return "";
        }
        return toISO8601(calendar.getTime());
    }

    public static String toISO8601(final Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(date);
    }

    public static String format(final String pattern, final Date date) {
        return format(pattern, date, "");
    }

    public static String format(final String pattern, final Date date, final String defaultValue) {
        if (date == null) {
            return defaultValue;
        }
        if (StringUtils.isEmpty(pattern)) {
            return defaultValue;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String format(final String pattern, final Calendar calendar) {
        return format(pattern, calendar, "");
    }

    public static String format(final String pattern, final Calendar calendar, final String defaultValue) {
        if (calendar == null) {
            return defaultValue;
        }
        return format(pattern, calendar.getTime(), defaultValue);
    }
}
