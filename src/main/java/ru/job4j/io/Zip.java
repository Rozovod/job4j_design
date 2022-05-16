package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static void validation(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Введены не все аргументы.");
        }
        ArgsName argsZip = ArgsName.of(args);
        File file = new File(argsZip.get("d"));
        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException("Указанная директория не найдена");
        }
        if (!argsZip.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Неверное расширение файлов для исключения");
        }
    }

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path file : sources) {
                zip.putNextEntry(new ZipEntry(file.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        validation(args);
        ArgsName argsZip = ArgsName.of(args);
        File file = new File(argsZip.get("d"));
        List<Path> rslPath = Search.search(file.toPath(), p -> !p.toFile().getName().endsWith(argsZip.get("e")));
        Zip zip = new Zip();
        zip.packFiles(rslPath, new File(argsZip.get("d") + argsZip.get("o")));
    }
}
