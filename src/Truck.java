import java.awt.*;
import java.util.Stack;

public class Truck extends Car {

    private Flatbed flatbed;
    private int maxCarAmount;
    private Stack<Car> cars;

    public Truck() {
        super();
        nrDoors = 2;
        color = Color.pink;
        enginePower = 75;
        modelName = "Longtrader (the kissing technique)";
        this.flatbed = new Flatbed(0,1); // 2-options (0 for up, 1 for down)
        maxCarAmount = 6;
        cars = new Stack<>();
        stopEngine();
    }

    public int getFlatbedAngle() {
        return this.flatbed.getFlatbedAngle();
    }

    public void setFlatbed(){ // works a toggle flatbed
        if(getCurrentSpeed() == 0.1) {
            if(this.flatbed.getFlatbedAngle() == 0){
                this.flatbed.setFlatbedAngle(1);
            } else {
                this.flatbed.setFlatbedAngle(0);
            }

        }
    }

    public void loadCar(Car currentCar){ // can only load when x,y is in 1m range
        if(this.flatbed.getFlatbedAngle() == 1 && cars.size() < maxCarAmount && checkCarDistance(currentCar) && !(currentCar instanceof Truck) && !(cars.contains(currentCar))) {
            cars.push(currentCar);
            currentCar.updatePos(this.x, this.y);
        }
    }

    protected boolean checkCarDistance(Car currentCar){
        if((Math.abs(currentCar.getX() - this.x) <= 1) && (Math.abs(currentCar.getY() - this.y) <= 1)){
            return true;
        } else {
            return false;
        }
    }

    public void releaseCar(){
        if(this.flatbed.getFlatbedAngle() == 1){
            Car currentCar = cars.pop();
            currentCar.updatePos(this.x + 1, this.y + 1); //sets the car 1m from the truck
        }
    }

    public int getFlatbedCarAmount(){
        return cars.size();
    }

    @Override
    public void move() {
        if(this.flatbed.getFlatbedAngle() == 0){
            switch(current_dir){
                case N: y += currentSpeed; break;
                case E: x += currentSpeed; break;
                case S: y -= currentSpeed; break;
                case W: x -= currentSpeed; break;
            }
            for (Car currentCar : cars) { // updates loaded-cars positions
                currentCar.updatePos(this.x, this.y);
            }
        }
    }

}