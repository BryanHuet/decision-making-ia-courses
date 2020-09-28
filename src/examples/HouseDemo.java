package examples;

import representation.Constraint;
import representation.DifferenceConstraint;
import representation.Variable;
import solvers.BacktrackSolver;
import solvers.Solver;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HouseDemo {
    public static void main(String[] args){
        Set<String> pieces = new HashSet<String>();
        pieces.add("Chambre");
        pieces.add("Salon");

        HouseExample h1 = new HouseExample(10,15,pieces);
        Set<Object> o = new HashSet<>();
        o.add(pieces);
        Variable p1=new Variable("p1",o);
        Variable p2=new Variable("p2",o);
        Constraint d = new DifferenceConstraint(p1,p2);
        h1.addVariable(p1);
        h1.addVariable(p2);
        h1.addConstaint(d);

    }
}
