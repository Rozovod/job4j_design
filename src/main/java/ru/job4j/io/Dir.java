package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File(
                "/Users/valentina/Documents/projects/job4j_design/src/main/java/ru/job4j/io");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : file.listFiles()) {
            System.out.printf("File name : %s; Size : %s", subfile.getAbsoluteFile(), file.length());
            System.out.println();
        }
    }
}
