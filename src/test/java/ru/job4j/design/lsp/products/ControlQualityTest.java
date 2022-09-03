package ru.job4j.design.lsp.products;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    public void whenAddDifferent() {
        Food milk = new Milk("Milk", LocalDate.of(2022, 9, 6),
                LocalDate.of(2022, 9, 1), 150.00, 30);
        Food buckwheat = new Buckwheat("Buckwheat", LocalDate.of(2022, 9, 20),
                LocalDate.of(2022, 9, 1), 120.00, 30);
        Food bananaTrash = new Banana("Banana", LocalDate.of(2022, 8, 23),
                LocalDate.of(2022, 8, 1), 60, 50);
        Food bananaSale = new Banana("Banana", LocalDate.of(2022, 9, 7),
                LocalDate.of(2022, 8, 20), 60, 50);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        controlQuality.distribution(List.of(milk, buckwheat, bananaTrash, bananaSale));
        assertThat(warehouse.getFoodsFromStore()).isEqualTo(List.of(buckwheat));
        assertThat(shop.getFoodsFromStore()).isEqualTo(List.of(milk, bananaSale));
        assertThat(trash.getFoodsFromStore()).isEqualTo(List.of(bananaTrash));
        assertThat(shop.getFoodsFromStore().get(1).getPrice()).isEqualTo(30.00);
    }

}