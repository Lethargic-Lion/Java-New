package designPatterns.behavioral.strategy;

interface DriveStrategy {
    void drive();
}

class CityDriveStrategy implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Driving in the city");
    }
}

class SportsDriveStrategy implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Driving in sports mode");
    }
}

class EcoDriveStrategy implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Driving in eco mode");
    }
}

class Vehicle {
    private DriveStrategy driveStrategy;

    public Vehicle(DriveStrategy driveStrategy) {
        this.driveStrategy = driveStrategy;
    }

    public void drive() {
        driveStrategy.drive();
    }
}

class GoodsVehicle extends Vehicle {
    public GoodsVehicle(DriveStrategy driveStrategy) {
        super(driveStrategy);
    }
}

class SportsVehicle extends Vehicle {
    public SportsVehicle(DriveStrategy driveStrategy) {
        super(driveStrategy);
    }
}

// In this example, we have defined a DriveStrategy interface with a drive() method.
// We have three concrete implementations of the DriveStrategy interface:
// CityDriveStrategy, SportsDriveStrategy, and EcoDriveStrategy.
// Each implementation provides a different driving behavior.
// This Design pattern allows us to change the driving behavior of a vehicle
// at runtime by passing different DriveStrategy implementations to the Vehicle class.
// The Vehicle class has a drive() method that delegates the driving behavior to the DriveStrategy implementation.
// Tight Coupling is avoided as the Vehicle class does not depend on any specific implementation of the DriveStrategy interface.
// Code Duplication is avoided as the driving behavior is encapsulated in separate classes, allowing for easy extension
// and modification of driving behaviors without changing the Vehicle class.
// The Strategy Design Pattern promotes the Open/Closed Principle,
// as new driving behaviors can be added without modifying existing code.
public class DriveStrategyExample {
    static void main() {
        Vehicle vehicle = new SportsVehicle(new SportsDriveStrategy()); // dynamically changing the driving behavior
        vehicle.drive(); // Output: Driving in sports mode

        Vehicle vehicle2 = new GoodsVehicle(new CityDriveStrategy());
        vehicle2.drive(); // Output: Driving in the city
    }

}
