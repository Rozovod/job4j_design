package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"),
                is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void whenPairWithCommentAndEmptyLine() {
        String path = "./data/pair_with_comment_and_empty_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.url"),
                is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
    }

    @Test
    public void whenPairWithValueEqualsSomething() {
        String path = "./data/pair_with_value_equals_something.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1"),
                is("value=1"));
    }

    @Test
    public void whenPairWithValueEquals() {
        String path = "./data/pair_with_value_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1"),
                is("value="));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPatternViolationEqualsValue() {
        String path = "./data/pattern_violation_equals_value.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPatternViolationKeyEquals() {
        String path = "./data/pattern_violation_key_equals.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLineWithoutEquals() {
        String path = "./data/line_without_equals.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLineOnlyWithEquals() {
        String path = "./data/line_only_with_equals.properties";
        Config config = new Config(path);
        config.load();
    }
}