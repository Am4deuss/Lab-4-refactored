public class Flatbed {

    private int flatbedAngle;
    private final int[] angleRange;

    public Flatbed(int min, int max) {
        flatbedAngle = 0;
        angleRange = new int[2]; // creating angle for min/max-angle
        angleRange[0] = min; // min angle of flatbed
        angleRange[1] = max; // max angle of flatbed
    }

    public int getFlatbedAngle() {
        return flatbedAngle;
    }

    // setter which acts as a "raise-lower"-function
    public void setFlatbedAngle(int amount) {
        if(amount >= angleRange[0] && amount <= angleRange[1]){
            flatbedAngle = amount;
        }
    }

}
