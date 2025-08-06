package deque;
import java.util.Comparator;

public class Maximizer61B {
    /**
     * Returns the maximum element from the given iterable of comparables.
     * You may assume that the iterable contains no nulls.
     *
     * @param iterable the Iterable of T
     * @return the maximum element
     */
    public static <T extends Comparable<T>> T max(Iterable<T> iterable) {

        T maxValue = null;

        for (T i : iterable) {
            if (i == null) {
                return null;
            }
            if (maxValue == null) {
                maxValue = i;
            }
            int cmp = i.compareTo(maxValue);
            if (cmp > 0) {
                maxValue = i;
            }
        }

        return maxValue;
    }

    /**
     * Returns the maximum element from the given iterable according to the specified comparator.
     * You may assume that the iterable contains no nulls.
     *
     * @param iterable the Iterable of T
     * @param comp     the Comparator to compare elements
     * @return the maximum element according to the comparator
     */
    public static <T> T max(Iterable<T> iterable, Comparator<T> comp) {
        T maxValue = null;

        for (T i : iterable) {
            if (i == null) {
                return null;
            }
            if (maxValue == null) {
                maxValue = i;
            }

            int cmp = comp.compare(i, maxValue);

            if (cmp > 0) {
                maxValue = i;
            }

        }

        return maxValue;
    }
}