package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.get(key) == null) {
            throw new IllegalArgumentException("Несуществующее значение ключа");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .filter(this::validateArgs)
                .map(l -> l.substring(1))
                .map(l -> l.split("=", 2))
                .forEach(i -> values.put(i[0], i[1]));
    }

    private boolean validateArgs(String args) {
        boolean rsl = args.startsWith("-") && args.contains("=")
                && !args.startsWith("-=") && args.indexOf("=") != args.length() - 1;
        if (!rsl) {
            throw new IllegalArgumentException("Нарушение шаблона -ключ=значение");
        }
        return rsl;
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Параметры не введены. Введите параметры.");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
