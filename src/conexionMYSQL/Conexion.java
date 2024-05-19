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

    public List<Producto> cargarProductos() {
        List<Producto> productos = new ArrayList<>();
        try (Connection conexion = conectar()) {
            String query = "SELECT * FROM productos";
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

    public int crearCompra(double totalCompra, String clienteEmail) {
        int compraId = -1;
        try (Connection conexion = conectar()) {
            String query = "INSERT INTO compras (cliente_email, total) VALUES (?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, clienteEmail);
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


   

    public boolean verificarCredenciales(String correo, String clave) {
        try (Connection conexion = conectar()) {
            String query = "SELECT * FROM clientes WHERE email = ? AND clave = ?";
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.setString(1, correo);
                statement.setString(2, clave);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next(); // Si hay al menos una fila, las credenciales son válidas
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Si hay algún error, consideramos que las credenciales no son válidas
        }
    }


    public void insertarDetalleCompra(int compraId, int productoId, int cantidad, double precioUnitario) {
        try (Connection conexion = conectar();
             PreparedStatement stmt = conexion.prepareStatement("INSERT INTO detallescompra (compra_id, producto_id, cantidad, precio_unitario) VALUES (?, ?, ?, ?)")) {
            stmt.setInt(1, compraId);
            stmt.setInt(2, productoId);
            stmt.setInt(3, cantidad);
            stmt.setDouble(4, precioUnitario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el detalle de compra: " + e.getMessage(), e);
        }
    }

    public void insertarTicketCompra(int compraId, double totalCompra) {
        try (Connection conexion = conectar
        		();
                PreparedStatement stmt = conexion.prepareStatement("INSERT INTO ticketscompra (compra_id, total) VALUES (?, ?)")) {
               stmt.setInt(1, compraId);
               stmt.setDouble(2, totalCompra);
               stmt.executeUpdate();
           } catch (SQLException e) {
               throw new RuntimeException("Error al insertar el ticket de compra: " + e.getMessage(), e);
           }
       }

       public void insertarAlbaran(int compraId, double totalCompra) {
           try (Connection conexion = conectar();
                PreparedStatement stmt = conexion.prepareStatement("INSERT INTO albaran (compra_id, total) VALUES (?, ?)")) {
               stmt.setInt(1, compraId);
               stmt.setDouble(2, totalCompra);
               stmt.executeUpdate();
           } catch (SQLException e) {
               throw new RuntimeException("Error al insertar el albarán: " + e.getMessage(), e);
           }
       }
       public boolean clienteIdValido(int clienteId) {
           try (Connection conexion = conectar();
                PreparedStatement statement = conexion.prepareStatement("SELECT id FROM clientes WHERE id = ?")) {
               statement.setInt(1, clienteId);
               try (ResultSet resultSet = statement.executeQuery()) {
                   return resultSet.next(); // Si hay al menos una fila, significa que el clienteId es válido
               }
           } catch (SQLException e) {
               e.printStackTrace();
               return false; // Si hay algún error, consideramos que el clienteId no es válido
           }
       }

   }

