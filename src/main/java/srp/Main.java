package srp;

public class Main {

    public static void main(String[] args) {
        UserValidator validator = new UserValidator();
        UserRepository repository = new UserRepository();
        NotificationService notificationService = new NotificationService();

        UserManager userManager = new UserManager(validator, repository, notificationService);

        System.out.println("=== Test 1: Valid user ===");
        userManager.addUser("example@domain.com", "password123");

        System.out.println();
        System.out.println("=== Test 2: Invalid email ===");
        userManager.addUser("invalid-email", "password123");

        System.out.println();
        System.out.println("=== Test 3: Short password ===");
        userManager.addUser("user@domain.com", "1234");

        System.out.println();
        System.out.println("=== Test 4: Both invalid ===");
        userManager.addUser("invalid-email", "1234");
    }
}
