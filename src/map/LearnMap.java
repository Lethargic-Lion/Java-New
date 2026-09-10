package map;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.Collections;

public class LearnMap {
    static void main() {
        Map<String, Integer> map = new HashMap<>();

        map.put("A", 10);
        map.put("B", 20);
        map.put("A", 30); // overwrites previous value

        System.out.println(map); // {A=30, B=20}
    }
}
