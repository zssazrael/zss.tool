package zss.tool;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

@Version("2019.07.11")
public class CollectionTool {
    public static <T> T removeFirst(final Deque<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.removeFirst();
    }

    public static <T> T removeFirst(final List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.remove(0);
    }

    public static <T> T removeFirst(final Collection<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        final Iterator<T> iterator = list.iterator();
        final T value = iterator.next();
        iterator.remove();
        return value;
    }
}
