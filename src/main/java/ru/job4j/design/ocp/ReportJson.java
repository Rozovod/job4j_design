package ru.job4j.design.ocp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.design.srp.*;

import java.util.List;
import java.util.function.Predicate;

public class ReportJson implements Report {
    private Store store;
    private Gson gson;

    public ReportJson(Store store) {
        this.store = store;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }
}
