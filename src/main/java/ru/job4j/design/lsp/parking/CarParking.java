package ru.job4j.design.lsp.parking;

public class CarParking implements IParking {
    AbstractCar[] parking;

    public CarParking(int amount) {
        this.parking = new AbstractCar[amount];
    }

    @Override
    public boolean park(AbstractCar car) {
        return false;
    }
}
