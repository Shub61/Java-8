package revision;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparableAndComparatorRevision {
    public static void main(String[] args) {

        PersonComparable  p1 = new PersonComparable("A", 10);
        PersonComparable  p2 = new PersonComparable("A", 10);

        PersonComparator p3 = new PersonComparator("C", 20);
        PersonComparator p4 = new PersonComparator("D", 30);
        PersonComparator p5 = new PersonComparator("E", 5);

        List<PersonComparator> personComparators = new ArrayList<>();
        personComparators.add(p3);
        personComparators.add(p4);
        personComparators.add(p5);

        personComparators.sort(new PersonComparatorSort());

        personComparators.forEach(System.out:: println);

    }
}

class PersonComparable implements Comparable<PersonComparable>{
    String name;
    int age;

    public PersonComparable(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(PersonComparable o) {
        return o.age == this.age ? 0 : 1;
    }

    @Override
    public String toString() {
        return "PersonComparable{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}


class PersonComparator {
    String name;
    int age;

    public PersonComparator(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return "PersonComparator{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}

class PersonComparatorSort implements Comparator<PersonComparator> {
    @Override
    public int compare(PersonComparator o1, PersonComparator o2) {
        return o1.age > o2.age ? 1 : -1;
    }
}