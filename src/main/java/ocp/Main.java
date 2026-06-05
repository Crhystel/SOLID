package ocp;

public class Main {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();

        service.sendNotification(new EmailNotification(), "Hello via Email!");
        service.sendNotification(new SMSNotification(), "Hello via SMS!");
        service.sendNotification(new PushNotification(), "Hello via Push Notification!");

        // El envío por Fax ahora funciona sin haber modificado NotificationService
        service.sendNotification(new FaxNotification(), "Hello via Fax!");
    }
}
