package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    public static final String INDENTS = "----";
    public static final String SPACE = " ";

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo item : menu) {
            StringBuilder builder = new StringBuilder();
            String number = item.getNumber();
            int repeat = number.split("\\.").length - 1;
            System.out.println(
                    builder.append(INDENTS.repeat(repeat))
                    .append(SPACE)
                    .append(item.getName())
                    .append(SPACE)
                    .append(number));
        }
    }
}
