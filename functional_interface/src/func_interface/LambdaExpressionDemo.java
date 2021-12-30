package func_interface;

import java.util.Optional;

interface LambdaExpression{
    int getLength(String s);
}
public class LambdaExpressionDemo {
    public static void main(String[] args) {
        LambdaExpression lambdaExpression = (s) -> s.length();
        System.out.println(lambdaExpression.getLength("Shubham"));
    }
}
