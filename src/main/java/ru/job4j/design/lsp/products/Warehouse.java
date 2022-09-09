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
        return LocalDate.now().isBefore(food.getExpiryDate())
                && consumedTimeInPercent(food) < WAREHOUSE;
    }

    @Override
    public List<Food> getFoodsFromStore() {
        return new ArrayList<>(foodsWarehouse);
    }

    @Override
    public void clear() {
        foodsWarehouse.clear();
    }
}
