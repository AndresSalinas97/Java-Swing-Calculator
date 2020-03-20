package calculator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The Calculator Controller.
 *
 * @author Andrés Salinas Lima {@literal <i52salia@uco.es>}
 */
final class CalculatorController {

    private final CalculatorModel model;
    private final CalculatorView view;

    /**
     * Constructor for class Controller.
     *
     * @param m the Calculator model
     * @param v the Calculator view
     */
    public CalculatorController(CalculatorModel m, CalculatorView v) {
        model = m;
        view = v;
        initView();
        initController();
    }

    /**
     * Initializes the CalculatorView.
     */
    private void initView() {
        updateView();
    }

    /**
     * Updates the CalculatorView to the current CalculatorModel state.
     */
    private void updateView() {
        view.getResultField().setText(model.getResultDisplay());
        view.getOperationField().setText(model.getOperationDisplay());
    }

    /**
     * Initializes the CalculatorController.
     */
    private void initController() {
        bindButtons();

        view.addKeyListener(new CalculatorControllerKeyListener(this));
    }

    /**
     * Binds the buttons with its actions.
     */
    private void bindButtons() {
        view.getNum0Button().addActionListener(e -> pressNumberButton(0));
        view.getNum1Button().addActionListener(e -> pressNumberButton(1));
        view.getNum2Button().addActionListener(e -> pressNumberButton(2));
        view.getNum3Button().addActionListener(e -> pressNumberButton(3));
        view.getNum4Button().addActionListener(e -> pressNumberButton(4));
        view.getNum5Button().addActionListener(e -> pressNumberButton(5));
        view.getNum6Button().addActionListener(e -> pressNumberButton(6));
        view.getNum7Button().addActionListener(e -> pressNumberButton(7));
        view.getNum8Button().addActionListener(e -> pressNumberButton(8));
        view.getNum9Button().addActionListener(e -> pressNumberButton(9));
        view.getResetButton().addActionListener(e -> pressResetButton());
        view.getCleanButton().addActionListener(e -> pressCleanButton());
        view.getSignButton().addActionListener(e -> pressSignButton());
        view.getDivButton().addActionListener(e -> pressOperationButton('÷'));
        view.getMulButton().addActionListener(e -> pressOperationButton('×'));
        view.getSubButton().addActionListener(e -> pressOperationButton('-'));
        view.getAddButton().addActionListener(e -> pressOperationButton('+'));
        view.getEqualButton().addActionListener(e -> pressEqualButton());
        view.getDotButton().addActionListener(e -> pressDotButton());
    }

    /**
     * Key listener controller for the calculator.
     *
     * Allows the calculator to be used with the keyboard.
     *
     * Uses the typical key bindings in calculator programs with a few twists:
     * Keys S (sign) or M (minus) can be used instead of the sign button. Keys C
     * (cancel) or R (reset) can be used instead of the AC (all cancel) button.
     * Keys BACKSPACE or DELETE can be used instead of the CE (clean entry)
     * button.
     */
    private final class CalculatorControllerKeyListener implements KeyListener {

        private final CalculatorController controller;

        /**
         * Constructor for class CalculatorControllerKeyListener.
         *
         * @param c the calculator controller.
         */
        public CalculatorControllerKeyListener(CalculatorController c) {
            controller = c;
        }

        /**
         * Calls the adequate method in the controller to handle the character
         * just typed.
         *
         * @param e the key event to be handled.
         */
        @Override
        public void keyTyped(KeyEvent e) {
            switch (e.getKeyChar()) {
                case '0':
                    controller.pressNumberButton(0);
                    break;
                case '1':
                    controller.pressNumberButton(1);
                    break;
                case '2':
                    controller.pressNumberButton(2);
                    break;
                case '3':
                    controller.pressNumberButton(3);
                    break;
                case '4':
                    controller.pressNumberButton(4);
                    break;
                case '5':
                    controller.pressNumberButton(5);
                    break;
                case '6':
                    controller.pressNumberButton(6);
                    break;
                case '7':
                    controller.pressNumberButton(7);
                    break;
                case '8':
                    controller.pressNumberButton(8);
                    break;
                case '9':
                    controller.pressNumberButton(9);
                    break;
                case 'c':
                case 'C':
                case 'r':
                case 'R':
                    controller.pressResetButton();
                    break;
                case 's':
                case 'S':
                case 'm':
                case 'M':
                    controller.pressSignButton();
                    break;
                case '-':
                    controller.pressOperationButton('-');
                    break;
                case '+':
                    controller.pressOperationButton('+');
                    break;
                case '.':
                case ',':
                    controller.pressDotButton();
                    break;
                case '/':
                    controller.pressOperationButton('÷');
                    break;
                case '*':
                    controller.pressOperationButton('×');
                    break;
                case '=':
                    controller.pressEqualButton();
                    break;
            }
        }

        /**
         * Does nothing but must be overridden.
         *
         * @param e the key event to be handled.
         */
        @Override
        public void keyPressed(KeyEvent e) {
        }

        /**
         * Calls the adequate method in the controller to handle the key just
         * pressed and released.
         *
         * @param e the key event to be handled.
         */
        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_BACK_SPACE:
                case KeyEvent.VK_DELETE:
                    controller.pressCleanButton();
                    break;
                case KeyEvent.VK_ENTER:
                    controller.pressEqualButton();
                    break;
            }
        }
    }

    /**
     * Manipulates the model and updates the view according to the numeric
     * button that was pressed.
     *
     * @param n an integer from 0 to 9 indicating the numeric button that was
     * pressed.
     */
    private void pressNumberButton(int n) {
        model.insertNumber(n);
        updateView();
    }

    /**
     * Manipulates the model and updates the view according to the operation
     * button that was pressed.
     *
     * @param op a char ('+', '-', '×', or '÷') indicating the operation button
     * that was pressed.
     */
    private void pressOperationButton(char op) {
        model.setOperation(op);
        updateView();
    }

    /**
     * Manipulates the model and updates the view in order to change the sign of
     * the calculator input.
     */
    private void pressSignButton() {
        model.switchSign();
        updateView();
    }

    /**
     * Manipulates the model and updates the view in order to insert decimal
     * separator (in this case is a dot).
     */
    private void pressDotButton() {
        model.insertDot();
        updateView();
    }

    /**
     * Manipulates the model and updates the view in order to clean the
     * calculator input.
     */
    private void pressCleanButton() {
        model.clean();
        updateView();
    }

    /**
     * Manipulates the model and updates the view in order to reset the
     * calculator.
     */
    private void pressResetButton() {
        model.reset();
        updateView();

    }

    /**
     * Manipulates the model and updates the view in order to calculate the
     * result.
     */
    private void pressEqualButton() {
        model.calculate();
        updateView();
    }
}
