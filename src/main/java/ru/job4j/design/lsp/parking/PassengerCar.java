package ru.job4j.design.lsp.parking;

public class PassengerCar extends AbstractCar {
    public static final int SIZE = 1;

    public PassengerCar(String name, int number) {
        super(name, number, SIZE);
    }
}
