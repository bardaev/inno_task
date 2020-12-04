package part1.lesson08.task01;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class WorkerUtils {

    public String getTextProgram(Path targetClass) {
        String packageStr = targetClass.getParent().toString().replace(File.separator, ".");
        String importPath = targetClass.toString().replace(File.separator, ".");
        importPath = importPath.substring(0, importPath.lastIndexOf(".java"));
        String header = "public class WorkerImpl implements Worker { \n" +
                "\tpublic void doWork() { \n";
        String footer = "\t}\n}";

        StringBuilder program = new StringBuilder();
        program.append("package " + packageStr + ";\n");
        program.append("import " + importPath + ";\n");
        program.append(header);

        Scanner in = new Scanner(System.in);
        String str;

        do {
            str = in.nextLine();
            program.append("\t\t" + str + "\n");
        } while (!str.equals(""));

        program.append(footer);

        return program.toString();
    }

    public void writeFileClass(String file, Path path) throws IOException {
        if (Files.exists(path)) {
            Files.deleteIfExists(path);
        }

        Files.createFile(path);

        BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
        writer.write(file);
        writer.close();
    }

    public void compileClass(Path path) throws FileNotFoundException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, path.toString());
    }
}
