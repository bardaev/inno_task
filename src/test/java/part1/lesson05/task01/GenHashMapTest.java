package part1.lesson05.task01;

import org.junit.jupiter.api.Test;
import part1.lesson05.task1.GenHashMap;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GenHashMapTest {

    @Test
    void size() {
        GenHashMap<Integer, String> genHashMap = new GenHashMap<>();
        HashMap<Integer, String> hashMap = new HashMap<>();
        assertEquals(hashMap.size(), genHashMap.size());
        genHashMap.put(1, "1");
        hashMap.put(1, "1");
        assertEquals(hashMap.size(), genHashMap.size());
        genHashMap.put(2, "2");
        hashMap.put(2, "2");
        assertEquals(hashMap.size(), genHashMap.size());
    }

    @Test
    void isEmpty() {
        GenHashMap<Integer, String> genHashMap = new GenHashMap<>();

        assertTrue(genHashMap.isEmpty());

        genHashMap.put(1, "1");
        genHashMap.put(2, "2");
        genHashMap.put(3, "3");

        genHashMap.clear();

        assertTrue(genHashMap.isEmpty());
    }

    @Test
    void get() {
        GenHashMap<Integer, String> genHashMap = new GenHashMap<>();
        HashMap<Integer, String> hashMap = new HashMap<>();

        genHashMap.put(1, "1");
        genHashMap.put(2, "2");
        genHashMap.put(3, "3");
        hashMap.put(1, "1");
        hashMap.put(2, "2");
        hashMap.put(3, "3");

        assertEquals(hashMap.get(1), genHashMap.get(1));
        assertEquals(hashMap.get(2), genHashMap.get(2));
        assertEquals(hashMap.get(3), genHashMap.get(3));
    }

    @Test
    void put() {
        GenHashMap<Integer, String> genHashMap = new GenHashMap<>();

        assertEquals("1", genHashMap.put(1, "1"));
        assertEquals("2", genHashMap.put(2, "2"));
        assertEquals("3", genHashMap.put(3, "3"));
    }

    @Test
    void negativePut() {
        GenHashMap<Integer, String> genHashMap = new GenHashMap<>();

        genHashMap.put(1, "1");
        genHashMap.put(1, "2");

        assertEquals("2", genHashMap.get(1));
    }

    @Test
    void remove() {
        GenHashMap<Integer, String> genHashMap = new GenHashMap<>();

        genHashMap.put(1, "1");
        genHashMap.put(2, "2");
        genHashMap.put(3, "3");

        genHashMap.remove(1);
        assertNull(genHashMap.get(1));
        genHashMap.remove(2);
        assertNull(genHashMap.get(2));
        genHashMap.remove(3);
        assertNull(genHashMap.get(3));
    }

    @Test
    void negativeRemove() {
        GenHashMap<Integer, String> genHashMap = new GenHashMap<>();

        genHashMap.put(1, "1");
        genHashMap.put(2, "2");
        genHashMap.put(3, "3");

        genHashMap.remove(3);
        assertNull(genHashMap.get(3));
    }

    @Test
    void keySet() {
        GenHashMap<Integer, String> genHashMap = new GenHashMap<>();

        for (int i = 0; i < 10; i++) {
            genHashMap.put(i, String.valueOf(i));
        }

        Set<Integer> set = genHashMap.keySet();

        for (int i = 0; i < 10; i++) {
            assertTrue(set.contains(i));
        }
    }

    @Test
    void values() {
        GenHashMap<Integer, String> genHashMap = new GenHashMap<>();

        for (int i = 0; i < 10; i++) {
            genHashMap.put(i, String.valueOf(i));
        }

        Collection<String> list = genHashMap.values();

        for (int i = 0; i < 10; i++) {
            assertTrue(list.contains(String.valueOf(i)));
        }
    }

    @Test
    void entrySet() {
        GenHashMap<Integer, String> genHashMap = new GenHashMap<>();

        for (int i = 0; i < 10; i++) {
            genHashMap.put(i, String.valueOf(i));
        }

        Set<Map.Entry<Integer, String>> entrySet = genHashMap.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            assertTrue(genHashMap.containsKey(entry.getKey()));
        }
    }
}