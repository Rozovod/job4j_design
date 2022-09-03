package ru.job4j.design.lsp.products;

import java.time.LocalDate;

public class Buckwheat extends Food {
    public Buckwheat(String name, LocalDate expiryDate, LocalDate createDate, double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
