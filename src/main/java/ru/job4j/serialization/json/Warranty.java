package ru.job4j.serialization.json;

public class Warranty {
    private final int date;
    private final boolean paidRenewal;


    public Warranty(int date, boolean paidRenewal) {
        this.date = date;
        this.paidRenewal = paidRenewal;
    }

    @Override
    public String toString() {
        return "Warranty{"
                + "date=" + date
                + ", paidRenewal=" + paidRenewal
                + '}';
    }
}
