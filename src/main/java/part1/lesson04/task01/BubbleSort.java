package part1.lesson04.task01;

import part1.lesson05.task1.NameAndAgeException;

import java.util.Arrays;

public class BubbleSort implements Sort {
    private Person[] array;
    boolean sorted = false;

    public BubbleSort (Person[] arr) {
        this.array = arr;
    }

    @Override
    public void sort() throws NameAndAgeException {

        while(!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (isNeedSwapSex(i)) {
                    swap(i);
                } else if (isSameSex(i) && isNeedSwapAge(i)) {
                    swap(i);
                } else if (isSameSex(i) && isSameAge(i) && array[i].getName().compareTo(array[i+1].getName()) > 0) {
                    swap(i);
                }
            }
        }
        Arrays.asList(array).stream()
                .forEach(System.out::println);
    }

    private boolean isNeedSwapSex(int pos) {
        if (array[pos].getSex().equals("WOMAN") && array[pos+1].getSex().equals("MAN")) return true;
        return false;
    }

    private boolean isNeedSwapAge(int pos) {
        return array[pos].getAge() < array[pos+1].getAge();
    }

    private boolean isSameSex(int pos) {
        return array[pos].getSex().equals(array[pos+1].getSex());
    }

    private boolean isSameAge(int pos) {
        return array[pos].getAge() == array[pos+1].getAge();
    }

    /**
     * Меняет местами элементы массива
     * @param pos in array
     * if name1 == name2 and age1 == age2 @throws NameAndAgeException
     */
    private void swap(int pos) throws NameAndAgeException {
        Person temp;

        if (array[pos].getName().equals(array[pos+1].getName()) && array[pos].getAge() == array[pos+1].getAge()) {
            NameAndAgeException err = new NameAndAgeException("Same name: " +
                    "name 1: " + array[pos].getName() + " name 2: " + array[pos+1].getName() +
                    " And age 1: " + array[pos].getAge() + " age 2: " + array[pos+1].getAge());
            throw err;
        }
        temp = array[pos];
        array[pos] = array[pos+1];
        array[pos+1] = temp;
        sorted = false;
    }

}
