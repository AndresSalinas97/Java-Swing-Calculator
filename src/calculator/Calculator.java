package calculator;

/**
 * Calculator - Práctica 2 - Asignatura de Sistemas Interactivos.
 *
 * A simple calculator made with Java Swing following the MVC pattern.
 *
 * @author Andrés Salinas Lima <i52salia@uco.es>
 */
public final class Calculator {

    /**
     * Starts the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Assemble all the pieces of the MVC
        new Controller(new Model(), new View());
    }
}
