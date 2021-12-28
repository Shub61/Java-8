package func_interface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExistingFIComparatorDemo {
    public static void main(String[] args) {
        Employee employeeOne = new Employee("Shubham", 30000);
        Employee employeeTwo = new Employee("Amogh", 50000);
        Employee employeeThree = new Employee("Sanjay", 10000);
        Employee employeeFour = new Employee("Prachit", 20000);
        List<Employee> list = Stream.of(employeeOne, employeeTwo, employeeThree, employeeFour)
                .collect(Collectors.toList());
        //Collections.sort(list, new SalarySortComparator());
        Collections.sort(list, (e1, e2) -> e2.name().compareTo(e1.name()));
        list.stream().forEach(System.out::println);
    }
}
record Employee(String name, int salary) { }

class SalarySortComparator implements Comparator<Employee>{
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.salary() > o2.salary() ? -1 : 1;
    }
}