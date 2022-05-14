package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Введены не все аргументы.");
        }
        ArgsName argsZip = ArgsName.of(args);
        File file = new File(argsZip.get("d"));
        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException("Указанная директория не найдена");
        }
        List<File> rslFile = new ArrayList<>();
        List<Path> rslPath = Search.search(file.toPath(), p -> !p.toFile().getName().endsWith(argsZip.get("e")));
        for (Path p : rslPath) {
            rslFile.add(p.toFile().getAbsoluteFile());
        }
        Zip zip = new Zip();
        zip.packFiles(rslFile, new File(argsZip.get("d") + argsZip.get("o")));
    }
}
