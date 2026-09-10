package designPatterns.creational.factory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

interface Employee {
    int salary();
}

class AndroidDeveloper implements Employee {
    @Override
    public int salary() {
        System.out.println("Getting android developer salary");
        return 15000;
    }
}

class IosDeveloper implements Employee {
    @Override
    public int salary() {
        System.out.println("Getting ios developer salary");
        return 20000;
    }
}

class EmployeeFactory {

    // This violates Open/Closed Principle
    // because every time we add a new Employee type,
    // we need to modify this method.
//    public static Employee getEmployee(EmployeeType type) {
//        return switch (type) {
//            case ANDROID -> new AndroidDeveloper();
//            case IOS -> new IosDeveloper();
//        };
//    }

    // Better approach with strict Open/Closed Principle
    static final ConcurrentHashMap<String, Supplier<Employee>> Registry = new ConcurrentHashMap<>();

    static {
        Registry.put(EmployeeType.ANDROID.name(), AndroidDeveloper::new);
        Registry.put(EmployeeType.IOS.name(), IosDeveloper::new);
    }

    static void registerEmployee(String type, Supplier<Employee> supplier) {
        Registry.put(type, supplier);
    }

    public static Employee getEmployee(String type) {
        Supplier<Employee> employeeSupplier = Registry.get(type);
        if (employeeSupplier != null) {
            return employeeSupplier.get();
        }
        throw new IllegalArgumentException("No such employee " + type);
    }
}

enum EmployeeType {
    ANDROID,
    IOS
}

public class FactoryDesign {
    static void main() {
//        Employee androidDev = EmployeeFactory.getEmployee(EmployeeType.ANDROID);
//        System.out.println("Android Dev Salary: " + androidDev.salary());
//
//        Employee iosDev = EmployeeFactory.getEmployee(EmployeeType.IOS);
//        System.out.println("iOS Dev Salary: " + iosDev.salary());
        Employee androidDev = EmployeeFactory.getEmployee("ANDROID");
        System.out.println("Android Dev Salary: " + androidDev.salary());
        Employee iosDev = EmployeeFactory.getEmployee("IOS");
        System.out.println("iOS Dev Salary: " + iosDev.salary());
        EmployeeFactory.registerEmployee("WEB", () -> new Employee() {
            @Override
            public int salary() {
                System.out.println("Getting web developer salary");
                return 18000;
            }
        });
        Employee webDev = EmployeeFactory.getEmployee("WEB");
        System.out.println("Web Dev Salary: " + webDev.salary());
    }
}
