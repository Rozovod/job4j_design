package ru.job4j.ood.lsp;

class ShippingFirst {
    protected int minTimeInSeconds;
    protected int averageSpeed;

    public ShippingFirst(int minTimeInSeconds, int averageSpeed) {
        this.minTimeInSeconds = minTimeInSeconds;
        this.averageSpeed = averageSpeed;
    }

    public int timeInSeconds(int distance) {
        int rsl = distance / averageSpeed;
        if (rsl > minTimeInSeconds) {
            throw new IllegalArgumentException("Превышено минимальное время доставки.");
        }
        return rsl;
    }
}

class ShippingSecond extends ShippingFirst {

    public ShippingSecond(int minTimeInSeconds, int averageSpeed) {
        super(minTimeInSeconds, averageSpeed);
    }

    /**
     * В классе ShippingSecond в методе timeInSeconds мы ослабили постусловие,
     * убрав проверку на минимальное время доставки.
     */

    public int timeInSeconds(int distance) {
        return distance / averageSpeed;
    }
}
