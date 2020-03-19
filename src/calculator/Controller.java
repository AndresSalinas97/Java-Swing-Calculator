package calculator;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * The Calculator Controller.
 *
 * @author Andrés Salinas Lima <i52salia@uco.es>
 */
final class Controller {

    private final Model model;
    private final View view;

    /**
     * Creates new Controller for the Calculator.
     *
     * @param m the Calculator model
     * @param v the Calculator view
     */
    public Controller(Model m, View v) {
        model = m;
        view = v;
        initView();
        initController();
    }

    /**
     * Initializes the View.
     */
    private void initView() {
        view.getResultField().setText("0");
        view.getOperationField().setText("");
    }

    /**
     * Initializes the Controller.
     */
    private void initController() {
        bindButtons();

        view.addKeyListener(new CalculatorKeyListener(this));
    }

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
        view.getDivButton().addActionListener(e -> pressOperationButton('/'));
        view.getMulButton().addActionListener(e -> pressOperationButton('*'));
        view.getSubButton().addActionListener(e -> pressOperationButton('-'));
        view.getAddButton().addActionListener(e -> pressOperationButton('+'));
        view.getEqualButton().addActionListener(e -> pressEqualButton());
        view.getDotButton().addActionListener(e -> pressDotButton());
    }

    private final class CalculatorKeyListener implements KeyListener {

        private final Controller controller;

        public CalculatorKeyListener(Controller c) {
            controller = c;
        }

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
                    controller.pressOperationButton('/');
                    break;
                case '*':
                    controller.pressOperationButton('*');
                    break;
                case '=':
                    controller.pressEqualButton();
                    break;
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

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

    private void pressNumberButton(int n) {
        System.out.println(n);
        view.getResultField().setText(String.valueOf(n));
    }

    private void pressOperationButton(char op) {
        switch (op) {
            case '+':
                System.out.println("Add");
                break;
            case '-':
                System.out.println("Sub");
                break;
            case '*':
                System.out.println("Mul");
                break;
            case '/':
                System.out.println("Div");
                break;
        }
    }

    private void pressSignButton() {
        System.out.println("Sign");
    }

    private void pressDotButton() {
        System.out.println("Dot");
    }

    private void pressCleanButton() {
        System.out.println("Clean");
    }

    private void pressResetButton() {
        System.out.println("Reset");
    }

    private void pressEqualButton() {
        System.out.println("Equal");
    }
}
