package testing;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import conexionMYSQL.Conexion;
import data.GestionProducto;
import logic.Producto;

public class TestMostrarProductos {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Connection connection;
    private Savepoint savepoint;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @BeforeEach
    public void setUpDatabase() throws Exception {
        // Inicializar la conexión a la base de datos
        Conexion conexion = new Conexion();
        connection = conexion.conectar();
        connection.setAutoCommit(false);

        // Crear un punto de guardado para poder hacer rollback después de cada prueba
        savepoint = connection.setSavepoint();
    }

    @AfterEach
    public void tearDownDatabase() throws Exception {
        if (connection != null) {
            // Hacer rollback al estado del punto de guardado
            connection.rollback(savepoint);
            connection.close();
        }
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMostrarProductosConProductos() {
        // Crear una instancia de la clase GestionProducto utilizando la conexión
        GestionProducto gestionProducto = new GestionProducto(0, connection);

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
        try (Statement stmt = connection.createStatement()) {
            // Eliminar los detalles de compra y proveedores antes de eliminar los productos
            stmt.execute("DELETE FROM detallescompra WHERE producto_id IN (SELECT id FROM productos)");
            stmt.execute("DELETE FROM proveedores WHERE producto_id IN (SELECT id FROM productos)");
            // Configuración del escenario: vaciar la tabla de productos
            stmt.execute("DELETE FROM productos");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Crear una instancia de la clase GestionProducto utilizando la conexión
        GestionProducto gestionProducto = new GestionProducto(0, connection);

        // Ejecutar el método que queremos probar
        gestionProducto.mostrarProductos();

        // Verificar que se muestra el mensaje adecuado cuando no hay productos
        String expectedOutput = "No hay productos disponibles en la tienda.";
        assertTrue(outputStreamCaptor.toString().contains(expectedOutput));
    }

    @Test
    public void testMostrarProductosListaNula() {
        // Crear una instancia de la clase GestionProducto utilizando la conexión
        GestionProducto gestionProducto = new GestionProducto(0, connection);

        // Configuración del escenario: establecer la lista de productos a nula
        gestionProducto.setProductos(null);

        // Ejecutar el método que queremos probar
        gestionProducto.mostrarProductos();

        // Verificar que se muestra el mensaje adecuado cuando la lista de productos es nula
        String expectedOutput = "No hay productos disponibles en la tienda.";
        assertTrue(outputStreamCaptor.toString().contains(expectedOutput));
    }

    @Test
    public void testMostrarProductosConNombresEspeciales() {
        try (Statement stmt = connection.createStatement()) {
            // Configuración del escenario: insertar productos con nombres especiales
            stmt.execute("INSERT INTO productos (id, nombre, precio_unitario, cantidad_stock, disponible) VALUES (1003, 'Producto con ñ', 10.5, 100, true)");
            stmt.execute("INSERT INTO productos (id, nombre, precio_unitario, cantidad_stock, disponible) VALUES (1004, 'Producto con é', 15.0, 100, true)");
            stmt.execute("INSERT INTO productos (id, nombre, precio_unitario, cantidad_stock, disponible) VALUES (1005, 'Producto con ü', 20.0, 100, true)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Crear una instancia de la clase GestionProducto utilizando la conexión
        GestionProducto gestionProducto = new GestionProducto(0, connection);

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
}




