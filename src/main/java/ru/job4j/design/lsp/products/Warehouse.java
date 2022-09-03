package ru.job4j.design.lsp.products;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private static final int WAREHOUSE = 25;
    private final List<Food> foodsWarehouse = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (checkExpDate(food)) {
            foodsWarehouse.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean checkExpDate(Food food) {
        boolean rsl = false;
        if (LocalDate.now().isBefore(food.getExpiryDate())) {
            rsl = consumedTimeInPercent(food) < WAREHOUSE;
        }
        return rsl;
    }

    @Override
    public List<Food> getFoodsFromStore() {
        return new ArrayList<>(foodsWarehouse);
    }
}
