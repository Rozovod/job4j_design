package ru.job4j.design.srp;

import org.junit.jupiter.api.Test;
import ru.job4j.design.ocp.ReportJson;
import ru.job4j.design.ocp.ReportXml;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.design.srp.ReportEngine.DATE_FORMAT;
import static ru.job4j.design.srp.ReportEngine.SEPARATOR;
import static ru.job4j.design.srp.ReportAccounting.CURRENCY;

class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;").append(SEPARATOR)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";").append(SEPARATOR);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenGeneratedForDevelopers() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report repDev = new ReportDevelopers(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>").append(SEPARATOR)
                .append("<html>").append(SEPARATOR)
                .append("<head>").append(SEPARATOR)
                .append("<title>ReportForDevelopers</title>").append(SEPARATOR)
                .append("</head>").append(SEPARATOR)
                .append("<body>").append(SEPARATOR)
                .append("Name; Hired; Fired; Salary;").append(SEPARATOR)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";").append(SEPARATOR)
                .append("</body>").append(SEPARATOR)
                .append("</html>").append(SEPARATOR);
        assertThat(repDev.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenGeneratedForAccounting() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report repAcc = new ReportAccounting(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;").append(SEPARATOR)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append((int) worker.getSalary()).append(CURRENCY).append(";").append(SEPARATOR);
        assertThat(repAcc.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenGeneratedForHR() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        Report repHR = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;").append(SEPARATOR)
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";").append(SEPARATOR)
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";").append(SEPARATOR);
        assertThat(repHR.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenGeneratedToJson() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        Report repJson = new ReportJson(store);
        StringBuilder expect = new StringBuilder()
                .append("[{\"name\":\"").append(worker1.getName())
                .append("\",\"hired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE))
                .append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"fired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE))
                .append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"salary\":").append(worker1.getSalary())
                .append("},{\"name\":\"").append(worker2.getName())
                .append("\",\"hired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE))
                .append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"fired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE))
                .append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"salary\":").append(worker2.getSalary()).append("}]");
        assertThat(repJson.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenGeneratedToXml() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX:00");
        Employee worker1 = new Employee("Ivan", now, now, 100);
        store.add(worker1);
        Report repXml = new ReportXml(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append(SEPARATOR)
                .append("<employees>").append(SEPARATOR)
                .append("    <employees>").append(SEPARATOR)
                .append("        <fired>").append(dateFormat.format(worker1.getFired().getTime())).append("</fired>")
                .append(SEPARATOR)
                .append("        <hired>").append(dateFormat.format(worker1.getHired().getTime())).append("</hired>")
                .append(SEPARATOR)
                .append("        <name>").append(worker1.getName()).append("</name>").append(SEPARATOR)
                .append("        <salary>").append(worker1.getSalary()).append("</salary>").append(SEPARATOR)
                .append("    </employees>").append(SEPARATOR)
                .append("</employees>").append(SEPARATOR);
        assertThat(repXml.generate(em -> true)).isEqualTo(expect.toString());
    }
}