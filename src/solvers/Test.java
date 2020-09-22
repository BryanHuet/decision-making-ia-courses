package solvers;
import solvertests.*;
import java.util.*;
import representation.*;

public class Test{
  public static void main(String[] args){
    boolean ok = true;
    ok = ok && AbstractSolverTests.testIsConsistent();
    ok = ok && BacktrackSolverTests.testSolve();
    System.out.println(ok ? "All tests passed" : "At least one test failed");
  }
}
