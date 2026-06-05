package srp;

/**
 * Responsabilidad única: enviar notificaciones al usuario (correo de bienvenida).
 */
public class NotificationService {

    public void sendWelcomeEmail(String email) {
        System.out.println("Sending welcome email to " + email);
    }
}
