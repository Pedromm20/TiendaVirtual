package baseDeDatos;

import java.sql.Timestamp;

/**
 * Clase que representa un ticket de compra.
 */
public class TicketCompra {
    private int id;
    private int compraId;
    private double total;
    private Timestamp fechaHora;

    /**
     * Constructor de la clase TicketCompra.
     *
     * @param id         El ID del ticket.
     * @param compraId   El ID de la compra asociada al ticket.
     * @param total      El total de la compra.
     * @param fechaHora  La fecha y hora en que se generó el ticket.
     */
    public TicketCompra(int id, int compraId, double total, Timestamp fechaHora) {
        this.id = id;
        this.compraId = compraId;
        this.total = total;
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el ID del ticket.
     *
     * @return El ID del ticket.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el ID de la compra asociada al ticket.
     *
     * @return El ID de la compra asociada al ticket.
     */
    public int getCompraId() {
        return compraId;
    }

    /**
     * Obtiene el total de la compra.
     *
     * @return El total de la compra.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Obtiene la fecha y hora en que se generó el ticket.
     *
     * @return La fecha y hora en que se generó el ticket.
     */
    public Timestamp getFechaHora() {
        return fechaHora;
    }
}
