package zss.tool;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;

@Version("2019.10.30")
public class LoggedException extends RuntimeException {
    private static final long serialVersionUID = 1304476906739L;

    public static LoggedException newLoggedException(Logger logger, Exception exception) {
        if (exception == null) {
            return null;
        }
        for (Throwable throwable : ExceptionUtils.getThrowableList(exception)) {
            if (throwable instanceof LoggedException) {
                return (LoggedException) throwable;
            }
        }
        logger.error(exception.getMessage(), exception);
        return new LoggedException(exception.getMessage());
    }

    private final String message;

    @Override
    public String getLocalizedMessage()
    {
        return message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    @Override
    public String toString()
    {
        return message;
    }

    public LoggedException(final String message)
    {
        this.message = message;
    }

    public LoggedException(final Class<?> type)
    {
        this(type.getName());
    }

    public LoggedException()
    {
        this.message = newMessage();
    }

    private String newMessage()
    {
        final StackTraceElement trace = getStackTrace()[0];
        String name = trace.getClassName();
        final int index = name.lastIndexOf('.');
        if (index > -1)
        {
            name = name.substring(index + 1);
        }
        final StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append('.');
        builder.append(trace.getMethodName());
        builder.append('(');
        builder.append(trace.getLineNumber());
        builder.append(')');
        return builder.toString();
    }
}
