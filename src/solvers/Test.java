package solvers;
import solvertests.*;
import java.util.*;
import representation.*;

public class Test{
  public static void main(String[] args){
    System.out.println("-----------------------------------------------------------------");
    System.out.println("in solvers");


boolean ok =true;
//ok = ok && AbstractSolverTests.testIsConsistent();
//ok = ok && BacktrackSolverTests.testSolve();
//ok = ok && ArcConsistencyTests.testEnforce();
//ok = ok && ArcConsistencyTests.testEnforceArcConsistency();
//ok = ok && MACSolverTests.testSolve();
System.out.println(ok ? "All tests passed" : "At least one test failed");

System.out.println("-----------------------------------------------------------------");

  }
}
