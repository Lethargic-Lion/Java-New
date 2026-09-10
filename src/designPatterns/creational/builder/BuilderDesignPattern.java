package designPatterns.creational.builder;

public class BuilderDesignPattern {
    static void main() {
        User user1 = new User.UserBuilder()
                .setFirstName("John")
                .setLastName("Doe")
                .setAge(30)
                .build();

        User user2 = new User.UserBuilder()
                .setFirstName("Jane")
                .setLastName("Smith")
                .setAge(25)
                .build();

        System.out.println(user1.toString());
        System.out.println(user2.toString());
        System.out.println(user1.getFirstName());
        System.out.println(user2.getAge());
        System.out.println(user1.getLastName());
    }
}
