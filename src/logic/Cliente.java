package logic;

import java.sql.Timestamp;

/**
 * La clase Cliente representa un cliente en el sistema.
 */
public class Cliente {
    private int id;
    private String nombre;
    private String email;
    private String clave;
    private String direccion;
    private String ciudad;
    private String pais;
    private Timestamp fechaRegistro;

    /**
     * Constructor de la clase Cliente.
     * @param id El ID del cliente.
     * @param nombre El nombre del cliente.
     * @param email El correo electrónico del cliente.
     * @param clave La clave del cliente.
     * @param direccion La dirección del cliente.
     * @param ciudad La ciudad del cliente.
     * @param pais El país del cliente.
     * @param fechaRegistro La fecha de registro del cliente.
     */
    public Cliente(int id, String nombre, String email, String clave, String direccion, String ciudad, String pais, Timestamp fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.pais = pais;
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Obtiene el ID del cliente.
     * @return El ID del cliente.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del cliente.
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Obtiene el correo electrónico del cliente.
     * @return El correo electrónico del cliente.
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Obtiene la clave del cliente.
     * @return La clave del cliente.
     */
    public String getClave() {
        return clave;
    }
    
    /**
     * Obtiene la dirección del cliente.
     * @return La dirección del cliente.
     */
    public String getDireccion() {
        return direccion;
    }
    
    /**
     * Obtiene la ciudad del cliente.
     * @return La ciudad del cliente.
     */
    public String getCiudad() {
        return ciudad;
    }
    
    /**
     * Obtiene el país del cliente.
     * @return El país del cliente.
     */
    public String getPais() {
        return pais;
    }
    
    /**
     * Obtiene la fecha de registro del cliente.
     * @return La fecha de registro del cliente.
     */
    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }
}