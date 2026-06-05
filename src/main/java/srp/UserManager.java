package srp;

/**
 * Responsabilidad única: coordinar el flujo de registro de un usuario.
 * Delega la validación, persistencia y notificación a sus respectivas clases.
 */
public class UserManager {

    private final UserValidator validator;
    private final UserRepository repository;
    private final NotificationService notificationService;

    public UserManager(UserValidator validator, UserRepository repository, NotificationService notificationService) {
        this.validator = validator;
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public void addUser(String email, String password) {
        if (validator.isValid(email, password)) {
            repository.save(email, password);
            notificationService.sendWelcomeEmail(email);
        } else {
            System.out.println("Invalid email or password. User not added.");
        }
    }
}
