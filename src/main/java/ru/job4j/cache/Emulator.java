package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {
    private static final int LOAD_IN_CACHE = 1;
    private static final int GET_FROM_CACHE = 2;

    private static final String MENU = """
                Введите 1, чтобы загрузить содержимое файла в кэш.
                Введите 2, чтобы получить содержимое файла из кэша.
                Введите любое другое число для выхода.
            """;

    public static final String SELECT = "Выберите пункт меню: ";
    public static final String DIR = "Введите адресс кэшируемой директории: ";
    public static final String FILE_ADD = "Введите имя кэшируемого файла: ";
    public static final String FILE_GET = "Введите имя файла, чтобы получить данные: ";
    public static final String GOAL = "Готово";
    public static final String EXIT = "Конец работы";

    private static void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(DIR);
        String dir = scanner.nextLine();
        DirFileCache cache = new DirFileCache(dir);
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == LOAD_IN_CACHE) {
                System.out.println(FILE_ADD);
                String nameFile = scanner.nextLine();
                cache.put(nameFile, cache.load(nameFile));
                System.out.println(GOAL);
            } else if (choice == GET_FROM_CACHE) {
                System.out.println(FILE_GET);
                String key = scanner.nextLine();
                if (cache.get(key) == null) {
                    cache.put(key, cache.load(key));
                }
                System.out.println(cache.get(key));
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    public static void main(String[] args) {
        start();
    }
}
