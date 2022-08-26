package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.function.Predicate;

public class ReportEngine implements Report {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    public static final String SEPARATOR = System.lineSeparator();

    private Store store;

    public ReportEngine(Store store) {
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
                    .append(employee.getSalary()).append(";").append(SEPARATOR);
        }
        return text.toString();
    }

    public static void main(String[] args) {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        store.add(new Employee("Ivan", now, now, 100));
        store.add(new Employee("Petr", now, now, 200));
        store.add(new Employee("Stas", now, now, 300));
        Report engine = new ReportEngine(store);
        System.out.println(engine.generate(em -> true));


    }
}
