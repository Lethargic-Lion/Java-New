package designPatterns.structural.decoratorDP;

// A pattern that allows behavior to be added to an individual object, dynamically, without affecting the behavior of other objects from the same class.
// This is useful for adhering to the Open/Closed Principle, allowing classes to be open for extension but closed for modification.
// Real life example:
// 1. Java I/O Streams: The Java I/O library uses the Decorator pattern extensively. For example, you can wrap a FileInputStream with a BufferedInputStream to add buffering functionality, or wrap it with a DataInputStream to read Java primitive data types.
// 2. Graphical User Interface (GUI) Components: In GUI frameworks, you can decorate components with additional features. For example, you can add scrollbars to a text area or add borders to a button without modifying the original component classes.
// 3. Logging Frameworks: Logging frameworks often use the Decorator pattern to add different logging behaviors. For instance, you can decorate a logger to log messages to a file, console, or remote server without changing the core logging functionality.
// 4. Java Collections: The Java Collections Framework uses the Decorator pattern to provide additional functionality to collections. For example, you can decorate a List with synchronized or unmodifiable behavior using Collections.synchronizedList() or Collections.unmodifiableList() methods.
// 5. Security: In security frameworks, you can decorate objects with additional security features, such as authentication and authorization checks, without modifying the original object classes.
// it provides a layer of abstraction and allows for flexible and reusable code,
// making it easier to maintain and extend the functionality of objects without modifying their core behavior.

// Avoids class explosion by allowing you to create combinations
// of behaviors at runtime instead of creating a new subclass for each combination.
// Add new functionality to an object without altering its structure.

interface BasePizza {
    String getDescription();
    double getCost();
}

class PlainPizza implements BasePizza {
    @Override
    public String getDescription() {
        return "Plain Pizza";
    }

    @Override
    public double getCost() {
        return 100.0;
    }
}

class FarmHousePizza implements BasePizza {
    @Override
    public String getDescription() {
        return "Farm House Pizza";
    }

    @Override
    public double getCost() {
        return 200.0;
    }
}

abstract class ToppingDecorator implements BasePizza {
    BasePizza pizza;
    ToppingDecorator(BasePizza pizza) {
        this.pizza = pizza;
    }
}

class CheeseTopping extends ToppingDecorator {
    CheeseTopping(BasePizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Cheese Topping";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 50.0;
    }
}

class MushroomTopping extends ToppingDecorator {
    MushroomTopping(BasePizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Mushroom Topping";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 30.0;
    }
}

public class DecoratorPatternExample {
    static void main() {
        BasePizza pizza = new PlainPizza();
        System.out.println(pizza.getDescription() + " Cost: " + pizza.getCost());

        pizza = new CheeseTopping(pizza);
        System.out.println(pizza.getDescription() + " Cost: " + pizza.getCost());

        pizza = new MushroomTopping(pizza);
        System.out.println(pizza.getDescription() + " Cost: " + pizza.getCost());
    }
}
