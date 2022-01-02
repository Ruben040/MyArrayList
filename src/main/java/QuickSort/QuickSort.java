package QuickSort;

import java.util.Comparator;

/**
 * A class for quick sorting
 */
public class QuickSort{

    /**
     * Method for quick sorting
     * @param array - Array for sorting
     * @param begin - Start element
     * @param end - Last element
     * @param c - the Comparator used to compare array elements
     * @param <T> - The Type of elements
     */
    public static <T> void sort(Object [] array, int begin, int end,  Comparator<? super T> c) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end, c);

            sort(array, begin, partitionIndex-1, c);
            sort(array, partitionIndex+1, end, c);
        }
    }

    private static <T> int partition(Object [] array, int begin, int end, Comparator<? super T> c) {
        T pivot = (T)array[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (!(array[j] == null)) {
                if (c.compare((T) array[j], pivot) <= 0) {
                    i++;
                    swapItem(array, i, j);
                }
            }
        }
        swapItem(array, i+1, end);
        return i+1;
    }

    private static void swapItem(Object[] obj, int i, int j){
        Object swapTemp = obj[i];
        obj[i] = obj[j];
        obj[j] = swapTemp;
    }
}
