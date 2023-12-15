package Coches;

public class Main {
    public static void main(String[] args) {
        Puente puente = new Puente();

        // Crear y ejecutar hilos de coches del Norte
        for (int i = 0; i < 3; i++) {
            new CocheNorte(puente).start();
        }

        // Crear y ejecutar hilos de coches del Sur
        for (int i = 0; i < 3; i++) {
            new CocheSur(puente).start();
        }
    }
}

