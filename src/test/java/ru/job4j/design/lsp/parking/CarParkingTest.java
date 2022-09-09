package ru.job4j.design.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Disabled
class CarParkingTest {
    IParking parking = new CarParking(2, 2);
    AbstractCar passCarFirst = new PassengerCar("Nissan Skyline", 555);
    AbstractCar passCarSecond = new PassengerCar("Porsche 911 GT", 777);
    AbstractCar passCarThird = new PassengerCar("Honda Civic", 999);
    AbstractCar smallTruck = new Truck("Small Truck", 123, 2);
    AbstractCar middleTruck = new Truck("Middle Truck", 456, 3);
    AbstractCar bigTruck = new Truck("Big Truck", 789, 4);

    @Test
    public void whenPassAndTruckPark() {
        assertThat(parking.park(passCarFirst)).isTrue();
        assertThat(parking.park(passCarSecond)).isTrue();
        assertThat(parking.park(bigTruck)).isTrue();
    }

    @Test
    public void whenOnlyTruckPark() {
        assertThat(parking.park(middleTruck)).isTrue();
        assertThat(parking.park(bigTruck)).isTrue();
        assertThat(parking.park(smallTruck)).isTrue();
    }

    @Test
    public void whenOnlyPassNotFit() {
        assertThat(parking.park(passCarFirst)).isTrue();
        assertThat(parking.park(passCarSecond)).isTrue();
        assertThat(parking.park(passCarThird)).isFalse();
    }

    @Test
    public void whenOnlyTruckNotFit() {
        assertThat(parking.park(smallTruck)).isTrue();
        assertThat(parking.park(middleTruck)).isTrue();
        assertThat(parking.park(bigTruck)).isFalse();
    }
}