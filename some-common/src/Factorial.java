public class Factorial {
    static long factorial(int N){
        // code here
        return factorialHelper(N);
    }

    static long factorialHelper(int N){
        if(N == 1) return 1;
        long nbr = N * factorial(N-1);
        return nbr;
    }

    public static void main(String[] args) {
        factorial(5);
    }
}
