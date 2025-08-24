import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    public class BSTNode {
        K key;
        V value;
        BSTNode right;
        BSTNode left;
        BSTNode(K k, V v) {
            key = k;
            value = v;
        }
    }

    private BSTNode root;
    private int size;

    public BSTMap() {
        clear();
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalAccessError("null is not excepted");
        }
        root = putHelper(root, key, value);
    }

    private BSTNode putHelper(BSTNode root, K key, V value) {
        if (root == null) {
            root = new BSTNode(key, value);
            size++;
        } else if (key.equals(root.key)) {
            root.value = value;
        } else if ((key.compareTo(root.key)) > 0) {
            root.right = putHelper(root.right, key, value);
        } else {
            root.left = putHelper(root.left, key, value);
        }

        return root;
    }

    @Override
    public V get(K key) {
        return getHelper(root, key);
    }

    private V getHelper(BSTNode root, K key) {
        if (root == null) { return null; }
        if (root.key.equals(key)) {
            return root.value;
        } else if ((key.compareTo(root.key)) > 0) {
            return getHelper(root.right, key);
        } else {
            return getHelper(root.left, key);
        }
    }

    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(root, key);
    }

    private boolean containsKeyHelper(BSTNode root, K key) {
        if (root == null) { return false; }
        if (root.key.equals(key)) {
            return true;
        } else if ((key.compareTo(root.key)) > 0) {
            return containsKeyHelper(root.right, key);
        } else {
            return containsKeyHelper(root.left, key);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}