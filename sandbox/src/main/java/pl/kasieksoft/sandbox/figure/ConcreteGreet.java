package pl.kasieksoft.sandbox.figure;

public class ConcreteGreet extends AbsractGreet implements Greetable {

    @Override
    public void greet() {
        System.out.println("hej hej hej hej");
    }
}
