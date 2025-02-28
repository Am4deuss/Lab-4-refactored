import java.util.ArrayList;

public class Model {

    private ArrayList<Car> allCars = new ArrayList<>();
    private ArrayList<Garage> allGarage = new ArrayList<>();
    private ArrayList<Subscriber> allSubscribers = new ArrayList<>();

    // Getters (to be accessed by Subscribers to get car-data)
    public ArrayList<Car> getAllCars(){
        return allCars;
    }

    public ArrayList<Garage> getAllGarage(){
        return allGarage;
    }

    public Model(ArrayList<Subscriber> subscribers){
        allSubscribers.addAll(subscribers);

        // Initiate and create a Volvo-garage + One of each car-model (type)
        allCars.add(CreateCars.createSaab(0, 100));
        allCars.add(CreateCars.createScania(0, 200));
        allCars.add(CreateCars.createVolvo(0, 300));
        allGarage.add(new Garage<>(3, 300, 300, Volvo240.class));

    }

    // from lecture about subscribers
    public void addSubscriber(Subscriber subscriber){
        allSubscribers.add(subscriber);
    }

    // no real usage for this application, added for good measure
    public void removeSubscriber(Subscriber subscriber){
        allSubscribers.remove(subscriber);
    }

    // Calls the gas method for each car once
    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : allCars) {
            car.gas(gas);
        }
    }

    // Same as with the gas-method
    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : allCars){
            car.brake(brake);
        }
    }

    // Turbo-mgmt for all Saab95
    void turboOn() {
        for (Car car : allCars){
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Car car : allCars){
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void liftBed(){
        for (Car car : allCars){
            if (car instanceof Scania){
                ((Scania) car).setFlatbedAngle(70);
            }
        }
    }

    void lowerBed(){
        for (Car car : allCars){
            if (car instanceof Scania){
                ((Scania) car).setFlatbedAngle(0);
            }
        }
    }

    void stop(){
        for (Car car : allCars){
            car.stopEngine();
        }
    }

    void start(){
        for (Car car : allCars){
            car.startEngine();
        }
    }

    void addCar(){
        if (allCars.size() < 10){
            allCars.add(CreateCars.createRandom());
        }
    }

    void removeCar(){
        if (!allCars.isEmpty()){
            allCars.removeLast();
        }
    }

    public void timerUpdate(){
        for (Car car : allCars){
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

            //frame.drawPanel.moveit(car, x, y);

            for (Garage garage : allGarage){
                garageCollision(car, garage);
            }
        }

        // Notifies DrawPanel.java
        for (Subscriber subscriber : allSubscribers){
            subscriber.notify(this);
        }
    }

    private void garageCollision(Car car, Garage garage){
        if(Math.abs(car.getY() - garage.getY()) <= 50){
            if(!garage.getVehiclesStored().contains(car)) {
                System.out.println("Successfully loaded VOLVO <3");
            }
            garage.addVehicle(car);

        }
    }

}
