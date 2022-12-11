package revision;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * A very good point is  If you don't override HashCode
 * Then u will be able to add duplicate objects in Set
 * Bcoz HashSet, HashMap, ArrayList  checks FIRST HashCode to compare and Then Equals.
 * But TreeMap, TreeSet uses CompareTo to check equalities. bcoz they are ordered.
 */
public class EqualsHasCodeRevision {

    public static void main(String[] args) {
        Person  p1 = new Person("A", 10);
        Person  p2 = new Person("A", 10);


        Person  p3 = new Person("C", 10);
        Person  p4 = new Person("D", 10);


        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
        System.out.println(p1 == p2);
        System.out.println(p1.equals(p2));

        // If we don't properly override HashCode/ Equals then we
        // Set can add duplicate elements too.
        Set<Person> set = new HashSet<>();
        set.add(p1);
        set.add(p2);
        System.out.println(set);

        // Below Piece of code is not adding p3 and p4
        // bcoz compareTo only override age, hence it treats p3 and p4 equals.
        Set<Person> treeSet = new TreeSet<>();
        treeSet.add(p3);
        treeSet.add(p4);
        System.out.println(treeSet);
    }
}

class Person implements Comparable<Person>{
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public int compareTo(Person o) {
        return o.age == this.age ? 0 : 1;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
