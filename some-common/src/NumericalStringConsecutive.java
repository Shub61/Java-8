public class NumericalStringConsecutive {
    public static boolean  isConsecutive(String str){
        // The Idea is to take the first number
        // and create the String of the STR length
        // Parse to  Int
        Integer first = Integer.parseInt(String.valueOf(str.toCharArray()[0]));
        StringBuilder sb = new StringBuilder();
        sb.append(first);
        for(int i = 0; i < str.length(); i++){
            first = first + 1;
            if(sb.length() != str.length()) sb.append(first);
        }
        if (sb.toString().equals(str)) return true;
        else return false;
    }

    public static void main(String[] args) {
        System.out.println(isConsecutive("1213"));
    }
}
