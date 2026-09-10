import static java.lang.IO.print;
import static java.lang.IO.println;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    println(String.format("Hello and welcome!"));

    for (int i = 1; i <= 5; i++) {
        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
        println("i = " + i);
    }

    println(factorial(BigInteger.valueOf(100)));

    int [] nums = {1, 0, 1, 2};
    TreeSet<Integer> set = new TreeSet<>();
    for(int num : nums){
        set.add(num);
    }
    println(set);
    Integer[] arr = set.toArray(new Integer[0]);


}

public static BigInteger factorial(BigInteger n){
    if (n.equals(BigInteger.ONE))
        return BigInteger.ONE;
    return n.multiply(factorial(n.subtract(BigInteger.ONE)));
}
