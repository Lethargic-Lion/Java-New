package designPatterns.creational.abstractDP;

class AndroidDeveloper implements Employee{
    @Override
    public int salary() {
        return 50000;
    }

    @Override
    public String name() {
        return "I am android Developer";
    }
}
