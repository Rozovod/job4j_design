package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        validateArgs(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getList();
    }

    public static void validateArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        File file = new File(args[0]);
        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException("First parameter is not a path or not a directory");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Second parameter is not the file extension");
        }
    }
}
