package ru.job4j.design.lsp.parking;

import java.util.HashSet;
import java.util.Set;

public class CarParking implements IParking {
    private int countPassengerCars;
    private int countTrucks;
    private Set<AbstractCar> passengerCarParking;
    private Set<AbstractCar> truckParking;

    public CarParking(int amountPassengerCars, int amountTrucks) {
        this.countPassengerCars = amountPassengerCars;
        this.countTrucks = amountTrucks;
        this.passengerCarParking = new HashSet<>(amountPassengerCars);
        this.truckParking = new HashSet<>(amountTrucks);
    }

    @Override
    public boolean park(AbstractCar car) {
        return false;
    }
}
