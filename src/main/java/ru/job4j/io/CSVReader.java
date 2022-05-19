package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        try (Scanner sc = new Scanner(new FileInputStream(argsName.get("path")))) {
            List<String> rslList = new ArrayList<>();
            List<String> columnFilter = new ArrayList<>(
                    Arrays.asList(argsName.get("filter").split(","))
            );
            String[] firstLine = sc.nextLine().split(delimiter);
            List<Integer> index = new ArrayList<>();
            List<String> filterFirstLine = new ArrayList<>();
            for (int i = 0; i < firstLine.length; i++) {
                if (columnFilter.contains(firstLine[i])) {
                    index.add(i);
                    filterFirstLine.add(firstLine[i]);
                }
            }
            StringJoiner sj = new StringJoiner(delimiter);
            filterFirstLine.forEach(sj::add);
            String firstLn = sj.toString();
            rslList.add(firstLn);
            while (sc.hasNext()) {
                StringJoiner sjSec = new StringJoiner(delimiter);
                String[] str = sc.nextLine().split(delimiter);
                for (int j = 0; j < str.length; j++) {
                    if (index.contains(j)) {
                        sjSec.add(str[j]);
                    }
                }
                String rslLine = sjSec.toString();
                rslList.add(rslLine);
            }
            if (Objects.equals("stdout", out)) {
                for (String ln : rslList) {
                    System.out.println(ln);
                }

            } else {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(out, true))) {
                    for (String ln : rslList) {
                        bw.write(ln);
                        bw.write(System.lineSeparator());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validationArgs(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Введены не все параметры.");
        }
        ArgsName argsCsv = ArgsName.of(args);
        String path = argsCsv.get("path");
        File file = new File(path);
        if (!file.exists() || !path.endsWith(".csv")) {
            throw new IllegalArgumentException(
                    "Указанный параметр path не существует или файл имеет неверный формат.");
        }
    }

    public static void main(String[] args) throws IOException {
        validationArgs(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
