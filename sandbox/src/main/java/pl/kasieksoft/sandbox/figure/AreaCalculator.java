package pl.kasieksoft.sandbox.figure;

class AreaCalculator {

    private int multiplier = 2;

    void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    int getMultiplier() {
        return multiplier;
    }

    static double getArea(Rectangle r) {
        return r.getA() * r.getB();
    }
}
