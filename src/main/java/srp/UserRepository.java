package srp;

/**
 * Responsabilidad única: persistir los datos del usuario (guardar en base de datos).
 */
public class UserRepository {

    public void save(String email, String password) {
        System.out.println("Saving user to the database...");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
    }
}
