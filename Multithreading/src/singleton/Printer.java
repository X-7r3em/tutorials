package singleton;

public class Printer implements Runnable {
    private final Person person;

    public Printer(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        if (person.getName().equals("Vasil")) {
            person.setName("Gosho");
        }

        if (person.getName().equals("Todor")) {
            person.setName("Vasil");
        }

        System.out.println(String.format("%s", person.getName()));
    }
}
