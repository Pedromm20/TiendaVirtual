package conexionMYSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConexion {

    public static void cerrarRecursos(Connection cn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (cn != null) cn.close();
            System.out.println("Recursos cerrados correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void consultaSelect() {
        String selectTableSQL = "SELECT * FROM clientes";
        try (Connection cn = new Conexion().conectar();
             PreparedStatement ps = cn.prepareStatement(selectTableSQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idCliente = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String clave = rs.getString("clave");
                String direccion = rs.getString("direccion");
                String ciudad = rs.getString("ciudad");
                String pais = rs.getString("pais");

                System.out.println(idCliente + " " + nombre + " " + email + " " + clave + " " + direccion + " " + ciudad + " " + pais);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void consultaInsert(String nombre, String email, String clave, String direccion, String ciudad, String pais) {
        String insertTableSQL = "INSERT INTO clientes (nombre, email, clave, direccion, ciudad, pais) VALUES (?,?,?,?,?,?)";
        try (Connection cn = new Conexion().conectar();
             PreparedStatement ps = cn.prepareStatement(insertTableSQL)) {

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, clave);
            ps.setString(4, direccion);
            ps.setString(5, ciudad);
            ps.setString(6, pais);
            ps.executeUpdate();

            System.out.println("El registro ha sido insertado con éxito en la base de datos");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void consultaUpdate(int idCliente, String nombre, String email, String clave, String direccion, String ciudad, String pais) {
        String updateTableSQL = "UPDATE clientes SET nombre = ?, email = ?, clave = ?, direccion = ?, ciudad = ?, pais = ? WHERE id = ?";
        try (Connection cn = new Conexion().conectar();
             PreparedStatement ps = cn.prepareStatement(updateTableSQL)) {

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, clave);
            ps.setString(4, direccion);
            ps.setString(5, ciudad);
            ps.setString(6, pais);
            ps.setInt(7, idCliente);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("El cliente ha sido actualizado correctamente");
            } else {
                System.out.println("No se ha actualizado ningún cliente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Realizando consulta SELECT:");
        consultaSelect();

        System.out.println("\nInsertando un nuevo cliente:");
        consultaInsert("Juan", "juan@example.com", "123456", "Calle Principal 123", "Ciudad Principal", "País Principal");

        System.out.println("\nActualizando un cliente existente:");
        consultaUpdate(1, "Juan Pérez", "juan.perez@example.com", "654321", "Calle Nueva 456", "Ciudad Nueva", "Nuevo País");
    }
}

