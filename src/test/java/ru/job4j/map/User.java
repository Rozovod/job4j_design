package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar date = new GregorianCalendar(1990, Calendar.MAY, 7);
        User userFirst = new User("Ivan", 1, date);
        User userSecond = new User("Ivan", 1, date);
        Map<User, Object> map = new HashMap<>();
        map.put(userFirst, new Object());
        map.put(userSecond, new Object());
        System.out.println(map);
    }
}
