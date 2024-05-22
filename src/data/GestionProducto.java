package data;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import logic.Producto;
import mensaje.Mensajes;
import conexionMYSQL.Conexion;
import leer.Leer;

public class GestionProducto {
    private List<Producto> productos;
    private Map<String, Producto> cesta = new TreeMap<>();
    private boolean compraRealizada = false;
    private Conexion conexion;
    private int clienteId;

    public GestionProducto(int clienteId, Conexion conexion) {
        this.clienteId = clienteId;
        this.conexion = new Conexion();
    }
    

    // Getters y setters para clienteId
    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    // Getters y setters para productos
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void mostrarProductos() {
        if (productos == null || productos.isEmpty()) {
            Mensajes.mensaje("No hay productos disponibles en la tienda.");
        } else {
            Mensajes.mensajeListaProductos();
            for (Producto producto : productos) {
                Mensajes.mensajeProducto(producto);
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

                if (cesta.containsKey(producto.getId())) {
                    Producto productoCesta = cesta.get(producto.getId());
                    productoCesta.setCantidadStock(productoCesta.getCantidadStock() + cantidadCompra);
                } else {
                    Producto productoCesta = new Producto(producto.getId(), producto.getNombre(), producto.getPrecioUnitario(), cantidadCompra, false);
                    cesta.put(producto.getId(), productoCesta);
                }

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

    public void finalizarCompra(double totalCompra, String clienteEmail) {
        if (!compraRealizada) {
            Mensajes.mensajeCompraPendiente();
            return;
        }

        boolean pagoExitoso = pagarConTarjeta(totalCompra);

        if (pagoExitoso) {
            int compraId = crearCompra(totalCompra, clienteEmail);
            escribirTicket(compraId);
            limpiarCestaYMarcarCompraRealizada();
        } else {
            Mensajes.mensajePagoFallido();
        }
    }

    private int crearCompra(double totalCompra, String clienteEmail) {
        return conexion.crearCompra(totalCompra, clienteEmail);
    }

    private void escribirTicket(int compraId) {
        Mensajes.mensajeTicketCompra();
        for (Producto producto : cesta.values()) {
            Mensajes.mensajeProducto(producto);
        }
        Mensajes.mensajeTotalCompra(calcularTotalCompra());
        Mensajes.mensajeCompraID(compraId);
        Mensajes.mensajeGraciasPorCompra();
    }

    public static boolean pagarConTarjeta(double cantidadTotal) {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

        Mensajes.mensajeIngreseTarjeta();

        System.out.print("Ingrese el número de la tarjeta (16 dígitos, sin espacios): ");
        String numeroTarjeta = scanner.nextLine();
        if (!esNumeroTarjetaValido(numeroTarjeta)) {
            System.out.println("Número de tarjeta inválido.");
            return false;
        }

        // Mostrar número de tarjeta en grupos de 4 dígitos
        String numeroTarjetaFormateado = formatearNumeroTarjeta(numeroTarjeta);
        System.out.println("Número de tarjeta ingresado: " + numeroTarjetaFormateado);

        System.out.print("Ingrese la fecha de expiración (MM/AA): ");
        String fechaExpiracion = scanner.nextLine();
        if (!esFechaExpiracionValida(fechaExpiracion)) {
            System.out.println("Fecha de expiración inválida.");
            return false;
        }

        System.out.print("Ingrese el código de seguridad (CVV): ");
        String cvv = scanner.nextLine();
        if (!esCVVValido(cvv)) {
            System.out.println("Código de seguridad inválido.");
            return false;
        }
        

        
        Mensajes.mensajePagoExitoso();

        return true;
        
    }
    

    private static boolean esNumeroTarjetaValido(String numeroTarjeta) {
        return numeroTarjeta.matches("\\d{16}");
    }

    private static boolean esFechaExpiracionValida(String fechaExpiracion) {
        return fechaExpiracion.matches("(0[1-9]|1[0-2])/\\d{2}");
    }

    private static boolean esCVVValido(String cvv) {
        return cvv.matches("\\d{3}");
    }

    private static String formatearNumeroTarjeta(String numeroTarjeta) {
        return numeroTarjeta.replaceAll(".{4}(?!$)", "$0 ");
    }


    public double calcularTotalCompra() {
        double total = 0;
        for (Producto producto : cesta.values()) {
            total += producto.getPrecioUnitario() * producto.getCantidadStock();
        }
        return total;
    }

    public void pagarYFinalizarCompra(double totalCompra, String clienteEmail) {
        pagarConTarjeta(totalCompra);
        finalizarCompra(totalCompra, clienteEmail);
    }

    private void limpiarCestaYMarcarCompraRealizada() {
        cesta.clear();
        compraRealizada = false;
    }
}


