package designPatterns.creational.abstractDP;

class EmployeeFactory {
    public static Employee getEmployee(EmployeeAbstractFactory factory) {
        return factory.getEmployee();
    }
}
