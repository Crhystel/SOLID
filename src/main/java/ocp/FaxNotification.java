package ocp;

public class FaxNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending Fax: " + message);
    }
}
