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
        allCars.add(CreateCars.createVolvo(0, 300));


    }


}
