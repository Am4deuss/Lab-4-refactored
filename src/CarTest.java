import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void testMoveForward() {
        Saab95 pissbil = new Saab95();
        pissbil.startEngine();
        pissbil.move();
        pissbil.move();
        assertEquals(0.2, pissbil.getY());
    }

    @Test
    void testTurnRight() {
        Volvo240 kingenbil = new Volvo240();
        kingenbil.startEngine();
        kingenbil.move();
        kingenbil.turnRight();
        kingenbil.move();
        assertEquals(0.1, kingenbil.getX());
        assertEquals(0.1, kingenbil.getY());
    }

    @Test
    void testTurnLeft() {
        Volvo240 kingenbil = new Volvo240();
        kingenbil.startEngine();
        kingenbil.move();
        kingenbil.turnLeft();
        kingenbil.move();
        assertEquals(-0.1, kingenbil.getX());
        assertEquals(0.1, kingenbil.getY());
    }

    @Test
    void testGas() {
        Volvo240 kingenbil = new Volvo240();
        kingenbil.startEngine();
        double testAmount = 1.1;
        assertFalse(kingenbil.gas(testAmount));
        testAmount = 1.0;
        assertTrue(kingenbil.gas(testAmount));
        testAmount = -0.5;
        assertFalse(kingenbil.gas(testAmount));
    }

    // Truck tests
    @Test
    void testToggleFlatbed() {
        Truck truckis = new Truck();
        truckis.startEngine();
        assertEquals(0, truckis.getFlatbedAngle());
        truckis.setFlatbed(); // should toggle from 0 -> 1 (down)
        assertEquals(1, truckis.getFlatbedAngle());
        truckis.setFlatbed(); // should toggle from 1 -> 0 (down)
        assertEquals(0, truckis.getFlatbedAngle());
        truckis.setFlatbed(); // should toggle from 0 -> 1 (down)
        assertEquals(1, truckis.getFlatbedAngle());
        truckis.stopEngine();
        truckis.setFlatbed(); // should do nothing since the vehicle is not on
        assertEquals(1, truckis.getFlatbedAngle());
    }

    @Test
    void testFlatbedDownWhileNotMoving() {
        Truck truckis2 = new Truck();
        truckis2.setFlatbed(); // nothing should happend -> not on
        assertNotEquals(1, truckis2.getFlatbedAngle());
        truckis2.startEngine();
        truckis2.incrementSpeed(1);
        truckis2.setFlatbed(); // should do nothing
        assertEquals(0, truckis2.getFlatbedAngle());
        truckis2.stopEngine();
        truckis2.startEngine();
        truckis2.setFlatbed(); // should work since it is no longer moving.
        assertEquals(1, truckis2.getFlatbedAngle());
    }

    @Test
    void testLoadCar() {
        Saab95 car1 = new Saab95();
        Saab95 car2 = new Saab95();
        Saab95 car3 = new Saab95();
        Saab95 car4 = new Saab95();
        Saab95 car5 = new Saab95();
        Saab95 car6 = new Saab95();
        Saab95 car7 = new Saab95();

        Truck coolTruck = new Truck();

        coolTruck.startEngine();
        coolTruck.loadCar(car1);
        assertEquals(0, coolTruck.getFlatbedCarAmount());
        coolTruck.setFlatbed();
        coolTruck.loadCar(car1);
        assertEquals(1, coolTruck.getFlatbedCarAmount());
        coolTruck.loadCar(car2);
        coolTruck.loadCar(car3);
        coolTruck.loadCar(car4);
        coolTruck.loadCar(car5);
        coolTruck.loadCar(car6);
        coolTruck.loadCar(car7);
        assertEquals(6, coolTruck.getFlatbedCarAmount());
        coolTruck.releaseCar();
        assertEquals(5, coolTruck.getFlatbedCarAmount());
        coolTruck.loadCar(car7);
        assertEquals(6, coolTruck.getFlatbedCarAmount());
        for(int i = 0; i < 6; i++){
            coolTruck.releaseCar();
        }
        assertEquals(0, coolTruck.getFlatbedCarAmount());
    }

    @Test
    void moveIfLoadedAndLoadingDistance() {
        Saab95 car1 = new Saab95();
        Saab95 car2 = new Saab95();
        Truck truck = new Truck();

        car1.startEngine();
        car1.incrementSpeed(1);
        car1.move(); // Moves North
        truck.startEngine();
        truck.setFlatbed();
        truck.loadCar(car1);
        assertEquals(0, truck.getFlatbedCarAmount());
        truck.loadCar(car2);
        assertEquals(1, truck.getFlatbedCarAmount());
        truck.setFlatbed();
        truck.incrementSpeed(1);
        truck.move(); // Moves North
        truck.brake(1); // Tvärnitar (krashar i en trädgårdstomte)
        truck.setFlatbed();
        truck.loadCar(car1);
        assertEquals(2, truck.getFlatbedCarAmount());
        assertEquals(truck.getY(), car2.getY());
    }

    @Test
    void testLoadTruck() {
        Truck truck1 = new Truck();
        Truck truck2 = new Truck();

        truck1.startEngine();
        truck1.setFlatbed();
        truck1.loadCar(truck2);
        assertEquals(0, truck1.getFlatbedCarAmount());
    }

    @Test
    void testScaniaFlatbed() {
        Scania storbil = new Scania();
        assertEquals(0, storbil.getFlatbedAngle());
    }

    @Test
    void testScaniaFlatbedRaiseLower() {
        Scania storarebil = new Scania();
        storarebil.setFlatbedAngle(71);
        assertNotEquals(71, storarebil.getFlatbedAngle());
        assertEquals(0, storarebil.getFlatbedAngle());
        storarebil.setFlatbedAngle(70);
        assertEquals(70, storarebil.getFlatbedAngle());
        storarebil.setFlatbedAngle(10);
        assertEquals(10, storarebil.getFlatbedAngle());
        storarebil.setFlatbedAngle(-10);
        assertNotEquals(-10, storarebil.getFlatbedAngle());
        storarebil.setFlatbedAngle(0);
        assertEquals(0, storarebil.getFlatbedAngle());
    }

    @Test
    void testGarageAdd() {
        Volvo240 kingenbil = new Volvo240();
        Garage sunkgarage = new Garage(4, 0, 0, Volvo240.class);
        sunkgarage.addVehicle(kingenbil);
        assertEquals(kingenbil, sunkgarage.getVehiclesStored().get(0));
    }

}