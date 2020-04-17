package multithreading;

/**
 * Shows how to start a new separate Thread
 */
public class MultiThreading {
    public static void main(String[] args) {
        //Executes in the same Thread
        CoffeeMaker coffeeMaker = new CoffeeMaker();
        coffeeMaker.run();
        System.out.println("CoffeeMaker End");

        //Executes in a new Thread
        CoffeeMaker workingCoffeeMaker = new CoffeeMaker();
        new Thread(workingCoffeeMaker).start();
        System.out.println("Working CoffeeMaker End");

        //Executes in a new Thread
        IceCreamMaker iceCreamMaker = new IceCreamMaker();
        iceCreamMaker.start();
        System.out.println("IceCreamMaker End");
    }
}
