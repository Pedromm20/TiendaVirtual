package menu;

public class Menu {
    /**
     * Muestra las opciones del menú.
     */
    public static void mostrarMenu() {
        System.out.println("\nSeleccione qué desea realizar:\n");

        System.out.println("\t1. Mostrar productos.");
        System.out.println("\t2. Comprar productos.");
        System.out.println("\t3. Ver cesta. ");
        System.out.println("\t4. Finalizar compra y pagar con tarjeta.");
        System.out.println("\t5. Salir.");
    }

    /**
     * Muestra un mensaje de finalización del programa.
     */
    public static void mostrarMensajeFinal() {
        System.out.println("Gracias por usar este software.");
    }
}
