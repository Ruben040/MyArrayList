package List;

import QuickSort.QuickSort;
import java.util.Comparator;
import java.util.Iterator;

/**
 * This interface provides a contract to realising dynamic array
 * Has effective insertion and deletion methods
 * Extend an iterator interface for using the ForEach function
 * Allowed adding duplicate elements and null
 * @param <T> - The Type of elements
 */
public interface MyList<T> extends Iterable<T>{
    /**
     * Adding an element to the end
     * @param obj - element to add
     */
    void add(T obj);

    /**
     * Adding an element by index
     * @param index - index to add
     * @param obj - element to add
     */
    void add(int index, T obj);

    /**
     * Adding a list elements to the end
     * List should implement this interface
     * @param list - list to add
     */
    void addAll(MyList<? extends T> list);

    /**
     * Adding a list elements by index
     * List should implement this interface
     * Adds from the given index
     * @param index - index to start adding
     * @param list - list to add
     */
    void addAll(int index, MyList<? extends T> list);

    /**
     * Overwrites an element by Index
     * @param index - index to Overwrite
     * @param obj - element to Overwrite
     */
    void set(int index, T obj);

    /**
     * Get any item by index
     * @param index - index for getting
     * @return - element
     */
    T get(int index);

    /**
     * Deleting an element by index
     * @param index - index to delete
     */
    void delete(int index);

    /**
     * Deleting an element
     * @param obj - element to delete
     */
    void delete(T obj);

    /**
     * Deleting all elements
     */
    void deleteAll();

    /**
     *
     * @return - amount of added elements
     */
    int size();

    /**
     * Checking for the existence of an element in the list
     * @param obj - element to check
     * @return true if the element found, else false
     */
    boolean contains(Object obj);

    /**
     * Checking for the existence of an elements in the list
     * @param list - list elements
     * @return true if the elements found, else false
     */
    boolean containsAll(MyList<?> list);

    /**
     * Checking for an empty list
     * @return true if the list is empty, else false
     */
    boolean isEmpty();

    /**
     * Converting the list to an array
     * @return Array of elements
     */
    Object [] toArray();

    /**
     * Reduces the size of the array to the count of elements
     */
    void trim();

    /**
     * Removes all the elements from this list
     */
    void clear();

    /**
     * Returns the index of the first equal element, or -1 if element not found
     * @param obj - element to check
     * @return Returns the index of the first equal element, or -1 if element not found
     */
    int indexOf(T obj);

    /**
     * Returns the index of the last equal element, or -1 if element not found
     * @param obj - element to check
     * @return Returns the index of the last equal element, or -1 if element not found
     */
    int lastIndexOf(T obj);

    /**
     * Returns a list with the specified range inclusive
     * @param start - start index
     * @param end - last index
     * @return Returns a list with the specified range inclusive
     */
    MyList<T> subList(int start, int end);

    /**
     * Returns an iterator over the elements
     * @return Returns an iterator over the elements
     */
    @Override
    Iterator<T> iterator();

    /**
     * Sorts this list according to the order induced by the specified Comparator.
     * @param c - the Comparator used to compare list elements
     */
    default void sort(Comparator<? super T> c){
        this.trim();
        Object[] o = this.toArray();
        QuickSort.sort(o, 0,  o.length-1, c);
        for (int i = 0; i < o.length; i++) {
            this.set(i, (T)o[i]);
        }
    }

}
