package part1.lesson02.task01;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyHashMap implements Map {

    static final int INITIAL_CAPACITY = 1 << 4;
    static final float LOAD_FACTOR = 0.75f;
    private int countElements;
    private int size;
    private int capacity = INITIAL_CAPACITY;
    private Node[] values;

    MyHashMap() {
        values = new Node[INITIAL_CAPACITY];
    }

    static class Node {
        final int hash;
        final String key;
        String value;
        Node next;

        Node(int hash, String key, String value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public int size() {
        return countElements;
    }

    public boolean isEmpty() {
        return countElements == 0;
    }

    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    public Object get(Object key) {
        String k = (String) key;
        int hash = hash(k);
        int index = hash % values.length;
        Node tmp = values[index];

        while (tmp != null) {
            if (k.equals(tmp.key)) {
                return tmp.value;
            }
            tmp = tmp.next;
        }

        return null;
    }

    public Object put(Object key, Object value) {

        Node node = new Node(hash((String) key), (String) key, (String) value, null);
        int index = node.hash % values.length;

        if (values[index] != null) {
            Node tmp = values[index];

            while (tmp.next != null) {
                if (tmp.key.equals(node.key)) {
                    tmp.value = node.value;
                    break;
                }
                if (tmp.next == null) {
                    tmp.next = node;
                    break;
                }
                tmp = tmp.next;
            }
        } else {
            values[index] = node;
            size++;
        }

        countElements++;
        resize();
        return value;
    }

    public Object remove(Object key) {
        String k = (String) key;
        int hash = hash(k);
        int index = hash % values.length;

        Node prev = null;
        Node current = values[index];
        Node firstElem;

        while (current != null) {
            if (k.equals(current.key)) {
                if (prev == null) {
                    firstElem = current;
                    values[index] = current.next;
                    if (values[index] == null) size--;
                    countElements--;
                    return firstElem.value;
                } else {
                    prev.next = current.next;
                }
                countElements--;
            }

            prev = current;
            current = current.next;
        }

        if (current == null) return null;
        return current.value;
    }

    public void putAll(Map m) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        Arrays.fill(values, null);
        countElements = 0;
    }

    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    public Collection values() {
        throw new UnsupportedOperationException();
    }

    public Set<Entry> entrySet() {
        throw new UnsupportedOperationException();
    }

    private void resize() {
        if (size > capacity * LOAD_FACTOR) {
            int tmpSize = size;
            size = 0;
            capacity = capacity * 2;
            Node[] tmp = values;
            values = new Node[capacity];
            for (int i = 0; i < tmpSize; i++) {
                Node tmpNode = tmp[i];
                while (tmpNode != null) {
                    put(tmpNode.key, tmpNode.value);
                    tmpNode = tmpNode.next;
                }
            }
        }
    }
}
