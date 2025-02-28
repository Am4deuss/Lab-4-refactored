import java.awt.*;

public class Scania extends Car {

    private Flatbed flatbed;

    public Scania() {
        super();
        nrDoors = 2;
        color = Color.black;
        enginePower = 80;
        modelName = "Scania";
        this.flatbed = new Flatbed(0,70);
        stopEngine();
    }

    public int getFlatbedAngle() {
        return this.flatbed.getFlatbedAngle();
    }

    public void setFlatbedAngle(int amount) {
        if(getCurrentSpeed() == 0) {
            this.flatbed.setFlatbedAngle(amount);
        }
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
        }
    }

    @Override
    public void startEngine(){
        if(this.flatbed.getFlatbedAngle() == 0) {
            currentSpeed = 0.1;
        } else {
            currentSpeed = 0.0;
        }
    }

}
