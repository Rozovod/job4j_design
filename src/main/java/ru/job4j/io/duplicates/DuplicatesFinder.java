package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {

    public static void findDuple(Path path) throws IOException {
        DuplicatesVisitor dupleVisit = new DuplicatesVisitor();
        Files.walkFileTree(path, dupleVisit);
        dupleVisit.getFiles().forEach((k, v) -> {
            if (v.size() > 1) {
                v.forEach(System.out::println);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        Path path = Path.of(".");
        findDuple(path);
    }
}
