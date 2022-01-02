package List;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Resizable array implementation of the MyList interface
 * @param <T> - The Type of elements
 */
public class MyArrayList<T> implements MyList<T>{
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int lastIndex = 0;

    /**
     * Initializing a default array of size DEFAULT_CAPACITY
     */
    public MyArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Initializing a default array of size capacity
     * @param capacity - array size
     */
    public MyArrayList(int capacity){
        array = (T[]) new Object[capacity];
    }

    /**
     * Adding an element to the end
     * @param obj - element to add
     */
    @Override
    public void add(T obj) {
        checkCapacity();
        array[lastIndex++] = obj;
    }

    /**
     * Adding an element by index
     * @param index - index to add
     * @param obj - element to add
     */
    @Override
    public void add(int index, T obj) {
        checkIndex(index);
        checkCapacity();
        System.arraycopy(array, index, array, index + 1, lastIndex - index);
        array[index] = obj;
        lastIndex++;
    }

    /**
     * Adding a list elements to the end
     * List should implement this interface
     * @param list - list to add
     */
    @Override
    public void addAll(MyList<? extends T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    /**
     * Adding a list elements by index
     * List should implement this interface
     * Adds from the given index
     * @param index - index to start adding
     * @param list - list to add
     */
    @Override
    public void addAll(int index, MyList<? extends T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(index++, list.get(i));
        }
    }

    /**
     * Overwrites an element by Index
     * @param index - index to Overwrite
     * @param obj - element to Overwrite
     */
    @Override
    public void set(int index, T obj) {
        checkIndex(index);
        array[index] = obj;
    }

    /**
     * Get any item by index
     * @param index - index for getting
     * @return - element
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    /**
     * Deleting an element by index
     * @param index - index to delete
     */
    @Override
    public void delete(int index) {
        checkIndex(index);
        System.arraycopy(array, index + 1, array, index, lastIndex - index - 1);
        array[--lastIndex] = null;
    }

    /**
     * Deleting an element
     * @param obj - element to delete
     */
    @Override
    public void delete(T obj) {
        if (contains(obj)){
            for (int i = 0; i < array.length; i++) {
                if (obj.equals(array[i])){
                    delete(i);
                }
            }
        }
    }

    /**
     * Deleting all elements
     */
    @Override
    public void deleteAll() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        lastIndex = 0;
    }

    /**
     * Amount of added elements
     * @return amount of added elements
     */
    @Override
    public int size() {
        return lastIndex;
    }

    /**
     * Checking for the existence of an element in the list
     * @param - obj element to check
     * @return true if the element found, else false
     */
    @Override
    public boolean contains(Object obj) {
        for (int i = 0; i < lastIndex; i++) {
            if (array[i].equals(obj)) return true;
        }
        return false;
    }

    /**
     * Checking for the existence of an elements in the list
     * @param list - list elements
     * @return true if the elements found, else false
     */

    @Override
    public boolean containsAll(MyList<?> list) {
        for (int i = 0; i < list.size(); i++) {
            if (!contains(list.get(i))) return false;
        }
        return true;
    }

    /**
     * Checking for an empty list
     * @return true if the list is empty, else false
     */
    @Override
    public boolean isEmpty() {
        return lastIndex > 0;
    }

    /**
     * Converting the list to an array
     * @return Array of elements
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, lastIndex);
    }

    /**
     * Reduces the size of the array to the count of elements
     */
    @Override
    public void trim() {
        array = Arrays.copyOf(array, lastIndex);
    }

    /**
     * Removes all the elements from this list
     */
    @Override
    public void clear() {
        for (int i = 0; i < lastIndex; i++) {
            array[i] = null;
        }
    }

    /**
     * Returns the index of the first equal element, or -1 if element not found
     * @param obj - element to check
     * @return Returns the index of the first equal element, or -1 if element not found
     */
    @Override
    public int indexOf(T obj) {
        if (contains(obj)){
            for (int i = 0; i < lastIndex; i++) {
                if (array[i].equals(obj)) return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last equal element, or -1 if element not found
     * @param obj - element to check
     * @return Returns the index of the last equal element, or -1 if element not found
     */
    @Override
    public int lastIndexOf(T obj) {
        if (contains(obj)){
            for (int i = lastIndex - 1; i >= 0; i--) {
                if (array[i].equals(obj)) return i;
            }
        }
        return -1;
    }

    /**
     * Returns a list with the specified range inclusive
     * @param start - start index
     * @param end - last index
     * @return Returns a list with the specified range inclusive
     */
    @Override
    public MyList<T> subList(int start, int end) {
        checkIndex(start);
        checkIndex(end);
        MyArrayList<T> ts = new MyArrayList<>();
        for (int i = start; i <= end ; i++) {
            ts.add(array[i]);
        }
        return ts;
    }

    /**
     * Returns an iterator over the elements
     * @return Returns an iterator over the elements
     */
    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(array);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayList<T> that = (MyArrayList<T>) o;
        return lastIndex == that.lastIndex && Arrays.equals(array, that.array);
    }

    private T[] newCapacity(T[] array) {
        T[] newArray = (T[]) new Object[(array.length * 3) / 2 + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private void checkIndex(int index){
        if (!(index <= lastIndex) || index < 0) throw new IndexOutOfBoundsException("index = " + index + " size = " + lastIndex);
    }

    private void checkCapacity(){
        if (lastIndex >= array.length) {
            array = newCapacity(array);
        }
    }

}
