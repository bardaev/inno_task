package part1.lesson04.task01;

public class Person {

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

}
