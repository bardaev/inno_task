package part1.lesson02.task01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    @Test
    void size() {
        MyHashMap myHashMap = new MyHashMap();

        for (int i = 0; i < 10; i++) {
            myHashMap.put(String.valueOf(i), String.valueOf(i));
        }

        assertEquals(10, myHashMap.size());
    }

    @Test
    void isEmpty() {
        MyHashMap myHashMap = new MyHashMap();

        assertEquals(0, myHashMap.size());

        for (int i = 0; i < 10; i++) {
            myHashMap.put(String.valueOf(i), String.valueOf(i));
        }

        myHashMap.clear();

        assertEquals(0, myHashMap.size());
    }

    @Test
    void containsKey() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("key", "val");
        myHashMap.put("abc", "val1");

        assertTrue(myHashMap.containsKey("key"));
        assertTrue(myHashMap.containsKey("abc"));
    }

    @Test
    void get() {
        MyHashMap myHashMap = new MyHashMap();

        myHashMap.put("key", "val");
        myHashMap.put("abc", "val1");

        assertEquals("val", (String) myHashMap.get("key"));
        assertEquals("val1", (String) myHashMap.get("abc"));
    }

    @Test
    void negativePut() {
        final MyHashMap myHashMap = new MyHashMap();

        ClassCastException err = assertThrows(ClassCastException.class, () -> myHashMap.put(12, "val"));

        assertTrue(err.getMessage().contains("cannot be cast"));
    }

    @Test
    void remove() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("key", "val");
        myHashMap.put("abc", "val1");
        myHashMap.remove("key");
        myHashMap.remove("abc");

        assertNull(myHashMap.get("key"));
        assertNull(myHashMap.get("abc"));
    }

    @Test
    void clear() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("key", "val");
        myHashMap.put("abc", "val1");
        myHashMap.clear();
        assertEquals(0, myHashMap.size());
    }


}