package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "warranty")
public class Warranty {
    @XmlAttribute
    private int date;
    @XmlAttribute
    private boolean paidRenewal;

    public Warranty() {

    }

    public Warranty(int date, boolean paidRenewal) {
        this.date = date;
        this.paidRenewal = paidRenewal;
    }

    public int getDate() {
        return date;
    }

    public boolean isPaidRenewal() {
        return paidRenewal;
    }

    @Override
    public String toString() {
        return "Warranty{"
                + "date=" + date
                + ", paidRenewal=" + paidRenewal
                + '}';
    }
}
