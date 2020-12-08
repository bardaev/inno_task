package part1.lesson10.task01.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import part1.lesson10.task01.model.Config;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class GsonParser {
    private static Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gson = gsonBuilder.create();
    }

    public static Config parseJson(String url) throws IOException {
        JsonReader jsonReader = new JsonReader(new FileReader(url));
        return gson.fromJson(jsonReader, Config.class);
    }

    public static void writeJson(Config config, Path path) {
        try {
            Files.write(path, gson.toJson(config).getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
