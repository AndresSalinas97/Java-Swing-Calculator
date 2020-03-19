package calculator;

/**
 * The Calculator Model.
 *
 * @author Andrés Salinas Lima <i52salia@uco.es>
 */
final class Model {

    private final static int MAX_INPUT_DIGITS = 10;
    private final static int MAX_RESULT_DIGITS = 10;
    private final static int MAX_RESULT_DECIMALS = 3;

    private String resultDisplay;
    private String operationDisplay;
    private double tempValue;
    private boolean insertingDecimals;
    private boolean inErrorMode;
    private boolean firstDigit;

    /**
     * Constructor for class Model.
     */
    public Model() {
        reset();
    }

    public String getResultDisplay() {
        return resultDisplay;
    }

    public String getOperationDisplay() {
        return operationDisplay;
    }

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

        if (!insertingDecimals) {
            resultDisplay += ".";
            insertingDecimals = true;
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
        if (inErrorMode) {
            return;
        }

        calculate();

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
        } catch (Exception ex) {
            enterErrorMode();
        }
    }

    private static double doTheMath(char op, double v1, double v2)
            throws Exception {
        switch (op) {
            case '+':
                return v1 + v2;
            case '-':
                return v1 - v2;
            case '×':
                return v1 * v2;
            case '÷':
                return v1 / v2;
            default:
                return 0;
        }
    }

    public void clean() {
        if (inErrorMode) {
            return;
        }

        resultDisplay = "0";
        firstDigit = true;
        insertingDecimals = false;
    }

    public void reset() {
        tempValue = 0.0;

        resultDisplay = "0";
        firstDigit = true;
        insertingDecimals = false;
        inErrorMode = false;

        operationDisplay = "";
    }

    private void enterErrorMode() {
        inErrorMode = true;
        resultDisplay = "Error";
        operationDisplay = "";
    }
}
