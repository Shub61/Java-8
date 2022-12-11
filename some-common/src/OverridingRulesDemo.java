import java.io.EOFException;
import java.io.IOException;

/*
// Rule_1 ::
        Parent not throw any exception -> Child can throw Runtime or No exception.
// Rule_2 ::
        Parent throw any Checked  -> Child can throw Runtime or Narrower Checked Exception or Equal Checked.
*/
class Parent {
    public static void display(){
        System.out.println("Print in PARENT");
    }
    public static void display_2() throws IOException {
        System.out.println("Print in PARENT");
    }
}
class Child extends Parent {
    public static void display() throws RuntimeException {
        System.out.println("Print in CHILD");
    }
    public static void display_2() throws EOFException { // RunTime// Or Child Checked Exception
        System.out.println("Print in CHILD");
    }
}

public class OverridingRulesDemo {
    public static void main(String[] args) throws Exception {
        Child.display();
        Parent.display();
    }
}
