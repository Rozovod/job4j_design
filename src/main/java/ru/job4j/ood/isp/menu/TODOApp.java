package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TODOApp {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    public static final int ADD_ITEM = 1;
    public static final int PRINT_ITEMS = 2;
    public static final String ITEM_NAME = "Введите название пункта меню.";
    public static final String CHILD_NAME = "Введите название подпункта меню.";
    public static final String ADD_SUCCESS = "Пункт меню добавлен успешно.";
    public static final String EXIT = "Завершение работы";
    public static final String MENU = """
                 Введите 1, чтобы добавить пункт меню.
                 Введите 2, чтобы вывести все пункты меню на экран.
                 Введите любое другое число для выхода.
             """;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scanner.nextLine());
            switch (userChoice) {
                case (ADD_ITEM) -> {
                    System.out.println(ITEM_NAME);
                    String itemName = scanner.nextLine();
                    System.out.println(CHILD_NAME);
                    String childName = scanner.nextLine();
                    menu.add(itemName, childName, STUB_ACTION);
                    System.out.println(ADD_SUCCESS);
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
