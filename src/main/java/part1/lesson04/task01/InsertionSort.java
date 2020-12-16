package part1.lesson04.task01;

import part1.lesson05.task1.NameAndAgeException;

import java.util.Arrays;

public class InsertionSort implements Sort {
    private Person[] array;

    public InsertionSort(Person[] arr) {
        this.array = arr;
    }

    @Override
    public void sort() throws NameAndAgeException {
        for (int i = 1; i < array.length; i++) {
            Person current = array[i];
            int j = i - 1;
            while (j >= 0 && current.compareTo(array[i]) > 0) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = current;
        }

        Arrays.asList(array).stream()
                .forEach(System.out::println);
    }
}
