package cyber;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Impresiones extends Cyber {
    enum Estado {PENDIENTE, IMPRESO}
    enum TipoImpresion {COLOR, BLANCOYNEGRO}

    private Queue<SolicitudImpresion> colaImpresiones;

    public Impresiones() {
        this.colaImpresiones = new LinkedList<>();
    }

    // Método para recibir una nueva solicitud de impresión
    public void recibirSolicitudImpresion(int idComputador) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del archivo:");
        String nombreArchivo = scanner.nextLine();

        System.out.println("Ingrese la cantidad de hojas:");
        int cantHojas = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea

        System.out.println("Seleccione el tipo de impresión:");
        System.out.println("1. Color");
        System.out.println("2. Blanco y Negro");
        int tipo = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea
        TipoImpresion tipoImpresion = (tipo == 1) ? TipoImpresion.COLOR : TipoImpresion.BLANCOYNEGRO;

        // Agregar la solicitud de impresión a la cola
        agregarSolicitud(idComputador, nombreArchivo, cantHojas, tipoImpresion);
        System.out.println(" ");
        System.out.println("Solicitud de impresión recibida.");
        System.out.println(" ");
        System.out.println("___________________________________________________ ");
        subMenu(); // Iniciar el submenú después de recibir la solicitud
    }

    // Agregar una nueva solicitud de impresión a la cola
    public void agregarSolicitud(int idComputador, String nombreArchivo, int cantHojas, TipoImpresion tipoImpresion) {
        SolicitudImpresion nuevaSolicitud = new SolicitudImpresion(idComputador, nombreArchivo, cantHojas, tipoImpresion);
        colaImpresiones.add(nuevaSolicitud);
    }

    // Mostrar todas las solicitudes en la cola de impresión
    public void mostrarSolicitudesCola() {
    	System.out.println(" ");
    	System.out.println("Solicitudes en la cola de impresión:");
        System.out.println(" ");
       
        if (colaImpresiones.isEmpty()) {
            System.out.println("No hay solicitudes en la cola de impresión.");
            System.out.println(" ");
            System.out.println("___________________________________________________ ");
        } else {
        for (SolicitudImpresion solicitud : colaImpresiones) {
        	 System.out.println("___________________________________________________ ");
        	 System.out.println(" ");
        	System.out.println("Estado: " + solicitud.estado );
            System.out.println("Archivo: " + solicitud.nombreArchivo + "         Computadora # " + solicitud.idComputador);
            System.out.println(" Tipo: " + solicitud.tipoImpresion + "         Hojas: " + solicitud.cantHojas);
            System.out.println(" ");
            System.out.println("------------------------------------------- ");
        	}
        }
    }

    // Mostrar el total de solicitudes pendientes en la cola
    public void mostrarTotalPendientes() {
        int pendientes = 0;
        for (SolicitudImpresion solicitud : colaImpresiones) {
            if (solicitud.estado == Estado.PENDIENTE) {
                pendientes++;
            }
        }
        System.out.println(" ");
        System.out.println("Total de solicitudes pendientes: " + pendientes);
        System.out.println(" ");
        System.out.println("___________________________________________________ ");
       
    }

    // Ver detalles de la próxima solicitud (incluyendo costo) sin imprimirla aún
    public void verDetallesProximaSolicitud() {
        if (colaImpresiones.isEmpty()) {
            System.out.println("No hay solicitudes en la cola.");
            System.out.println(" ");
            System.out.println("___________________________________________________ ");
            return;
        }
        SolicitudImpresion solicitud = colaImpresiones.peek(); // Obtener la próxima solicitud sin eliminarla
        System.out.println("\nPróxima solicitud en la cola:");
        System.out.println(" ");
        System.out.println("___________________________________________________ ");
        System.out.println(" ");
        System.out.println("Archivo: " + solicitud.nombreArchivo + "         Computadora # " + solicitud.idComputador);
        System.out.println("Tipo de impresión: " + solicitud.tipoImpresion + "        Cantidad de hojas: " + solicitud.cantHojas);
        System.out.println("Costo estimado de impresión: " + solicitud.calcularCosto()+" Bs");
        System.out.println("___________________________________________________ ");
        System.out.println(" ");
    }

    // Procesar la próxima solicitud en la cola (imprimir)
    public void procesarProximaSolicitud() {
        if (colaImpresiones.isEmpty()) {
            System.out.println("No hay solicitudes en la cola.");
            System.out.println(" ");
            System.out.println("___________________________________________________ ");
            return;
        }
        SolicitudImpresion solicitud = colaImpresiones.poll(); // Obtiene y elimina la próxima solicitud
        solicitud.imprimir(); // Imprime y actualiza el estado de la solicitud
    }

    // Submenú para manejar el estado de la cola y procesamiento de solicitudes
 // Submenú para manejar el estado de la cola y procesamiento de solicitudes
    public void subMenu() {
        Scanner scanner = new Scanner(System.in);

        boolean continuarSubMenu = true;
        while (continuarSubMenu) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1.  Agregar una nueva solicitud de impresiónVer detalles de la próxima solicitud");
            System.out.println("2. Ver detalles de la próxima solicitud");
            System.out.println("3. Imprimir próxima solicitud");
            System.out.println("4. Lista completa de solicitudes");
            System.out.println("5. Solicitudes pendientes");
            System.out.println("6. Salir del Submenú");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 1:
                	 recibirSolicitudImpresion(obtenerIdComputadorAleatorio());
                    
                    break;
                case 2:
                	verDetallesProximaSolicitud();
                    break;
                case 3:
                	 procesarProximaSolicitud();
                    break;
                case 4:
                	mostrarSolicitudesCola();
                    break;
                case 5:
                	 mostrarTotalPendientes();
                    break;
                case 6:
                    continuarSubMenu = false;
                    System.out.println("Gracias por utilizar el servicio de impresión.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }


    // Clase interna para representar una solicitud de impresión
    private class SolicitudImpresion {
        private int idComputador;
        private String nombreArchivo;
        private int cantHojas;
        private TipoImpresion tipoImpresion;
        private Estado estado;

        public SolicitudImpresion(int idComputador, String nombreArchivo, int cantHojas, TipoImpresion tipoImpresion) {
            this.idComputador = idComputador;
            this.nombreArchivo = nombreArchivo;
            this.cantHojas = cantHojas;
            this.tipoImpresion = tipoImpresion;
            this.estado = Estado.PENDIENTE;
        }

        // Calcular el costo de la impresión según el tipo y la cantidad de hojas
        public double calcularCosto() {
            double costoPorHoja = (tipoImpresion == TipoImpresion.COLOR) ? 3 : 1.5;
            return cantHojas * costoPorHoja;
        }

        // Imprimir la solicitud de impresión y actualizar su estado
        public void imprimir() {
            if (estado == Estado.PENDIENTE) {
            	System.out.println(" ");
                System.out.println("\nImprimiendo archivo: " + nombreArchivo + "         del computador: " + idComputador);
                System.out.println("Tipo de impresión: " + tipoImpresion + "        Cantidad de hojas: " + cantHojas);
                estado = Estado.IMPRESO;
                System.out.println("Estado actualizado a: " + estado);
                System.out.println("Costo de impresión: " + calcularCosto());
                System.out.println(" ");
                System.out.println("___________________________________________________ ");
            } else {
                System.out.println("La solicitud ya fue procesada.");
            }
        }
    }
}
