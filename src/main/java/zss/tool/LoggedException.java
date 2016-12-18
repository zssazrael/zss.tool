package zss.tool;

@Version("2016-02-24")
public class LoggedException extends RuntimeException
{
    private static final long serialVersionUID = 1304476906739L;

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
