package pl.kasieksoft.sandbox.figure;

public class Square implements Figure {

    private double a;

    Square(double a) {
        this.a = a;
    }

    @Override
    public double area() {
        return a * a;
    }

    boolean isBiggerThan(Figure f) {
        return area() > f.area();
    }

    boolean isSecondSqareBigger(Square first, Square second) {
        return first.area() < second.area();
    }

    static String isItASquare(Figure f) {
        return f instanceof Square ? "Square!" : "Something else!";
    }
}
