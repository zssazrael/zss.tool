package zss.tool;

@Version("2018.07.13")
public abstract class LazyValue<T> {
    private final Object lock = new Object();

    private T value;
    private long timeLive;
    private long timeIdle;

    public final T get() {
        synchronized (lock) {
            final long currentTime = System.currentTimeMillis();
            if (value != null) {
                final long live = timeToLive();
                if ((live > 0) && ((currentTime - timeLive) > live)) {
                    destroy(value);
                    value = null;
                }
            }
            if (value != null) {
                final long idle = timeToIdle();
                if ((idle > 0) && ((currentTime - timeIdle) > idle)) {
                    destroy(value);
                    value = null;
                }
            }
            if (value == null) {
                value = initialize();
                timeLive = currentTime;
            }
            timeIdle = currentTime;
            return value;
        }
    }

    protected abstract T initialize();

    protected abstract void destroy(T value);

    protected abstract long timeToLive();

    protected abstract long timeToIdle();
}
