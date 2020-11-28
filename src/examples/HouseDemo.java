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
        Set<String> pieces = new HashSet<>();
        pieces.add("Chambre");
        pieces.add("Salon");
        pieces.add("Pipi room");

        HouseExample h1 = new HouseExample(10,15,pieces);
        Set<Object> o = new HashSet<>();
        o.add(pieces);
        Variable p1=new Variable("p1",o);
        Variable p2=new Variable("p2",o);
        h1.addVariable(p1);
        h1.addVariable(p2);

        Constraint d = new DifferenceConstraint(p1,p2);
        Constraint d2 = new DifferenceConstraint(p2,p1);
        h1.addConstaint(d2);
        h1.addConstaint(d);


    }
}
