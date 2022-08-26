package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportDevelopers implements Report {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    public static final String SEPARATOR = System.lineSeparator();
    private Store store;

    public ReportDevelopers(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder()
                .append("<!DOCTYPE html>").append(SEPARATOR)
                .append("<html>").append(SEPARATOR)
                .append("<head>").append(SEPARATOR)
                .append("<title>ReportForDevelopers</title>").append(SEPARATOR)
                .append("</head>").append(SEPARATOR)
                .append("<body>").append(SEPARATOR)
                .append("Name; Hired; Fired; Salary;").append(SEPARATOR);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary()).append(";").append(SEPARATOR);
        }
        text.append("</body>").append(SEPARATOR)
                .append("</html>").append(SEPARATOR);
        return text.toString();
    }
}
