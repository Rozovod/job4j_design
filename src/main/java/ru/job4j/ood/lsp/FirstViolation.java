package ru.job4j.ood.lsp;

class Truck {
    protected int loadCapacity;

    public Truck(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public void transport(int cargoWeight) {
        if (cargoWeight < 0) {
            throw new IllegalArgumentException("Неверный вес груза.");
        }
        if (loadCapacity < 10000 && cargoWeight > 10000) {
            throw new IllegalArgumentException("Превышена грузоподъемность.");
        }
        /* какая-то логика */
    }
}

class OtherTruck extends Truck {
    public OtherTruck(int loadCapacity) {
        super(loadCapacity);
    }

    /**
     * В классе OtherTruck в методе transport мы усилили предусловие, уменьшив грузоподъемность.
     */

    public void transport(int cargoWeight) {
        if (cargoWeight < 0) {
            throw new IllegalArgumentException("Неверный вес груза.");
        }
        if (loadCapacity < 5000 && cargoWeight > 5000) {
            throw new IllegalArgumentException("Превышена грузоподъемность.");
        }
        /* какая-то логика */
    }
}
