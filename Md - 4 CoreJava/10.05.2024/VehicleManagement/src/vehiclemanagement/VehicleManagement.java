package vehiclemanagement;

import vehicle.Car;
import vehicle.Motorcycle;

public class VehicleManagement {

    public static void main(String[] args) {

        Car myCar = new Car();
        Motorcycle myMotorcycle = new Motorcycle();

        // Start and stop the car engine
        myCar.startEngine();
        myCar.stopEngine();

        // Start and stop the motorcycle engine
        myMotorcycle.startEngine();
        myMotorcycle.stopEngine();
    }

}
