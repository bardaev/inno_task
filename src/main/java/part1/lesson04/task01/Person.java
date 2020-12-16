package part1.lesson04.task01;

public class Person implements Comparable<Person> {

    String sex;
    int age;
    String name;

    public Person(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "sex='" + sex + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Person person = (Person) obj;
        return this.name.equals(person.name) && age == person.age && this.sex.equals(person.sex);
    }

    @Override
    public int compareTo(Person person) {
        if (this.equals(person)) return 0;
        if (this.sex.equals("MAN") && person.sex.equals("WOMAN")) return 1;
        else if (this.sex.equals(person.sex) && this.age > person.age) return 1;
        else if (this.sex.equals(person.sex) && this.age == person.age && this.name.compareTo(person.name) > 0) return 1;
        return -1;
    }
}
