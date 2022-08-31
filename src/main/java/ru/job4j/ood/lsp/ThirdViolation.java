package ru.job4j.ood.lsp;

class Series {
    private int series;

    public int getSeries() {
        return series;
    }
}

class Number {
    private int number;

    public int getNumber() {
        return number;
    }
}

class FirstPassport {
    protected Series series;
    protected Number number;

    public FirstPassport(Series series, Number number) {
        validateSeries(series);
        validateNumber(number);
        this.series = series;
        this.number = number;
    }

    protected void validateSeries(Series series) {
        if (series.getSeries() < 1000 || series.getSeries() > 9999) {
            throw new IllegalArgumentException("Неверная серия пасспорта.");
        }
    }

    protected void validateNumber(Number number) {
        if (number.getNumber() < 100000 || number.getNumber() > 999999) {
            throw new IllegalArgumentException("Неверный номер пасспорта.");
        }
    }

    public Series getSeries() {
        return series;
    }

    public Number getNumber() {
        return number;
    }

    public void setSeries(Series series) {
        validateSeries(series);
        this.series = series;
    }

    public void setNumber(Number number) {
        validateNumber(number);
        this.number = number;
    }
}

class SecondPassport extends FirstPassport {
    public SecondPassport(Series series, Number number) {
        super(series, number);
    }

    /**
     * В классе SecondPassport в Сеттерах мы не сделали проверку и можем выйти за пределы валидных значений.
     */

    @Override
    public void setSeries(Series series) {
        this.series = series;
    }

    @Override
    public void setNumber(Number number) {
        this.number = number;
    }
}
