package immutable;

/**
 * An immutable object can be used by multiple threads.
 */
public class MessageService {

    private final String message;

    public MessageService(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static void main(String[] args) throws InterruptedException {
        MessageService messageService = new MessageService("Testing");
        Thread f = new Thread(() -> System.out.println(messageService.getMessage().equals("Testing")));

        Thread s = new Thread(() -> System.out.println(messageService.getMessage().equals("Testing")));

        f.start();
        s.start();
        f.join();
        s.join();
    }
}
