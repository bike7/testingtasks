package pl.kasieksoft.sandbox.figure;

import org.testng.annotations.Test;

public class ConcreteGreetTest {

    @Test
    void concreteGreetTest() {
        ConcreteGreet greet = new ConcreteGreet();
        greet.greet();
    }

}