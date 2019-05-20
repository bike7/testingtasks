package pl.kasieksoft.sandbox.figure;

import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Collections;

public class TriangleTest {

    @Test
    void testGreeting() {
        new Triangle(5.0, 2.3).greet();
    }

    @Test
    void howPolymorphismWorks() {
        Triangle t = new Triangle(4.0, 2.0);
        t.beATriangle();
        printArea(t);
        greetEveryone(Collections.singletonList(t));
    }

    private void printArea(Figure f) {
        System.out.println(f.area());
    }

    private void greetEveryone(Collection<Greetable> greetables) {
        greetables.forEach(Greetable::greet);
    }

}