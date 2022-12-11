import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CompareAndEqualContractDemo {
    public static void main(String[] args) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Set<Employee> setEmployees = new TreeSet<>();
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee("Tom", "Eagar", dateFormat.parse("2007-12-03"));
        Employee employee2 = new Employee("Tom", "Smith", dateFormat.parse("2005-06-20"));
        Employee employee3 = new Employee("Bill", "Joy", dateFormat.parse("2007-12-03"));
        Employee employee4 = new Employee("Bill", "Gates", dateFormat.parse("2005-05-12"));
        Employee employee5 = new Employee("Alice", "Wooden", dateFormat.parse("2005-06-20"));
        setEmployees.add(employee1);
        setEmployees.add(employee2);
        setEmployees.add(employee3);
        setEmployees.add(employee4);
        setEmployees.add(employee5);

        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        employeeList.add(employee4);
        employeeList.add(employee5);

       System.out.println(setEmployees);

       // Here everything looks Fine bcoz compareTO == equal() SAME CONTRACT
        // What IF - We sort the Employee BY JOIN DATE

        // AS SOON AS We violate the CONTRACT :: The problem arises
        // PROBLEM :
            // we are adding 5 item , but only 3 will be stores
            // bcoz TREESET(In general Any SORTED COLLECTION) compares based on compareTo() and JOINING date is same for 2 employees
            //  So duplicated won't be added.

        // SOLUTION --> Modify compareTO()

        // Comparator
        Collections.sort(employeeList, new Employee.SortByJoiningDate());
        System.out.println(employeeList);

    }
}

record Employee(String firstName, String lastName, Date joiningDate) implements Comparable<Employee>{

//    @Override
//    public int compareTo(Employee o) {
//        int compareValue = this.firstName.compareTo(o.firstName);
//        if(compareValue == 0){
//            compareValue = this.lastName.compareTo(o.lastName);
//        }
//        return compareValue;
//    }

    // What IF - We sort the Employee BY JOIN DATE
    // bcoz SET compares based on compareTo() and JOINING date is same for 2 employees
    //  So duplicated won't be added.
    // SOLUTION :

    @Override
    public int compareTo(Employee o) {
        //return this.joiningDate.compareTo(o.joiningDate);
        // CHANGES for SOLUTION
        int compareValue = this.joiningDate.compareTo(o.joiningDate);
        if(compareValue == 0){
            compareValue = this.firstName.compareTo(o.firstName);
            if(compareValue == 0){
                compareValue = this.lastName.compareTo(o.lastName);
            }
        }
        return compareValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        if(firstName.equals(employee.firstName) && lastName.equals(employee.lastName)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return firstName + " " + lastName + " " + dateFormat.format(joiningDate);
    }

    static class SortByJoiningDate implements Comparator<Employee>{

        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.joiningDate.compareTo(o2.joiningDate);
        }
    }
}