package java17;

import java.util.Scanner;

public class SwitchUsage {
    static void main() {
        // fall through bug in traditional switch statement
        // if we forget to add break statement after each case,
        // it will execute all the cases below it until it finds
        // a break statement or reaches the end of the switch block
        int day = 2;
        switch (day) {
            case 1:
                System.out.println("Monday");
            case 2:
                System.out.println("Tuesday");
            case 3:
                System.out.println("Wednesday");
            default:
                System.out.println("Invalid day");
        }

        // new switch expression in Java 17
        // it does not have fall through behavior and it returns a value

        // take user input for day1
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a day (1-7): ");
        int day1 = scanner.nextInt();
        String dayName = switch (day1) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            default -> "Invalid day";
        };
        System.out.println(dayName);

        // new switch expression with multiple labels
        int month = 5;
        String season = switch (month) {
            case 12, 1, 2 -> "Winter";
            case 3, 4, 5 -> "Spring";
            case 6, 7, 8 -> "Summer";
            case 9, 10, 11 -> "Autumn";
            default -> "Invalid month";
        };
        System.out.println(season);
    }
}
