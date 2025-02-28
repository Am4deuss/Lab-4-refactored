import java.util.ArrayList;
import java.util.Arrays;

public class RunGame {
    DrawPanel view;
    Model model;

    public static void main(String[] args){
        DrawPanel view = new DrawPanel(800, 800-240);
        Model model = new Model(new ArrayList<Subscriber>(Arrays.asList(view)));
        CarController controller = new CarController(model);
        CarView ui = new CarView("CarSimulator 4.0 Refactored", controller, view);

        // Start the timer (start the game)
        controller.startup();
    }

}
