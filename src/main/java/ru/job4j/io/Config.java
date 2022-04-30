package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(l -> !l.startsWith("#"))
                    .filter(l -> !l.isEmpty())
                    .filter(this::correctPattern)
                    .map(i -> i.split("=", 2))
                    .filter(i -> i.length == 2)
                    .forEach(i -> values.put(i[0], i[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    private boolean correctPattern(String line) {
        String[] ln = line.split("=", 2);
        if (!line.contains("=") || ln[0].isEmpty() || ln[1].isEmpty()
                || line.matches("[=]+")) {
            throw new IllegalArgumentException("Нарушение шаблона ключ=значение");
        }
        return line.contains("=") || !line.matches("[=]+");
    }


    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
        String line = "=key";
        String[] lineArr = line.split("=", 2);
        for (String ln : lineArr) {
            System.out.println(ln);
        }
        System.out.println(lineArr.length);
    }
}
