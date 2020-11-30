package part1.lesson04.task01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import part1.lesson05.task1.NameAndAgeException;
import static org.junit.Assert.fail;

import java.util.Random;

public class SortTest {

    Person[] array;
    int size = 10;

    @BeforeEach
    void setUp() {
        array = new Person[size];

        for (int i = 0; i < size; i++) {
            array[i] = new Person(getName(), getSex(), getAge());
        }
    }

    // подумайте, как можно вынести код из тестов в функцию, чтобы избежать дублирования

    @Test
    void bubbleSortTest() {
        long start = System.currentTimeMillis();

        BubbleSort bubbleSort = new BubbleSort(array);
        try {
            bubbleSort.sort();
        } catch (NameAndAgeException e) {
            fail(e.getMessage());
        }

        long end = System.currentTimeMillis();

        System.out.println("Прошло времени, мс: " + (end - start));
    }

    @Test
    void insertionSort() {
        long start = System.currentTimeMillis();

        InsertionSort insertionSort = new InsertionSort(array);
        try {
            insertionSort.sort();
        } catch (NameAndAgeException e) {
            fail(e.getMessage());
        }

        long end = System.currentTimeMillis();

        System.out.println("Прошло времени, мс: " + (end - start));
    }

    String getName() {
        Random sRandom = new Random();
        char[] sAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        int sLength = sAlphabet.length;

        int maxLen = sRandom.nextInt(15);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < maxLen; i++) {
            stringBuffer.append(sAlphabet[sRandom.nextInt(sLength)]);
        }

        return stringBuffer.toString();
    }

    int getAge() {
        return new Random().nextInt(100);
    }

    String getSex() {
        final String m = "MAN";
        final String w = "WOMAN";

        if (new Random().nextInt(2) == 1) {
            return m;
        }
        return w;
    }
}
