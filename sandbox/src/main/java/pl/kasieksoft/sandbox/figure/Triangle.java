package pl.kasieksoft.sandbox.figure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Triangle implements Figure, Greetable {

    private double a;
    private double h;

    Triangle(double a, double h) {
        this.a = a;
        this.h = h;
    }

    @Override
    public double area() {
        return 0.5 * a * h;
    }

    @Override
    public void greet() {
        System.out.println(FigureConstants.HELLO_MESSAGE + " T" + area() + FigureConstants.GREETING_MESSAGE);
    }

    void beATriangle() {
        System.out.println("I am a triangle, beware");
    }

    boolean isTaller(Triangle other) {
        ArrayList<Triangle> list = new ArrayList<>();
        List<Triangle> figures = list;
        Collection<Triangle> collection = list;
        Cloneable c = list;
        return true;
    }
}
