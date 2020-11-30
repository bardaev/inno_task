package part1.lesson04.task01;

import part1.lesson05.task1.NameAndAgeException;

import java.util.Arrays;

public class BubbleSort implements Sort {
    private Person[] array;
    // temp нужен лишь в функции swap, старайтесь максимально ограничивать область видимости переменных
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
                // по идее вы уже после срабатывания этого условия можете выйти из цикла, вам не нужно потом три раза
                // ещё проверять isSameSex. Тоже относится и к следующему условию. Проще объединить все условия
                // в одно как вы сделали в InsertionSort. Можете посмотреть в сторону Comparator и реализовать в
                // Person интерфейс Comparable
                if (isNeedSwap(i)) {
                    swap(i);
                }

                if (isSameSex(i) && isNeedSwapAge(i)) {
                    swap(i);
                }

                if (isSameSex(i) && isSameAge(i) && array[i].name.compareTo(array[i+1].name) > 0) {
                    swap(i);
                }
            }
        }
        Arrays.asList(array).stream()
                .forEach(System.out::println);
    }

    // единообразность это важно. Эту функцию следует назвать isNeedSwapSex в соответствии со всеми остальными
    private boolean isNeedSwap(int pos) {
        if (array[pos].sex.equals("WOMAN") && array[pos+1].sex.equals("MAN")) return true;
        return false;
    }

    private boolean isNeedSwapAge(int pos) {
        return array[pos].age < array[pos+1].age;
    }

    private boolean isSameSex(int pos) {
        // старайтесь писать максимально компактно. Конструкт if тут не обязателен. И опять же будьте последовательны и
        // пишите единообразно
        if (array[pos].sex.equals(array[pos+1].sex)) return true;
        return false;
    }

    private boolean isSameAge(int pos) {
        return array[pos].age == array[pos+1].age;
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
