package calculator;

/**
 * Calculator - Práctica 2 - Asignatura de Sistemas Interactivos.
 *
 * A simple GUI based calculator made with Java Swing following the MVC pattern.
 *
 * It works like a typical calculator in the sense that you can concatenate
 * operations (without pressing the equal button in every step).
 *
 * Functionality and layout was inspired by the iPhone iOS 13 Calculator App.
 *
 * @author Andrés Salinas Lima {@literal <i52salia@uco.es>}
 */
public final class Calculator {

    /**
     * The main function: starts the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Assemble all the pieces of the MVC
        new CalculatorController(new CalculatorModel(), new CalculatorView());
    }
}
