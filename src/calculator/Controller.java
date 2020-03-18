package calculator;

/**
 * The Calculator Controller.
 *
 * @author Andrés Salinas Lima <i52salia@uco.es>
 */
final class Controller {

    private Model model;
    private View view;

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
    }

    /**
     * Initializes the View.
     */
    private void initView() {

    }

    /**
     * Initializes the Controller.
     */
    public void initController() {

    }
}
