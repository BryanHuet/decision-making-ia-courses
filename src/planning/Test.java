package planning;
import planningtests.*;

public class Test {
  public static void main(String[] args){
    System.out.println("-----------------------------------------------------------------");
    boolean ok = true;
    ok = ok && BasicActionTests.testIsApplicable();
    ok = ok && BasicActionTests.testSuccessor();
    ok = ok && BasicActionTests.testGetCost();
    ok = ok && BasicGoalTests.testIsSatisfiedBy();
    ok = ok && DFSPlannerTests.testPlan();
    System.out.println(ok ? "All tests passed" : "At least one test failed");
    System.out.println("-----------------------------------------------------------------");
}
}
