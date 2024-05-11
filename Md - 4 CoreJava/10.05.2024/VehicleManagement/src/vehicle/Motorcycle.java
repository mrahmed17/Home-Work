package vehicle;

public class Motorcycle extends Vehicle {

    public Motorcycle() {
    }

    @Override
    public void startEngine() {
        System.out.println("Motorcycle engine started. Rrrrr...");
    }

    @Override
    public void stopEngine() {
        System.out.println("Motorcycle engine stopped. Silence.");

    }
}
