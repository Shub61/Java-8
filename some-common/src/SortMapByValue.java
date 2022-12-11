import javax.naming.PartialResultException;
import java.util.*;
import java.util.stream.Collectors;

public class SortMapByValue {
    private HashMap<String, Integer> map;
    private LinkedHashMap<String, Integer> linkedMap;
    private ArrayList<Map.Entry<String, Integer>> arr;
    private TreeMap<String, Integer> treeMap;

    public SortMapByValue() {
        map = new HashMap<>();
        map.put("a", 10);
        map.put("b", 30);
        map.put("c", 20);
        map.put("d", 5);
        map.put("e", 40);

        linkedMap = new LinkedHashMap<>();
        arr = new ArrayList<>();
        treeMap = new TreeMap<String , Integer>();
    }

    public static void main(String[] args) {
        SortMapByValue sortMapByValue = new SortMapByValue();
        sortMapByValue.map.entrySet().forEach(entry-> sortMapByValue.arr.add(entry));
        Collections.sort(sortMapByValue.arr, (o1, o2)-> o1.getValue() > o2.getValue() ? -1 : 1);
        sortMapByValue.arr.forEach(entry -> sortMapByValue.linkedMap.put(entry.getKey(), entry.getValue()));
        sortMapByValue.linkedMap.forEach((k, v) -> {
            System.out.println(k + "::" + v);
        });
    }
}

class MyComparator implements Comparator<Map.Entry<String, Integer>> {
    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return o1.getValue() > o2.getValue() ? -1 : 1;
    }
}
