package mensaje;

import logic.Producto;

public class Mensajes {

    public static void mensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public static void mensajeInicial() {
        mensaje("¡Bienvenido a la tienda virtual!");
        mensaje("---------------------------------");
    }


    public static void mensajeProductosDisponibles() {
        mensaje("Selección de productos disponibles:".toUpperCase() + "\n");
    }

    public static void mensajeMostrarCaja() {
        mensaje("Contenido de la caja:");
    }

    public static void mensajeFin() {
        mensaje("Gracias por utilizar nuestra tienda virtual. ¡Hasta luego!");
    }
    // Mensaje para indicar que la compra se ha realizado con éxito
    public static void mensajeListaProductos() {
        mensaje("Productos disponibles en la tienda:");
    }
    // 
    // Mensaje para indicar que la compra se ha realizado con éxito
    public static void mensajeCompraExitosa(String nombreProducto) {
        mensaje("Compra realizada con éxito: " + nombreProducto);
    }
    // Mensaje para indicar que el producto no está disponible
    public static void mensajeProductoNoDisponible() {
        mensaje("El producto no está disponible en la tienda.");
    }
    // Mensaje para indicar que la opción seleccionada no es válida
    public static void mensajeOpcionInvalida() {
        mensaje("Opción inválida. Por favor, seleccione una opción válida.");
    }

    // Nuevo mensaje para pedir la cantidad deseada del producto
    public static void mensajeCantidadProducto() {
        mensaje("Por favor, introduzca la cantidad que desea comprar:");
    }

    // Nuevo mensaje para pedir el ID del producto
    public static void mensajeIdProducto() {
        mensaje("Por favor, introduzca el ID del producto que desea comprar:");
    }

    // Nuevo mensaje para decir que el stock es insuficiente.
    public static void mensajeStockInsuficiente() {
        mensaje("No tenemos la cantidad deseada de este producto, indique una cantidad inferior");
    }

    // Nuevo mensaje para ver la cesta
    public static void mensajeVerCesta() {
        mensaje("----- Contenido de la cesta -----");
    }

    // Nuevo mensaje para indicar compra pendiente
    public static void mensajeCompraPendiente() {
        mensaje("Debe realizar una compra antes de ver la cesta o finalizar la compra.");
    }
    // Nuevo mensaje de compra finalizada
    public static void mensajeCompraFinalizada() {
        mensaje("Compra finalizada con éxito.");
    }

    // nuevo mensaje de pago fallido
    public static void mensajePagoFallido() {
        mensaje("El pago no se ha realizado con éxito.");
    }
    // Pregunta si se desea imprimir un ticket
    public static void mensajePreguntaTicket() {
        mensaje("¿Desea imprimir un ticket de compra? (S/N)");
    }
    public static void mensajeTarjetaInvalida() {
        mensaje("El número de tarjeta no es válido.");
    }

    public static void mensajeErrorPago() {
        mensaje("Ha ocurrido un error durante el proceso de pago.");
    }

    public static void mensajeIngreseTarjeta() {
        mensaje("Ingrese el número de la tarjeta: ");
    }

    public static void mensajeIngreseFechaExpiracion() {
        mensaje("Ingrese la fecha de expiración de la tarjeta (MM/YY): ");
    }

	public static void mensajeTicketCompra() {
		mensaje("Ticket de compra generado con éxito.");
    }
	    public static void mensajeProcesandoPago() {
        mensaje("Procesando pago...");
    }

		public static void mensajeProducto(Producto productoComprado) {
            mensaje("Producto: " + productoComprado.getNombre());
            mensaje("ID " + productoComprado.getId());
            mensaje("Precio unitario: " + productoComprado.getPrecioUnitario());
            mensaje("-----------------------------------------");
        }

		public static void mensajeGraciasPorCompra() {
            mensaje("Gracias por su compra.");
        }

		public static void mensajeTotalCompra(double totalCompra) {
		            mensaje("Total: " + totalCompra);
        }
		
		public static void mensajeCompraID(int compraId) {
		    System.out.println("ID de la compra: " + compraId);
		}

		public static void mensajeCredencialesInvalidas() {
            mensaje("Credenciales inválidas. Por favor, inténtelo de nuevo.");
        }

		public static void mensajeBienvenida() {
            mensaje("¡Bienvenido a la tienda virtual!");
        }

		public static void mensajeInicioSesion() {
            mensaje("Inicio de sesión:");
            
		}

		public static void mensajeCorreo() {
            mensaje("Correo electrónico:");
        }

		public static void mensajeClave() {
            mensaje("Clave:");			
		}

		public static void mensajeInicioSesionFallido() {
			mensaje("Inicio de sesión fallido. Por favor, inténtelo de nuevo.");
        }

		public static void mensajeInicioSesionExitoso() {
            mensaje("Inicio de sesión exitoso.");
        }

		public static void mensajePagoExitoso() {
            mensaje("Pago realizado con éxito.");			
		}
		
			
		
		
		
		

}
