package part1.lesson10.task01.utils;

import part1.lesson10.task01.model.Config;
import part1.lesson10.task01.model.Coord;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileOperation {
    static private Config config;
    private static String path;

    public static Config readConfig(String p) {
        path = p;
        try {
            config = GsonParser.parseJson(p);
        } catch (IOException e) {
            System.err.println("Can't read file");
        }
        return config;
    }

    public static void writeConfig(List<Coord> list) {
        config.setCoords(list);
        Path p = Paths.get(path);
        p = p.getParent();
        p = p.resolve("result.json");
        GsonParser.writeJson(config, p);
    }
}
