package leer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase que proporciona métodos para leer datos desde la entrada estándar.
 */
public class Leer {

    private static BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Lee una línea de texto desde la entrada estándar y la devuelve como una cadena.
     * @return La cadena leída desde la entrada estándar
     */
    public static String dato() {
        String sdato = "";
        try {
            sdato = flujoEntrada.readLine();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return sdato;
    }

    /**
     * Lee un número entero desde la entrada estándar y lo devuelve.
     * @return El número entero leído desde la entrada estándar
     */
    public static int datoInt() {
        try {
            return Integer.parseInt(dato());
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número válido.");
            return datoInt(); // Llamada recursiva si la entrada no es un número válido
        }
    }

    /**
     * Lee un número en punto flotante desde la entrada estándar y lo devuelve.
     * @return El número en punto flotante leído desde la entrada estándar
     */
    public static float datoFloat() {
        try {
            return Float.parseFloat(dato());
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número válido.");
            return datoFloat(); // Llamada recursiva si la entrada no es un número válido
        }
    }

    /**
     * Lee un carácter desde la entrada estándar y lo devuelve.
     * @return El carácter leído desde la entrada estándar
     */
    public static char datoChar() {
        try {
            return (char) flujoEntrada.read();
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
            return '\0';
        }
    }

    /**
     * Lee un número largo desde la entrada estándar y lo devuelve.
     * @return El número largo leído desde la entrada estándar
     */
    public static long datoLong() {
        try {
            return Long.parseLong(dato());
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número válido.");
            return datoLong(); // Llamada recursiva si la entrada no es un número válido
        }
    }

    /**
     * Cierra el flujo de entrada.
     */
    public static void cerrarFlujo() {
        try {
            flujoEntrada.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el flujo de entrada: " + e.getMessage());
        }
    }
}


