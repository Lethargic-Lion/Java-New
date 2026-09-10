package designPatterns.creational.abstractDP;

class AndroidDevFactory extends EmployeeAbstractFactory{
    @Override
    public Employee getEmployee() {
        return new AndroidDeveloper();
    }
}
