package zss.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Version("2017.03.29")
public class DateTool
{
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
}
