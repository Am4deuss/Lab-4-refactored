import java.util.ArrayList;

public class Garage<T extends Car> {
    private ArrayList<T> vehiclesStored;
    private int maxVehicles;
    private Class<?>[] vehicleTypes;

    private double x;
    private double y;

    public Garage(int maxVehicles, double x, double y, Class<? extends T>... carModels){
        this.x = x;
        this.y = y;
        vehiclesStored = new ArrayList<>();
        this.maxVehicles = maxVehicles;
        vehicleTypes = carModels;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public ArrayList<T> getVehiclesStored() {
        return vehiclesStored;
    }

    public Class<?>[] getVehicleTypes() {
        return vehicleTypes;
    }

    public void addVehicle(T vehicle) {
        // check if instance of the right type with accepted Vehicle()
        if(!vehiclesStored.contains(vehicle)) {
            if (isAccepted(vehicle)) {
                vehiclesStored.add(vehicle);
            } else {
                System.out.println(vehicle.getCarModel());
                throw new IllegalArgumentException("Wrong car type :P");
            }
        }
    }

    public Car removeVehicle(int vehicleIndex) {
       Car removedCar = null;
       if(vehicleIndex != 0 && vehicleIndex < vehiclesStored.size()) {
           removedCar = vehiclesStored.get(vehicleIndex);
           vehiclesStored.remove(vehicleIndex);
       }
       return removedCar;
    }

    private boolean isAccepted(T vehicle) {
        boolean check = false;
        if (vehiclesStored.size() == this.maxVehicles) {
            return false;
        } else {
            for (Class<?> model : vehicleTypes) {
                if (model.isInstance(vehicle)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }
}