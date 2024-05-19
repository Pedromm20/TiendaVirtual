package baseDeDatos;

import java.sql.Timestamp;

public class Compra {
    private int id;
    private int clienteId;
    private Timestamp fechaCompra;
    private double total;

    // Constructor, getters y setters
        public Compra(int id, int clienteId, Timestamp fechaCompra, double total) {
        	            this.id = id;
            this.clienteId = clienteId;
            this.fechaCompra = fechaCompra;
            this.total = total;
        }
        
		public int getId() {
			return id;
		}
		
		public int getClienteId() {
			return clienteId;
		}

		public Timestamp getFechaCompra() {
			return fechaCompra;
		}
		
		public double getTotal() {
			return total;
		}
        
        
}

