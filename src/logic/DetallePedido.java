package logic;

/**
 * La clase DetallePedido representa un detalle de un pedido realizado por un cliente.
 */
public class DetallePedido {
    private int id;
    private int compraId;
    private int productoId;
    private int cantidad;
    private double precioUnitario;

    /**
     * Constructor de la clase DetallePedido.
     * @param id El ID del detalle del pedido.
     * @param compraId El ID de la compra asociada a este detalle.
     * @param productoId El ID del producto asociado a este detalle.
     * @param cantidad La cantidad de productos en este detalle.
     * @param precioUnitario El precio unitario del producto en este detalle.
     */
    public DetallePedido(int id, int compraId, int productoId, int cantidad, double precioUnitario) {
        this.id = id;
        this.compraId = compraId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    /**
     * Obtiene el ID del detalle del pedido.
     * @return El ID del detalle del pedido.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Obtiene el ID de la compra asociada a este detalle.
     * @return El ID de la compra asociada a este detalle.
     */
    public int getCompraId() {
        return compraId;
    }
    
    /**
     * Obtiene el ID del producto asociado a este detalle.
     * @return El ID del producto asociado a este detalle.
     */
    public int getProductoId() {
        return productoId;
    }
    
    /**
     * Obtiene la cantidad de productos en este detalle.
     * @return La cantidad de productos en este detalle.
     */
    public int getCantidad() {
        return cantidad;
    }
    
    /**
     * Obtiene el precio unitario del producto en este detalle.
     * @return El precio unitario del producto en este detalle.
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }
}