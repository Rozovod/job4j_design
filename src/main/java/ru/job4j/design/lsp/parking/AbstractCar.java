package ru.job4j.design.lsp.parking;

import java.util.Objects;

public abstract class AbstractCar {
    private String name;
    private int number;
    private int size;

    public AbstractCar(String name, int number, int size) {
        this.name = name;
        this.number = number;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "AbstractCar{"
                + "name='" + name + '\''
                + ", number=" + number
                + ", size=" + size
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCar that = (AbstractCar) o;
        return number == that.number && size == that.size && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number, size);
    }
}
