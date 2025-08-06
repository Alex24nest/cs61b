package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int length;

    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = Math.floorMod(-1, items.length);
        nextLast = 0;
        length = 8;
    }

    private void resizeUp(int capacity) {
        T[] a = (T[]) new Object[capacity];
        length = capacity;
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
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resizeDown(int capacity) {
        T[] a = (T[]) new Object[capacity];
        length = capacity;
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
        if (size == 0) { return null; }
        T returnItem;

        if (nextFirst == length - 1) {

            returnItem = items[0];
            items[0] = null;

            int i = 1;
            while (i < size) {
                if (items[i] != null) {
                    items[i - 1] = items[i];
                }
                i++;
            }
            items[i-1] = null;

            nextLast--;
        } else {
            returnItem = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst++;
        }
        size--;

        if (size < (items.length * 0.25) && items.length > 15) {
            resizeDown(items.length / 2);
        }

        return returnItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) { return null; }

        T returnItem;

        if (nextLast == 0) {
            returnItem = items[length - 1];
            items[length - 1] = null;

            int i = length - 2;
            while (i >= 0) {
                if (items[i] != null) {
                    items[i + 1] = items[i];
                }
                i--;
            }
            items[i + 1] = null;

            nextFirst++;

        } else {
            returnItem = items[nextLast - 1];
            items[nextLast - 1] = null;

            nextLast--;
        }
        size--;


        if (size < (items.length * 0.25) && items.length > 15) {
            resizeDown(items.length / 2);
        }

        return  returnItem;
    }

    @Override
    public T get(int index) {
        if (size < index) return null;

        if (index < 0) {
            index += size;
        }

        int i = (index + nextFirst + 1) % items.length;

        return items[i];
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

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o instanceof Deque61B<?> other) {
            if (this.size != other.size()) {
                return false;
            }

            Iterator<T> thisIterator = this.iterator();
            Iterator<?> otherIterator = other.iterator();



            while (thisIterator.hasNext()) {
                T a = thisIterator.next();
                Object b = otherIterator.next();

                if (a == null) {
                    if (b != null) { return false; }
                } else if (!a.equals(b)) { return false; }
            }

            return true;

        }
        return false;
    }

    @Override
    public String toString() {
        return this.toList().toString();
    }

}
