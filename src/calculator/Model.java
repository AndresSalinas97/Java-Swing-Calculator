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

    /**
     * Constructor for class Model.
     */
    public Model() {
        resultDisplay = "0";
        operationDisplay = "";
        insertingDecimals = false;
        operationSet = false;
    }

    public String getResultDisplay() {
        return resultDisplay;
    }

    public String getOperationDisplay() {
        return operationDisplay;
    }

    public void insertNumber(int n) {
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
        if (!insertingDecimals) {
            resultDisplay += ".";
            insertingDecimals = true;
        }
    }

    public void switchSign() {
        if (resultDisplay.charAt(0) == '-') {
            resultDisplay = resultDisplay.substring(1);
        } else if (!resultDisplay.equals("0")) {
            resultDisplay = "-" + resultDisplay;
        }
    }

    public void setOperation(char op) {
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
        System.err.println("=");
    }

    public void clean() {
        resultDisplay = "0";
        insertingDecimals = false;
    }

    public void reset() {
        System.err.println("AC");
    }
    
    private void enterErrorMode() {
        
    }
}
