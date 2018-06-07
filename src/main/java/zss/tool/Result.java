package zss.tool;

@Version("2018.09.21")
public class Result extends StringObjectMap {
    private static final long serialVersionUID = 20170903112936L;
    public static final String KEY_RESULT = "result";
    public static final String KEY_MESSAGE = "message";

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
        return getString(KEY_MESSAGE, "");
    }

    public Result setMessage(final String message) {
        setString(KEY_MESSAGE, message);
        return this;
    }

    public boolean getResult() {
        return getBoolean(KEY_RESULT, false);
    }

    public Result setResult(final boolean result) {
        setBoolean(KEY_RESULT, result);
        return this;
    }
}
