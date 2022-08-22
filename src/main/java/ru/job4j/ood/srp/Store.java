package ru.job4j.ood.srp;

import java.util.List;

/**
 * Здесь нарушением SRP является то, что логика работы с разными хранилищами
 * не обобщена в одном интерфейсе абстрактно и не разделена на две реализации.
 */

public interface Store {
    void addMem(Model model);

    void addDB(Model model);

    boolean replaceMem(int id, Model model);

    boolean replaceDB(int id, Model model);

    boolean deleteMem(int id);

    boolean deleteDB(int id);

    /**
     * В этом методе есть нарушение принципа SRP.
     * Метод сохраняет объект класса и выводит список всех сохраненных объектов
     * на экран.
     */

    List<Model> saveAndSout(Model model);
}
