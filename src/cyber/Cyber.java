package cyber;

import java.util.Random;
import java.util.Scanner;

public class Cyber {
    enum Servicios {IMPRESIONES, INTERNET, ENCUADERNACION}

    private int[] idComputadoras = {1, 2, 3, 4, 5};
    private Servicios servicio;

    public Cyber() {
        // Constructor sin referencia a `Impresiones` para evitar recursión infinita.
    }

    // Método para seleccionar aleatoriamente un ID de computadora
    protected int obtenerIdComputadorAleatorio() {
        Random random = new Random();
        int indiceAleatorio = random.nextInt(idComputadoras.length);
        return idComputadoras[indiceAleatorio];
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Seleccione el servicio:");
            System.out.println("1. Impresiones");
            System.out.println("2. Internet");
            System.out.println("3. Encuadernación");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 1:
                    servicio = Servicios.IMPRESIONES;
                    Impresiones impresiones = new Impresiones();
                    impresiones.subMenu();  // Ejecuta el submenú de impresión
                    break;

                case 2:
                    System.out.println("Servicio de Internet no disponible en este momento.");
                    break;

                case 3:
                    System.out.println("Servicio de Encuadernación no disponible en este momento.");
                    break;

                case 4:
                    continuar = false;  // Finaliza el bucle y termina el programa
                    System.out.println("Gracias por utilizar nuestros servicios. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }


}
