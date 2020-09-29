package solvers;
import solvertests.*;
import java.util.*;
import representation.*;

public class Test{
  public static void main(String[] args){
/*
    Set<Variable>vars=new HashSet<>();
    Set<Object>entiers=new HashSet<>();
    entiers.add(new Integer(1));
    entiers.add(new Integer(2));
    entiers.add(new Integer(3));
    Variable x1=new Variable("x1",entiers);
    Variable x2=new Variable("x2",entiers);
    Variable x3=new Variable("x3",entiers);
    Set<Constraint>constraints=new HashSet<>();

    constraints.add(new DifferenceConstraint(x1,x3));
    constraints.add(new DifferenceConstraint(x2,x3));
    vars.add(x1); vars.add(x2);vars.add(x3);
    Solver mysolver = new BacktrackSolver(vars,constraints);
    Map<Variable,Object> test = mysolver.solve();

    test.entrySet().forEach(entry->{
     System.out.println(entry.getKey() + " " + entry.getValue());
  });
*/

boolean ok =true;
//ok = ok && AbstractSolverTests.testIsConsistent();
ok = ok && BacktrackSolverTests.testSolve();
//ok = ok && ArcConsistencyTests.testEnforce();
//ok = ok && ArcConsistencyTests.testEnforceArcConsistency();
//ok = ok && MACSolverTests.testSolve();
System.out.println(ok ? "All tests passed" : "At least one test failed");


  }
}
