package almacenamiento;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import logic.Producto;

/**
 * Clase que gestiona la escritura de tickets de compra y el cálculo del total de una compra.
 */
public class Ficheros {
    private static final String RUTA_TICKET = "C:\\Users\\Usuario\\eclipse-workspace\\TiendaVirtual2\\ticket_compra.txt";

    /**
     * Escribe un ticket de compra con los productos especificados, incluyendo el producto comprado, si lo hay, y el total de la compra.
     * @param productos Mapa de productos en la cesta
     * @param productoComprado Producto comprado
     * @param total Total de la compra
     */
    public static void escribirTicket(Map<String, Producto> productos, Producto productoComprado, double total) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RUTA_TICKET))) {
            if (productoComprado != null) {
                writer.println("----- Producto Comprado -----");
                writer.println("Producto: " + productoComprado.getNombre());
                writer.println("Precio unitario: " + productoComprado.getPrecioUnitario());
                writer.println("-----------------------------------------");
            }

            writer.println("----- Productos en la cesta -----");
            for (Producto p : productos.values()) {
                if (!p.isDisponible()) {
                    writer.println("Producto: " + p.getNombre());
                    writer.println("Precio unitario: " + p.getPrecioUnitario());
                    writer.println("-----------------------------------------");
                }
            }

            writer.println("Total: " + total);
        } catch (IOException e) {
            System.out.println("Error al imprimir el ticket: " + e.getMessage());
        }
    }

    /**
     * Calcula el total de la compra en función de los productos en la cesta.
     * @param productos Mapa de productos en la cesta
     * @return Total de la compra
     */
    public static double calcularTotal(Map<String, Producto> productos) {
        double total = 0;
        for (Producto producto : productos.values()) {
            if (!producto.isDisponible()) {
                total += producto.getPrecioUnitario();
            }
        }
        return total;
    }

    /**
     * Retorna la ruta del archivo de ticket.
     * @return Ruta del archivo de ticket
     */
    public static String getRutaTicket() {
        return RUTA_TICKET;
    }
}
