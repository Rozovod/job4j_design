package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {
    @Test
    void checkMax() {
        MaxMin mm = new MaxMin();
        List<Integer> values = List.of(1, 4, 2, 5, 3, 8, 9);
        assertThat(mm.max(values, Integer::compare)).isEqualTo(9);
    }

    @Test
    void checkMin() {
        MaxMin mm = new MaxMin();
        List<Integer> values = List.of(1, 4, 2, 5, 3, 8, 9);
        assertThat(mm.min(values, Integer::compare)).isEqualTo(1);
    }

    @Test
    void chechIsEmpty() {
        MaxMin mm = new MaxMin();
        List<Integer> values = List.of();
        assertThatThrownBy(() -> mm.sortMax(values, Integer::compare))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Values is empty");
    }
}