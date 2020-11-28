package part1.lesson05.task1;

import java.util.*;

public class GenHashMap<Key, Value> implements Map<Key, Value> {

    static final int INITIAL_CAPACITY = 1 << 4;
    static final float LOAD_FACTOR = 0.75f;
    private int capacity = INITIAL_CAPACITY;
    private int countElements;
    private int size;
    private Node<Key, Value>[] values;

    public GenHashMap() {
        values = new Node[INITIAL_CAPACITY];
    }

    static class Node<Key, Value> {
        final int hash;
        final Key key;
        Value value;
        Node<Key, Value> next;

        Node(int hash, Key key, Value value, Node<Key, Value> next) {
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

    @Override
    public int size() {
        return countElements;
    }

    @Override
    public boolean isEmpty() {
        return countElements == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    private Value getKey(Key key) {
        int hash = hash(key);
        int index = hash % values.length;
        Node<Key, Value> temp = values[index];
        while (temp != null) {
            if (key.equals(temp.key)) {
                return temp.value;
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public Value get(Object key) {
        return getKey((Key) key);
    }

    @Override
    public Value put(Key key, Value value) {
        Node<Key, Value> node = new Node<>(hash(key), key, value, null);
        int index = node.hash % values.length;

        if (values[index] != null) {
            Node<Key, Value> temp = values[index];
            while (temp != null) {
                if (temp.key.equals(node.key)) {
                    temp.value = node.value;
                    return value;
                }
                if (temp.next == null) {
                    temp.next = node;
                    return value;
                }
                temp = temp.next;
            }

        } else {
            values[index] = node;
            size++;
        }
        countElements++;
        resize();
        return value;
    }

    @Override
    public Value remove(Object key) {
        int hash = hash(key);
        int index = hash % values.length;

        Node<Key, Value> prev = null;
        Node<Key, Value> current = values[index];
        Node<Key, Value> firstEl;

        while (current != null) {
            if (key.equals(current.key)) {
                if (prev == null) {
                    firstEl = current;
                    values[index] = current.next;
                    if (values[index] == null) size--;
                    countElements--;
                    return firstEl.value;
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

    @Override
    public void putAll(Map<? extends Key, ? extends Value> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        countElements = 0;
        Arrays.fill(values, null);
    }

    @Override
    public Set<Key> keySet() {

        HashSet<Key> keys = new HashSet<>();

        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                Node<Key, Value> tmp = values[i];
                while (tmp != null) {
                    keys.add(tmp.key);
                    tmp = tmp.next;
                }
            }
        }
        return keys;
    }

    @Override
    public Collection<Value> values() {
        ArrayList<Value> val = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                Node<Key, Value> tmp = values[i];
                while (tmp != null) {
                    val.add(tmp.value);
                    tmp = tmp.next;
                }
            }
        }

        return val;
    }

    @Override
    public Set<Entry<Key, Value>> entrySet() {
        Set<Map.Entry<Key, Value>> es = new HashSet<>();

        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                Node<Key, Value> tmp = values[i];
                while (tmp != null) {
                    EntrySet<Key, Value> tmpSet = new EntrySet<>();
                    tmpSet.setKey(tmp.key);
                    tmpSet.setValue(tmp.value);
                    es.add(tmpSet);
                    tmp = tmp.next;
                }
            }
        }

        return es;
    }

    private static class EntrySet<Key, Value> implements Map.Entry<Key, Value> {

        private Key key;
        private Value value;

        @Override
        public Key getKey() {
            return key;
        }

        @Override
        public Value getValue() {
            return value;
        }

        public Key setKey(Key key) {
            this.key = key;
            return key;
        }

        @Override
        public Value setValue(Value value) {
            this.value = value;
            return value;
        }
    }

    private void resize() {
        if (size > capacity * LOAD_FACTOR) {
            int tempSize = size;
            size = 0;
            capacity = capacity * 2;
            Node<Key, Value>[] temp = values;
            values = new Node[capacity];
            for (int i = 0; i < tempSize; i++) {
                Node<Key, Value> tempNode = temp[i];
                while (tempNode != null) {
                    put(tempNode.key, tempNode.value);
                    tempNode = tempNode.next;
                }
            }
        }
    }
}
