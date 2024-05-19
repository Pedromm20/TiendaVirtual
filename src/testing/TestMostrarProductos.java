package testing;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import conexionMYSQL.Conexion;
import logic.Producto;

class TestMostrarProductos {

    private Conexion conexion;

    @BeforeEach
    void setUp() throws Exception {
        conexion = new Conexion();
    }

    @Test
    void testCargarProductos() {
        List<Producto> productos = conexion.cargarProductos();
        
        // Verifica que la lista no sea nula
        assertNotNull(productos);
        
        // Verifica que la lista no esté vacía
        assertFalse(productos.isEmpty());

        // Verifica que la cantidad de productos cargados sea correcta
        assertEquals(7, productos.size());

        // Verifica algunos detalles de los productos cargados

        // Producto 1
        Producto primerProducto = productos.get(0);
        assertEquals("Disco de Vinilo", primerProducto.getNombre());
        assertEquals(20.00, primerProducto.getPrecioUnitario());
        assertEquals(50, primerProducto.getCantidadStock());
        assertTrue(primerProducto.isDisponible());

        // Producto 2
        Producto segundoProducto = productos.get(1);
        assertEquals("Blu-ray de Película", segundoProducto.getNombre());
        assertEquals(15.99, segundoProducto.getPrecioUnitario());
        assertEquals(100, segundoProducto.getCantidadStock());
        assertTrue(segundoProducto.isDisponible());

        // Producto 3
        Producto tercerProducto = productos.get(2);
        assertEquals("Videojuego", tercerProducto.getNombre());
        assertEquals(49.99, tercerProducto.getPrecioUnitario());
        assertEquals(200, tercerProducto.getCantidadStock());
        assertTrue(tercerProducto.isDisponible());

        // Producto 4
        Producto cuartoProducto = productos.get(3);
        assertEquals("Willy Wonka", cuartoProducto.getNombre());
        assertEquals(6.99, cuartoProducto.getPrecioUnitario());
        assertEquals(500, cuartoProducto.getCantidadStock());
        assertTrue(cuartoProducto.isDisponible());

        // Producto 5
        Producto quintoProducto = productos.get(4);
        assertEquals("Mario Bros", quintoProducto.getNombre());
        assertEquals(34.99, quintoProducto.getPrecioUnitario());
        assertEquals(1000, quintoProducto.getCantidadStock());
        assertTrue(quintoProducto.isDisponible());

        // Producto 6
        Producto sextoProducto = productos.get(5);
        assertEquals("Microdosis", sextoProducto.getNombre());
        assertEquals(139.99, sextoProducto.getPrecioUnitario());
        assertEquals(200, sextoProducto.getCantidadStock());
        assertTrue(sextoProducto.isDisponible());

        // Producto 7
        Producto septimoProducto = productos.get(6);
        assertEquals("Donde quiero estar", septimoProducto.getNombre());
        assertEquals(99.99, septimoProducto.getPrecioUnitario());
        assertEquals(600, septimoProducto.getCantidadStock());
        assertTrue(septimoProducto.isDisponible());
    }

 
    
    
    
    
    
    
    
    
    
    
    
/*
    @Test
    void testCargarProductosCantidadMaxima() {
        // Caso de prueba para verificar el comportamiento cuando la base de datos contiene un gran número de productos
        // (Puede ser útil si se quiere evaluar el rendimiento de la aplicación al cargar una gran cantidad de productos)
        
        // Aquí podrías cargar una gran cantidad de productos en la base de datos antes de ejecutar la prueba
        
        // Act
        List<Producto> productos = conexion.cargarProductos();
        
        // Assert
        // Verifica que la lista no sea nula y contenga al menos un producto
        assertNotNull(productos);
        assertFalse(productos.isEmpty(), "La lista de productos no debería estar vacía");
    }
    
 @Test
    void testCargarProductosConProductosNoDisponibles() {
        // Caso de prueba para verificar que los productos marcados como no disponibles no se incluyan en la lista
        // Se debe asegurar de que los productos no disponibles no estén presentes en la lista devuelta por cargarProductos
        
        // Aquí podrías marcar algunos productos como no disponibles en la base de datos antes de ejecutar la prueba
        
        // Act
        List<Producto> productos = conexion.cargarProductos();
        
        // Assert
        // Verifica que la lista no contenga productos no disponibles
        assertFalse(productos.stream().anyMatch(p -> !p.isDisponible()), "La lista no debería contener productos no disponibles");
    }
    */
}


