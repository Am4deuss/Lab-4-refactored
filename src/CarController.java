import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:
    private final Model model;
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    //methods:

    public CarController(Model model) {
        this.model = model;
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           model.timerUpdate();
        }
    }

    public void startup(){
        timer.start();
    }

    void gas(int amount) {
        model.gas(amount);
    }

    void brake(int amount) {
        model.brake(amount);
    }

    void turboOn() {
        model.turboOn();
    }

    void turboOff() {
        model.turboOn();
    }

    void liftBed() {
        model.liftBed();
    }

    void lowerBed() {
        model.lowerBed();
    }

    void start(){
        model.start();
    }

    void stop(){
        model.stop();
    }

    void addCar() {
        model.addCar();
    }

    void removeCar() {
        model.removeCar();
    }


}
