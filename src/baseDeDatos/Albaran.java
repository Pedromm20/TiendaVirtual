package baseDeDatos;

import java.sql.Timestamp;

public class Albaran {
    private int id;
    private int compraId;
    private double total;
    private Timestamp fechaHora;

    // Constructor, getters y setters
	public Albaran(int id, int compraId, double total, Timestamp fechaHora) {
		this.id = id;
		this.compraId = compraId;
		this.total = total;
		this.fechaHora = fechaHora;
	}
	
	public int getId() {
		return id;
	}
	
	public int getCompraId() {
		return compraId;
	}
	
	
	public double getTotal() {
		return total;
	}
	
	
	public Timestamp getFechaHora() {
		return fechaHora;
	}
	
	
	
}
