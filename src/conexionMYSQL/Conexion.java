package conexionMYSQL;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import logic.Producto;

/**
 * Clase para establecer la conexión con la base de datos MySQL.
 */
public class Conexion {

    private static String nombreBD;
    private static String ubicacion;
    private static String puerto;
    private static String usuario;
    private static String clave;

    static {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("C:\\Users\\Usuario\\eclipse-workspace\\TiendaVirtual2\\src\\conexionMYSQL\\config.properties")) {
            prop.load(fis);
            nombreBD = prop.getProperty("nombre_bd");
            ubicacion = prop.getProperty("ubicacion");
            puerto = prop.getProperty("puerto");
            usuario = prop.getProperty("usuario");
            clave = prop.getProperty("clave");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection conectar() {
        Connection conexion = null;
        try {
            String url = "jdbc:mysql://" + ubicacion + ":" + puerto + "/" + nombreBD +
                    "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            conexion = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexión correctamente establecida con la base de datos " + nombreBD);
        } catch (SQLException e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }
        return conexion;
    }

    /**
     * Carga los productos desde la base de datos.
     *
     * @return Una lista de productos cargados desde la base de datos.
     */
    public List<Producto> cargarProductos() {
        List<Producto> productos = new ArrayList<>();
        try (Connection conexion = conectar()) {
            String query = "SELECT * FROM Productos";
            try (PreparedStatement statement = conexion.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Producto producto = new Producto(
                            resultSet.getString("id"),
                            resultSet.getString("nombre"),
                            resultSet.getDouble("precio_unitario"),
                            resultSet.getInt("cantidad_stock"),
                            resultSet.getBoolean("disponible")
                    );
                    productos.add(producto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    /**
     * Inserta un nuevo ticket de compra en la base de datos.
     * @param totalCompra El total de la compra
     */
    public void insertarTicketCompra(double totalCompra) {
        try (Connection conexion = conectar();
             PreparedStatement stmt = conexion.prepareStatement("INSERT INTO ticketscompra (total) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, totalCompra);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int ticketId = rs.getInt(1);
                System.out.println("Ticket ID: " + ticketId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el ticket de compra: " + e.getMessage(), e);
        }
    }
    public int crearCompra(double totalCompra) {
        int compraId = -1;
        try (Connection conexion = conectar()) {
            String query = "INSERT INTO compras (cliente_id, total) VALUES (?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                // Supongamos que el cliente tiene el ID 1, pero deberías establecer el ID del cliente real
                statement.setInt(1, 1);
                statement.setDouble(2, totalCompra);
                int filasAfectadas = statement.executeUpdate();
                if (filasAfectadas == 1) {
                    ResultSet rs = statement.getGeneratedKeys();
                    if (rs.next()) {
                        compraId = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compraId;
    }
}
