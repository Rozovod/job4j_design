package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void isThisCube() {
        Box box = new Box(6, 8);
        assertThat(box.whatsThis()).isEqualTo("Cube");
        assertThat(box.getArea()).isEqualTo(384.0);
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 4);
        assertThat(box.getNumberOfVertices()).isEqualTo(4);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(8, 3);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
        assertThat(box.getNumberOfVertices()).isEqualTo(-1);
        assertThat(box.getArea()).isEqualTo(0);
    }
}