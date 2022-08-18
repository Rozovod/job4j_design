package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {

    public <T> T sortMax(List<T> values, Comparator<T> comparator) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException("Values is empty");
        }
        T max = values.get(0);
        for (T val : values) {
            if (comparator.compare(val, max) > 0) {
                max = val;
            }
        }
        return max;
    }

    public <T> T max(List<T> values, Comparator<T> comparator) {
        return sortMax(values, comparator);
    }

    public <T> T min(List<T> values, Comparator<T> comparator) {
        return sortMax(values, comparator.reversed());
    }
}
