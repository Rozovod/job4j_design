package ru.job4j.serialization.json;

import java.util.Arrays;

public class MobilePhone {
    private final String name;
    private final String model;
    private final int memory;
    private final boolean headphoneJack;
    private final Warranty warranty;
    private final String[] accessories;

    public MobilePhone(String name, String model, int memory, boolean headphoneJack, Warranty warranty, String[] accessories) {
        this.name = name;
        this.model = model;
        this.memory = memory;
        this.headphoneJack = headphoneJack;
        this.warranty = warranty;
        this.accessories = accessories;
    }

    @Override
    public String toString() {
        return "MobilePhone{"
                + "name='" + name + '\''
                + ", model='" + model + '\''
                + ", memory=" + memory
                + ", headphoneJack=" + headphoneJack
                + ", warranty=" + warranty
                + ", accessories=" + Arrays.toString(accessories)
                + '}';
    }
}
