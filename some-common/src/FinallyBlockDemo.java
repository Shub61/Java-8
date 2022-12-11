public class FinallyBlockDemo {

    public static int finallyBlock(){
        try {
            System.out.println("I am in try");
            return 1;
        } finally {
            System.out.println("I am in finally");
            return 2;
        }
    }

    public static void main(String[] args) {
        int returnValue = FinallyBlockDemo.finallyBlock();
        System.out.println(returnValue);
    }
}
