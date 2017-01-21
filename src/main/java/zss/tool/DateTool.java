package zss.tool;

import java.util.Calendar;

@Version("2017-01-20")
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
}
