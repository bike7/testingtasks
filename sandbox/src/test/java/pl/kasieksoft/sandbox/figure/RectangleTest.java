package pl.kasieksoft.sandbox.figure;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RectangleTest {

    @Test
    void shouldCalculateCorrectArea() {
        Rectangle r = new Rectangle(1.0, 2.0);
        Rectangle r1 = new Rectangle(1.2, 2.0);
        Rectangle.calculateArea(r.getA(), r.getB());
    }

    @Test
    void areaCalculatorTest() {
        Rectangle r = new Rectangle(2.0, 1.0);

        AreaCalculator.getArea(r);

        AreaCalculator areaCalculator = new AreaCalculator();
        System.out.println(areaCalculator.getMultiplier());
        areaCalculator.setMultiplier(666);
        System.out.println(areaCalculator.getMultiplier());
    }

}