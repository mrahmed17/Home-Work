package vehicle;

public class Car extends Vehicle {

    public Car() {
    }

    @Override
    public void startEngine() {
        System.out.println("Car engine started. Vroom vroom!");
    }

    @Override
    public void stopEngine() {
        System.out.println("Car engine stopped. Shhh...");
    }
}
