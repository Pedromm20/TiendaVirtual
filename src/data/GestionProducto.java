package data;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import logic.Producto;
import mensaje.Mensajes;
import leer.Leer;
import conexionMYSQL.Conexion;

public class GestionProducto {
    private List<Producto> productos;
    private Map<String, Producto> cesta = new TreeMap<>();
    private boolean compraRealizada = false;
    private Conexion conexion = new Conexion();
    

    public void mostrarProductos() {
        if (productos == null || productos.isEmpty()) {
            Mensajes.mensaje("No hay productos disponibles en la tienda.");
        } else {
            Mensajes.mensajeListaProductos();
            for (Producto producto : productos) {
                Mensajes.mensajeProducto(producto); // Mostrar ID junto con el nombre y el precio
            }
        }
    }

    public void cargarProductos() {
        productos = conexion.cargarProductos();
    }

    public void comprarProductos() {
        Mensajes.mensajeIdProducto();
        String idProducto = Leer.dato();
        Producto producto = buscarProductoPorId(idProducto);
        
        if (producto != null && producto.isDisponible()) {
            Mensajes.mensajeCantidadProducto();
            int cantidadCompra = Leer.datoInt();

            int cantidadDisponible = producto.getCantidadStock();
            if (cantidadCompra <= cantidadDisponible && cantidadCompra > 0) {
                producto.setCantidadStock(cantidadDisponible - cantidadCompra);
                producto.setDisponible(cantidadDisponible > cantidadCompra);
                Mensajes.mensajeCompraExitosa(producto.getNombre());

                Producto productoCesta = new Producto(producto.getId(), producto.getNombre(), producto.getPrecioUnitario(), cantidadCompra, false);
                cesta.put(producto.getId(), productoCesta);

                compraRealizada = true;
            } else {
                Mensajes.mensajeStockInsuficiente();
            }
        } else {
            Mensajes.mensajeProductoNoDisponible();
        }
    }

    private Producto buscarProductoPorId(String id) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }

    public void verCesta() {
        if (!compraRealizada) {
            Mensajes.mensajeCompraPendiente();
            return;
        }

        Mensajes.mensajeVerCesta();
        double totalCesta = 0;
        for (Producto producto : cesta.values()) {
            Mensajes.mensajeProducto(producto);
            totalCesta += producto.getPrecioUnitario() * producto.getCantidadStock();
        }
        Mensajes.mensajeTotalCompra(totalCesta);
    }

    public void finalizarCompra(double totalCompra) {
        if (!compraRealizada) {
            Mensajes.mensajeCompraPendiente();
            return;
        }

        boolean pagoExitoso = pagarConTarjeta(calcularTotalCompra());

        if (pagoExitoso) {
            Producto productoComprado = obtenerProductoComprado();
            int compraId = crearCompra(totalCompra); // Creamos la compra y obtenemos su ID
            escribirTicket(productoComprado, totalCompra, compraId); // Pasamos el ID de la compra al método escribirTicket
            limpiarCestaYMarcarCompraRealizada();
        } else {
            Mensajes.mensajePagoFallido();
        }
    }

    private int crearCompra(double totalCompra) {
        return conexion.crearCompra(totalCompra);
    }

    private void escribirTicket(Producto productoComprado, double totalCompra, int compraId) {
        Mensajes.mensajeTicketCompra();
        Mensajes.mensajeProducto(productoComprado);
        Mensajes.mensajeTotalCompra(totalCompra);
        Mensajes.mensajeCompraID(compraId); // Mostramos el ID de la compra en el ticket
        Mensajes.mensajeGraciasPorCompra();
    }

    public static boolean pagarConTarjeta(double cantidadTotal) {
        // Método de pago
        return true;
    }

    private Producto obtenerProductoComprado() {
        for (Producto producto : cesta.values()) {
            return producto;
        }
        return null;
    }

    public double calcularTotalCompra() {
        double total = 0;
        for (Producto producto : cesta.values()) {
            total += producto.getPrecioUnitario() * producto.getCantidadStock();
        }
        return total;
    }

    public void pagarYFinalizarCompra(double totalCompra) {
        pagarConTarjeta(totalCompra);
        finalizarCompra(totalCompra);
    }

    private void limpiarCestaYMarcarCompraRealizada() {
        cesta.clear();
        compraRealizada = false;
    }
}
