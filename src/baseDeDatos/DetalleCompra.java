package baseDeDatos;

public class DetalleCompra {
    private int id;
    private int compraId;
    private int productoId;
    private int cantidad;
    private double precioUnitario;

    // Constructor, getters y setters
	public DetalleCompra(int id, int compraId, int productoId, int cantidad, double precioUnitario) {
		this.id = id;
		this.compraId = compraId;
		this.productoId = productoId;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
	}
	
	public int getId() {
		return id;
	}
	
	public int getCompraId() {
		return compraId;
	}
	
	public int getProductoId() {
		return productoId;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	
}

