
import model.services.StaffService;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        StaffService service = new StaffService();

        System.out.println(service.login("a@a.com", "a"));
    }
}
