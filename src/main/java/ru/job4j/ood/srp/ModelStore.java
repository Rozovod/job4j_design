package ru.job4j.ood.srp;

import java.util.List;

/**
 * Здесь нарушением SRP является то, что логика работы с разными хранилищами
 * не обобщена в одном интерфейсе абстрактно и не разделена на две реализации.
 */

public class ModelStore implements Store {
    @Override
    public void addMem(Model model) {
    }

    @Override
    public void addDB(Model model) {
    }

    @Override
    public boolean replaceMem(int id, Model model) {
        return false;
    }

    @Override
    public boolean replaceDB(int id, Model model) {
        return false;
    }

    @Override
    public boolean deleteMem(int id) {
        return false;
    }

    @Override
    public boolean deleteDB(int id) {
        return false;
    }

    /**
     * В этом методе есть нарушение принципа SRP.
     * Метод сохраняет объект класса и выводит список всех сохраненных объектов
     * на экран.
     */

    @Override
    public List<Model> saveAndSout(Model model) {
        return null;
    }
}
