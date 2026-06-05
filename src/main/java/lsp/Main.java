package lsp;

public class Main {
    public static void main(String[] args) {
        // Demostrando la sustitución de la clase base (LSP)
        Animal myDog = new Dog();
        Animal myFish = new Fish();

        // Ambos son Animales y pueden ser tratados como tal para hacer sonido
        System.out.println("--- Probando Sonidos (Clase Base Animal) ---");
        myDog.makeSound();
        myFish.makeSound();

        // Solo los animales que implementan Walkable pueden caminar
        System.out.println("\n--- Probando Caminata (Interfaz Walkable) ---");

        // Podemos tratar al perro como un ente caminador
        Walkable walkingDog = new Dog();
        walkingDog.walk();

        // El compilador no nos permitirá hacer esto con un Fish,
        // evitando errores en tiempo de ejecución:
        // Walkable walkingFish = new Fish(); // <- Error de compilación
    }
}
