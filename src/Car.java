import java.awt.*;

public abstract class Car implements Movable {

    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name
    protected double x; // X-coordinate
    protected double y; // Y-coordinate

    protected enum Directions{
        N,
        E,
        S,
        W
    }; // Directions (to be used by switch)
    protected Directions current_dir;

    public Car(){
        // in meters
        x = 0;
        y = 0;
        current_dir = Directions.N;
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public String getCarModel() {
        return modelName;
    }

    protected void updatePos(double xCar, double yCar) {
        this.x = xCar;
        this.y = yCar;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    protected double speedFactor(){
        return enginePower * 0.01;
    }

    protected void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    protected void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0.1);
    }

    public boolean gas(double amount){
        boolean correctRange = true;
        if(amount >= 0 && amount <= 1) {
            incrementSpeed(amount);
        } else{
            correctRange = false;
        }
        return correctRange;
    }

    public boolean brake(double amount){
        boolean correctRange = true;
        if(amount >= 0 && amount <= 1) {
            decrementSpeed(amount);
        } else{
            correctRange = false;
        }
        return correctRange;
    }

    @Override
    public void move() {
        switch(current_dir){
            case N: y += currentSpeed; break;
            case E: x += currentSpeed; break;
            case S: y -= currentSpeed; break;
            case W: x -= currentSpeed; break;
        }
    }

    @Override
    public void turnLeft() {
        switch(current_dir){
            case N: current_dir = Directions.W; break;
            case E: current_dir = Directions.N; break;
            case S: current_dir = Directions.E; break;
            case W: current_dir = Directions.S; break;
        }
    }

    @Override
    public void turnRight() {
        switch(current_dir){
            case N: current_dir = Directions.E; break;
            case E: current_dir = Directions.S; break;
            case S: current_dir = Directions.W; break;
            case W: current_dir = Directions.N; break;
        }
    }
}