package ru.job4j.design.lsp.products;

import java.util.List;

public class ControlQuality {
    public List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void distribution(List<Food> foods) {
        for (Food food : foods) {
            for (Store store : stores) {
                if (store.add(food)) {
                    break;
                }
            }
        }
    }
}
