package func_interface;
public class TestFunctionalInterface implements MyFunctionalInterface {

    public static void main(String[] args) {
        TestFunctionalInterface testFunctionalInterface = new TestFunctionalInterface();
        testFunctionalInterface.printMessage();
        testFunctionalInterface.showMessageOne();
        testFunctionalInterface.showMessageTwo();
    }

    @Override
    public void printMessage() {
        System.out.println("I am in MY Functional Interface");
    }
}

