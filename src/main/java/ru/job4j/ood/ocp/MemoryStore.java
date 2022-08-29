package ru.job4j.ood.ocp;

import ru.job4j.ood.srp.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Здесь нарушением принципа OCP является то, что хранилище привязано к одной конкретной модели.
 * Если мы захотим использовать хранилище с другими моделями, логичнее действовать через абстрактный класс.
 * Второе, если мы захотим сохранять объекты в базу, нужно создавать отдельное хранилище и чтобы объекты
 * взаимодействовали с хранилищем через интерфейс без прямой связи.
 * Третье нарушение, это то что в методах параметр класса Model не является абстрактным классом и возвращаемый
 * тип метода (findBy) тоже. А это не позволяет нам использовать MemoryStore с другими моделями данных, помимо Model.
 */

public class MemoryStore {
    private final List<Model> models = new ArrayList<>();

    public void add(Model model) {
    }

    public boolean replace(int id, Model model) {
        return false;
    }

    public boolean delete(int id) {
        return false;
    }

    public List<Model> findBy(Predicate<Model> filter) {
        return null;
    }
}
