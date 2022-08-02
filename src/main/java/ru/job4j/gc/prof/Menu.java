package ru.job4j.gc.prof;

import java.util.Random;
import java.util.Scanner;

public class Menu {

    private static void showMenu() {
        String[] menu = {
                "Создание массива", "Сортировка пузырьком",
                "Сортировка вставками", "Сортировка слиянием", "Выход"
        };
        System.out.println("Выберите действие: ");
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i + ". " + menu[i]);
        }
    }

    public void init(Scanner scanner) {
        RandomArray data = new RandomArray(new Random());
        boolean run = true;
        while (run) {
            showMenu();
            int select = Integer.parseInt(scanner.nextLine());
            if (select == 0) {
                data.insert(250000);
                System.out.println("Создан массив на 250000 элементов.");
            }
            if (select == 1) {
                BubbleSort bubbleSort = new BubbleSort();
                bubbleSort.sort(data);
                System.out.println("Сортировка пузырьком выполнена.");
            }
            if (select == 2) {
                InsertSort insertSort = new InsertSort();
                insertSort.sort(data);
                System.out.println("Сортировка вставками выполнена.");
            }
            if (select == 3) {
                MergeSort mergeSort = new MergeSort();
                mergeSort.sort(data);
                System.out.println("Сортировка слиянием выполнена.");
            }
            if (select == 4) {
                run = false;
                System.out.println("Выбран выход из программы.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu startMenu = new Menu();
        startMenu.init(scanner);
    }
}
