package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportAccounting implements Report {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    public static final String SEPARATOR = System.lineSeparator();

    private Store store;

    public ReportAccounting(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder()
                .append("Name; Hired; Fired; Salary;").append(SEPARATOR);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append((int) employee.getSalary()).append(" RUB.").append(";")
                    .append(SEPARATOR);
        }
        return text.toString();
    }
}
