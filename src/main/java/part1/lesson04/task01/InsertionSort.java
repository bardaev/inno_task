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
            while(j >= 0 && isNeedSwap(current, array[j])) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = current;
        }

        Arrays.asList(array).stream()
                .forEach(System.out::println);
    }

    private boolean isNeedSwap(Person person1, Person person2) throws NameAndAgeException {
        if (person1.name.equals(person2.name) && person1.age == person2.age) {
            NameAndAgeException err = new NameAndAgeException("Same name: " +
                    "name 1: " + person1.name + " name 2: " + person2.name +
                    " And age 1: " + person1.age + " age 2: " + person2.age);
            throw err;
        }
        if (person1.sex.equals("MAN") && person2.sex.equals("WOMAN")) return true;
        else if (person1.sex.equals(person2.sex) && person1.age > person2.age) return true;
        else if (person1.sex.equals(person2.sex) && person1.age == person2.age && person1.name.compareTo(person2.name) > 0) return true;
        return false;
    }

}
