// Clase Aplicacion
package app;

import data.GestionProducto;
import leer.Leer;
import mensaje.Mensajes;
import menu.Menu;
import conexionMYSQL.Conexion;

public class Aplicacion {

    public static void main(String[] args) {
        Mensajes.mensajeInicioSesion();
        Mensajes.mensajeCorreo();
        String correo = Leer.dato();
        Mensajes.mensajeClave();
        String clave = Leer.dato();

        Conexion conexion = new Conexion();
        if (conexion.verificarCredenciales(correo, clave)) {
            Mensajes.mensajeBienvenida();

            
            boolean continuar = true;
            GestionProducto gestion = new GestionProducto(0);
            gestion.cargarProductos();

            do {
                Menu.mostrarMenu();

                switch (Leer.datoInt()) {
                    case 1:
                        gestion.mostrarProductos();
                        break;
                    case 2:
                        gestion.comprarProductos();
                        break;
                    case 3:
                        gestion.verCesta();
                        break;
                    case 4:
                        double totalCompra = gestion.calcularTotalCompra();
                        gestion.finalizarCompra(totalCompra, correo);
                        break;
                    case 5:
                        continuar = false;
                        break;
                    default:
                        Mensajes.mensajeOpcionInvalida();
                        break;
                }

            } while (continuar);

            Mensajes.mensajeFin();
        } else {
            Mensajes.mensajeCredencialesInvalidas();
        }
    }
}


