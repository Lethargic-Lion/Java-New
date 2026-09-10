package Java8;

import java.util.NoSuchElementException;
import java.util.Optional;

import static java.lang.IO.println;

public class LearnOptional {
    static void main() {
        Optional<String> name = getName(2);
        name.map(String::toUpperCase).ifPresent(IO::println);
//        System.out.println(name.get());
//        if(name.isPresent())
//            println(name.get());
//
//        name.ifPresent(IO::println);
//
//        println(name.orElse("NA"));
//        println(name.orElseGet(() -> "Default Name"));
//        println(name.orElseThrow(() -> new NoSuchElementException("Name not found")));
//
//        name.map(String::length).ifPresent(IO::println);
    }

    private static Optional<String> getName(int id) {
//        String name = "RAM";
//        return Optional.ofNullable(name);
        return Optional.empty();
    }
}
