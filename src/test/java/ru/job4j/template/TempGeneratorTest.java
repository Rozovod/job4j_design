package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled
class TempGeneratorTest {

    @Test
    public void whenAllValid() {
        Generator generator = new TempGenerator();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        String template = "I am a ${name}, Who are ${subject}?";
        assertThat(generator.produce(template, args)).isEqualTo("I am a Petr Arsentev, Who are you?");
    }

    @Test
    public void whenMoreArguments() {
        Generator generator = new TempGenerator();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        args.put("time", "now");
        String template = "I am a ${name}, Who are ${subject}?";
        assertThrows(IllegalArgumentException.class, () -> generator.produce(template, args));
    }

    @Test
    public void whenMoreElTemplate() {
        Generator generator = new TempGenerator();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        String template = "I am a ${name}, Who are ${subject}? What are you doing ${time}?";
        assertThrows(IllegalArgumentException.class, () -> generator.produce(template, args));
    }
}