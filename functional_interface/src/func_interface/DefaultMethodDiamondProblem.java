package func_interface;

interface RBCBank{
    boolean depositMoneyToRBC(int amt);
    default void printAmount(){
        System.out.println("PRINT from RBC");
    }
    void checkAmount();
}

interface CIBCBank{
    boolean depositMoneyToCIBC(int amt);
    default void printAmount(){
        System.out.println("PRINT from CIBC");
    }
    void checkAmount();
}
public class DefaultMethodDiamondProblem implements RBCBank, CIBCBank{
    @Override
    public boolean depositMoneyToRBC(int amt) {
        return false;
    }

    @Override
    public void printAmount() {
        RBCBank.super.printAmount();
    }

    @Override
    public boolean depositMoneyToCIBC(int amt) {
        return false;
    }

    @Override
    public void checkAmount(){
        System.out.println("Yes Amount is present");
    }

    public static void main(String[] args) {
        DefaultMethodDiamondProblem defaultMethodDiamondProblem = new DefaultMethodDiamondProblem();
        defaultMethodDiamondProblem.printAmount();
    }
}
