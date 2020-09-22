package representation;
import representationtests.*;

public class Test {
  public static void main(String[] args){

    System.out.println("in representation");
    boolean ok = true;
    ok = ok && VariableTests.testEquals();
    ok = ok && VariableTests.testHashCode();
    ok = ok && BooleanVariableTests.testConstructor();
    //ok = ok && RuleTests.testIsSatisfiedBy();
    System.out.println(ok ? "All tests passed" : "At least one test failed");

  }


}
