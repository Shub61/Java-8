package revision;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

public class ImmutableMapDemo {

    public static void main(String[] args) {
        Map<String, Integer> stringMap = new HashMap<String, Integer>();
        stringMap.put("a", 1);
        stringMap.put("b", 2);
        stringMap.put("c", 3);
        Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(stringMap);
        Map<String, Integer> immutableMap = stringMap.entrySet().stream().collect(Collectors
               .toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(stringMap);
        stringMap.put("d", 4);
        System.out.println(unmodifiableMap);
        System.out.println(immutableMap);
    }
}
