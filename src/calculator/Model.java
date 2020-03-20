package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The Calculator Model.
 *
 * @author Andrés Salinas Lima <i52salia@uco.es>
 */
final class Model {

    /**
     * Maximum number of digits that the user can introduce.
     */
    private final static int MAX_INPUT_DIGITS = 10;

    /**
     * Maximum number of decimal places the results will be rounded to.
     */
    private final static int MAX_RESULT_DECIMALS = 5;

    private String resultDisplay;
    private String operationDisplay;
    private double tempValue;
    private boolean inErrorMode;
    private boolean firstDigit;

    /**
     * Constructor for class Model.
     */
    public Model() {
        reset();
    }

    /**
     * Returns the current display content.
     *
     * @return the current display content.
     */
    public String getResultDisplay() {
        return resultDisplay;
    }

    /**
     * Returns the current operation display content.
     *
     * @return the current operation display content.
     */
    public String getOperationDisplay() {
        return operationDisplay;
    }

    /**
     * Inserts a new digit in the display.
     *
     * If it is the firstDigit it will replace the value on display, if not, it
     * will be appended to the current value on display.
     *
     * @param n the number to be introduced.
     */
    public void insertNumber(int n) {
        if (inErrorMode) {
            return;
        }

        if (resultDisplay.length() > MAX_INPUT_DIGITS) {
            return;
        }

        if (n == 0 && resultDisplay.equals("0")) {
            return;
        }

        if (firstDigit) {
            resultDisplay = String.valueOf(n);
            firstDigit = false;
        } else {
            resultDisplay += n;
        }
    }

    /**
     * Inserts the dot decimal separator.
     *
     * It won't allow to have more than one dot at the same time.
     */
    public void insertDot() {
        if (inErrorMode) {
            return;
        }

        if (resultDisplay.contains(".")) {
            return;
        }

        resultDisplay += ".";

        if (firstDigit) {
            firstDigit = false;
        }
    }

    /**
     * Switches the sign of the value on display.
     *
     * To avoid changing the way the number is presented (number of decimal
     * places for example) this operation is made directly to the String without
     * converting it to double.
     *
     * When expecting the user to introduce a new number pressing the switch
     * sign button will result in the display content being replaced by a '-'.
     */
    public void switchSign() {
        if (inErrorMode) {
            return;
        }

        if (firstDigit && !operationDisplay.isEmpty()) {
            resultDisplay = "-";
            firstDigit = false;
            return;
        }

        if (resultDisplay.charAt(0) == '-') {
            resultDisplay = resultDisplay.substring(1);
        } else if (Double.valueOf(resultDisplay) != 0.0) {
            resultDisplay = "-" + resultDisplay;
        }
    }

    /**
     * Sets the operation to be calculated and calculates the previous operation
     * if there is one.
     *
     * @param op a char ('+', '-', '×', or '÷') indicating the math operation to
     * be set.
     */
    public void setOperation(char op) {
        calculate();

        if (inErrorMode) {
            return;
        }

        try {
            tempValue = Double.valueOf(resultDisplay);

            operationDisplay = String.valueOf(op);

            firstDigit = true;
        } catch (Exception e) {
            enterErrorMode();
        }
    }

    /**
     * Proceeds with the selected operation between the previous value on screen
     * (stored on tempValue) and the current one.
     */
    public void calculate() {
        if (inErrorMode) {
            return;
        }

        if (operationDisplay.isEmpty()) {
            return;
        }

        try {
            char op = operationDisplay.charAt(0);
            Double valueIndisplay = Double.valueOf(resultDisplay);

            Double result = doTheMath(op, tempValue, valueIndisplay);

            resultDisplay = result.toString();
            operationDisplay = "";
            firstDigit = true;
        } catch (Exception e) {
            enterErrorMode();
        }
    }

    /**
     * Performs the specified math operation.
     *
     * @param op a char ('+', '-', '×', or '÷') indicating the math operation to
     * be calculated.
     * @param v1 the first operand.
     * @param v2 the second operand.
     * @return the result of the mathematical operation.
     * @throws ArithmeticException in case of division by 0, for example.
     */
    private static double doTheMath(char op, double v1, double v2)
            throws ArithmeticException {
        double result = 0.0;

        switch (op) {
            case '+':
                result = v1 + v2;
                break;
            case '-':
                result = v1 - v2;
                break;
            case '×':
                result = v1 * v2;
                break;
            case '÷':
                if (v2 == 0.0) {
                    throw new ArithmeticException("Division by 0");
                }
                result = v1 / v2;
                break;
        }

        return round(result, MAX_RESULT_DECIMALS);
    }

    /**
     * Rounds a double to the specified number of decimal places.
     *
     * @param value number to be rounded.
     * @param places number of decimal places.
     * @return rounded double.
     */
    private static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Cleans the screen (but maintains operation and the stored tempValue).
     */
    public void clean() {
        if (inErrorMode) {
            return;
        }

        resultDisplay = "0";
        firstDigit = true;
    }

    /**
     * Resets the calculator.
     */
    public void reset() {
        tempValue = 0.0;

        resultDisplay = "0";
        firstDigit = true;
        inErrorMode = false;

        operationDisplay = "";
    }

    /**
     * Makes the calculator enter error mode.
     *
     * To be used during Exceptions.
     *
     * In this mode the display will show "Error" and only pressing the AC
     * button can make it leave this state.
     */
    private void enterErrorMode() {
        inErrorMode = true;
        resultDisplay = "Error";
        operationDisplay = "";
    }
}
