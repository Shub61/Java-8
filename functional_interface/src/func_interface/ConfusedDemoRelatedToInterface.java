package func_interface;

interface TestInterface{

}
interface TestInterfaceTwo {
    @Override
    public boolean equals(Object obj); // We can Override Object's Public method in Interface.

//    @Override
//    public final Class geClass(); // Only Public can be overridden.

//    @Override
//    public native Object clone(); // Again Compile Error.
}

public class ConfusedDemoRelatedToInterface {
    public static void main(String[] args) {
        TestInterface testInterface = null;
        testInterface.equals(null);
        testInterface.hashCode();

        // Interface Don't extend Object Class Still, Object's Public Method are visible
    }
}
