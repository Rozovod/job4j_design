package ru.job4j.design.lsp.products;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private final List<Food> foodsTrash = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (checkExpDate(food)) {
            foodsTrash.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean checkExpDate(Food food) {
        return food.getExpiryDate().isBefore(LocalDate.now());
    }

    @Override
    public List<Food> getFoodsFromStore() {
        return new ArrayList<>(foodsTrash);
    }
}
