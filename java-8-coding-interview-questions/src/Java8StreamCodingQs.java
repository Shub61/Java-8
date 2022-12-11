import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.*;


/**
 * // https://javaconceptoftheday.com/solving-real-time-queries-using-java-8-features-employee-management-system/
 * //https://javahungry.blogspot.com/2020/05/java-8-coding-and-programming-interview-questions.html
 */
public class Java8StreamCodingQs {
    private static Map<Character, Integer> charMap = new HashMap<Character, Integer>();
    static {
        charMap.put('a', 1);
        charMap.put('b', 2);
        charMap.put('c', 3);
        charMap. put('d', 4);
        charMap.put('e', 5);
        charMap.put('f', 6);
        charMap.put('g', 7);
        charMap.put('h', 8);
        charMap.put('i', 9);
        charMap.put('j', 10);
        charMap.put('k', 11);
        charMap.put('l', 12);
        charMap.put('m', 13);
        charMap.put('n', 14);
        charMap.put('o', 15);
        charMap.put('p', 16);
        charMap.put('q', 17);
        charMap.put('r', 18);
        charMap.put('s', 19);
        charMap.put('t', 20);
        charMap.put('u', 21);
        charMap.put('v', 22);
        charMap.put('w', 23);
        charMap.put('x', 24);
        charMap.put('y', 25);
        charMap.put('z', 26);
    }
    public static boolean isPrime(int nbr) {
        if (nbr == 1 || nbr == 2) return true;
        return IntStream.range(2, nbr).noneMatch(n -> nbr % n == 0);
    }

    /**
     * Now, starting again from a list of names,
     * give me the total number of letters in all the names with more than 5 letters
     *  System.out.println("Testing if [william, jones, aaron, seppe, frank, gilliam] returns 14")
     */
    public static int countTotalLetter(List<String> stringList) {
        return stringList.stream()
                .filter(s -> s.length() > 5)
                .mapToInt(s -> s.length())
                .sum();
    }

    /**
     * Get the oldest person from the collection
     *
     * @param personList
     * @return
     */
    public static Person oldestPerson(List<Person> personList) {
        return personList.stream()
                .sorted((p1, p2) -> p1.getAge() > p2.getAge() ? -1 : 1)
                .findFirst().get();
    }

    /**
     * Sum all elements of a collection,
     * try to use the reduce operator with identity parameter instead of an IntStream
     *
     *
     */
    public static int sumOfAllElements(List<Integer> integerList) {
        return integerList.stream()
                .reduce(0, (partialSum, element) -> partialSum + element);
    }

    /**
     * 1.Write a program to print employee details working in each department
     *
     */
    public static void employeeDetailsForEachDepartment(List<Employee> employeeList) {
        employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDeptId))
                .forEach((k, v)->{
                    System.out.println("Department Id:: " + k);
                    v.forEach(employee -> {
                        System.out.println("Employee name:: " + employee.getEmpName());
                    });
                });
    }

    /**
     * Write a program to print employees count working in each department
     *
     */

    public static void employeeCountForEachDepartment(List<Employee> employeeList) {
        employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDeptId, Collectors.counting()))
                .forEach((k,v) -> {
                    System.out.println("Department Id:: " + k +  " and count :: " + v);
                });
    }

    /**
     * Write a program to print active and inactive employees in the given collection
     */
    public static void printActiveInactiveEmployees(List<Employee> employeeList) {
        employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getStatus))
                .forEach((k,v) -> {
                    System.out.println("STATUS of Employee:: " + k);
                    v.forEach(employee -> {
                        System.out.println("Employee name:: " + employee.getEmpName());
                    });
                });
    }

    /**
     * Write a program to print Max/Min employee salary from the given collection
     */
    public static void printMaxMinSalary(List<Employee> employeeList) {
        IntSummaryStatistics summaryStatistics = employeeList.stream()
                .mapToInt(Employee::getSalary)
                .summaryStatistics();
        System.out.println("Max Salary::-> " + summaryStatistics.getMax());
        System.out.println("Min Salary::-> " + summaryStatistics.getMin());
    }

    /**
     * Write a program to print the max salary of an employee from each department
     */
    public static void printMaxSalaryByDepartment(List<Employee> employeeList) {
        Map<Integer, IntSummaryStatistics> employeeIntSummaryStatisticsMap = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDeptId, Collectors.summarizingInt(Employee::getSalary)));
        employeeIntSummaryStatisticsMap.forEach((k, v) -> {
            System.out.println("Department Id ::-> " + k);
            System.out.println("Max Salary ::-> " + v.getMax());
        });
    }

    /**
     * print all the department in organization
     */
    public static void printAllDepartment(List<Employee> employeeList) {
        employeeList.stream().mapToInt(Employee::getDeptId)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * print avg age of Male & Female
     */
    public static void printAvgAgeByGender(List<Employee> employeeList) {
        employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)))
                .forEach((k, v) -> {
                    System.out.println("Gender ::-> " + k);
                    System.out.println("Avg Age ::-> " + v);
                });;
    }
    /**
     * print Employee Detail for Max Salary
     */
    public static void printEmployeeDetailForMaxSalary(List<Employee> employeeList) {
        Employee maxSalaryEmployee = employeeList.stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .get();
        System.out.println("Employee Details :: " + maxSalaryEmployee.getEmpName() + " :: " + maxSalaryEmployee.getSalary());
    }

    /**
     * print Yougest Male Employee Departmentwise
     */
    public static void printYoungestMaleEmployeeDepartmentWise(List<Employee> employeeList) {
        employeeList.stream()
                .filter(emp -> emp.getGender().equals("Male"))
                .collect(Collectors.groupingBy(Employee::getDeptId))
                .entrySet().stream()
                .forEach(entry-> {
                    System.out.println("Dept Id :: " + entry.getKey());
                    Employee youngest = entry.getValue()
                            .stream()
                            .min(Comparator.comparingInt(Employee::getAge))
                            .get();
                    System.out.println("Youngest emplyee ::" + youngest.getEmpName());
                });
    }

    /**
     * print Separate Male Employee Department wise
     */
    public static void separateEmployeesByAge(List<Employee> employeeList) {
        employeeList.stream()
                .collect(Collectors.partitioningBy(emp -> emp.getAge() > 25))
                .forEach((aBoolean, employeeList1) -> {
                    if(aBoolean) {
                        System.out.println("Age > 25 :: ");
                        employeeList1.forEach(emp -> {
                            System.out.println("Employee Name ::" + emp.getEmpName());
                        });
                    } else {
                        System.out.println("Age < 25 :: ");
                        employeeList1.forEach(emp -> {
                            System.out.println("Employee Name ::" + emp.getEmpName());
                        });
                    }
                });
    }


    /**
     * findAllNumbersStartingWith1
     */

    public static void findAllNumbersStartingWith1(List<Integer> integerList){
        integerList.stream()
                .map(i-> String.valueOf(i))
                .filter(item-> item.startsWith("1"))
                .collect(Collectors.toList())
                .forEach(System.out:: println);

    }

    /**
     * Find Duplicates
     */

    public static void findDuplicates(List<Integer> integerList){
        integerList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((k, v)-> {
                    if(v > 1) System.out.println(k);
                });

        // Another Approach is to Add items in SET and negate.
    }

    /**
     * Find Max with Reduce
     */

    public static void findMax(List<Integer> integerList){
        Integer maxItem = integerList.stream()
                .reduce((a, b) -> a > b? a : b)
                .get();
        System.out.println(maxItem);

        // Another Approach is to Add items in SET and negate.
    }

    /**
     * Find Max with Reduce
     */

    public static void findMaxEvenLengthWord(String str){
        String maxEvenLengthWord = Arrays.asList(str.split(" ")).stream()
                .filter(w -> w.length() % 2 == 0)
                .reduce((a, b) -> a.length() > b.length() ? a : b)
                .get();
        System.out.println(maxEvenLengthWord);

        // Another Approach is to Add items in SET and negate.
    }


    /**
     * find the first non-repeated character
     */

    public static void firstNonRepeatedCharacter(String str){
        char [] chars = str.toCharArray();
        Character firstNonRepeatedCharacter = IntStream.range(0, chars.length)
                .mapToObj(i-> (char) i)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry-> entry.getValue() == 1)
                .findFirst().get().getKey();
        System.out.println("First Non Repeated Character:: " + firstNonRepeatedCharacter);
    }

    /**
     * find the first repeated character
     */

    public static void firstRepeatedCharacter(String str){
        char [] chars = str.toCharArray();
        Character firstNonRepeatedCharacter = IntStream.range(0, chars.length)
                .mapToObj(i-> (char) i)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry-> entry.getValue() > 1)
                .findFirst().get().getKey();
        System.out.println("First Repeated Character:: " + firstNonRepeatedCharacter);
    }
    /**
     * sum Of Digits Of Given String
     */

    public static void sumOfDigitsOfGivenString(String str){
        char [] chars = str.toCharArray();
        char [] intChar = IntStream.range(0, chars.length)
                .mapToObj(idx-> charMap.get(chars[idx]))
                .map(String::valueOf)
                .collect(Collectors.joining())
                .toCharArray();
       Integer sumNew =  IntStream.range(0, intChar.length)
                .mapToObj(idx -> intChar[idx])
                .map(Character::getNumericValue)
                .collect(Collectors.summingInt(Integer::intValue));

         Integer sum = str.chars()
            .mapToObj(i-> String.valueOf(charMap.get((char) i)))
                 .collect(Collectors.joining())
                 .chars()
                 .mapToObj(i-> (char) i)
                 .map(String::valueOf)
                 .map(Integer::parseInt)
                 .collect(Collectors.summingInt(Integer::intValue));
//                    .collect(Collectors.summingInt(Integer::intValue));
        System.out.println("Sum of All digits:: " + sum);
        System.out.println("Sum of All digits:: " + sumNew);
    }

    public static void multipleOfFive(List<Integer> list){
        list.stream()
                .map(i-> i * 5).
                collect(Collectors.toList())
                .forEach(System.out::println);

    }

    public static void main(String args[]) {
//        System.out.println(isPrime(7));
//        int count = countTotalLetter(Arrays.asList("william", "jones", "aaron", "seppe", "frank", "gilliam"));
//        System.out.println(count);
//        Person sara = new Person("Sara", 4);
//        Person viktor = new Person("Viktor", 40);
//        Person eva = new Person("Eva", 42);
//        List<Person> personList = Arrays.asList(sara, eva, viktor);
//        System.out.println(oldestPerson(personList).getName());
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//        System.out.println(sumOfAllElements(numbers));
//        List<Employee> employeeList = new ArrayList<>();
//        employeeList.add(new Employee(101, "siva", 101, "active", 2000, "Male", 32, 2012));
//        employeeList.add(new Employee(101, "shubham", 101, "active", 4000, "Male", 31, 2014));
//        employeeList.add(new Employee(102, "reddy", 101, "active", 5000, "Female", 25, 2015));
//        employeeList.add(new Employee(103, "raju", 102, "inactive", 6000, "Female", 30, 2016));
//        employeeList.add(new Employee(104, "shivam", 102, "inactive", 4000, "Male", 28, 2028));
//        employeeList.add(new Employee(105, "bob", 103, "active", 3500, "Male", 31, 2031));
//        employeeList.add(new Employee(106, "alice", 103, "inactive", 3500, "Female", 22, 2022));
//        employeeList.add(new Employee(107, "srinu", 104, "active", 3500, "Female", 24, 2024));

//        employeeDetailsForEachDepartment(employeeList);
//        employeeCountForEachDepartment(employeeList);
//        printActiveInactiveEmployees(employeeList);
//        printMaxMinSalary(employeeList);
//        printMaxSalaryByDepartment(employeeList);
//        printAllDepartment(employeeList);
//        printAvgAgeByGender(employeeList);
//        printEmployeeDetailForMaxSalary(employeeList);
//        printYoungestMaleEmployeeDepartmentWise(employeeList);
//        separateEmployeesByAge(employeeList);
//        findAllNumbersStartingWith1(Arrays.asList(10,15,8,49,25,98,32));
//        findDuplicates(Arrays.asList(10,15,8,49,25,98,98,32,15));
//          firstNonRepeatedCharacter("Java Hungry Blog Alive is Awesome");
//        firstRepeatedCharacter("Java Hungry Blog Alive is Awesome");
//        sumOfDigitsOfGivenString("zbax");
//        multipleOfFive(Arrays.asList(10,15,8,49,25,98,98,32,15));
//        findMax(Arrays.asList(10,15,8,49,25,98,98,32,15));
//        findMaxEvenLengthWord("Java Hungry Blog Alive is Awesome");
        String [] logs = new String [] {"10.00.00.00 200 google.com", "10.00.00.00 200 yahoo.com", "10.00.00.00 200 google.com"};
        List<String> tempList = new ArrayList<>();
                Arrays.asList(logs)
        .stream()
        .filter(i-> i.split(" ")[1].equals("200"))
        .map(i-> i.split(" ")[2])
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .collect(Collectors.toList())
                        .forEach(i-> tempList.add(i.getKey()));
        String [] ansArray = tempList.stream().toArray(String[]::new);
        System.out.println(ansArray);
    }
}

class Employee {
    private int empId;
    private String empName;
    private int deptId;
    private String status = "active";
    private int salary;
    private String gender;
    private int yearOfJoining;
    private int age;

    public Employee(int empId, String empName, int deptId, String status, int salary, String gender, int age, int yearOfJoining) {
        this.empId = empId;
        this.empName = empName;
        this.deptId = deptId;
        this.status = status;
        this.salary = salary;
        this.gender = gender;
        this.yearOfJoining = yearOfJoining;
        this.age = age;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getYearOfJoining() {
        return yearOfJoining;
    }

    public void setYearOfJoining(int yearOfJoining) {
        this.yearOfJoining = yearOfJoining;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class Person {

    private String name;
    private int age;
    private String nationality;

    public Person(String name, int age, String nationality) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }

    public Person(String name, int age) {
        this(name, age, "");
    }
}
