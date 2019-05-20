package pl.kasieksoft.sandbox.figure;

public class Rectangle implements Figure {

    private double a;
    private double b;

    Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double area() {
        return a * b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    static double calculateArea(double a, double b) {
        return a * b;
    }
}
