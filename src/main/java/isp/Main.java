package isp;

public class Main {
    public static void main(String[] args) {
        Phone phone = new Phone();
        DisposableCamera camera = new DisposableCamera();

        System.out.println("--- Probando Teléfono ---");
        phone.turnOn();
        phone.charge();
        phone.turnOff();

        System.out.println("\n--- Probando Cámara Desechable ---");
        camera.turnOn();
        // camera.charge(); // Esta línea ni siquiera compilaría, lo cual es excelente y seguro.
        camera.turnOff();
    }
}
