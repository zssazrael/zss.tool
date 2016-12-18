package zss.tool;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ThrowableTool
{
    public static String trace(final Throwable throwable)
    {
        StringWriter writer = new StringWriter();
        throwable.printStackTrace(new PrintWriter(writer, true));
        return writer.toString();
    }
}
