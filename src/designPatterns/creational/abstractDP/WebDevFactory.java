package designPatterns.creational.abstractDP;

class WebDevFactory extends EmployeeAbstractFactory{
    @Override
    public Employee getEmployee() {
        return new WebDeveloper();
    }
}
