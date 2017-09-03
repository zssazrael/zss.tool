package zss.tool;

@Version("2017.09.03")
public class Result extends StringObjectMap {
    private static final long serialVersionUID = 20170903112936L;
    public static final String RESULT = "result";
    public static final String MESSAGE = "message";

    public Result() {
        setResult(false);
        setMessage("");
    }

    public static Result newResult(final boolean result, final String message) {
        final Result value = new Result();
        value.setResult(result);
        value.setMessage(message);
        return value;
    }

    public static Result newResult(final boolean result) {
        final Result value = new Result();
        value.setResult(result);
        return value;
    }

    public String getMessage() {
        return getString(MESSAGE, "");
    }

    public void setMessage(final String message) {
        setString(MESSAGE, message);
    }

    public boolean getResult() {
        return getBoolean(RESULT, false);
    }

    public void setResult(final boolean result) {
        setBoolean(RESULT, result);
    }
}