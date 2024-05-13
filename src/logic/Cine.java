package logic;

/**
 * Clase que representa un producto de cine.
 */
public class Cine extends Producto {
    private String genero;

    /**
     * Constructor de la clase Cine.
     * @param id El ID del producto
     * @param nombre El nombre del producto
     * @param precioUnitario El precio unitario del producto
     * @param cantidadStock La cantidad en stock del producto
     * @param disponible Indica si el producto está disponible o no
     * @param genero El género del producto de cine
     */
    public Cine(String id, String nombre, double precioUnitario, int cantidadStock, boolean disponible, String genero) {
        super(id, nombre, precioUnitario, cantidadStock, disponible);
        this.genero = genero;
    }

    // Getters y setters para el atributo específico de Cine

    /**
     * Obtiene el género del producto de cine.
     * @return El género del producto de cine
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el género del producto de cine.
     * @param genero El género del producto de cine
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Cine{" +
                "id='" + getId() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", precioUnitario=" + getPrecioUnitario() +
                ", cantidadStock=" + getCantidadStock() +
                ", disponible=" + isDisponible() +
                ", genero='" + genero + '\'' +
                '}';
    }
}