package revision;

import java.util.*;

public class SortByValueRevision {
    private HashMap<String, Integer> map;
    private ArrayList<Map.Entry<String, Integer>> arr;
    private LinkedHashMap<String, Integer> sortedByValueMap;
    public SortByValueRevision() {
        map = new HashMap<>();
        map.put("a", 10);
        map.put("b", 30);
        map.put("c", 20);
        map.put("d", 5);
        map.put("e", 40);
        arr = new ArrayList<>();
        sortedByValueMap = new LinkedHashMap<>();
    }
    public static void main(String[] args) {
        SortByValueRevision sortByValueRevision = new SortByValueRevision();
        sortByValueRevision.map.entrySet().forEach(entry-> sortByValueRevision.arr.add(entry));
        sortByValueRevision.arr.sort((e1, e2)-> e1.getValue() > e2.getValue() ? -1 : 1);
        sortByValueRevision.arr.forEach(e-> sortByValueRevision.sortedByValueMap.put(e.getKey(), e.getValue()));
        sortByValueRevision.sortedByValueMap.forEach((k,v)-> System.out.println(k + "::" + v)) ;
    }
}
