package logic;

/**
 * Clase que representa un producto de videojuego.
 */
public class Videojuego extends Producto {
    private String plataforma;

    /**
     * Constructor de la clase Videojuego.
     * @param id El ID del producto
     * @param nombre El nombre del producto
     * @param precioUnitario El precio unitario del producto
     * @param cantidadStock La cantidad en stock del producto
     * @param disponible Indica si el producto está disponible o no
     * @param plataforma La plataforma del producto de videojuego
     */
    public Videojuego(String id, String nombre, double precioUnitario, int cantidadStock, boolean disponible, String plataforma) {
        super(id, nombre, precioUnitario, cantidadStock, disponible);
        this.plataforma = plataforma;
    }

    // Getters y setters para el atributo específico de Videojuego

    /**
     * Obtiene la plataforma del producto de videojuego.
     * @return La plataforma del producto de videojuego
     */
    public String getPlataforma() {
        return plataforma;
    }

    /**
     * Establece la plataforma del producto de videojuego.
     * @param plataforma La plataforma del producto de videojuego
     */
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    public String toString() {
        return "Videojuego{" +
                "id='" + getId() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", precioUnitario=" + getPrecioUnitario() +
                ", cantidadStock=" + getCantidadStock() +
                ", disponible=" + isDisponible() +
                ", plataforma='" + plataforma + '\'' +
                '}';
    }
}