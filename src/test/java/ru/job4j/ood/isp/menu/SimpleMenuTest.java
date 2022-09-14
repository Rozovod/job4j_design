package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.ood.isp.menu.SimpleMenuPrinter.*;

class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    public static final String SEPARATOR = System.lineSeparator();

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenSelectMenuItem() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Купить молоко", List.of(), STUB_ACTION, "1.1.1."))
                .isEqualTo(menu.select("Купить молоко").get());
    }
    
    @Test
    public void whenPrintMenu() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        StringBuilder builder = new StringBuilder()
                .append(SPACE).append("Сходить в магазин 1.").append(SEPARATOR)
                .append(INDENTS).append(SPACE).append("Купить продукты 1.1.").append(SEPARATOR)
                .append(INDENTS.repeat(2)).append(SPACE).append("Купить хлеб 1.1.1.").append(SEPARATOR)
                .append(INDENTS.repeat(2)).append(SPACE).append("Купить молоко 1.1.2.").append(SEPARATOR)
                .append(SPACE).append("Покормить собаку 2.").append(SEPARATOR);
        new SimpleMenuPrinter().print(menu);
        assertThat(out.toString()).isEqualTo(builder.toString());
    }
}