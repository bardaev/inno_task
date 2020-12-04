package part1.lesson08.task01;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RunMe {
    public static void main(String[] args) {
        Path targetClass = Paths.get("part1");
        targetClass = targetClass.resolve("lesson08").resolve("task01").resolve("Worker.java");

        WorkerUtils implementation = new WorkerUtils();
        String text = implementation.getTextProgram(targetClass);

        Path fullPathToTargetClass = Paths.get(System.getProperty("user.dir"));
        fullPathToTargetClass = fullPathToTargetClass.resolve("src").resolve("main").resolve("java").resolve(targetClass);
        fullPathToTargetClass = fullPathToTargetClass.getParent().resolve("WorkerImpl.java");
        System.out.println(fullPathToTargetClass.toAbsolutePath());

        try {
            implementation.writeFileClass(text, fullPathToTargetClass);
            implementation.compileClass(fullPathToTargetClass);
        } catch (IOException e) {
            e.printStackTrace();
        }

        WorkerLoader workerLoader = new WorkerLoader(ClassLoader.getSystemClassLoader());
        try {
            Class clazz = workerLoader.loadClass("part1.lesson08.task01.WorkerImpl");
            Worker workerImpl = (Worker) clazz.newInstance();
            workerImpl.doWork();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
