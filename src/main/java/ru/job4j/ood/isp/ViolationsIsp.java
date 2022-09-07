package ru.job4j.ood.isp;

/**
 * Первый пример. Здесь нарушением является то, что не каждое траспортное средство перевозит и груз и людей одновременно.
 * Некоторые реализации не смогут реализовать какой-то из методов. Правильно было бы разделить их на два интерфейса.
 */

interface ITransport {
    void transportCargo(double weight);

    void transportPeople(int amount);
}

/**
 * Второй пример. В этом интерфейсе слишком много методов и не каждая техника может выполнять все эти виды работ.
 * Здесь также нужно разделить этот интерфейс на отдельные виды работ.
 */

interface IWorkOfTechnology {
    void dig();

    void drill(double depth);

    void plow(double square);

    void mow(double square);

    void liftUp(double weight);
}

/**
 * Третий пример. Здесь интерфейс можно разделить на два по видам работ.
 * Один для маляра, другой для строителя.
 */

interface IWorker {
    void paintTheFloor();

    void paintsTheWall();

    void pourTheFoundation();

    void layBricks();
}


