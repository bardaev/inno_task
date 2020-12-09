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
        if (person1.getName().equals(person2.getName()) && person1.getAge() == person2.getAge()) {
            NameAndAgeException err = new NameAndAgeException("Same name: " +
                    "name 1: " + person1.getName() + " name 2: " + person2.getName() +
                    " And age 1: " + person1.getAge() + " age 2: " + person2.getAge());
            throw err;
        }
        if (person1.getSex().equals("MAN") && person2.getSex().equals("WOMAN")) return true;
        else if (person1.getSex().equals(person2.getSex()) && person1.getAge() > person2.getAge()) return true;
        else if (person1.getSex().equals(person2.getSex()) && person1.getAge() == person2.getAge() && person1.getName().compareTo(person2.getName()) > 0) return true;
        return false;
    }

}
