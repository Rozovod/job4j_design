package ru.job4j.ood.srp;

import java.util.List;

public class Model {
    private int id;
    private String name;

    public Model(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Model{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }

    /**
     * Здесь нарушением принципа SRP является наличие метода filter,
     * так как помимо основной ответственности хранения данных у модели добавляется
     * дополнительная - обработка этих данных.
     */

    public List<Model> filter(String key) {
        return null;
    }
}
