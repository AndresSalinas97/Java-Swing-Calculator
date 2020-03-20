package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The Calculator Model.
 *
 * @author Andrés Salinas Lima {@literal <i52salia@uco.es>}
 */
final class CalculatorModel {

    /**
     * Maximum number of digits that the user can introduce.
     */
    private final static int MAX_INPUT_DIGITS = 12;

    /**
     * Maximum number of decimal places the results will be rounded to.
     */
    private final static int MAX_RESULT_DECIMALS = 5;

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

    private String resultDisplay;
    private String operationDisplay;
    private double tempValue;
    private boolean inErrorMode;
    private boolean firstDigit;

    /**
     * Constructor for class Model.
     */
    public CalculatorModel() {
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

        // Control if we have to replace the display
        if (firstDigit) {
            resultDisplay = String.valueOf(n);
            firstDigit = false;
            return;
        }

        // Control we don't go over the limit of the display
        if (resultDisplay.length() >= MAX_INPUT_DIGITS) {
            return;
        }

        // Control we don't have 0s on the left
        if (resultDisplay.equals("0")) {
            resultDisplay = String.valueOf(n);
            return;
        }
        if (resultDisplay.equals("-0")) {
            resultDisplay = "-" + n;
            return;
        }

        resultDisplay += n;
    }

    /**
     * Inserts the dot decimal separator.
     *
     * It won't allow to have more than one dot at the same time.
     *
     * If it is the firstDigit it will add a 0 to the left.
     */
    public void insertDot() {
        if (inErrorMode) {
            return;
        }

        // Control that if it is the firstDigit it will add a 0 to the left
        if (firstDigit) {
            resultDisplay = "0.";
            firstDigit = false;
            return;
        }
        if (resultDisplay.contains("-")) {
            resultDisplay = "-0.";
            return;
        }

        // Control we don't have more than one dot at the same time
        if (resultDisplay.contains(".")) {
            return;
        }

        resultDisplay += ".";
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

        // Control if we are expecting the user to introduce a new number
        if (firstDigit && !operationDisplay.isEmpty()) {
            resultDisplay = "-0";
            firstDigit = false;
            return;
        }

        if (resultDisplay.charAt(0) == '-') {
            resultDisplay = resultDisplay.substring(1);
        } else {
            resultDisplay = "-" + resultDisplay;
        }

        if (firstDigit) {
            firstDigit = false;
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
            // Stores the current value on display so we don't loose it when the
            // user introduces a new number
            tempValue = Double.valueOf(resultDisplay);

            operationDisplay = String.valueOf(op);

            // After this operation we expect the user to introduce a new number
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

            // After this operation we expect the user to introduce a new number
            firstDigit = true;
        } catch (NumberFormatException | ArithmeticException e) {
            enterErrorMode();
        }
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
