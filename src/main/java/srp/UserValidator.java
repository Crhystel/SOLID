package srp;

import java.util.regex.Pattern;

/**
 * Responsabilidad única: validar los datos del usuario (email y contraseña).
 */
public class UserValidator {

    public boolean isValidEmail(String email) {
        return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email);
    }

    public boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

    public boolean isValid(String email, String password) {
        return isValidEmail(email) && isValidPassword(password);
    }
}
