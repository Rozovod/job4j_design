package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import javax.naming.Name;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void checkNameNotContainsEquals() {
        NameLoad nameLoad = new NameLoad();
        String name = "Ivan";
        assertThatThrownBy(() -> nameLoad.validate(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(name);

    }

    @Test
    void checkNameStartsWithEquals() {
        NameLoad nameLoad = new NameLoad();
        String name = "=Ivan";
        assertThatThrownBy(() -> nameLoad.validate(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(name);
    }

    @Test
    void checkNameEndsWithEquals() {
        NameLoad nameLoad = new NameLoad();
        String name = "Ivan=";
        assertThatThrownBy(() -> nameLoad.validate(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(name);
    }
}