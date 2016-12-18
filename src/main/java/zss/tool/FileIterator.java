package zss.tool;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

@Version("2014-10-02")
public class FileIterator implements Iterator<File>, Iterable<File>
{
    private final LinkedList<File> queue = new LinkedList<>();

    public FileIterator(final File folder)
    {
        queue.addLast(folder);
    }

    @Override
    public boolean hasNext()
    {
        return !queue.isEmpty();
    }

    @Override
    public File next()
    {
        if (queue.isEmpty())
        {
            return null;
        }
        File file = queue.removeLast();
        if (file.isDirectory())
        {
            Iterator<File> iterator = IOTool.listFiles(file).descendingIterator();
            while (iterator.hasNext())
            {
                queue.addLast(iterator.next());
            }
        }
        return file;
    }

    @Override
    public void remove()
    {
    }

    @Override
    public Iterator<File> iterator()
    {
        return this;
    }
}
