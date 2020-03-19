package calculator;

/**
 * The Calculator Model.
 *
 * @author Andrés Salinas Lima <i52salia@uco.es>
 */
final class Model {

    private final int MAX_INPUT_DIGITS = 10;
    private final int MAX_RESULT_DIGITS = 10;
    private final int MAX_RESULT_DECIMALS = 3;

    private String resultDisplay;
    private String operationDisplay;
    private Double tempValue; // TODO: it could be a basic double
    private boolean insertingDecimals;
    private boolean operationSet;
    private boolean inErrorMode;

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

        if (resultDisplay.equals("0")) {
            if (n != 0) {
                resultDisplay = String.valueOf(n);
            }
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
        }
    }

    public void switchSign() {
        if (inErrorMode) {
            return;
        }

        if (resultDisplay.charAt(0) == '-') {
            resultDisplay = resultDisplay.substring(1);
        } else if (!resultDisplay.equals("0")) {
            resultDisplay = "-" + resultDisplay;
        }
    }

    public void setOperation(char op) {
        if (inErrorMode) {
            return;
        }

        System.err.println(op);

        switch (op) {
            case '+':
                break;
            case '-':
                break;
            case '*':
                break;
            case '/':
                break;
        }
    }

    public void calculate() {
        if (inErrorMode) {
            return;
        }

        System.err.println("=");
    }

    public void clean() {
        if (inErrorMode) {
            return;
        }

        resultDisplay = "0";
        insertingDecimals = false;
    }

    public void reset() {
        resultDisplay = "0";
        operationDisplay = "";
        insertingDecimals = false;
        operationSet = false;
        inErrorMode = false;
    }

    private void enterErrorMode() {
        inErrorMode = true;
        resultDisplay = "Error";
        operationDisplay = "";
    }
}
