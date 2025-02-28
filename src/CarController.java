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

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    // Creating a new Volvo workshop
    Garage<Volvo240>  volvoGarage = new Garage<>(5, 0, 300, Volvo240.class);

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        ArrayList<Car> allVehicles = new ArrayList<>();
        Volvo240 volvo = new Volvo240();
        Saab95 saab95 = new Saab95();
        Scania scania = new Scania();
        allVehicles.add(volvo);
        allVehicles.add(saab95);
        allVehicles.add(scania);
        int currentY = 0;

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        for(Car vehicle : allVehicles){
            vehicle.updatePos(0, currentY);
            cc.cars.add(vehicle);
            cc.frame.drawPanel.addToCarAndPoint(vehicle, new Point((int)vehicle.getX(), (int)vehicle.getY()));
            currentY += 100;
        }

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());

                // About to hit a wall?  => stop + switch direction + starta motor
                if ((x < 0 || x > 800) || (y < 0 || y > 800)) {
                    //car.stopEngine();
                    car.turnLeft();
                    car.turnLeft();
                    //car.startEngine();
                }

                frame.drawPanel.moveit(car, x, y);

                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();

                if (car instanceof Volvo240){
                    if(Math.abs(car.getY() - volvoGarage.getY()) <= 50){
                        if(!volvoGarage.getVehiclesStored().contains(car)) {
                            System.out.println("Successfully loaded VOLVO <3");
                        }
                        volvoGarage.addVehicle((Volvo240) car);

                    }
                }

            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars
                ) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars){
            car.brake(brake);
        }
    }

    void turboOn() {
        for (Car car : cars){
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Car car : cars){
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void liftBed(){
        for (Car car : cars){
            if (car instanceof Scania){
                ((Scania) car).setFlatbedAngle(70);
            }
        }
    }

    void lowerBed(){
        for (Car car : cars){
            if (car instanceof Scania){
                ((Scania) car).setFlatbedAngle(0);
            }
        }
    }

    void stop(){
        for (Car car : cars){
            car.stopEngine();
        }
    }

    void start(){
        for (Car car : cars){
            car.startEngine();
        }
    }

}
