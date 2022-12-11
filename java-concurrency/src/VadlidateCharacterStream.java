import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class VadlidateCharacterStream {

    private List<Character> openCharList = new ArrayList<>();
    Stack<Character> characterStack = new Stack<>();

    public VadlidateCharacterStream() {
        this.openCharList.add('[');
        this.openCharList.add('{');
        this.openCharList.add('(');
        this.openCharList.add('<');
    }

    public static void main(String[] args) {
        Character[] input = new Character[]{'(', ')', '(', ')', '<', '[', ']', '>', '(', '{', '}', ')'};
        VadlidateCharacterStream vadlidateCharacterStream = new VadlidateCharacterStream();
        boolean result = vadlidateCharacterStream.validateStream(Arrays.asList(input));
        System.out.println(result);
    }

    public boolean validateStream(List<Character> inputStream) {
        AtomicBoolean flag = new AtomicBoolean(true);
        for (Character i : inputStream) {
            if (this.openCharList.contains(i))
                this.characterStack.push(i);
            else {
                if (this.characterStack.isEmpty()) return false;
                else {
                    Character fromStack = this.characterStack.peek();
                    if (i == '[' && fromStack != i) return false;
                    if (i == '(' && fromStack != i) return false;
                    if (i == '{' && fromStack != i) return false;
                    if (i == '<' && fromStack != i) return false;
                    this.characterStack.pop();
                }
            }
        }
        return true;
    }
}
