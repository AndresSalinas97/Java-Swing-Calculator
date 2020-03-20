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

    public void switchSign() {
        if (inErrorMode) {
            return;
        }

        if (resultDisplay.charAt(0) == '-') {
            resultDisplay = resultDisplay.substring(1);
        } else if (Double.valueOf(resultDisplay) != 0.0) {
            resultDisplay = "-" + resultDisplay;
        }
    }

    public void setOperation(char op) {
        calculate();

        if (inErrorMode) {
            return;
        }

        tempValue = Double.valueOf(resultDisplay);

        operationDisplay = String.valueOf(op);

        firstDigit = true;
    }

    public void calculate() {
        if (inErrorMode) {
            return;
        }

        if (operationDisplay.isEmpty()) {
            return;
        }

        char op = operationDisplay.charAt(0);
        Double valueIndisplay = Double.valueOf(resultDisplay);

        try {
            Double result = doTheMath(op, tempValue, valueIndisplay);
            resultDisplay = result.toString();
            operationDisplay = "";
            firstDigit = true;
        } catch (Exception e) {
            enterErrorMode();
        }
    }

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

    private static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void clean() {
        if (inErrorMode) {
            return;
        }

        resultDisplay = "0";
        firstDigit = true;
    }

    public void reset() {
        tempValue = 0.0;

        resultDisplay = "0";
        firstDigit = true;
        inErrorMode = false;

        operationDisplay = "";
    }

    private void enterErrorMode() {
        inErrorMode = true;
        resultDisplay = "Error";
        operationDisplay = "";
    }
}
