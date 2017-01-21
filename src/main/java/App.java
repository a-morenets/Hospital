
import model.services.PersonService;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        PersonService service = new PersonService();

        System.out.println(service.login("a@a.com", "a"));
    }
}
