package solvers;
import solvertests.*;
import java.util.*;
import representation.*;

public class Test{
  public static void main(String[] args){
    Set<Variable>vars=new HashSet<>();
    Set<Object>entiers=new HashSet<>();
    entiers.add(new Integer(1));
    entiers.add(new Integer(2));
    entiers.add(new Integer(3));
    Variable x1=new Variable("x1",entiers);
    Variable x2=new Variable("x2",entiers);
    Set<Constraint>constraints=new HashSet<>();

    constraints.add(new DifferenceConstraint(x1,x2));
    vars.add(x1); vars.add(x2);
    boolean ok = true;
    ok = ok && (new BacktrackSolver(vars,constraints) ).
    System.out.println(ok ? "All tests passed" : "At least one test failed");

/*

    ok = ok && AbstractSolverTests.testIsConsistent();
    ok = ok && BacktrackSolverTests.testSolve();
    */
  }
}
