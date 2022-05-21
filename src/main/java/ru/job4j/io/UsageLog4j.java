package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 29;
        char c = 'C';
        byte b = 8;
        short s = 16;
        long l = 31L;
        float f = 2.5F;
        double d = 3.14D;
        boolean modOn = true;
        LOG.debug("Primitive enumeration. age : {}, c : {}, b : {}, s : {}, l : {}, f : {}, d : {}, modOn : {}",
                age, c, b, s, l, f, d, modOn);
    }
}
