package ru.job4j.design.lsp.products;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public interface Store {

    boolean add(Food food);

    boolean checkExpDate(Food food);

    List<Food> getFoodsFromStore();

    default int consumedTimeInPercent(Food food) {
        int passed = Period.between(food.getCreateDate(), LocalDate.now()).getDays();
        int exp = Period.between(food.getCreateDate(), food.getExpiryDate()).getDays();
        int consumed = 0;
        if (exp != 0) {
            consumed = passed * 100 / exp;
        }
        return consumed;
    }
}
