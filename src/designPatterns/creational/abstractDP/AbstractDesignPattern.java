package designPatterns.creational.abstractDP;

public class AbstractDesignPattern {
    static void main(String[] args) {
        Employee e1 = EmployeeFactory.getEmployee(new AndroidDevFactory());
        System.out.println(e1.name());
        System.out.println(e1.salary());

        Employee e2 = EmployeeFactory.getEmployee(new WebDevFactory());
        System.out.println(e2.name());
        System.out.println(e2.salary());

    }
}
