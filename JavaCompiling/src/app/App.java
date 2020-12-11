package app;

import dto.Cat;
import dto.Dog;


public class App {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Cat cat = new Cat();
        dog.speak();
        cat.speak();
    }
}
