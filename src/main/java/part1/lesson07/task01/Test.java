package part1.lesson07.task01;

import java.util.Arrays;
import java.util.Date;

public class Test {
    boolean f1;
    String f2;
    int f3;
    Date d;
    float f4;
    char f5;
    double f6;
    int[] arr;

    public Test() {}

    public Test(boolean f1, String f2, int f3, Date d, float f4, char f5, double f6, int[] arr) {
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
        this.d = d;
        this.f4 = f4;
        this.f5 = f5;
        this.f6 = f6;
        this.arr = arr;
    }

    @Override
    public String toString() {
        return "Test{" +
                "f1=" + f1 +
                ", f2='" + f2 + '\'' +
                ", f3=" + f3 +
                ", d=" + d +
                ", f4=" + f4 +
                ", f5=" + f5 +
                ", arr=" + Arrays.toString(arr) +
                '}';
    }
}