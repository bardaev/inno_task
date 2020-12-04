package part1.lesson08.task01;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WorkerLoader extends ClassLoader {

    public WorkerLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.equals("part1.lesson08.task01.WorkerImpl")) {
            Path dest = Paths.get(System.getProperty("user.dir")).resolve("src").resolve("main").resolve("java").resolve("part1").resolve("lesson08").resolve("task01").resolve("WorkerImpl.java");
            byte[] classData = null;
            try (InputStream inputStream = new URL(dest.toUri().toString()).openConnection().getInputStream();
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                int data = inputStream.read();
                while (data != -1) {
                    byteArrayOutputStream.write(data);
                    data = inputStream.read();
                }
                classData = byteArrayOutputStream.toByteArray();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return defineClass(name, classData, 0, classData.length);
        } else {
            return super.loadClass(name);
        }
    }
}
