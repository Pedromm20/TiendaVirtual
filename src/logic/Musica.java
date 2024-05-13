package logic;

/**
 * Clase que representa un producto de música.
 */
public class Musica extends Producto {
    private String genero;
    private String autor;

    /**
     * Constructor de la clase Musica.
     * @param id El ID del producto
     * @param nombre El nombre del producto
     * @param precioUnitario El precio unitario del producto
     * @param cantidadStock La cantidad en stock del producto
     * @param disponible Indica si el producto está disponible o no
     * @param genero El género del producto de música
     * @param autor El autor del producto de música
     */
    public Musica(String id, String nombre, double precioUnitario, int cantidadStock, boolean disponible, String genero, String autor) {
        super(id, nombre, precioUnitario, cantidadStock, disponible);
        this.genero = genero;
        this.autor = autor;
    }

    // Getters y setters para los atributos específicos de Musica

    /**
     * Obtiene el género del producto de música.
     * @return El género del producto de música
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el género del producto de música.
     * @param genero El género del producto de música
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene el autor del producto de música.
     * @return El autor del producto de música
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Establece el autor del producto de música.
     * @param autor El autor del producto de música
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "id='" + getId() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", precioUnitario=" + getPrecioUnitario() +
                ", cantidadStock=" + getCantidadStock() +
                ", disponible=" + isDisponible() +
                ", genero='" + genero + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}