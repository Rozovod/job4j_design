package ru.job4j.design.lsp.parking;

public class Truck extends AbstractCar {
    public Truck(String name, int number, int size) {
        super(name, number, size);
        validateSize(size);
    }

    public void validateSize(int size) {
        if (size <= PassengerCar.SIZE) {
            throw new IllegalArgumentException("Неверный размер для грузовой машины");
        }
    }
}
