package ru.job4j.design.lsp.products;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private static final int START_SHOP = 25;
    private static final int END_SHOP = 75;
    private final List<Food> foodsShop = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (checkExpDate(food)) {
            foodsShop.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean checkExpDate(Food food) {
        boolean rsl = false;
        if (consumedTimeInPercent(food) >= START_SHOP && consumedTimeInPercent(food) <= END_SHOP) {
            rsl = true;
        } else if (consumedTimeInPercent(food) > END_SHOP && consumedTimeInPercent(food) < 100) {
            food.setPrice(food.getPrice() - food.getPrice() * food.getDiscount() / 100);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getFoodsFromStore() {
        return new ArrayList<>(foodsShop);
    }

    @Override
    public void clear() {
        foodsShop.clear();
    }
}
