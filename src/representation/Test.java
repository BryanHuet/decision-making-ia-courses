package representation;
import representationtests.*;

public class Test {
  public static void main(String[] args){
    System.out.println("-----------------------------------------------------------------");
    System.out.println("in representation");

    boolean ok = true;
    ok = ok && VariableTests.testEquals();
    ok = ok && VariableTests.testHashCode();
    ok = ok && BooleanVariableTests.testConstructor();
    ok = ok && DifferenceConstraintTests.testGetScope();
    ok = ok && DifferenceConstraintTests.testIsSatisfiedBy();
    ok = ok && RuleTests.testGetScope();
    ok = ok && RuleTests.testIsSatisfiedBy();
    ok = ok && BinaryExtensionConstraintTests.testGetScope();
    ok = ok && BinaryExtensionConstraintTests.testIsSatisfiedBy();
    System.out.println(ok ? "All tests passed" : "At least one test failed");
    System.out.println("-----------------------------------------------------------------");

  }


}
