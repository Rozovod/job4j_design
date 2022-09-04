package ru.job4j.design.lsp.products;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    public void whenAddDifferent() {
        Food milk = new Milk("Milk", LocalDate.now().plusDays(3),
                LocalDate.now().minusDays(2), 150.00, 30);
        Food buckwheat = new Buckwheat("Buckwheat", LocalDate.now().plusDays(17),
                LocalDate.now().minusDays(2), 120.00, 30);
        Food bananaTrash = new Banana("Banana", LocalDate.now().minusDays(6),
                LocalDate.now().minusDays(36), 60, 50);
        Food bananaSale = new Banana("Banana", LocalDate.now().plusDays(4),
                LocalDate.now().minusDays(15), 60, 50);
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