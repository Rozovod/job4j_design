package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void toArrayAssert() {
        SimpleConvert sc = new SimpleConvert();
        String[] rsl = sc.toArray("Ivan", "Stepan", "Petr", "Anna");
        assertThat(rsl).isNotEmpty()
                .hasSize(4)
                .contains("Ivan")
                .doesNotContain("Stas")
                .startsWith("Ivan")
                .endsWith("Anna")
                .containsSequence("Stepan", "Petr");
    }

    @Test
    void toListAssert() {
        SimpleConvert sc = new SimpleConvert();
        List<String> rsl = sc.toList("Ivan", "Stepan", "Petr", "Anna");
        assertThat(rsl).isNotEmpty()
                .hasSize(4)
                .containsAnyOf("Anna", "Ivan")
                .anySatisfy(e -> assertThat(e).contains("a"));
        assertThat(rsl).element(1).isEqualTo("Stepan");
    }

    @Test
    void toSetAssert() {
        SimpleConvert sc = new SimpleConvert();
        Set<String> rsl = sc.toSet("Ivan", "Stepan", "Petr", "Anna", "Ivan");
        assertThat(rsl).isNotEmpty()
                .hasSize(4)
                .allSatisfy(e -> assertThat(e.length()).isLessThan(8))
                .anyMatch(e -> e.equals("Anna"))
                .noneMatch(e -> e.endsWith("f"));
    }

    @Test
    void toMapAssert() {
        SimpleConvert sc = new SimpleConvert();
        Map<String, Integer> rsl = sc.toMap("Ivan", "Stepan", "Petr", "Anna", "Ivan");
        assertThat(rsl).hasSize(4)
                .containsKey("Petr")
                .containsValues(2, 3)
                .doesNotContainKeys("Stas", "Inna")
                .containsEntry("Anna", 3);
    }
}