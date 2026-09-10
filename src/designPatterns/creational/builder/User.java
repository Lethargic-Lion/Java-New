package designPatterns.creational.builder;

class User {
    private final String firstName;
    private final String lastName;
    private final int age;

    // getters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }

    // private constructor to enforce object creation through Builder
    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    // static nested Builder class
    static class UserBuilder {
        private String firstName;
        private String lastName;
        private int age;

        // constructor with required parameters
        public UserBuilder() {
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        // Build method
        public User build() {
            return new User(this);
        }


    }
}
