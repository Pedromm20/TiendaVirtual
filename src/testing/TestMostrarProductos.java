package testing;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import conexionMYSQL.Conexion;
import data.GestionProducto;
import logic.Producto;

public class TestMostrarProductos {

	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@AfterEach
	public void restoreStreams() {
		System.setOut(originalOut);
	}

	@Test
	public void testMostrarProductosConProductos() {
		// Inicializar la conexión a la base de datos
		Conexion conexion = new Conexion();

		// Crear una instancia de la clase GestionProducto utilizando el constructor con
		// la conexión a la base de datos
		GestionProducto gestionProducto = new GestionProducto(0, conexion);

		// Cargar los productos desde la base de datos
		gestionProducto.cargarProductos();

		// Ejecutar el método que queremos probar
		gestionProducto.mostrarProductos();

		// Verificar que se imprimen todos los productos correctamente
		for (Producto producto : gestionProducto.getProductos()) {
			assertTrue(outputStreamCaptor.toString().contains(producto.getNombre()));
			assertTrue(outputStreamCaptor.toString().contains(String.valueOf(producto.getPrecioUnitario())));
		}

	}

	@Test
    public void testMostrarProductosSinProductos() {
        // Crear una instancia de la clase GestionProducto sin productos
        GestionProducto gestionProducto = new GestionProducto(0, null);
        // Configuración del escenario: vaciar la lista de productos
        gestionProducto.setProductos(new ArrayList<>());

        // Ejecutar el método que queremos probar
        gestionProducto.mostrarProductos();

        // Verificar que se muestra el mensaje adecuado cuando no hay productos
        String expectedOutput = "No hay productos disponibles en la tienda.";
        assertTrue(outputStreamCaptor.toString().contains(expectedOutput));
    }

}
