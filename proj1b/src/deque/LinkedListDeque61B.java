package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    public class linkedListDeque61B {
        public T item;
        public linkedListDeque61B prev;
        public linkedListDeque61B next;

        public linkedListDeque61B(linkedListDeque61B p, T i, linkedListDeque61B n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private int size;
    private linkedListDeque61B sentinel;

    public LinkedListDeque61B() {
        size = 0;
        sentinel = new linkedListDeque61B(null,null, null);
    }

    @Override
    public void addFirst(T x) {
        if (size == 0) {
            sentinel.next = new linkedListDeque61B(sentinel, x, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next.prev  = new linkedListDeque61B(sentinel, x, sentinel.next);
            sentinel.next = sentinel.next.prev;
        }
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == 0) {
            sentinel.next = new linkedListDeque61B(sentinel, x, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.prev.next = new linkedListDeque61B(sentinel.prev, x, sentinel);
            sentinel.prev = sentinel.prev.next;
        }
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>(size);
        if (size == 0) return returnList;

        linkedListDeque61B p = sentinel.next;
        while (p != sentinel) {
            returnList.add(p.item);
            p = p.next;
        }

        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) return null;

        T returnItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;

        return  returnItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) return null;

        T returnItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;

        return  returnItem;
    }

    @Override
    public T get(int index) {
        if (size < index) return null;
        if (index < 0) index += size;

        int i = 0;
        linkedListDeque61B p = sentinel.next;
        while (i != index) {
            p = p.next;
            i++;
        }

        return p.item;
    }

    @Override
    public T getRecursive(int index) {
        if (size < index) return null;
        if (index < 0) index += size;

        if (index == 0) return sentinel.next.item;

        sentinel.next = sentinel.next.next;
        return getRecursive(index - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

}
