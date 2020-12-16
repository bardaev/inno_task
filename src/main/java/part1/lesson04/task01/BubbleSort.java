package part1.lesson04.task01;

import part1.lesson05.task1.NameAndAgeException;

import java.util.Arrays;

public class BubbleSort implements Sort {
    private Person[] array;
    private Person temp;
    boolean sorted = false;

    public BubbleSort (Person[] arr) {
        this.array = arr;
    }

    @Override
    public void sort() throws NameAndAgeException {

        while(!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i].compareTo(array[i+1]) < 0) {
                    swap(i);
                }
            }
        }
        Arrays.asList(array).stream()
                .forEach(System.out::println);
    }

    /**
     * Меняет местами элементы массива
     * @param pos in array
     * if name1 == name2 and age1 == age2 @throws NameAndAgeException
     */
    private void swap(int pos) throws NameAndAgeException {

        if (array[pos].name.equals(array[pos+1].name) && array[pos].age == array[pos+1].age) {
            NameAndAgeException err = new NameAndAgeException("Same name: " +
                    "name 1: " + array[pos].name + " name 2: " + array[pos+1].name +
                    " And age 1: " + array[pos].age + " age 2: " + array[pos+1].age);
            throw err;
        }
        temp = array[pos];
        array[pos] = array[pos+1];
        array[pos+1] = temp;
        sorted = false;
    }

}
