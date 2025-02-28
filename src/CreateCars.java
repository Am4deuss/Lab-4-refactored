import java.util.Random;

public class CreateCars {

    public static Car createVolvo(int x, int y){
        Volvo240 volvo = new Volvo240();
        volvo.updatePos(x, y);
        return volvo;
    }

    public static Car createSaab(int x, int y){
        Saab95 saab = new Saab95();
        saab.updatePos(x, y);
        return saab;
    }

    public static Car createScania(int x, int y){
        Scania scania = new Scania();
        scania.updatePos(x, y);
        return scania;
    }

    public static Car createRandom(int x, int y){
        Random random = new Random();
        int randomInt = random.nextInt(3);
        if(randomInt == 0){
            return createVolvo(x, y);
        } else if(randomInt == 1){
            return createSaab(x, y);
        } else{
            return createScania(x, y);
        }
    }

}
