package pl.kasieksoft.sandbox.figure;

import org.testng.annotations.Test;

public class SquareTest {

    @Test
    void basicSquareTest() {
        Square s = new Square(6.0);
        Square s1 = new Square(5.9);

        System.out.println(s.isBiggerThan(s1));
    }

    @Test
    void staticSquareTest() {
        Square s = new Square(6.0);
        Square s1 = new Square(5.9);

        System.out.println(s.isSecondSqareBigger(s, s1));
    }

    @Test
    void polymorphismSquareTest() {
        Rectangle r = new Rectangle(1.0, 3.0);

        System.out.println(Square.isItASquare(r));
    }

}