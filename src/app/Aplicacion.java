package app;

import data.GestionProducto;
import leer.Leer;
import mensaje.Mensajes;
import menu.Menu;

public class Aplicacion {

    public static void main(String[] args) {
        Mensajes.mensajeInicial();

        boolean continuar = true;
        GestionProducto gestion = new GestionProducto();
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
                    gestion.finalizarCompra(totalCompra);
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
    }
}


