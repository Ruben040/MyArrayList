package List;

import java.util.Iterator;

/**
 * Class for working with iterator
 * @param <T>
 */
public class MyIterator<T> implements Iterator<T> {
    private int index;
    private T[] values;

    /**
     * Initializing an array
     * @param values - array
     */
    public MyIterator(T[] values) {
        this.values = values;
    }

    /**
     * Checking for the following element
     * @return True if element exist
     */
    @Override
    public boolean hasNext() {
        return index < values.length;
    }

    /**
     * Returns an element
     * @return Returns an element
     */
    @Override
    public T next() {
        return values[index++];
    }
}
