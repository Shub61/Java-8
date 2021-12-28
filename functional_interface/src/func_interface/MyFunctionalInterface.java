package func_interface;

@FunctionalInterface
interface MyFunctionalInterface {
    void printMessage();
//    void printMyName(); // Not allowed, ONLY one ABSTRACT method is allowed in FI.
    default void showMessageOne(){
        System.out.println("I am the DEFAULT MESSAGE - 1");
    }

    default void showMessageTwo(){
        System.out.println("I am the DEFAULT MESSAGE - 2");
    }
}

