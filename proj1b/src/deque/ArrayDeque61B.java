package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = Math.floorMod(-1, items.length);
        nextLast = 0;
    }

    private void resizeUp(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < nextLast; i++) {
            a[i] = items[i];
        }
        for (int i = 1; i < size - nextFirst; i++) {
            a[capacity-i] = items[size-i];
        }
        items = a;
        nextFirst = Math.floorMod(nextFirst-size, items.length);
    }

    @Override
    public void addFirst(T x) {
        if ((nextFirst - nextLast) < 0) {
            resizeUp(size * 2);
        }
        items[nextFirst] = x;
        size++;
        nextFirst--;
    }

    @Override
    public void addLast(T x) {
        if ((nextFirst - nextLast) < 0) {
            resizeUp(size * 2);
        }
        items[nextLast] = x;
        size++;
        nextLast++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();

        for (int i = nextFirst + 1; i < items.length; i++) {
            returnList.add(items[i]);
        }
        for (int i = 0; i < nextLast; i++) {
            returnList.add(items[i]);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        // option: nextLast == 0 && nextFirst == Math.floorMod(-1, items.length
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    private void resizeDown(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < nextLast; i++) {
            a[i] = items[i];
        }
        for (int i = 1; i <= size-nextLast; i++) {
            a[capacity-i] = items[items.length-i];
        }
        items = a;
        nextFirst = Math.floorMod(nextLast-size-1, items.length);
    }

    @Override
    public T removeFirst() {
        T returnItem = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        size--;
        nextFirst++;

        if (size < (items.length * 0.25) && items.length > 15) {
            resizeDown(items.length / 2);
        }

        return returnItem;
    }

    @Override
    public T removeLast() {
        T returnItem = items[nextLast - 1];
        items[nextLast - 1] = null;
        size--;
        nextLast--;

        if (size < (items.length * 0.25) && items.length > 15) {
            resizeDown(items.length / 2);
        }

        return  returnItem;
    }

    @Override
    public T get(int index) {
        if (size < index) return null;

        if (index < 0) {
            index += size + 1;
        }

        if (index < (items.length - nextFirst)) {
            return items[nextFirst + index];
        }

        return items[index - nextFirst];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    private class ArrayDeque61BIterator implements Iterator<T> {
        private int wizPos;

        ArrayDeque61BIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            if (wizPos < size) {
                return true;
            }
            return false;
        }


        @Override
        public T next() {
            // returns the item
            T itemToReturn = items[(wizPos + nextFirst + 1) % items.length];

            // advances
            wizPos += 1;

            return itemToReturn;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDeque61BIterator();
    }

}
