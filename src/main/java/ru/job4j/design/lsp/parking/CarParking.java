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
        boolean rsl = false;
        if (validateOnDuplicate(car)) {
            if (validatePassengerCarOnPassengerParking(car)) {
                passengerCarParking.add(car);
                countPassengerCars--;
                rsl = true;
            } else if (validateTruckOnTruckParking(car)) {
                truckParking.add(car);
                countTrucks--;
                rsl = true;
            } else if (validateTruckOnPassengerParking(car)) {
                passengerCarParking.add(car);
                countPassengerCars = countPassengerCars - car.getSize();
                rsl = true;
            }
        }
        return rsl;
    }

    private boolean validateOnDuplicate(AbstractCar car) {
        return !passengerCarParking.contains(car) && !truckParking.contains(car);
    }

    private boolean validatePassengerCarOnPassengerParking(AbstractCar car) {
        return car.getSize() == PassengerCar.SIZE && countPassengerCars > 0;
    }

    private boolean validateTruckOnTruckParking(AbstractCar car) {
        return car.getSize() > PassengerCar.SIZE && countTrucks > 0;
    }

    private boolean validateTruckOnPassengerParking(AbstractCar car) {
        return car.getSize() > PassengerCar.SIZE && countTrucks == 0
                && countPassengerCars >= car.getSize();
    }
}
