package Java8;

import java.time.LocalDate;
import java.time.Month;

import static java.lang.IO.println;
import static java.time.Month.MARCH;

public class DateTimeApi {
    static void main() {
        LocalDate now = LocalDate.now();
        LocalDate.of(2024, MARCH, 3);
        LocalDate parse = LocalDate.parse("2024-03-30");

        println(now + " " + now.getDayOfWeek() + " " + now.getMonth() + " " + now.getYear());

        println(LocalDate.now().minusDays(3));
        
    }
}
