package logic;

/**
 * Clase base para representar un producto genérico.
 */
public class Producto {
    private String id;
    private String nombre;
    private double precioUnitario;
    private int cantidadStock;
    private boolean disponible;

    /**
     * Constructor de la clase Producto.
     * @param id El ID del producto
     * @param nombre El nombre del producto
     * @param precioUnitario El precio unitario del producto
     * @param cantidadStock La cantidad en stock del producto
     * @param disponible Indica si el producto está disponible o no
     */
    public Producto(String id, String nombre, double precioUnitario, int cantidadStock, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.cantidadStock = cantidadStock;
        this.disponible = disponible;
    }

    // Getters y setters para los atributos

    /**
     * Obtiene el ID del producto.
     * @return El ID del producto
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el ID del producto.
     * @param id El ID del producto
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     * @return El nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * @param nombre El nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio unitario del producto.
     * @return El precio unitario del producto
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Establece el precio unitario del producto.
     * @param precioUnitario El precio unitario del producto
     */
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * Obtiene la cantidad en stock del producto.
     * @return La cantidad en stock del producto
     */
    public int getCantidadStock() {
        return cantidadStock;
    }

    /**
     * Establece la cantidad en stock del producto.
     * @param cantidadStock La cantidad en stock del producto
     */
    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    /**
     * Verifica si el producto está disponible.
     * @return true si el producto está disponible, false en caso contrario
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Establece la disponibilidad del producto.
     * @param disponible true si el producto está disponible, false en caso contrario
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", cantidadStock=" + cantidadStock +
                ", disponible=" + disponible +
                '}';
    }
}
