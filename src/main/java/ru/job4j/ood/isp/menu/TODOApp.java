package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TODOApp {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    public static final int ADD_ROOT_ITEM = 1;
    public static final int ADD_ITEM = 2;
    public static final int PRINT_ITEMS = 3;
    public static final String ITEM_NAME = "Введите название пункта меню.";
    public static final String CHILD_NAME = "Введите название подпункта меню.";
    public static final String ADD_SUCCESS = "Пункт меню добавлен успешно.";
    public static final String ADD_CHILD_SUCCESS = "Подпункт меню добавлен успешно.";
    public static final String ADD_FAIL = "Пункт меню НЕ добавлен. Проверьте корректность имени.";
    public static final String ADD_CHILD_FAIL = "Подпункт меню НЕ добавлен. Проверьте корректность имени предка.";
    public static final String EXIT = "Завершение работы";
    public static final String MENU = """
                 Введите 1, чтобы добавить пункт меню.
                 Введите 2, чтобы добавить подпункт меню.
                 Введите 3, чтобы вывести все пункты меню на экран.
                 Введите любое другое число для выхода.
             """;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new ConsoleMenuPrinter();
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scanner.nextLine());
            switch (userChoice) {
                case (ADD_ROOT_ITEM) -> {
                    System.out.println(ITEM_NAME);
                    String itemName = scanner.nextLine();
                    boolean additionResult = menu.add(Menu.ROOT, itemName, STUB_ACTION);
                    System.out.println(additionResult ? ADD_SUCCESS : ADD_FAIL);
                }
                case (ADD_ITEM) -> {
                    System.out.println(ITEM_NAME);
                    String itemName = scanner.nextLine();
                    System.out.println(CHILD_NAME);
                    String childName = scanner.nextLine();
                    boolean additionResult = menu.add(itemName, childName, STUB_ACTION);
                    System.out.println(additionResult ? ADD_CHILD_SUCCESS : ADD_CHILD_FAIL);
                }
                case (PRINT_ITEMS) -> printer.print(menu);
                default -> {
                    run = false;
                    System.out.println(EXIT);
                }
            }
        }
    }
}
