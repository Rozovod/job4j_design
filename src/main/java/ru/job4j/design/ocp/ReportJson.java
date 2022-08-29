package ru.job4j.design.ocp;

import com.google.gson.GsonBuilder;
import ru.job4j.design.srp.*;

import java.util.List;
import java.util.function.Predicate;

public class ReportJson implements Report {
    private Store store;

    public ReportJson(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        var gson = new GsonBuilder().create();
        return gson.toJson(employees);
    }
}
