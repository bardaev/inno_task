package part1.lesson07.task01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CleanUpStreamTest {

    TreeSet<String> fieldToCleanUp;
    TreeSet<String> fieldToOutput;
    CleanUpStream cleanUp;

    @BeforeEach
    void setUp() {
        fieldToCleanUp = new TreeSet<>();
        fieldToOutput = new TreeSet<>();

        fieldToCleanUp.add("f1");
        fieldToCleanUp.add("f2");
        fieldToCleanUp.add("f3");
        fieldToCleanUp.add("d");
        fieldToCleanUp.add("f4");
        fieldToCleanUp.add("f5");
        fieldToCleanUp.add("f6");
        fieldToCleanUp.add("arr");

        fieldToOutput.add("f1");
        fieldToOutput.add("f2");
        fieldToOutput.add("f3");
        fieldToCleanUp.add("d");
        fieldToOutput.add("f4");
        fieldToOutput.add("f5");
        fieldToOutput.add("f6");
        fieldToOutput.add("arr");

        cleanUp = new CleanUpStream();
    }

    @Test
    void cleanUpObject() {
        part1.lesson07.task01.Test test = new part1.lesson07.task01.Test(true, "abc", 2, new Date(), 0.5f, '\u1233', 0.7, new int[10]);
        cleanUp.cleanUp(test, fieldToCleanUp, fieldToOutput);

        assertFalse(test.f1);
        assertNull(test.f2);
        assertEquals(0, test.f3);
        assertNull(test.d);
        assertEquals(0.0f, test.f4);
        assertEquals('\u0000', test.f5);
        assertEquals(0.0, test.f6);
        assertNull(test.arr);
    }

    @Test
    void negativeCleanUpObject() {
        part1.lesson07.task01.Test test = new part1.lesson07.task01.Test(true, "abc", 2, new Date(), 0.5f, '\u1233', 0.7, new int[10]);

        fieldToCleanUp.add("noExistField");

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> cleanUp.cleanUp(test, fieldToCleanUp, fieldToOutput));

        assertNotNull(thrown.getMessage());
    }

    @Test
    void cleanUpMap() {
        Object PRESENT = new Object();
        Map<String, Object> map = new HashMap<>();

        map.put("f1", PRESENT);
        map.put("f2", PRESENT);
        map.put("f3", PRESENT);
        map.put("d", PRESENT);
        map.put("f4", PRESENT);
        map.put("f5", PRESENT);
        map.put("f6", PRESENT);
        map.put("arr", PRESENT);

        fieldToCleanUp.remove("f2");
        fieldToCleanUp.remove("f4");
        fieldToCleanUp.remove("f6");
        fieldToCleanUp.remove("arr");

        fieldToOutput.remove("f1");
        fieldToOutput.remove("f3");
        fieldToOutput.remove("d");
        fieldToOutput.remove("f5");

        cleanUp.cleanUp(map, fieldToCleanUp, fieldToOutput);

        Iterator<String> iterator = fieldToCleanUp.iterator();

        while (iterator.hasNext()) {
            assertFalse(map.containsKey(iterator.next()));
        }
    }
}