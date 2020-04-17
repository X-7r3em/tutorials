package singleton;

/**
 * The main problem with a Singleton in multithreading may occur
 * during its initialization.
 *
 * This example also shows that the Singleton's behaviour
 * is dependant on the dependencies. This example has a state.
 * The state is being mutated by one of the Threads, which is why we
 * want a stateless singleton.
 *
 * The result should be Vasil first, but it is sometimes in different positions, due to another
 * Thread finishing first. However, there are times that the result has 3 Gosho. This means that the first
 * Thread went in and changed to Vasil and then another changed to Gosho before the first could finish.
 */
public class Main {
    public static void main(String[] args) {
        Person person = new Person("Todor");
        Printer printer = new Printer(person);
        new Thread(printer).start();
        new Thread(printer).start();
        new Thread(printer).start();
    }
}
