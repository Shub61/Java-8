public class MethodOverLoading {

    public void test(Integer i){
        System.out.println("Integer called ");
    }
//    public void test(String s){
//        System.out.println("String called ");
//    }
    public void test(Object s){
        System.out.println("String called ");
    }

    public static void main(String[] args) {
        MethodOverLoading methodOverLoading = new MethodOverLoading();
        methodOverLoading.test(null);

        // Compile Time Error

    }
    /**
     *  Very good point :
     *      if we have 2 methods accepting Objects as an argument
     *      we cannot pass null , bcoz it will get confuse which method to invoke.
     *      if we have one OBJECT and one Wrapper then the more specific will get invoked.
     *      if we have one primitive and one wrapper, wrapper will get invoked.
     *
     *
     *      test(String) , test(Integer) --> Compile Error if we pass test(null)
     *      test(Object), test(String/Integer) --> test(String/Integer) will get invoked.
     *      test(int), test(String) --> test(String) will get invoked.
     */
}
